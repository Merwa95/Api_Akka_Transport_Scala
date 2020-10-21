package io.swagger.server.service
import akka.actor.ActorRef
import akka.http.scaladsl.marshalling.ToEntityMarshaller
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.pattern.ask
import akka.util.Timeout
import io.swagger.server.api.TransportApiService
import io.swagger.server.model.{Ligne, Station, Train}
import io.swagger.server.service.Voyageur._

import scala.concurrent.ExecutionContext
import scala.concurrent.duration._

class TransportServiceImpl(voyageur:ActorRef)(implicit ec: ExecutionContext) extends TransportApiService {

  implicit val timeout: Timeout = 5.seconds
  /**
   * Code: 200, Message: ajouter un tuple(nomligne,IDVoyaugeur,status).
   * Code: 400, Message: erreur
   */
  override def  ajoutVoyageurL(nomL: String, stationD: String, numeroID: Int): Route = {
    (voyageur! AjoutVoyageurL(nomL,stationD,numeroID))
    complete((200, "Ok"))
  }
  /**
   * Code: 200, Message: modifier le tuple(nomligne,IDVoyaugeur,status).
   * Code: 400, Message: erreur
   */
  override def  supprimeVoyageurL(nomL: String, stationD: String, numerID: Int): Route = {
    (voyageur! SupprimerVoyageurL(nomL,stationD,numerID))
    complete((200, "Ok"))
  }
  /**
   * Code: 200, Message: ajouter un tuple(nomTrain,IDVoyaugeur,status).
   * Code: 400, Message: erreur
   */
  override def  ajoutVoyageurT(nomT: String, nomL: String, statusVT: Boolean, numeroID: Int): Route = {
    (voyageur! AjoutVoyageurT(nomT,nomL,numeroID))
    complete((200, "Ok"))
  }
  /**
   * Code: 200, Message: supprimer voyageur&#x3D;&#x3D; changer la valeur de lattribut statusVL.
   * Code: 400, Message: erreur
   */
  override def  supprimeVoyageurTrain(nomT: String, nomL: String, numeroID: Int): Route = {
    (voyageur! SupprimerVoyageurT(nomT,nomL,numeroID))
    complete((200, "Ok"))
  }

  /*densite*/
  /**
   * Code: 200, Message: a density of line, DataType: String
   */
  override  def getLigneDensite(station: String, ligne: String): Route = {
    (voyageur! GetLigneDensite(station,ligne))
    complete((200, "Ok"))
  }

  /**
   * Code: 200, Message: A density, DataType: String
   */
  override def getTrainsDensite(ligne: String, train: String): Route = {
    (voyageur! GetTrainDensite(ligne,train))
    complete((200, "Ok"))
  }
  // Pour les get

  //def complete(i: Int, stations: Any): List[Station] => Route = ???

  /**
   * Code: 200, Message: ensemble des stations., DataType: List[Station]
   */
  override def getStation()(implicit toEntityMarshallerStationarray: ToEntityMarshaller[List[Station]]): Route = {
    val response = (voyageur ? GetStation).mapTo[List[Station]]
      onSuccess(response){(stations: List[Station]) => complete((200, stations))}
    }


  /**
   * Code: 200, Message: A list of lines., DataType: List[Ligne]
   */
  override def getStationLignes(station: String)
                               (implicit toEntityMarshallerLignearray: ToEntityMarshaller[List[Ligne]]): Route = {
      val response = (voyageur ? GetStationLigne(station)).mapTo[List[Ligne]]
      onSuccess(response){(lignes: List[Ligne]) => complete((200, lignes))}
    }

  override  def getTrains(ligne: String) (implicit toEntityMarshallerTrainarray: ToEntityMarshaller[List[Train]]): Route =
     {
      val response = (voyageur ? GetStationLigne(ligne)).mapTo[List[Train]]
      onSuccess(response){(trains: List[Train]) => complete((200, trains))}
    }



}

