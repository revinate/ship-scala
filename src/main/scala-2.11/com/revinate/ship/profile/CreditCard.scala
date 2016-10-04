package com.revinate.ship.profile

import java.time.LocalDate

case class CreditCard(
    creditCardLast4: Option[String] = None,
    creditCardExpirationDate: Option[LocalDate] = None,
    creditCardType: Option[String] = None,
    primary: Boolean
)
