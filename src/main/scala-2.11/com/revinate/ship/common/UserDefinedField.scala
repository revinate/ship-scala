package com.revinate.ship.common

/** A name-value pair defined by a specific system or application
  *
  * @param name  Field name. If two fields have the same name, their values form an unordered list
  * @param value Field value
  */
case class UserDefinedField(name: String, value: String)
