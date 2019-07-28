package com.kduda.springboot.rsocket.common

import java.time.Instant
import java.util.*

class MessageFactory {
    fun build(uuid: UUID, value: String) = Message(uuid, Instant.now(), value)
}
