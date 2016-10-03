package com.revinate.ship.profile

import java.time.OffsetDateTime

case class EmailAddress(
    emailAddress: String,
    primary: Boolean,
    inactiveDate: Option[OffsetDateTime] = None
)
