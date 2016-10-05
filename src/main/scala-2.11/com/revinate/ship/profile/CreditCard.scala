package com.revinate.ship.profile

import java.time.LocalDate

/** A credit card
  *
  * @param creditCardLast4          Last 4 digits of the credit card number
  * @param creditCardExpirationDate Credit card expiration date
  * @param creditCardType           Credit card type
  * @param primary                  Whether this is the primary credit card on the profile. Only one credit card can be primary per profile
  */
case class CreditCard(
    creditCardLast4: Option[String] = None,
    creditCardExpirationDate: Option[LocalDate] = None,
    creditCardType: Option[String] = None,
    primary: Boolean
)
