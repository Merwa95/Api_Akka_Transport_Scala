package io.swagger.server.model


/**
 * @param id 
 * @param status 
 * @param capacity 
 * @param ligne 
 */
case class Train (
  id: String,
  status: Boolean,
  capacity: Int,
  ligne: String
)

