package com.kduda.springboot.rsocket.spike.common.rpc

import com.google.protobuf.GeneratedMessageV3


internal interface KotlinRepresentation<P : GeneratedMessageV3> {
    fun toProto(): P
}
