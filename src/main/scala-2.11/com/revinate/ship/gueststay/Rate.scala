package com.revinate.ship.gueststay

import java.time.OffsetDateTime

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.common.MonetaryValue
import com.revinate.ship.gueststay.Rate.RateTimeUnit.RateTimeUnit
import com.revinate.ship.gueststay.Rate.RateTimeUnitType

object Rate {

  object RateTimeUnit extends Enumeration {
    type RateTimeUnit = Value
    val DAY, HOUR, MINUTE = Value
  }

  class RateTimeUnitType extends TypeReference[RateTimeUnit.type]

  def apply(
      amount: MonetaryValue,
      startTime: OffsetDateTime,
      timeUnits: Int,
      timeUnitType: RateTimeUnit
  ) = new Rate(amount, Some(startTime), Some(timeUnits), Some(timeUnitType))

}

/** A rate during a specific time period
  *
  * @param amount       Rate per time unit
  * @param startTime    Start time of the rate
  * @param timeUnits    Number of time units of the rate
  * @param timeUnitType Type of the time units
  */
case class Rate(
    amount: MonetaryValue,
    startTime: Option[OffsetDateTime],
    timeUnits: Option[Int],
    @JsonScalaEnumeration(classOf[RateTimeUnitType]) timeUnitType: Option[RateTimeUnit]
)
