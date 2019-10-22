package com.kduda.springboot.rsocket.spike.producer.rpc

import com.google.protobuf.Empty
import com.google.protobuf.Timestamp
import com.kduda.springboot.rsocket.spike.common.rpc.*
import io.netty.buffer.ByteBuf
import io.reactivex.Flowable
import io.reactivex.Single
import mu.KotlinLogging
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant
import java.util.*


// TODO: check toString implementations
// TODO: Use Reactor or `java.util.concurrent.Flow` (even better!) for generated code
internal class RSocketRpcServiceExampleAcceptor : RSocketRpcServiceExample {
    private val logger = KotlinLogging.logger {}

    override fun requestResponse(message: RSocketRpcRequest, metadata: ByteBuf): Single<RSocketRpcResponse> {
        val messageId = message.uuid
        logger.info("Responding to request with single response id ${UUID.fromString(messageId.value)}")

        val now = Instant.now()
            .let {
                Timestamp.newBuilder()
                    .setSeconds(it.epochSecond)
                    .setNanos(it.nano)
                    .build()
            }


        val response = RSocketRpcResponse.newBuilder()
            .apply {
                uuid = messageId
                time = now
                value = "Request - response"
            }
            .build()

        val monoResponse = Mono.just(response)
            .doOnNext { logger.info("Responding to request with single response $it") }

        return Single.fromPublisher(monoResponse)
    }

    override fun requestStream(message: RSocketRpcRequest, metadata: ByteBuf): Flowable<RSocketRpcResponse> {
        val messageId = message.uuid
        logger.info("Responding to request with stream id $messageId")

        val fluxResponse = Flux.interval(Duration.ofSeconds(1))
            .map {
                val now = Instant.now()
                    .let {
                        Timestamp.newBuilder()
                            .setSeconds(it.epochSecond)
                            .setNanos(it.nano)
                            .build()
                    }

                RSocketRpcResponse.newBuilder()
                    .apply {
                        uuid = messageId
                        time = now
                        value = "Request - stream"
                    }.build()
            }.log()
            .doOnNext { logger.info("Sending streaming response $it") }

        return Flowable.fromPublisher(fluxResponse)
    }

    override fun fireAndForget(message: RSocketRpcRequest, metadata: ByteBuf): Single<Empty> {
        val messageId = message.uuid

        logger.info("Received fire and forget data with id $messageId")
        val monoResponse = Mono.empty<Empty>()

        return Single.fromPublisher(monoResponse)
    }

    override fun streamStream(
        message: Publisher<RSocketRpcStreamingRequest>,
        metadata: ByteBuf
    ): Flowable<RSocketRpcStreamingResponse> {
        val fluxInput = Flux.from(message)

        val fluxResponse = fluxInput.flatMap {
            logger.info("Responding with stream ${it.streamId}")

            val messageId = it.uuid
            val streamId = it.streamId

            Flux.interval(Duration.ofSeconds(1))
                .map {
                    val now = Instant.now()
                        .let {
                            Timestamp.newBuilder()
                                .setSeconds(it.epochSecond)
                                .setNanos(it.nano)
                                .build()
                        }

                    RSocketRpcResponse.newBuilder()
                        .apply {
                            uuid = messageId
                            time = now
                            value = "Request - stream"
                        }.build()
                }
                .map {
                    RSocketRpcStreamingResponse.newBuilder()
                        .setResponse(it)
                        .setStreamId(streamId)
                        .build()
                }.log()
        }.doOnNext { logger.info("Sending streaming response $it") }

        return Flowable.fromPublisher(fluxResponse)
    }

    override fun exception(message: RSocketRpcRequest, metadata: ByteBuf): Single<RSocketRpcResponse> {
        throw IllegalStateException("Should by handled by exception handler for this specific type")
            .also { logger.error("Throwing exception", it) }
    }

}
/*
    @MessageExceptionHandler
    internal fun illegalStateExceptionHandler(exception: IllegalStateException) =
        exception.also { logger.info("Handling exception $it") }
            .let { messageFactory.build(UUID.randomUUID(), exception.message ?: "Fallback message") }
            .let { Mono.just(it) }
 */
