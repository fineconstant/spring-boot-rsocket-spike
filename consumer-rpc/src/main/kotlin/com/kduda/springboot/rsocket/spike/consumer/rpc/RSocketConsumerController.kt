package com.kduda.springboot.rsocket.spike.consumer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcRequestDto
import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcResponseDto
import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleClient
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*


// TODO: check toString implementations
@RestController
internal class RSocketConsumerController(private val rSocketRpcServiceExampleClient: RSocketRpcServiceExampleClient) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("request-response")
    internal fun requestResponse() = UUID.randomUUID()
        .also { logger.info { "Requesting for response with id $it" } }
        .let { RSocketRpcRequestDto(it).toProto() }
        .let { rSocketRpcServiceExampleClient.requestResponse(it) }
        .map { RSocketRpcResponseDto.from(it) }
        .doOnNext { response -> logger.info { "Received response $response" } }

    @GetMapping("request-stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun requestStream() = UUID.randomUUID()
        .also { logger.info { "Request for stream id $it" } }
        .let { RSocketRpcRequestDto(it).toProto() }
        .let { rSocketRpcServiceExampleClient.requestStream(it) }
        .map { RSocketRpcResponseDto.from(it) }
        .doOnNext { response -> logger.info { "Received streaming response $response" } }

    @GetMapping("fire-and-forget")
    internal fun fireAndForget() =
        UUID.randomUUID()
            .also { logger.info { "Sending fire and forget data with id $it" } }
            .let { RSocketRpcRequestDto(it).toProto() }
            .let { rSocketRpcServiceExampleClient.fireAndForget(it) }

    @GetMapping("stream-stream/{streams}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun streamStream(@PathVariable streams: Int) =
        streams.also { logger.info { "Requesting for $streams streams" } }

    @GetMapping("exception")
    internal fun exception() =
        UUID.randomUUID()
            .also { logger.info { "Endpoint should throw an exception" } }
            .let { RSocketRpcRequestDto(it).toProto() }
            .let { rSocketRpcServiceExampleClient.exception(it) }
            .map { RSocketRpcResponseDto.from(it) }

}
