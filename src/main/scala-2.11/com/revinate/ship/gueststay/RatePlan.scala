package com.revinate.ship.gueststay

import com.revinate.ship.common.TimeSpan

case class RatePlan(
    rateCode: String,
    timeSpan: Option[TimeSpan] = None,
    confidential: Option[Boolean] = None,
    rates: List[Rate] = List.empty
)
