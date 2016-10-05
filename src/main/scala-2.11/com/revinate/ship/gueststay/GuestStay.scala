package com.revinate.ship.gueststay

import java.time.{LocalDate, OffsetDateTime}

import com.fasterxml.jackson.annotation.{JsonCreator, JsonProperty}
import com.revinate.ship.common._
import com.revinate.ship.gueststay.GuestStay.GuestStayAction.Action
import com.revinate.ship.gueststay.GuestStay.GuestStayStatusCode.StatusCode
import com.revinate.ship.gueststay.GuestStay.{GuestStayAction, GuestStayStatusCode}
import com.revinate.ship.profile.Profile

object GuestStay {

  object GuestStayAction extends Enumeration {
    type Action = Value
    val BOOK, ADD, WAITLIST, CONFIRM, DENY, CANCEL, CHECKIN, NOSHOW, CHECKOUT, EDIT, NA = Value
  }

  object GuestStayStatusCode extends Enumeration {
    type StatusCode = Value
    val REQUESTED, RESERVED, WAITLISTED, REQUESTDENIED, INHOUSE, CANCELED, NOSHOW, CHECKEDOUT = Value
  }

}

/** The SHIP guest stay
  *
  * A guestStay object represents a past, current, or upcoming stay by a guest or guests at a property.
  * It contains information about the location, duration and other details of the stay, as well as profile
  * information about the guests and other associated entities.
  *
  * @param action                      Action that triggered the guest-stay message
  * @param property                    Property code that the stay belongs to
  * @param interfaceType               Usually a code to identify the type of PMS. Internal use only
  * @param remoteSystemName            Name of the remote system. Usually the origin of the data. Internal use only
  * @param confirmationCode            ID of the stay
  * @param statusCode                  Current status of the stay
  * @param guaranteeCode               Code representing the type of guarantee for the stay
  * @param lastUpdatedAt               Time at which the stay was last updated
  * @param lastUpdatedBy               Name, username, or initials of the user or system that last updated the stay
  * @param bookingNumber               ID assigned to the stay by the booking engine or central reservation system
  * @param bookingDate                 Time at which the stay was booked
  * @param bookedBy                    Name, username, or initials of the user or system that booked the stay
  * @param reservationSource           Channel by which the stay was booked
  * @param cancellationNumber          ID assigned to the cancellation of the stay
  * @param cancellationDate            Time at which the stay was canceled
  * @param canceledBy                  Name, username, or initials of the user or system that canceled the stay
  * @param checkinDate                 Scheduled check-in date of the stay
  * @param actualCheckinDate           Actual check-in time of the stay
  * @param checkedInBy                 Name, username, or initials of the user or system that checked the guest in
  * @param checkoutDate                Scheduled check-out date of the stay
  * @param actualCheckoutDate          Actual check-out time of the stay
  * @param checkedOutBy                Name, username, or initials of the user or system that checked the guest out
  * @param stayLength                  Length of the stay
  * @param numberOfAdults              Number of adults in the stay
  * @param numberOfChildren            Number of children in the stay
  * @param roomNumber                  Room number or room identifier
  * @param roomType                    Actual room type of the stay, preferably in code/short form
  * @param roomTypeChargeCode          Room type charged to the stay
  * @param blockCode                   Block code of the stay
  * @param numberOfRooms               Number of rooms of the stay
  * @param market                      Market segment name or code of the stay
  * @param purposeOfStay               Purpose of the stay
  * @param companyInfo                 Travel agency associated with the stay. Attach a profile object with profileType of "TRAVEL" to the stay
  *                                    instead of using this field. Deprecated
  * @param totalRoomRevenue            Room revenue amount of the stay
  * @param totalFoodAndBeverageRevenue Food and beverage revenue amount of the stay
  * @param totalLuggageRevenue         Luggage revenue amount of the stay
  * @param totalOtherRevenue           Total of all revenue amounts excluding room, food, beverage, and luggage, of the stay
  * @param totalTaxes                  Total of all taxes. Use the taxAmount field in each monetaryAmount object instead. Deprecated
  * @param totalRemainingBalance       Balance remaining on the stay
  * @param totalDepositRequired        Deposit amount required for the stay
  * @param depositRequiredDate         Date on which deposit is due
  * @param ratePlans                   Rate plans attached to the stay
  * @param services                    Services rendered during the stay
  * @param profiles                    Profiles associated with the stay. A minimum of 1 profile with profileType "GUEST" is required.
  * @param guestNotes                  Notes attached to the stay
  * @param pmsDefinedFields            PMS-defined fields
  * @param propertyDefinedFields       Property-defined fields
  */
