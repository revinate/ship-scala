package com.revinate.ship.gueststay

import java.time.LocalDate.{parse => parseDate}
import java.time.OffsetDateTime.{parse => parseDateTime}

import com.revinate.ship.JacksonSupport
import org.scalatest.{FreeSpec, Matchers}

class GuestStayTest extends FreeSpec with Matchers with JacksonSupport {

  "SHIP Guest Stay" - {
    "deserialization" - {
      "should contain all the mapped fields" in {
        val guestStay = loadToObject("/samples/guest-stays/guest-stay-full.json", classOf[GuestStay])

        guestStay should equal(
          GuestStay(
            action = Action.ADD,
            property = "AVERTINE",
            interfaceType = "SHIP",
            remoteSystemName = "avertine_ship",
            confirmationCode = "38001",
            statusCode = Some(StatusCode.RESERVED),
            guaranteeCode = Some("CC"),
            lastUpdatedAt = Some(parseDateTime("2009-08-15T16:52:36.000-07:00")),
            lastUpdatedBy = Some("System Agent"),
            bookingNumber = Some("CRO-123A"),
            bookingDate = Some(parseDateTime("2009-08-15T16:52:36.000-07:00")),
            bookedBy = Some("Sample User"),
            reservationSource = Some("COD"),
            cancellationNumber = Some("12345"),
            cancellationDate = Some(parseDateTime("2009-08-15T16:52:37.000-07:00")),
            canceledBy = Some("Sample User 2"),
            checkinDate = Some(parseDate("2007-07-19")),
            actualCheckinDate = Some(parseDateTime("2007-07-19T15:23:00.000-07:00")),
            checkedInBy = Some("Ms. Green"),
            checkoutDate = Some(parseDate("2007-07-22")),
            actualCheckoutDate = Some(parseDateTime("2007-07-22T08:10:40.000-07:00")),
            checkedOutBy = Some("AJP"),
            stayLength = Some(StayLength(3)),
            numberOfAdults = Some(1),
            numberOfChildren = Some(0),
            roomNumber = Some("123"),
            roomType = Some("KING"),
            roomTypeChargeCode = Some("KING"),
            blockCode = Some("KING-Block"),
            numberOfRooms = Some(1),
            market = Some("ALL"),
            purposeOfStay = Some("BUSINESS")
          )
        )
      }
    }
  }
}
