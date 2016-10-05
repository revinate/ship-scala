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

/** A postal address
  *
  * @param address1     Street address line 1
  * @param address2     Street address line 2
  * @param city         City
  * @param state        State or province, preferably short code
  * @param country      Country, preferably ISO3166 2-character code
  * @param zipCode      Zip or postal code
  * @param addressType  Address type
  * @param primary      Whether this is the primary postal address on the profile. Only one postal address can be primary per profile.
  * @param inactiveDate Time after which the postal address should not be used
  */
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
