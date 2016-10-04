package com.revinate.ship.gueststay

import java.time.LocalDate.{parse => parseDate}
import java.time.OffsetDateTime.{parse => parseDateTime}

import com.revinate.ship.JacksonSupport
import com.revinate.ship.common.TimeSpan.TimeSpanUnit
import com.revinate.ship.common.{MonetaryAmount, TimeSpan}
import com.revinate.ship.gueststay.GuestStay.{GuestStayAction, GuestStayStatusCode}
import com.revinate.ship.gueststay.Rate.RateTimeUnit
import com.revinate.ship.implicits._
import org.scalatest.{FreeSpec, Matchers}

class GuestStayTest extends FreeSpec with Matchers with JacksonSupport {

  "SHIP Guest Stay" - {
    "deserialization" - {
      "should contain all the mapped fields" in {
        val guestStay = loadToObject("/samples/guest-stays/guest-stay-full.json", classOf[GuestStay])

        guestStay should equal(
          GuestStay(
            action = GuestStayAction.ADD,
            property = "AVERTINE",
            interfaceType = "SHIP",
            remoteSystemName = "avertine_ship",
            confirmationCode = "38001",
            statusCode = Some(GuestStayStatusCode.RESERVED),
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
            purposeOfStay = Some("BUSINESS"),
            totalRoomRevenue = Some(MonetaryAmount(404.5.usd, 35.39.usd)),
            totalFoodAndBeverageRevenue = Some(MonetaryAmount(4.99.usd, 0.43.usd)),
            totalLuggageRevenue = Some(MonetaryAmount(49.99.usd, 4.37.usd)),
            totalOtherRevenue = Some(MonetaryAmount(11.25.usd, 0.98.usd)),
            totalRemainingBalance = Some(24.55.usd),
            totalDepositRequired = Some(24.55.usd),
            depositRequiredDate = Some(parseDate("2009-08-15")),
            ratePlans = Vector(
              RatePlan(
                rateCode = "SECRET_RATE",
                timeSpan = Some(TimeSpan(
                  startTime = parseDateTime("2007-07-19T00:00:00.000-07:00"),
                  timeUnits = 3,
                  timeUnitType = TimeSpan.TimeSpanUnit.DAY
                )),
                confidential = Some(true),
                rates = List(
                  Rate(100.usd, parseDateTime("2007-07-19T00:00:00.000-07:00"), 1, RateTimeUnit.DAY),
                  Rate(100.usd, parseDateTime("2007-07-20T00:00:00.000-07:00"), 1, RateTimeUnit.DAY),
                  Rate(99.5.usd, parseDateTime("2007-07-20T00:00:00.000-07:00"), 1, RateTimeUnit.DAY),
                  Rate(105.usd, parseDateTime("2007-07-21T00:00:00.000-07:00"), 1, RateTimeUnit.DAY)
                )
              )
            ),
            services = Vector(
              Service(
                inventoryCode = "SPA",
                rateCode = "STD_SPA",
                timeSpan = Some(TimeSpan(
                  startTime = parseDateTime("2007-07-19T23:00:00.000-07:00"),
                  timeUnits = 1,
                  timeUnitType = TimeSpanUnit.HOUR
                )),
                pricePerUnit = MonetaryAmount(25.9.usd, 2.27.usd),
                numberOfUnits = 2
              )
            )
          )
        )
      }
    }
  }
}
