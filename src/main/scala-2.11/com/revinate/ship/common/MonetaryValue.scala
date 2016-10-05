package com.revinate.ship.common

object MonetaryValue {

  val zero = MonetaryValue(0)

  def apply(value: BigDecimal): MonetaryValue = new MonetaryValue(value, None)

  def apply(value: BigDecimal, currency: String): MonetaryValue = new MonetaryValue(value, Option(currency))

  def addValues(left: Option[MonetaryValue], right: Option[MonetaryValue]): Option[MonetaryValue] = (left, right) match {
    case (Some(leftValue), None) => Some(leftValue)
    case (None, Some(rightValue)) => Some(rightValue)
    case (Some(leftValue), Some(rightValue)) => leftValue + rightValue
    case _ => None
  }
}

/** A monetary value consisting of a number and a currency
  *
  * @param value    Number of units of the currency
  * @param currency ISO4217 3-character currency code
  */
case class MonetaryValue(value: BigDecimal, currency: Option[String] = None) {

  def +(that: MonetaryValue): Option[MonetaryValue] = (this.currency, that.currency) match {
    case (Some(curr), None) => Some(MonetaryValue(this.value + that.value, curr))
    case (None, Some(curr)) => Some(MonetaryValue(this.value + that.value, curr))
    case (Some(curr1), Some(curr2)) if curr1 == curr2 => Some(MonetaryValue(this.value + that.value, curr1))
    case (Some(curr1), Some(curr2)) if curr1 != curr2 => addWithDifferentCurrency(that)
    case (None, None) => Some(MonetaryValue(this.value + that.value, None))
    case _ => None
  }

  private def addWithDifferentCurrency(that: MonetaryValue) = (this.value, that.value) match {
    case (left, right) if left == 0 =>
      if (right == 0) {
        Some(MonetaryValue(0, None))
      } else {
        Some(MonetaryValue(right, that.currency))
      }
    case (left, right) if left != 0 && right == 0 => Some(MonetaryValue(left, this.currency))
    case _ => None
  }

  def *(multiplicand: BigDecimal): MonetaryValue = copy(value = this.value * multiplicand)
}
