package com.revinate.ship.common

object MonetaryValue {

  val empty = new MonetaryValue()
  val zero = MonetaryValue(0)

  def apply(value: BigDecimal) = new MonetaryValue(Option(value), None)

  def apply(value: BigDecimal, currency: String) = new MonetaryValue(Option(value), Option(currency))
}

case class MonetaryValue(value: Option[BigDecimal] = None, currency: Option[String] = None) {

  def +(that: MonetaryValue): Option[MonetaryValue] = (this.currency, that.currency) match {
    case (Some(curr), None) => {
      (this.value, that.value) match {
        case (Some(v1), Some(v2)) => Some(MonetaryValue(v1 + v2, curr))
        case (Some(v1), None) => Some(MonetaryValue(v1, curr))
        case (None, Some(v2)) => Some(MonetaryValue(Some(v2), None))
        case (None, None) => Some(MonetaryValue(None, Some(curr)))
      }
    }
    case (None, Some(curr)) => {
      (this.value, that.value) match {
        case (Some(v1), Some(v2)) => Some(MonetaryValue(v1 + v2, curr))
        case (Some(v1), None) => Some(MonetaryValue(Some(v1), None))
        case (None, Some(v2)) => Some(MonetaryValue(v2, curr))
        case (None, None) => Some(MonetaryValue(None, Some(curr)))
      }
    }
    case (Some(curr1), Some(curr2)) if curr1 == curr2 => {
      (this.value, that.value) match {
        case (Some(v1), Some(v2)) => Some(MonetaryValue(v1 + v2, curr1))
        case (Some(v1), None) => Some(MonetaryValue(v1, curr1))
        case (None, Some(v2)) => Some(MonetaryValue(v2, curr1))
        case (None, None) => Some(MonetaryValue(None, Some(curr1)))
      }
    }
    case (Some(curr1), Some(curr2)) if curr1 != curr2 => {
      (this.value, that.value) match {
        case (Some(v1), Some(v2)) if v1 != 0 && v2 == 0 => Some(MonetaryValue(v1, curr1))
        case (Some(v1), Some(v2)) if v1 == 0 && v2 != 0 => Some(MonetaryValue(v2, curr2))
        case (Some(v1), Some(v2)) if v1 == 0 && v2 == 0 => Some(MonetaryValue.zero)
        case (Some(v1), None) => Some(MonetaryValue(v1, curr1))
        case (None, Some(v2)) => Some(MonetaryValue(v2, curr2))
        case _ => None
      }
    }
    case (None, None) => {
      (this.value, that.value) match {
        case (Some(v1), Some(v2)) => Some(MonetaryValue(Some(v1 + v2), None))
        case (Some(v1), None) => Some(MonetaryValue(Some(v1), None))
        case (None, Some(v2)) => Some(MonetaryValue(Some(v2), None))
        case (None, None) => Some(MonetaryValue(None, None))
      }
    }
    case _ => None
  }

  def *(multiplicand: BigDecimal): MonetaryValue = MonetaryValue(value.map(_ * multiplicand), currency)
}
