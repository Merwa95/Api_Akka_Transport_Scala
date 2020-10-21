package io.swagger.server

import akka.actor.{ActorSystem, Props}
import akka.stream.ActorMaterializer
import io.swagger.server.api.TransportApi
import io.swagger.server.service._

import scala.concurrent.ExecutionContext

object TransportServer {
  def main(args: Array[String]): Unit = {
    //creer l objet defaultMArchar

    implicit  val system:ActorSystem=ActorSystem.create("TransportHtppServer")
    implicit val materializer: ActorMaterializer = ActorMaterializer()

    implicit val ec: ExecutionContext = system.dispatcher
    val voyageur=system.actorOf(Props(classOf[Voyageur],new SationLigneTrainStore,new VoyageurStore),"voyageur")
    val transportapi=new TransportApi(new TransportServiceImpl(voyageur),TransportMarshaller)
    new Controller(transportapi)
  }
}
