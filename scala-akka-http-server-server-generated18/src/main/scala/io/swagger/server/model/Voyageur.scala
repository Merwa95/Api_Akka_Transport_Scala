package io.swagger.server.model


/**
 * @param id 
 * @param nom 
 * @param status 
 */
case class Voyageur (
  id: Option[Int],
  nom: String,
  status: Boolean
)

