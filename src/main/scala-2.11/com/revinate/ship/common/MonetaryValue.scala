package com.revinate.ship.common

object MonetaryValue {

  val zero = MonetaryValue(0)

  def apply(value: BigDecimal) = new MonetaryValue(value, None)

  def apply(value: BigDecimal, currency: String) = new MonetaryValue(value, Option(currency))

  def addValues(left: Option[MonetaryValue], right: Option[MonetaryValue]): Option[MonetaryValue] = (left, right) match {
    case (Some(leftValue), None) => Some(leftValue)
    case (None, Some(rightValue)) => Some(rightValue)
    case (Some(leftValue), Some(rightValue)) => leftValue + rightValue
    case _ => None
  }
}

case class MonetaryValue(value: BigDecimal, currency: Option[String] = None) {

  def +(that: MonetaryValue): Option[MonetaryValue] = (this.currency, that.currency) match {
    case (Some(curr), None) => Some(MonetaryValue(this.value + that.value, curr))
    case (None, Some(curr)) => Some(MonetaryValue(this.value + that.value, curr))
    case (Some(curr1), Some(curr2)) if curr1 == curr2 => Some(MonetaryValue(this.value + that.value, curr1))
    case (Some(curr1), Some(curr2)) if curr1 != curr2 => (this.value, that.value) match {
      case (left, right) if left == 0 && right == 0 => Some(MonetaryValue(0, None))
      case (left, right) if left != 0 && right == 0 => Some(MonetaryValue(left, this.currency))
      case (left, right) if left == 0 && right != 0 => Some(MonetaryValue(right, that.currency))
      case _ => None
    }
    case (None, None) => Some(MonetaryValue(this.value + that.value, None))
    case _ => None
  }

  def *(multiplicand: BigDecimal): MonetaryValue = copy(value = this.value * multiplicand)
}
