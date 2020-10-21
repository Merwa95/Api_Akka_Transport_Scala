package io.swagger.server.service

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import akka.http.scaladsl.marshalling.ToEntityMarshaller
import io.swagger.server.api.TransportApiMarshaller
import io.swagger.server.model.{Ligne, Station, Train}

object TransportMarshaller extends TransportApiMarshaller with SprayJsonSupport {
  import spray.json.DefaultJsonProtocol._
  implicit  def toEntityMarshallerStationarray: ToEntityMarshaller[List[Station]]= {
    listFormat(jsonFormat3(Station))
  }

  implicit  def toEntityMarshallerLignearray: ToEntityMarshaller[List[Ligne]]= {
    listFormat(jsonFormat5(Ligne))
  }
  implicit  def toEntityMarshallerTrainarray: ToEntityMarshaller[List[Train]]= {
    listFormat(jsonFormat4(Train))
  }
}
