package com.revinate.ship.gueststay

import com.revinate.ship.common.TimeSpan

case class RatePlan(
    rateCode: String,
    timeSpan: Option[TimeSpan],
    confidential: Option[Boolean]
)
