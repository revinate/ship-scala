package com.revinate.ship.gueststay

import java.time.{LocalDate, OffsetDateTime}

import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.gueststay.Action._
import com.revinate.ship.gueststay.StatusCode._

case class GuestStay(
    @JsonScalaEnumeration(classOf[ActionType]) action: Action,
    property: String,
    interfaceType: String,
    remoteSystemName: String,
    confirmationCode: String,
    @JsonScalaEnumeration(classOf[StatusCodeType]) statusCode: Option[StatusCode] = None,
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
    checkinDate: Option[LocalDate] = None ,
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
    purposeOfStay: Option[String] = None
)


