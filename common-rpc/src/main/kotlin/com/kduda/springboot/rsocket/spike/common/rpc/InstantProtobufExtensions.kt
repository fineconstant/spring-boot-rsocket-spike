package com.kduda.springboot.rsocket.spike.common.rpc

import com.google.protobuf.Timestamp
import java.time.Instant


object InstantProtobufExtensions {
    fun Instant.toProto(): Timestamp =
        Timestamp.newBuilder()
            .setSeconds(this.epochSecond)
            .setNanos(this.nano)
            .build()

    fun Timestamp.toInstant(): Instant = Instant.ofEpochSecond(this.seconds, this.nanos.toLong())
}
