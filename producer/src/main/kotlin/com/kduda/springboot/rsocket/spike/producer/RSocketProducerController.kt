package com.kduda.springboot.rsocket.spike.producer

import com.kduda.springboot.rsocket.spike.common.Message
import com.kduda.springboot.rsocket.spike.common.StreamingRequest
import com.kduda.springboot.rsocket.spike.common.StreamingResponse
import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageExceptionHandler
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Duration
import java.util.*


@Controller
internal class RSocketProducerController(private val messageFactory: MessageFactory) {
    private val logger = KotlinLogging.logger {}

    @MessageMapping("request-response")
    internal fun requestResponse(uuid: UUID) = uuid
        .let { Mono.just(messageFactory.build(it, "Request - response")) }
        .doOnNext { logger.info { "Responding to request with single response $it" } }

    @MessageMapping("request-stream")
    internal fun requestStream(uuid: UUID) = uuid
        .also { logger.info { "Responding to request with stream id $it" } }
        .let {
            Flux.interval(Duration.ofSeconds(1))
                .map { messageFactory.build(uuid, "Request - stream") }
        }.doOnNext { logger.info { "Sending streaming response $it" } }

    @MessageMapping("fire-and-forget")
    internal fun fireAndForget(uuid: UUID) =
        logger.info { "Received fire and forget data with id $uuid" }
            .let { Mono.empty<Void>() }

    @MessageMapping("stream-stream")
    internal fun streamStream(streamingRequests: Flux<StreamingRequest>) =
        streamingRequests.flatMap { streamingRequest ->
            logger.info { "Responding with stream ${streamingRequest.streamId}" }
            Flux.interval(Duration.ofSeconds(1))
                .map { messageFactory.build(streamingRequest.uuid, "Stream - stream") }
                .map { StreamingResponse(streamingRequest.streamId, it) }
        }.doOnNext { logger.info { "Sending streaming response $it" } }

    @MessageMapping("exception")
    internal fun exception(uuid: UUID): Mono<Message> =
        throw IllegalStateException("Should by handled by exception handler for this specific type")
            .also { logger.error(it) { "Throwing exception" } }

    // Request - response type
    @MessageExceptionHandler
    internal fun illegalStateExceptionHandler(exception: IllegalStateException) =
        exception.also { logger.info { "Handling exception $it" } }
            .let { messageFactory.build(UUID.randomUUID(), exception.message ?: "Fallback message") }
            .let { Mono.just(it) }
}
