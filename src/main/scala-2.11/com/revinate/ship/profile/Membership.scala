package com.revinate.ship.profile

import java.time.LocalDate

/** A loyalty program or other program membership
  *
  * @param loyaltyNumber Membership ID
  * @param programCode   Code of the membership program
  * @param levelCode     Membership level code
  * @param expireDate    Membership expiration date
  */
case class Membership(
    loyaltyNumber: String,
    programCode: Option[String] = None,
    levelCode: Option[String] = None,
    expireDate: Option[LocalDate] = None
)
