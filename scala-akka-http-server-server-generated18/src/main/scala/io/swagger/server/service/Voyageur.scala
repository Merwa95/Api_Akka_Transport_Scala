package io.swagger.server.service

import akka.actor.{Actor, ActorLogging}
import io.swagger.server.service.Voyageur.{AjoutVoyageurL, AjoutVoyageurT, GetLigneDensite, GetStation, GetStationLigne, GetTrainDensite, GetTrains, SupprimerVoyageurL, SupprimerVoyageurT}

object Voyageur {
  case class GetStationLigne(station:String)
  case object GetStation
  case class GetLigneDensite(station:String,ligne:String)
  case class AjoutVoyageurL(nomLigne:String,nomStation:String,IDVoyaguer:Int)
  case class SupprimerVoyageurL(nomLigne:String,nomStation:String,IDVoyaguer:Int)
  case class AjoutVoyageurT(nomTrain:String,nomLigne:String,IDVoyaguer:Int)
  case class SupprimerVoyageurT(nomTrain:String,nomLigne:String,IDVoyaguer:Int)
  case class GetTrains(ligne:String)
  case class GetTrainDensite(ligne:String,train:String)

}


class Voyageur(dataStore1: SationLigneTrainStore,dataStore2:VoyageurStore ) extends Actor with ActorLogging {

  def receive: Receive = {
    case  GetStationLigne(station:String) =>

      sender ! dataStore1.getStationLines(station)

    case GetStation =>

      sender ! dataStore1.getStations

    case  GetLigneDensite(station:String,ligne:String) =>
      /*affichage le nombre de voyageur*/
      sender ! dataStore2.getLinesDensity(station,ligne)

    case  AjoutVoyageurL(nomLigne:String,nomStation:String,iDVoyaguer:Int) =>

      sender ! dataStore2.addVoyageurL(nomLigne, nomStation,iDVoyaguer)

    case SupprimerVoyageurL(nomLigne:String, nomStation:String,iDVoyaguer: Int) =>
      sender ! dataStore2.deleteVoyageurL(nomLigne,nomStation,iDVoyaguer)

    case  AjoutVoyageurT(nomTrain:String,nomLigne:String,iDVoyaguer:Int) =>
      sender ! dataStore2.addVoyageurT(nomTrain, nomLigne,true,iDVoyaguer)

    case  SupprimerVoyageurT(nomTrain:String,nomLigne:String,iDVoyaguer:Int) =>
      sender ! dataStore2.deleteVoyageurTrain(nomTrain,nomLigne,iDVoyaguer)

    case GetTrains(ligne:String) =>
      sender ! dataStore1.getTransl(ligne)
    case  GetTrainDensite(ligne:String,train:String)=>
      sender ! dataStore2.getTrainsDensity(ligne,train)
    /*affichage le nombre de voyageur*/
    case msg @ error => log.info(s"Error : $msg $error")
  }
}

