package com.kduda.springboot.rsocket.spike.consumer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleClient
import mu.KotlinLogging
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController


// TODO: implement using RSocketRpcServiceExampleClient
@RestController
internal class RSocketConsumerController(private val rSocketRpcServiceExampleClient: RSocketRpcServiceExampleClient) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("request-response")
    internal fun requestResponse(): Nothing =TODO("implement")


    @GetMapping("request-stream", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun requestStream(): Nothing =
        TODO("implement")

    @GetMapping("fire-and-forget")
    internal fun fireAndForget(): Nothing =
        TODO("implement")


    @GetMapping("stream-stream/{streams}", produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    internal fun streamStream(@PathVariable streams: Int): Nothing =
        TODO("implement")


    @GetMapping("exception")
    internal fun exception(): Nothing = TODO("implement")

}
