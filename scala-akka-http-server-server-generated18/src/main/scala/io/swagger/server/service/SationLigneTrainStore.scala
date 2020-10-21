package io.swagger.server.service
import io.swagger.server.model

._

class SationLigneTrainStore {
  //list of Station
  private val listStation :List[Station]= List(Station("StationA",true,"RATP"),Station("StationB",true,"SNCF"))
  //a list(station_nom,(list_line))
  private val listStatioLigne: Map[String,List[Ligne] ]= Map(
    "StationA"->List(Ligne("ligne1",true,20,"StationA","StationB"),Ligne("ligne2",true,20,"StationA","StationB")),
    "StationB"->List(Ligne("ligne1",true,15,"StationB","StationA"),Ligne("ligne2",true,15,"StationB","StationA"))
  )
  //a list(ligne_nom,(list_train))
  private val listLigneTrain: Map[String,List[Train] ]= Map(
    "ligne1"->List(Train("train1",true,10,"ligne1"),Train("train2",true,15,"ligne1")),
    "ligne2"->List(Train("train3",true,10,"ligne2"),Train("train4",true,15,"ligne2"))
  )

  def getStations: List[Station] = listStation;

  def getStationLines(station: String): List[Ligne] =  listStatioLigne(station);

  def getTransl(line: String): List[Train] = listLigneTrain(line);
}
