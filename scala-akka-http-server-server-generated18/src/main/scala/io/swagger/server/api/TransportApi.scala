package io.swagger.server.api

import akka.http.scaladsl.marshalling.ToEntityMarshaller
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import io.swagger.server.AkkaHttpHelper._
import io.swagger.server.model.{Ligne, Station, Train}

class TransportApi(
    transportService: TransportApiService,
    transportMarshaller: TransportApiMarshaller
) {
  import transportMarshaller._

  lazy val route: Route =
    path("Ligne" / "Voyageur" / Segment / Segment / IntNumber) { (nomL, stationD, numeroID) => 
      post {
        
          
            
              
                
                  transportService.ajoutVoyageurL(nomL = nomL, stationD = stationD, numeroID = numeroID)
               
             
           
         
       
      }
    } ~
    path("Ligne" / "Train" / "Voyageur" / Segment / Segment / Boolean / IntNumber) { (nomT, nomL, statusVT, numeroID) => 
      post {
        
          
            
              
                
                  transportService.ajoutVoyageurT(nomT = nomT, nomL = nomL, statusVT = statusVT, numeroID = numeroID)
               
             
           
         
       
      }
    } ~
    path("ligne" / "densite" / Segment / Segment) { (station, ligne) => 
      get {
        
          
            
              
                
                  transportService.getLigneDensite(station = station, ligne = ligne)
               
             
           
         
       
      }
    } ~
    path("stations") { 
      get {
        
          



                  transportService.getStation()





      }
    } ~
    path("Station" / "ligne" / Segment) { (station) => 
      get {
        
          
            
              
                
                  transportService.getStationLignes(station = station)
               
             
           
         
       
      }
    } ~
    path("lignes" / "trains" / Segment) { (ligne) => 
      get {
        
          
            
              
                
                  transportService.getTrains(ligne = ligne)
               
             
           
         
       
      }
    } ~
    path("lignes" / "trains" / "densite" / Segment / Segment) { (ligne, train) => 
      get {
        
          
            
              
                
                  transportService.getTrainsDensite(ligne = ligne, train = train)
               
             
           
         
       
      }
    } ~
    path("Ligne" / "Voyageur" / Segment / Segment / IntNumber) { (nomL, stationD, numerID) => 
      delete {
        
          
            
              
                
                  transportService.supprimeVoyageurL(nomL = nomL, stationD = stationD, numerID = numerID)
               
             
           
         
       
      }
    } ~
    path("LigneTrain" / "Voyageur" / Segment / Segment / IntNumber) { (nomT, nomL, numeroID) => 
      delete {
        
          
            
              
                
                  transportService.supprimeVoyageurTrain(nomT = nomT, nomL = nomL, numeroID = numeroID)
               
             
           
         
       
      }
    }
}

trait TransportApiService {

  def ajoutVoyageurL200: Route =
    complete((200, "ajouter un tuple(nomligne,IDVoyaugeur,status)."))
  def ajoutVoyageurL400: Route =
    complete((400, "erreur"))
  /**
   * Code: 200, Message: ajouter un tuple(nomligne,IDVoyaugeur,status).
   * Code: 400, Message: erreur
   */
  def ajoutVoyageurL(nomL: String, stationD: String, numeroID: Int): Route

  def ajoutVoyageurT200: Route =
    complete((200, "ajouter un tuple(nomTrain,IDVoyaugeur,status)."))
  def ajoutVoyageurT400: Route =
    complete((400, "erreur"))
  /**
   * Code: 200, Message: ajouter un tuple(nomTrain,IDVoyaugeur,status).
   * Code: 400, Message: erreur
   */
  def ajoutVoyageurT(nomT: String, nomL: String, statusVT: Boolean, numeroID: Int): Route

  def getLigneDensite200(responseString: String): Route =
    complete((200, responseString))
  /**
   * Code: 200, Message: a density of line, DataType: String
   */
  def getLigneDensite(station: String, ligne: String): Route

  def getStation200(responseStationarray: List[Station])(implicit toEntityMarshallerStationarray: ToEntityMarshaller[List[Station]]): Route =
    complete((200, responseStationarray))
  /**
   * Code: 200, Message: ensemble des stations., DataType: List[Station]
   */
  def getStation()
      (implicit toEntityMarshallerStationarray: ToEntityMarshaller[List[Station]]): Route

  def getStationLignes200(responseLignearray: List[Ligne])(implicit toEntityMarshallerLignearray: ToEntityMarshaller[List[Ligne]]): Route =
    complete((200, responseLignearray))
  /**
   * Code: 200, Message: A list of lines., DataType: List[Ligne]
   */
  def getStationLignes(station: String)
      (implicit toEntityMarshallerLignearray: ToEntityMarshaller[List[Ligne]]): Route

  def getTrains200(responseTrainarray: List[Train])(implicit toEntityMarshallerTrainarray: ToEntityMarshaller[List[Train]]): Route =
    complete((200, responseTrainarray))
  /**
   * Code: 200, Message: A list of trains, DataType: List[Train]
   */
  def getTrains(ligne: String)
      (implicit toEntityMarshallerTrainarray: ToEntityMarshaller[List[Train]]): Route

  def getTrainsDensite200(responseString: String): Route =
    complete((200, responseString))
  /**
   * Code: 200, Message: A density, DataType: String
   */
  def getTrainsDensite(ligne: String, train: String): Route

  def supprimeVoyageurL200: Route =
    complete((200, "modifier le tuple(nomligne,IDVoyaugeur,status)."))
  def supprimeVoyageurL400: Route =
    complete((400, "erreur"))
  /**
   * Code: 200, Message: modifier le tuple(nomligne,IDVoyaugeur,status).
   * Code: 400, Message: erreur
   */
  def supprimeVoyageurL(nomL: String, stationD: String, numerID: Int): Route

  def supprimeVoyageurTrain200: Route =
    complete((200, "supprimer voyageur&#x3D;&#x3D; changer la valeur de lattribut statusVL."))
  def supprimeVoyageurTrain400: Route =
    complete((400, "erreur"))
  /**
   * Code: 200, Message: supprimer voyageur&#x3D;&#x3D; changer la valeur de lattribut statusVL.
   * Code: 400, Message: erreur
   */
  def supprimeVoyageurTrain(nomT: String, nomL: String, numeroID: Int): Route

}

trait TransportApiMarshaller {

  implicit def toEntityMarshallerStationarray: ToEntityMarshaller[List[Station]]

  implicit def toEntityMarshallerLignearray: ToEntityMarshaller[List[Ligne]]

  implicit def toEntityMarshallerTrainarray: ToEntityMarshaller[List[Train]]

}

