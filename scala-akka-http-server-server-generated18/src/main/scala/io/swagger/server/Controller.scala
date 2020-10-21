package io.swagger.server

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer
import io.swagger.server.api.TransportApi

import scala.util.{Failure, Success}

class Controller(transport: TransportApi)(implicit system: ActorSystem, materializer: ActorMaterializer) {

    lazy val routes: Route = transport.route
    implicit val executionContext = system.dispatcher

    val service = Http().bindAndHandle(routes, "0.0.0.0", 9000)
    service.onComplete {
        case Success(binding) =>
            val address = binding.localAddress
            system.log.info("Server online at http://{}:{}/", address.getHostString, address.getPort)
        case Failure(ex) =>
            system.log.error("Failed to bind HTTP endpoint, terminating system", ex)
            system.terminate()
    }
}