package com.kduda.springboot.rsocket.producer

import com.kduda.springboot.rsocket.common.Message
import mu.KotlinLogging
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.stereotype.Controller
import reactor.core.publisher.Mono
import java.util.*


// TODO: request/ stream
// TODO: stream/ stream
// TODO: fire-and-forget

@Controller
internal class RsocketProducerController {
    private val logger = KotlinLogging.logger {}

    @MessageMapping("request-response")
    internal fun requestResponse(uuid: UUID) = uuid
        .also { logger.info("Responding to request with id $it") }
        .let { Mono.just(Message(it, "Request - response")) }
}