case class GuestStay(
    action: Action,
    property: String,
    interfaceType: String,
    remoteSystemName: String,
    confirmationCode: String,
    statusCode: Option[StatusCode] = None,
    guaranteeCode: Option[String] = None,
    lastUpdatedAt: Option[OffsetDateTime] = None,
    lastUpdatedBy: Option[String] = None,
    bookingNumber: Option[String] = None,
    bookingDate: Option[OffsetDateTime] = None,
    bookedBy: Option[String] = None,
    reservationSource: Option[String] = None,
    cancellationNumber: Option[String] = None,
    cancellationDate: Option[OffsetDateTime] = None,
    canceledBy: Option[String] = None,
    checkinDate: Option[LocalDate] = None,
    actualCheckinDate: Option[OffsetDateTime] = None,
    checkedInBy: Option[String] = None,
    checkoutDate: Option[LocalDate] = None,
    actualCheckoutDate: Option[OffsetDateTime] = None,
    checkedOutBy: Option[String] = None,
    stayLength: Option[StayLength] = None,
    numberOfAdults: Option[Int] = None,
    numberOfChildren: Option[Int] = None,
    roomNumber: Option[String] = None,
    roomType: Option[String] = None,
    roomTypeChargeCode: Option[String] = None,
    blockCode: Option[String] = None,
    numberOfRooms: Option[Int] = None,
    market: Option[String] = None,
    purposeOfStay: Option[String] = None,
    companyInfo: Option[CompanyInfo] = None,
    totalRoomRevenue: Option[MonetaryAmount] = None,
    totalFoodAndBeverageRevenue: Option[MonetaryAmount] = None,
    totalLuggageRevenue: Option[MonetaryAmount] = None,
    totalOtherRevenue: Option[MonetaryAmount] = None,
    totalTaxes: Option[MonetaryValue] = None,
    totalRemainingBalance: Option[MonetaryValue] = None,
    totalDepositRequired: Option[MonetaryValue] = None,
    depositRequiredDate: Option[LocalDate] = None,
    ratePlans: Vector[RatePlan] = Vector.empty,
    services: Vector[Service] = Vector.empty,
    profiles: Vector[Profile] = Vector.empty,
    guestNotes: Vector[GuestNote] = Vector.empty,
    pmsDefinedFields: Vector[UserDefinedField] = Vector.empty,
    propertyDefinedFields: Vector[UserDefinedField] = Vector.empty
) {

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
      @JsonProperty("confirmationCode") confirmationCode: String,
      @JsonProperty("statusCode") statusCode: Option[String],
      @JsonProperty("guaranteeCode") guaranteeCode: Option[String],
      @JsonProperty("lastUpdatedAt") lastUpdatedAt: Option[OffsetDateTime],
      @JsonProperty("lastUpdatedBy") lastUpdatedBy: Option[String],
      @JsonProperty("bookingNumber") bookingNumber: Option[String],
      @JsonProperty("bookingDate") bookingDate: Option[OffsetDateTime],
      @JsonProperty("bookedBy") bookedBy: Option[String],
      @JsonProperty("reservationSource") reservationSource: Option[String],
      @JsonProperty("cancellationNumber") cancellationNumber: Option[String],
      @JsonProperty("cancellationDate") cancellationDate: Option[OffsetDateTime],
      @JsonProperty("canceledBy") canceledBy: Option[String],
      @JsonProperty("checkinDate") checkinDate: Option[LocalDate],
      @JsonProperty("actualCheckinDate") actualCheckinDate: Option[OffsetDateTime],
      @JsonProperty("checkedInBy") checkedInBy: Option[String],
      @JsonProperty("checkoutDate") checkoutDate: Option[LocalDate],
      @JsonProperty("actualCheckoutDate") actualCheckoutDate: Option[OffsetDateTime],
      @JsonProperty("checkedOutBy") checkedOutBy: Option[String],
      @JsonProperty("stayLength") stayLength: Option[StayLength],
      @JsonProperty("numberOfAdults") numberOfAdults: Option[Int],
      @JsonProperty("numberOfChildren") numberOfChildren: Option[Int],
      @JsonProperty("roomNumber") roomNumber: Option[String],
      @JsonProperty("roomType") roomType: Option[String],
      @JsonProperty("roomTypeChargeCode") roomTypeChargeCode: Option[String],
      @JsonProperty("blockCode") blockCode: Option[String],
      @JsonProperty("numberOfRooms") numberOfRooms: Option[Int],
      @JsonProperty("market") market: Option[String],
      @JsonProperty("purposeOfStay") purposeOfStay: Option[String],
      @JsonProperty("companyInfo") companyInfo: Option[CompanyInfo],
      @JsonProperty("totalRoomRevenue") totalRoomRevenue: Option[MonetaryAmount],
      @JsonProperty("totalFoodAndBeverageRevenue") totalFoodAndBeverageRevenue: Option[MonetaryAmount],
      @JsonProperty("totalLuggageRevenue") totalLuggageRevenue: Option[MonetaryAmount],
      @JsonProperty("totalOtherRevenue") totalOtherRevenue: Option[MonetaryAmount],
      @JsonProperty("totalTaxes") totalTaxes: Option[MonetaryValue],
      @JsonProperty("totalRemainingBalance") totalRemainingBalance: Option[MonetaryValue],
      @JsonProperty("totalDepositRequired") totalDepositRequired: Option[MonetaryValue],
      @JsonProperty("depositRequiredDate") depositRequiredDate: Option[LocalDate],
      @JsonProperty("ratePlans") ratePlans: Option[Vector[RatePlan]],
      @JsonProperty("services") services: Option[Vector[Service]],
      @JsonProperty("profiles") profiles: Option[Vector[Profile]],
      @JsonProperty("guestNotes") guestNotes: Option[Vector[GuestNote]],
      @JsonProperty("pmsDefinedFields") pmsDefinedFields: Option[Vector[UserDefinedField]],
      @JsonProperty("propertyDefinedFields") propertyDefinedFields: Option[Vector[UserDefinedField]]
  ) = this(
    action = GuestStayAction.withName(action),
    property = property,
    interfaceType = interfaceType,
    remoteSystemName = remoteSystemName,
    confirmationCode = confirmationCode,
    statusCode = statusCode.map(GuestStayStatusCode.withName),
    guaranteeCode = guaranteeCode,
    lastUpdatedAt = lastUpdatedAt,
    lastUpdatedBy = lastUpdatedBy,
    bookingNumber = bookingNumber,
    bookingDate = bookingDate,
    bookedBy = bookedBy,
    reservationSource = reservationSource,
    cancellationNumber = cancellationNumber,
    cancellationDate = cancellationDate,
    canceledBy = canceledBy,
    checkinDate = checkinDate,
    actualCheckinDate = actualCheckinDate,
    checkedInBy = checkedInBy,
    checkoutDate = checkoutDate,
    actualCheckoutDate = actualCheckoutDate,
    checkedOutBy = checkedOutBy,
    stayLength = stayLength,
    numberOfAdults = numberOfAdults,
    numberOfChildren = numberOfChildren,
    roomNumber = roomNumber,
    roomType = roomType,
    roomTypeChargeCode = roomTypeChargeCode,
    blockCode = blockCode,
    numberOfRooms = numberOfRooms,
    market = market,
    purposeOfStay = purposeOfStay,
    companyInfo = companyInfo,
    totalRoomRevenue = totalRoomRevenue,
    totalFoodAndBeverageRevenue = totalFoodAndBeverageRevenue,
    totalLuggageRevenue = totalLuggageRevenue,
    totalOtherRevenue = totalOtherRevenue,
    totalTaxes = totalTaxes,
    totalRemainingBalance = totalRemainingBalance,
    totalDepositRequired = totalDepositRequired,
    depositRequiredDate = depositRequiredDate,

    /*
      If https://github.com/FasterXML/jackson-module-scala/pull/257 works then we can use the
      default constructor for everything. Meanwhile we use this alternate constructor to handle
      the null collection as an Option
    */
    ratePlans = ratePlans.getOrElse(Vector.empty),
    services = services.getOrElse(Vector.empty),
    profiles = profiles.getOrElse(Vector.empty),
    guestNotes = guestNotes.getOrElse(Vector.empty),
    pmsDefinedFields = pmsDefinedFields.getOrElse(Vector.empty),
    propertyDefinedFields = propertyDefinedFields.getOrElse(Vector.empty)
  )
}
