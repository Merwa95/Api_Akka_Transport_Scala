package io.swagger.server.service

class VoyageurStore {
  /**
  case class Voyageur (
                        id: Option[Int],
                        nom: String,
                        status: Boolean)

 case class TrainVoyaguer (
  Train: Option[String],
  statusVT: Option[Boolean],
  voyageur: Option[Int],
  densite: Option[Densite]
   */
  //list voyaguer dans  lignes dans differentes stations ,ligne,numeorID,statusVL
  private var listStationLignesVoyageur : Map[(String,String),List[(Int,Boolean)]]=
  Map(("StationA","ligne1")->List(
    (1,true),
    (2,true),
    (3,true),
    (4,true),
    (5,false),
    (6,false),
    (7,true),
    (8,true),
    (9,true),
    (10,false),
    (11,false),
    (12,true)
  ),
    ("StationA","ligne2")->List(
      (1,true),
      (2,true),
      (3,true),
      (4,true),
      (5,true),
      (6,true),
      (7,true),
      (8,true),
      (9,true),
      (10,false),
      (11,true),
      (12,true)  ),

    ("StationB","ligne1")->List(

      (1,true),
      (2,true),
      (3,true),
      (4,true),
      (5,true),
      (6,true),
      (7,true),
      (8,true),
      (9,true),
      (10,false),
      (11,false),
      (12,true)
    ),
    ("StationB","ligne2")->List(
      (1,true),
      (2,true),
      (3,true),
      (4,true),
      (5,false),
      (6,false),
      (7,true),
      (8,true),
      (9,true),
      (10,false),
      (11,false),
      (12,true)
    )
  )

  // list des capa
  private  val listCap:Map[(String,String),Int]=Map(
    ("StationA","ligne1")->20,
    ("StationA","ligne2")->20,
    ("StationB","ligne1")->10,
    ("StationB","ligne2")->10,
    ("ligne1","train1")->10,
    ("ligne1","train2")->15,
    ("ligne2","train3")->10,
    ("ligne2","train4")->15
  )

  //list voyageur dans trains dans n'importe  station A ou B (mais apres il faut ajouter un attribut stationD ou bien la liste(station, true)
  // la date aussi                      ligne,     Train,     numeroID, StatusVT

  private var listTrainVoyageur : Map[(String,String),List[(Int,Boolean)]]=
    Map(("ligne1","train1")->List(
      (1,true),
      (2,true),
      (3,true),
      (4,true),
      (5,false),
      (6,false),
      (7,true),
      (8,true),
      (9,true),
      (10,false),
      (11,false),
      (12,true)
    ),
      ("ligne1","train2")->List(
        (1,true),
        (2,true),
        (3,true),
        (4,true),
        (5,false),
        (6,false),
        (7,true),
        (8,true),
        (9,true),
        (10,false),
        (11,false),
        (12,true)
      ),
      ("ligne2","train3")->List(
        (1,true),
        (2,true),
        (3,true),
        (4,true),
        (5,false),
        (6,false),
        (7,true),
        (8,true),
        (9,true),
        (10,false),
        (11,false),
        (12,true)
      ),
      ("ligne2","train4")->List(
        (1,true),
        (2,true),
        (3,true),
        (4,true),
        (5,false),
        (6,false),
        (7,true),
        (8,true),
        (9,true),
        (10,false),
        (11,false),
        (12,true)
      )
    )

  def addVoyageurL(nomL: String, stationD: String, numeroID: Int):Unit={
    val listVL=(listStationLignesVoyageur((stationD,nomL) ))
    //val listVL1: Seq[(Int, Boolean)] = listVL++(numeroID,true)
    //listStationLignesVoyageur=listStationLignesVoyageur + ((stationD,nomL),listVL1)
    print("ajout le voyageur")
  }

  def addVoyageurT(nomT: String, nomL: String, statusVT: Boolean, numeroID: Int):Unit={
    val listVL=(listTrainVoyageur((nomL,nomT) ))
    //val listVL1: Seq[(Int, Boolean)] = listVL++(numeroID,true)
    //listStationLignesVoyageur=listStationLignesVoyageur + ((stationD,nomL),listVL1)
    print("ajout le voyageur")
  }

  def deleteVoyageurL(nomL: String, stationD: String, numerID: Int):Unit={
    val listVL=(listStationLignesVoyageur((stationD,nomL) ))
    //val listVL1: Seq[(Int, Boolean)] = listVL--(numerID,true)
    //listStationLignesVoyageur=listStationLignesVoyageur + ((stationD,nomL),listVL1)
    print("supprimer le voyageur")
  }

  def deleteVoyageurTrain(nomT: String, nomL: String, numeroID: Int): Unit = {
    val listVL = (listTrainVoyageur((nomL, nomT)))
    //val listVL1: Seq[(Int, Boolean)] = listVL++(numeroID,true)
    //listStationLignesVoyageur=listStationLignesVoyageur + ((stationD,nomL),listVL1)
    print("supprime le voyageur")
  }

  def getLinesDensity(station: String, ligne: String):Unit= {
    val listVL=(listStationLignesVoyageur((station,ligne) )).length
    val diff= (listCap((station,ligne)))-listVL
    if(diff<0){
      print((s"danger in $ligne"))
    }else if(diff>5){
      print((s"some danger in $ligne"))
    }else{
      print((s"no danger in $ligne"))
    }

  }
  // il faut ajouter apres la condition statusVL=true oki

  def getTrainsDensity(ligne: String, train: String):Unit={
    val listVL=(listTrainVoyageur((ligne,train) )).length
    val diff= (listCap((ligne,train)))-listVL
    if(diff<0){
      print((s"danger in $ligne"))
    }else if(diff>5){
      print((s"some danger in $ligne"))
    }else{
      print((s"no danger in $ligne"))
    }

  }
}

