package com.revinate.ship.common

object MonetaryAmount {
  val empty = new MonetaryAmount(None, None)

  def apply(amountBeforeTax: MonetaryValue, taxAmount: MonetaryValue) = new MonetaryAmount(Some(amountBeforeTax), Some(taxAmount))
}

case class MonetaryAmount(
    amountBeforeTax: Option[MonetaryValue],
    taxAmount: Option[MonetaryValue],
    @Deprecated value: Option[BigDecimal] = None,
    @Deprecated currency: Option[String] = None
) {

  import com.revinate.ship.common.MonetaryValue.addValues

  def +(that: MonetaryAmount): Option[MonetaryAmount] =
    (addValues(this.amountBeforeTax, that.amountBeforeTax), addValues(this.taxAmount, that.taxAmount)) match {
      case (Some(beforeTax), Some(tax)) if beforeTax.currency == tax.currency => Some(MonetaryAmount(beforeTax, tax))
      case _ => None
    }

  def *(multiplier: BigDecimal) = MonetaryAmount(amountBeforeTax.map(_ * multiplier), taxAmount.map(_ * multiplier))
}
