package com.kduda.springboot.rsocket.spike.common.rpc

import java.util.*


object UuidProtobufExtensions {
    fun UUID.toProto(): UUIDProto =
        UUIDProto.newBuilder()
            .setValue(this.toString())
            .build()

    fun UUIDProto.toUuid(): UUID = UUID.fromString(this.value)
}
