package com.kduda.springboot.rsocket.spike.consumer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
@EnableConfigurationProperties(RSocketProducerProperties::class)
internal class SpringBootRsocketSpikeConsumerApplication

internal fun main(args: Array<String>) {
    Hooks.onOperatorDebug()
    runApplication<SpringBootRsocketSpikeConsumerApplication>(*args)
}
