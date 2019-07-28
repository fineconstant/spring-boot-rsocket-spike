package com.kduda.springboot.rsocket.consumer

import com.kduda.springboot.rsocket.common.Message
import com.kduda.springboot.rsocket.common.StreamingRequest
import com.kduda.springboot.rsocket.common.StreamingResponse
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.util.*


@RestController
internal class RSocketConsumerController(private val requester: RSocketRequester) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("request-response")
    internal fun requestResponse() =
        UUID.randomUUID()
            .also { logger.info("Request response for id $it") }
            .let {
                requester.route("request-response")
                    .data(it)
                    .retrieveMono(Message::class.java)
            }

    @GetMapping("request-stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun requestStream() =
        UUID.randomUUID()
            .also { logger.info("Request stream for id $it") }
            .let {
                requester.route("request-stream")
                    .data(it)
                    .retrieveFlux(Message::class.java)
            }

    @GetMapping("fire-and-forget")
    internal fun fireAndForget() =
        UUID.randomUUID()
            .also { logger.info("Sending data with id $it") }
            .let {
                requester.route("fire-and-forget")
                    .data(it)
                    .send()
            }

    @GetMapping("stream-stream/{streams}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun streamStream(@PathVariable streams: Int) =
        streams.also { logger.info("Requesting for $streams streams") }
            .let {
                requester.route("stream-stream")
                    .data(Flux.range(0, streams)
                        .map { StreamingRequest(it, UUID.randomUUID()) })
                    .retrieveFlux(StreamingResponse::class.java)
                    .doOnNext { logger.info("Received streaming response $it") }
            }

    @GetMapping("exception")
    internal fun exception() = UUID.randomUUID()
        .also { logger.info("Endpoint should throw an exception") }
        .let {
            requester.route("exception")
                .data(it)
                .retrieveMono(Message::class.java)
        }

}

