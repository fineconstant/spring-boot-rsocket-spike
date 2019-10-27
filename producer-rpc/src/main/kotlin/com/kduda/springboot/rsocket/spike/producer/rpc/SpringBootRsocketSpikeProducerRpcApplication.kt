package com.kduda.springboot.rsocket.spike.producer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleServer
import io.rsocket.RSocketFactory
import io.rsocket.rpc.rsocket.RequestHandlingRSocket
import io.rsocket.transport.netty.server.TcpServerTransport
import org.springframework.boot.autoconfigure.SpringBootApplication
import reactor.core.publisher.Mono
import java.util.*


// TODO: configure RSocketMessageHandler to be RSocket RPC implementation
@SpringBootApplication
internal class SpringBootRsocketSpikeProducerRpcApplication

internal fun main(args: Array<String>) {
    val rSocketRpcServiceExampleServer = RSocketRpcServiceExampleServer(
        RSocketRpcServiceExampleAcceptor(), Optional.empty(), Optional.empty()
    )

    RSocketFactory
        .receive()
        .acceptor { _, _ -> Mono.just(RequestHandlingRSocket(rSocketRpcServiceExampleServer)) }
        .transport(TcpServerTransport.create("localhost", 7000))
        .start()
        .block()

    Thread.currentThread().join()

    // Hooks.onOperatorDebug()
    // runApplication<SpringBootRsocketSpikeProducerRpcApplication>(*args)
}
