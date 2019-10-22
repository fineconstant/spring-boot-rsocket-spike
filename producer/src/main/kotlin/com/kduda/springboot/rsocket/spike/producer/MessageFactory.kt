package com.kduda.springboot.rsocket.spike.producer

import com.kduda.springboot.rsocket.spike.common.Message
import java.time.Instant
import java.util.*

internal class MessageFactory {
    fun build(uuid: UUID, value: String) = Message(uuid, Instant.now(), value)
}
