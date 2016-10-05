package com.revinate.ship.common

object MonetaryAmount {
  val empty = new MonetaryAmount(None, None)

  def apply(
      amountBeforeTax: MonetaryValue,
      taxAmount: MonetaryValue
  ): MonetaryAmount = new MonetaryAmount(Some(amountBeforeTax), Some(taxAmount))
}

/** An amount of money with associated tax
  *
  * @param amountBeforeTax Amount before tax
  * @param taxAmount       Tax amount
  * @param value           Number of units of the currency of the pre-tax amount. Use amountBeforeTax instead. Deprecated
  * @param currency        ISO4217 3-character currency code of the pre-tax amount. Use amountBeforeTax instead. Deprecated
  */
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

  def *(multiplier: BigDecimal): MonetaryAmount = MonetaryAmount(amountBeforeTax.map(_ * multiplier), taxAmount.map(_ * multiplier))
}
