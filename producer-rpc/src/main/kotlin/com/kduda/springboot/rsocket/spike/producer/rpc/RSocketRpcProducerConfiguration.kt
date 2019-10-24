package com.kduda.springboot.rsocket.spike.producer.rpc

import com.kduda.springboot.rsocket.spike.common.rpc.RSocketRpcServiceExampleServer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*


// TODO: configure server - specify RSocket Acceptor
@Configuration
internal class RSocketRpcProducerConfiguration {
    @Bean
    fun rSocketRpcServiceExampleServer(): RSocketRpcServiceExampleServer =
        RSocketRpcServiceExampleServer(RSocketRpcServiceExampleAcceptor(), Optional.empty(), Optional.empty())

    // @Bean
    // fun rSocketFactory(rSocketRpcServiceExampleServer:RSocketRpcServiceExampleServer)=
    //     RSocketFactory
    //         .receive()
    //         .acceptor { setup, sendingSocket -> Mono.just(RequestHandlingRSocket(rSocketRpcServiceExampleServer)) }
    //         .transport(TcpServerTransport.create("localhost", 7000))
    //         .start()
    //         .block()

}
