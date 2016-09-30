package com.revinate.ship.gueststay

import com.fasterxml.jackson.core.`type`.TypeReference

object Action extends Enumeration {
  type Action = Value
  val BOOK, ADD, WAITLIST, CONFIRM, DENY, CANCEL, CHECKIN, NOSHOW, CHECKOUT, EDIT, NA = Value
}

class ActionType extends TypeReference[Action.type]

object StatusCode extends Enumeration {
  type StatusCode = Value
  val REQUESTED, RESERVED, WAITLISTED, REQUESTDENIED, INHOUSE, CANCELED, NOSHOW, CHECKEDOUT = Value
}

class StatusCodeType extends TypeReference[StatusCode.type]
