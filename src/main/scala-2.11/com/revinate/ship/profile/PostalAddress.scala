package com.revinate.ship.profile

import java.time.OffsetDateTime

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.profile.PostalAddress.AddressType.AddressType
import com.revinate.ship.profile.PostalAddress.AddressTypeTypeRef

object PostalAddress {

  object AddressType extends Enumeration {
    type AddressType = Value
    val HOME, BUSINESS, MAILING, SHIPPING, BILLING, OTHER = Value
  }

  class AddressTypeTypeRef extends TypeReference[AddressType.type]

}

case class PostalAddress(
    address1: Option[String] = None,
    address2: Option[String] = None,
    city: Option[String] = None,
    state: Option[String] = None,
    country: Option[String] = None,
    zipCode: Option[String] = None,
    @JsonScalaEnumeration(classOf[AddressTypeTypeRef]) addressType: AddressType,
    primary: Boolean,
    inactiveDate: Option[OffsetDateTime] = None
)
