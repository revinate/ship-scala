package com.revinate.ship.profile

import java.time.LocalDate

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
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
    action: Action,
    property: String,
    interfaceType: String,
    remoteSystemName: String,
    profileId: String,
    profileType: Type,
    title: Option[String] = None,
    firstName: Option[String] = None,
    middleName: Option[String] = None,
    lastName: String,
    gender: Option[Gender] = None,
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
) {

  @JsonCreator
  def this(
      @JsonProperty("action") @JsonScalaEnumeration(classOf[ActionTypeRef]) action: Action,
      @JsonProperty("property") property: String,
      @JsonProperty("interfaceType") interfaceType: String,
      @JsonProperty("remoteSystemName") remoteSystemName: String,
      @JsonProperty("profileId") profileId: String,
      @JsonProperty("profileType") @JsonScalaEnumeration(classOf[ProfileTypeTypeRef]) profileType: Type,
      @JsonProperty("title") title: Option[String],
      @JsonProperty("firstName") firstName: Option[String],
      @JsonProperty("middleName") middleName: Option[String],
      @JsonProperty("lastName") lastName: String,
      @JsonProperty("gender") @JsonScalaEnumeration(classOf[GenderTypeRef]) gender: Option[Gender],
      @JsonProperty("dateOfBirth") dateOfBirth: Option[LocalDate],
      @JsonProperty("vipStatus") vipStatus: Option[String],
      @JsonProperty("primaryLanguage") primaryLanguage: Option[String],
      @JsonProperty("companyInfo") companyInfo: Option[CompanyInfo],
      @JsonProperty("emailOptOut") emailOptOut: Option[Boolean],
      @JsonProperty("mailOptOut") mailOptOut: Option[Boolean],
      @JsonProperty("emailAddresses") emailAddresses: Option[Vector[EmailAddress]],
      @JsonProperty("postalAddresses") postalAddresses: Option[Vector[PostalAddress]],
      @JsonProperty("phoneNumbers") phoneNumbers: Option[Vector[PhoneNumber]],
      @JsonProperty("memberships") memberships: Option[Vector[Membership]],
      @JsonProperty("creditCards") creditCards: Option[Vector[CreditCard]],
      @JsonProperty("guestNotes") guestNotes: Option[Vector[GuestNote]]
  ) = this(
    action = action,
    property = property,
    interfaceType = interfaceType,
    remoteSystemName = remoteSystemName,
    profileId = profileId,
    profileType = profileType,
    title = title,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    gender = gender,
    dateOfBirth = dateOfBirth,
    vipStatus = vipStatus,
    primaryLanguage = primaryLanguage,
    companyInfo = companyInfo,
    emailOptOut = emailOptOut,
    mailOptOut = mailOptOut,
    emailAddresses = emailAddresses.getOrElse(Vector.empty),
    postalAddresses = postalAddresses.getOrElse(Vector.empty),
    phoneNumbers = phoneNumbers.getOrElse(Vector.empty),
    memberships = memberships.getOrElse(Vector.empty),
    creditCards = creditCards.getOrElse(Vector.empty),
    guestNotes = guestNotes.getOrElse(Vector.empty)
  )
}

