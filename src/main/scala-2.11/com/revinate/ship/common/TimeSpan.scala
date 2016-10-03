package com.revinate.ship.common

import java.time.OffsetDateTime

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.common.TimeSpan.Units.Units
import com.revinate.ship.common.TimeSpan.UnitsType

object TimeSpan {

  object Units extends Enumeration {
    type Units = Value
    val DAY, HOUR, MINUTE = Value
  }

  class UnitsType extends TypeReference[Units.type]

}

case class TimeSpan(
    startTime: OffsetDateTime,
    timeUnits: Int,
    @JsonScalaEnumeration(classOf[UnitsType]) timeUnitType: Units
)
