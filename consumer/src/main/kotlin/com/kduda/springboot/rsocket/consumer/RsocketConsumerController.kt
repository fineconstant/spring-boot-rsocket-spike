package com.kduda.springboot.rsocket.consumer

import com.kduda.springboot.rsocket.common.Message
import mu.KotlinLogging
import org.springframework.messaging.rsocket.RSocketRequester
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
internal class RsocketConsumerController(private val requester: RSocketRequester) {
    private val logger = KotlinLogging.logger {}

    @GetMapping("request-response")
    internal fun hello() =
        UUID.randomUUID()
            .also { logger.info("Request response for id $it") }
            .let {
                requester.route("request-response")
                    .data(it)
                    .retrieveMono(Message::class.java)
            }
}

