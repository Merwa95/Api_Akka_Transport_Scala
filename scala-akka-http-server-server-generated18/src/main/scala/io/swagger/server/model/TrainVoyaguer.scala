package io.swagger.server.model


/**
 * @param Train 
 * @param statusVT 
 * @param voyageur 
 * @param densite 
 */
case class TrainVoyaguer (
  Train: Option[String],
  statusVT: Option[Boolean],
  voyageur: Option[Int],
  densite: Option[Densite]
)

