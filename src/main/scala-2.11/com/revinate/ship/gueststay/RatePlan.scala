package com.revinate.ship.gueststay

import com.revinate.ship.common.TimeSpan

/** A rate plan
  *
  * @param rateCode     Rate plan name or rate code
  * @param timeSpan     Time period over which the rate plan applies
  * @param confidential Whether the rate plan should be hidden from guests
  * @param rates        Breakdown of the rates under the rate plan
  */
case class RatePlan(
    rateCode: String,
    timeSpan: Option[TimeSpan] = None,
    confidential: Option[Boolean] = None,
    rates: List[Rate] = List.empty
)
