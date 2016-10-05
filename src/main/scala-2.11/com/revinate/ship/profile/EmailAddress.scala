package com.revinate.ship.profile

import java.time.OffsetDateTime

/** An email address
  *
  * @param emailAddress Email address
  * @param primary      Whether this is the primary email address on the profile. Only one email address can be primary per profile.
  * @param inactiveDate Time after which the email address should not be used
  */
case class EmailAddress(
    emailAddress: String,
    primary: Boolean,
    inactiveDate: Option[OffsetDateTime] = None
)
