package com.kduda.springboot.rsocket.spike.producer.rpc

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.core.publisher.Hooks


// TODO: configure server
@SpringBootApplication
internal class SpringBootRsocketSpikeProducerRpcApplication {
        /*
    val rSocket = RSocketFactory
                .connect()
                /*transport is pluggable */
                .transport(OkhttpWebsocketClientTransport.create(url))
                .start()
                .timeout(5, TimeUnit.SECONDS)
                .blockingGet()

            /*Requester of client side of connection*/
            val exampleClient = ExampleServiceClient(rSocket)
     */
}

internal fun main(args: Array<String>) {
    Hooks.onOperatorDebug()
    runApplication<SpringBootRsocketSpikeProducerRpcApplication>(*args)
}
