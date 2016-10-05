package com.revinate.ship.profile

import java.time.OffsetDateTime

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.profile.PhoneNumber.PhoneNumberType.PhoneNumberType
import com.revinate.ship.profile.PhoneNumber.PhoneNumberTypeTypeRef

object PhoneNumber {

  object PhoneNumberType extends Enumeration {
    type PhoneNumberType = Value
    val HOME, BUSINESS, MOBILE, HOMEFAX, BUSINESSFAX, PAGER, TELEX, TTY, OTHER = Value
  }

  class PhoneNumberTypeTypeRef extends TypeReference[PhoneNumberType.type]

}

/** A phone number
  *
  * @param phoneNumber     Phone number
  * @param phoneNumberType Phone number type
  * @param primary         Whether this is the primary phone number on the profile. Only one phone number can be primary per profile
  * @param inactiveDate    Time after which the phone number should not be used
  */
case class PhoneNumber(
    phoneNumber: String,
    @JsonScalaEnumeration(classOf[PhoneNumberTypeTypeRef]) phoneNumberType: PhoneNumberType,
    primary: Boolean,
    inactiveDate: Option[OffsetDateTime] = None
)