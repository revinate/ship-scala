package com.revinate.ship.gueststay

import java.time.{LocalDate, OffsetDateTime}

import com.fasterxml.jackson.core.`type`.TypeReference
import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.common._
import com.revinate.ship.gueststay.GuestStay.GuestStayAction.Action
import com.revinate.ship.gueststay.GuestStay.GuestStayStatusCode.StatusCode
import com.revinate.ship.gueststay.GuestStay.{ActionTypeRef, StatusCodeTypeRef}
import com.revinate.ship.profile.Profile

object GuestStay {

  object GuestStayAction extends Enumeration {
    type Action = Value
    val BOOK, ADD, WAITLIST, CONFIRM, DENY, CANCEL, CHECKIN, NOSHOW, CHECKOUT, EDIT, NA = Value
  }

  class ActionTypeRef extends TypeReference[GuestStayAction.type]

  object GuestStayStatusCode extends Enumeration {
    type StatusCode = Value
    val REQUESTED, RESERVED, WAITLISTED, REQUESTDENIED, INHOUSE, CANCELED, NOSHOW, CHECKEDOUT = Value
  }

  class StatusCodeTypeRef extends TypeReference[GuestStayStatusCode.type]

}

case class GuestStay(
    @JsonScalaEnumeration(classOf[ActionTypeRef]) action: Action,
    property: String,
    interfaceType: String,
    remoteSystemName: String,
    confirmationCode: String,
    @JsonScalaEnumeration(classOf[StatusCodeTypeRef]) statusCode: Option[StatusCode] = None,
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
)


