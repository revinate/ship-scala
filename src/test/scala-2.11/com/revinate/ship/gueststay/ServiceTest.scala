package com.revinate.ship.gueststay

import com.revinate.ship.common.MonetaryAmount
import com.revinate.ship.implicits._
import org.scalatest.{FreeSpec, Matchers}

class ServiceTest extends FreeSpec with Matchers {

  "Guest stay service" - {
    "should calculate total price" in {
      val service = Service(
        inventoryCode = "WIFI",
        rateCode = "WIFI",
        pricePerUnit = MonetaryAmount(9.99.usd, 0.10.usd),
        numberOfUnits = 10
      )

      service.totalPrice shouldEqual MonetaryAmount(99.90.usd, 1.usd)
    }
  }
}
