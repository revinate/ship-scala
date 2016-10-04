package com.revinate.ship.common

import java.time.OffsetDateTime

case class GuestNote(
    title: Option[String] = None,
    text: String,
    `type`: Option[String] = None,
    time: Option[OffsetDateTime] = None
)
