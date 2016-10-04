package com.revinate.ship.profile

import java.time.LocalDate

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.common.{CompanyInfo, GuestNote}
import com.revinate.ship.profile.Profile.ProfileAction.Action
import com.revinate.ship.profile.Profile.ProfileGender.Gender
import com.revinate.ship.profile.Profile.ProfileType.Type
import com.revinate.ship.profile.Profile.{ActionTypeRef, GenderTypeRef, ProfileTypeTypeRef}

object Profile {

  object ProfileAction extends Enumeration {
    type Action = Value
    val ADD, DELETE = Value
  }

  class ActionTypeRef extends TypeReference[ProfileAction.type]

  object ProfileType extends Enumeration {
    type Type = Value
    val NA, GUEST, CORPORATE, TRAVEL, WHOLESALER, GROUP, TOUR, CRO, CONTACT, AIRLINE, REPCOMPANY, INTERNET = Value
  }

  class ProfileTypeTypeRef extends TypeReference[ProfileType.type]

  object ProfileGender extends Enumeration {
    type Gender = Value
    val MALE, FEMALE, UNKNOWN = Value
  }

  class GenderTypeRef extends TypeReference[ProfileGender.type]

}

case class Profile(
    @JsonScalaEnumeration(classOf[ActionTypeRef]) action: Action,
    property: String,
    interfaceType: String,
    remoteSystemName: String,
    profileId: String,
    @JsonScalaEnumeration(classOf[ProfileTypeTypeRef]) profileType: Type,
    title: Option[String] = None,
    firstName: Option[String] = None,
    middleName: Option[String] = None,
    lastName: String,
    @JsonScalaEnumeration(classOf[GenderTypeRef]) gender: Option[Gender] = None,
    dateOfBirth: Option[LocalDate] = None,
    vipStatus: Option[String] = None,
    primaryLanguage: Option[String] = None,
    companyInfo: Option[CompanyInfo] = None,
    emailOptOut: Option[Boolean] = None,
    mailOptOut: Option[Boolean] = None,
    emailAddresses: Vector[EmailAddress] = Vector.empty,
    postalAddresses: Vector[PostalAddress] = Vector.empty,
    phoneNumbers: Vector[PhoneNumber] = Vector.empty,
    memberships: Vector[Membership] = Vector.empty,
    creditCards: Vector[CreditCard] = Vector.empty,
    guestNotes: Vector[GuestNote] = Vector.empty
)

