package com.revinate.ship.gueststay

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.gueststay.StayLength.Units.Units
import com.revinate.ship.gueststay.StayLength.UnitsType

object StayLength {

  object Units extends Enumeration {
    type Units = Value
    val DAY = Value
  }

  class UnitsType extends TypeReference[Units.type]

  def apply(stayLength: Int): StayLength = new StayLength(Some(stayLength))

}

case class StayLength(
    stayLength: Option[Int] = None,
    @JsonScalaEnumeration(classOf[UnitsType]) stayLengthUnits: Option[Units] = None
)

