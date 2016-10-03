package com.revinate.ship

import com.revinate.ship.common.MonetaryValue

package object implicits {

  implicit class MonetaryValueImplicits[T: Numeric](value: T) {

    private def convertedValue = BigDecimal(value.toString)

    def cad: MonetaryValue = MonetaryValue(convertedValue, "CAD")

    def chf: MonetaryValue = MonetaryValue(convertedValue, "CHF")

    def eur: MonetaryValue = MonetaryValue(convertedValue, "EUR")

    def gbp: MonetaryValue = MonetaryValue(convertedValue, "GBP")

    def jpy: MonetaryValue = MonetaryValue(convertedValue, "JPY")

    def usd: MonetaryValue = MonetaryValue(convertedValue, "USD")
  }

}
