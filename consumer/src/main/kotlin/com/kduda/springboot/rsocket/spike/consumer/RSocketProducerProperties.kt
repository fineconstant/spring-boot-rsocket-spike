package com.kduda.springboot.rsocket.spike.consumer

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding


@ConstructorBinding
@ConfigurationProperties("rsocket.producer")
internal data class RSocketProducerProperties(
    val host: String,
    val port: Int
)
