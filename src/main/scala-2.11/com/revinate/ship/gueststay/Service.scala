package com.revinate.ship.gueststay

import com.fasterxml.jackson.annotation.JsonIgnore
import com.revinate.ship.common.{MonetaryAmount, TimeSpan}

/** A service rendered during a stay
  *
  * @param inventoryCode Inventory code of the service
  * @param rateCode      Rate plan name or rate code of the service
  * @param timeSpan      Time period over which the service was rendered
  * @param pricePerUnit  Price per unit of the service
  * @param numberOfUnits Number of units of the service
  */
case class Service(
    inventoryCode: String,
    rateCode: String,
    timeSpan: Option[TimeSpan] = None,
    pricePerUnit: MonetaryAmount,
    numberOfUnits: Integer
) {

  @JsonIgnore val totalPrice = pricePerUnit * BigDecimal(numberOfUnits)
}
