package com.kduda.springboot.rsocket.producer

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks

@SpringBootApplication
internal class SpringBootRsocketSpikeProducerApplication

internal fun main(args: Array<String>) {
    Hooks.onOperatorDebug()
    runApplication<SpringBootRsocketSpikeProducerApplication>(*args)
}
