package com.revinate.ship.common

import java.time.OffsetDateTime

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.common.TimeSpan.TimeSpanUnit.TimeSpanUnit
import com.revinate.ship.common.TimeSpan.TimeSpanUnitType

object TimeSpan {

  object TimeSpanUnit extends Enumeration {
    type TimeSpanUnit = Value
    val DAY, HOUR, MINUTE = Value
  }

  class TimeSpanUnitType extends TypeReference[TimeSpanUnit.type]

}

case class TimeSpan(
    startTime: OffsetDateTime,
    timeUnits: Int,
    @JsonScalaEnumeration(classOf[TimeSpanUnitType]) timeUnitType: TimeSpanUnit
)
