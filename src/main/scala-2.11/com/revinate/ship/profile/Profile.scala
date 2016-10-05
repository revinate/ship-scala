package com.revinate.ship.profile

import java.time.LocalDate

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.revinate.ship.common.{CompanyInfo, GuestNote}
import com.revinate.ship.profile.Profile.ProfileAction.Action
import com.revinate.ship.profile.Profile.ProfileGender.Gender
import com.revinate.ship.profile.Profile.ProfileType.Type
import com.revinate.ship.profile.Profile.{ProfileAction, ProfileGender, ProfileType}

object Profile {

  object ProfileAction extends Enumeration {
    type Action = Value
    val ADD, DELETE = Value
  }


  object ProfileType extends Enumeration {
    type Type = Value
    val NA, GUEST, CORPORATE, TRAVEL, WHOLESALER, GROUP, TOUR, CRO, CONTACT, AIRLINE, REPCOMPANY, INTERNET = Value
  }


  object ProfileGender extends Enumeration {
    type Gender = Value
    val MALE, FEMALE, UNKNOWN = Value
  }

}

/** The SHIP profile
  *
  * A profile object represents a person or organization that may be associated with a guest-stay, for example
  * the primary guest, an accompanying guest, a corporate entity, or a travel agency.
  *
  * @param action           Action that triggered the profile message
  * @param property         Property code that the profile belongs to
  * @param interfaceType    Usually a code to identify the type of PMS. Internal use only
  * @param remoteSystemName Name of the remote system. Usually the origin of the data. Internal use only
  * @param profileId        ID of the profile
  * @param profileType      Profile type
  * @param title            Title of the guest
  * @param firstName        First name of the guest
  * @param middleName       Middle name of the guest
  * @param lastName         Last name of the guest, or name of a non-guest entity
  * @param gender           Gender of the guest
  * @param dateOfBirth      Date-of-birth of the guest
  * @param vipStatus        VIP status code of the guest
  * @param primaryLanguage  Primary language of the guest, preferably ISO639-1 2-character code
  * @param companyInfo      Company of the guest
  * @param emailOptOut      Whether the entity in the profile has opted out of email marketing
  * @param mailOptOut       Whether the entity in the profile has opted out of mail marketing
  * @param emailAddresses   Email addresses associated with the profile
  * @param postalAddresses  Postal addresses associated with the profile
  * @param phoneNumbers     Phone numbers associated with the profile
  * @param memberships      Loyalty program memberships associated with the profile
  * @param creditCards      Credit cards associated with the profile
  * @param guestNotes       Notes attached to the profile
  */
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

  // scalastyle:off parameter.number
  /*
    The json creator has to be an alternate constructor because of https://github.com/FasterXML/jackson-module-scala/issues/110
    Ideally this should be a factory method in the companion object
   */
  @JsonCreator
  def this(
      @JsonProperty("action") action: String,
      @JsonProperty("property") property: String,
      @JsonProperty("interfaceType") interfaceType: String,
      @JsonProperty("remoteSystemName") remoteSystemName: String,
      @JsonProperty("profileId") profileId: String,
      @JsonProperty("profileType") profileType: String,
      @JsonProperty("title") title: Option[String],
      @JsonProperty("firstName") firstName: Option[String],
      @JsonProperty("middleName") middleName: Option[String],
      @JsonProperty("lastName") lastName: String,
      @JsonProperty("gender") gender: Option[String],
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
    action = ProfileAction.withName(action),
    property = property,
    interfaceType = interfaceType,
    remoteSystemName = remoteSystemName,
    profileId = profileId,
    profileType = ProfileType.withName(profileType),
    title = title,
    firstName = firstName,
    middleName = middleName,
    lastName = lastName,
    gender = gender.map(ProfileGender.withName),
    dateOfBirth = dateOfBirth,
    vipStatus = vipStatus,
    primaryLanguage = primaryLanguage,
    companyInfo = companyInfo,
    emailOptOut = emailOptOut,
    mailOptOut = mailOptOut,

    /*
      If https://github.com/FasterXML/jackson-module-scala/pull/257 works then we can use the
      default constructor for everything. Meanwhile we use this alternate constructor to handle
      the null collection as an Option
    */
    emailAddresses = emailAddresses.getOrElse(Vector.empty),
    postalAddresses = postalAddresses.getOrElse(Vector.empty),
    phoneNumbers = phoneNumbers.getOrElse(Vector.empty),
    memberships = memberships.getOrElse(Vector.empty),
    creditCards = creditCards.getOrElse(Vector.empty),
    guestNotes = guestNotes.getOrElse(Vector.empty)
  )
  // scalastyle:on parameter.number

}
