package com.kduda.springboot.rsocket.spike.producer.rpc

import com.google.protobuf.Empty
import com.kduda.springboot.rsocket.spike.common.rpc.*
import com.kduda.springboot.rsocket.spike.common.rpc.UuidProtobufExtensions.toUuid
import io.netty.buffer.ByteBuf
import mu.KotlinLogging
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.time.Instant


internal class RSocketRpcServiceExampleAcceptor : RSocketRpcServiceExample {
    private val logger = KotlinLogging.logger {}

    override fun requestResponse(message: RSocketRpcRequest, metadata: ByteBuf): Mono<RSocketRpcResponse> {
        val messageId = message.uuid.toUuid()
        logger.info { "Responding to request with single response id $messageId" }

        return RSocketRpcResponseDto(messageId, Instant.now(), "Request - response")
            .toProto()
            .let { Mono.just(it) }
            .doOnNext { logger.info { "Responding to request with single response $it" } }
    }

    override fun requestStream(message: RSocketRpcRequest, metadata: ByteBuf): Flux<RSocketRpcResponse> {
        val messageId = message.uuid.toUuid()
        logger.info { "Responding to request with stream id $messageId" }

        return Flux.interval(Duration.ofSeconds(1))
            .map {
                RSocketRpcResponseDto(messageId, Instant.now(), "Request - stream")
                    .toProto()
            }.doOnNext { logger.info { "Sending streaming response $it" } }
    }

    override fun fireAndForget(message: RSocketRpcRequest, metadata: ByteBuf): Mono<Empty> {
        logger.info { "Received fire and forget data with id ${message.uuid.toUuid()}" }
        return Mono.empty<Empty>()
    }

    override fun streamStream(
        message: Publisher<RSocketRpcStreamingRequest>,
        metadata: ByteBuf
    ): Flux<RSocketRpcStreamingResponse> {
        return Flux.from(message).flatMap {
            logger.info { "Responding with stream ${it.streamId}" }

            val messageId = it.uuid
            val streamId = it.streamId

            Flux.interval(Duration.ofSeconds(1))
                .map { RSocketRpcResponseDto(messageId.toUuid(), Instant.now(), "Stream - stream") }
                .map { response -> RSocketRpcStreamingResponseDto(streamId, response).toProto() }
        }.doOnNext { logger.info { "Sending streaming response $it" } }
    }

    override fun exception(message: RSocketRpcRequest, metadata: ByteBuf): Mono<RSocketRpcResponse> {
        throw IllegalStateException("Should by handled by exception handler for this specific type")
            .also { logger.error(it) { "Throwing exception" } }
    }
}
