package com.kduda.springboot.rsocket.spike.producer.rpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks


@SpringBootApplication
internal class SpringBootRsocketSpikeProducerRpcApplication


internal fun main(args: Array<String>) {
    Hooks.onOperatorDebug()
    runApplication<SpringBootRsocketSpikeProducerRpcApplication>(*args)
}
