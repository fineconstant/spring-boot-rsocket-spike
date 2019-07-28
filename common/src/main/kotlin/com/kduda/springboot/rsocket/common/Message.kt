package com.kduda.springboot.rsocket.common

import java.time.Instant
import java.util.*

data class Message(val uuid: UUID, val time: Instant, val value: String)
