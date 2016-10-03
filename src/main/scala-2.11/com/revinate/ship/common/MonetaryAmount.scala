package com.revinate.ship.common

object MonetaryAmount {
}

case class MonetaryAmount(
    amountBeforeTax: MonetaryValue,
    taxAmount: MonetaryValue,
    @Deprecated value: Option[BigDecimal] = None,
    @Deprecated currency: Option[String] = None
) {

  def +(that: MonetaryAmount): Option[MonetaryAmount] =
    (this.amountBeforeTax + that.amountBeforeTax, this.taxAmount + that.taxAmount) match {
      case (Some(beforeTax), Some(tax)) if beforeTax.currency == tax.currency => Some(MonetaryAmount(beforeTax, tax))
      case _ => None
    }

  def *(multiplier: BigDecimal) = MonetaryAmount(amountBeforeTax * multiplier, taxAmount * multiplier)
}
