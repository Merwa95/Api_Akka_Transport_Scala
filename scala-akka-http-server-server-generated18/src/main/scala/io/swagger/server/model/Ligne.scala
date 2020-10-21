package io.swagger.server.model


/**
 * @param id 
 * @param status 
 * @param capacity 
 * @param stationD 
 * @param stationA 
 */
case class Ligne (
  id: String,
  status: Boolean,
  capacity: Int,
  stationD: String,
  stationA: String
)

