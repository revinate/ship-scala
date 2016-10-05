package com.revinate.ship.common

import java.time.OffsetDateTime

/** A note
  *
  * @param title Note title
  * @param text  Note text
  * @param type  Note type
  * @param time  Time the note was created/updated
  */
case class GuestNote(
    title: Option[String] = None,
    text: String, `type`: Option[String] = None, time: Option[OffsetDateTime] = None
)
