package com.revinate.ship.profile

import java.time.LocalDate

case class Membership(
    loyaltyNumber: String,
    programCode: Option[String] = None,
    levelCode: Option[String] = None,
    expireDate: Option[LocalDate] = None
)
