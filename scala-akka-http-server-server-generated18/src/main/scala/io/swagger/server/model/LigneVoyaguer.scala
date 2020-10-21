package io.swagger.server.model


/**
 * @param ligne 
 * @param statusVL 
 * @param voyageur 
 * @param densite 
 */
case class LigneVoyaguer (
  ligne: Option[String],
  statusVL: Option[Boolean],
  voyageur: Option[Int],
  densite: Option[Densite]
)

