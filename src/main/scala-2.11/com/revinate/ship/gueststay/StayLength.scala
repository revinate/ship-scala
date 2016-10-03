package com.revinate.ship.gueststay

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.gueststay.StayLength.StayLengthUnit.StayLengthUnit
import com.revinate.ship.gueststay.StayLength.StayLengthUnitType

object StayLength {

  object StayLengthUnit extends Enumeration {
    type StayLengthUnit = Value
    val DAY = Value
  }

  class StayLengthUnitType extends TypeReference[StayLengthUnit.type]

  def apply(stayLength: Int): StayLength = new StayLength(Some(stayLength))

}

case class StayLength(
    stayLength: Option[Int] = None,
    @JsonScalaEnumeration(classOf[StayLengthUnitType]) stayLengthUnits: Option[StayLengthUnit] = None
)

