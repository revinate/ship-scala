package com.revinate.ship.gueststay

import com.fasterxml.jackson.annotation.JsonIgnore
import com.revinate.ship.common.{MonetaryAmount, TimeSpan}

case class Service(
    inventoryCode: String,
    rateCode: String,
    timeSpan: Option[TimeSpan] = None,
    pricePerUnit: MonetaryAmount,
    numberOfUnits: Integer
) {

  @JsonIgnore val totalPrice = pricePerUnit * BigDecimal(numberOfUnits)
}
