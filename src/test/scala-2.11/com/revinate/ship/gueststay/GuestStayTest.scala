package com.revinate.ship.gueststay

import java.time.LocalDate.{parse => parseDate}
import java.time.OffsetDateTime.{parse => parseDateTime}

import com.revinate.ship.JacksonSupport
import com.revinate.ship.common.TimeSpan.TimeSpanUnit
import com.revinate.ship.common._
import com.revinate.ship.gueststay.GuestStay.{GuestStayAction, GuestStayStatusCode}
import com.revinate.ship.gueststay.Rate.RateTimeUnit
import com.revinate.ship.implicits._
import com.revinate.ship.profile.PhoneNumber.PhoneNumberType
import com.revinate.ship.profile.PostalAddress.AddressType
import com.revinate.ship.profile.Profile.{ProfileAction, ProfileGender, ProfileType}
import com.revinate.ship.profile._
import org.scalatest.{FreeSpec, Matchers}

class GuestStayTest extends FreeSpec with Matchers with JacksonSupport {

  "SHIP Guest Stay" - {
    "deserialization" - {
      "should contain all the mapped fields" in {
        val guestStay = loadToObject("/samples/guest-stays/guest-stay-full.json", classOf[GuestStay])

        guestStay shouldEqual GuestStay(
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
          ),
          profiles = Vector(
            Profile(
              action = ProfileAction.ADD,
              property = "AVERTINE",
              interfaceType = "SHIP",
              remoteSystemName = "avertine_ship",
              profileId = Some("28002"),
              profileType = ProfileType.GUEST,
              title = Some("Dr"),
              firstName = Some("Contact"),
              middleName = Some("J"),
              lastName = "Sample",
              gender = Some(ProfileGender.FEMALE),
              dateOfBirth = Some(parseDate("1975-07-18")),
              vipStatus = Some("2"),
              primaryLanguage = Some("EN"),
              companyInfo = Some(CompanyInfo("Avertine")),
              emailOptOut = Some(false),
              mailOptOut = Some(true),
              emailAddresses = Vector(
                EmailAddress("sample@mailinator.com", primary = true),
                EmailAddress("inactive@mailinator.com", primary = false, Some(parseDateTime("2007-07-19T00:00:00.000-07:00")))
              ),
              postalAddresses = Vector(
                PostalAddress(
                  address1 = Some("56574 Sample Street"),
                  address2 = Some("Unit 1234"),
                  city = Some("Ft Myers"),
                  state = Some("FL"),
                  country = Some("US"),
                  zipCode = Some("33967"),
                  addressType = AddressType.HOME,
                  primary = true
                ),
                PostalAddress(
                  address1 = Some("1500 Example Hwy"),
                  city = Some("Naples"),
                  country = Some("AG"),
                  zipCode = Some("32781"),
                  addressType = AddressType.BUSINESS,
                  primary = false,
                  inactiveDate = Some(parseDateTime("2007-07-19T00:00:00.000-07:00"))
                )
              ),
              phoneNumbers = Vector(
                PhoneNumber("412-555-1234", PhoneNumberType.BUSINESS, primary = true),
                PhoneNumber("1-239-555-1111", PhoneNumberType.MOBILE, primary = false, Some(parseDateTime("2007-07-19T00:00:00.000-07:00")))
              ),
              memberships = Vector(
                Membership(
                  loyaltyNumber = "213213213213213213",
                  programCode = Some("PC"),
                  levelCode = Some("GOLD"),
                  expireDate = Some(parseDate("2010-12-31"))
                )
              ),
              creditCards = Vector(
                CreditCard(
                  creditCardLast4 = Some("1234"),
                  creditCardExpirationDate = Some(parseDate("2015-06-30")),
                  creditCardType = Some("AX"),
                  primary = true
                ),
                CreditCard(
                  creditCardLast4 = Some("8888"),
                  creditCardExpirationDate = Some(parseDate("2016-01-31")),
                  creditCardType = Some("VA"),
                  primary = false
                ),
                CreditCard(
                  creditCardLast4 = Some("5555"),
                  creditCardExpirationDate = Some(parseDate("2018-10-31")),
                  creditCardType = Some("MC"),
                  primary = false
                )
              ),
              guestNotes = Vector(
                GuestNote(
                  title = Some("Profile Background Note"),
                  text = "Profile Background Note Text",
                  `type` = Some("Profile Background Notes"),
                  time = Some(parseDateTime("2014-01-16T11:30:38.000-08:00"))
                ),
                GuestNote(
                  title = Some("General Note"),
                  text = "General Note Text",
                  `type` = Some("General Notes"),
                  time = Some(parseDateTime("2014-01-16T11:30:38.000-08:00"))
                )
              )
            ),
            Profile(
              action = ProfileAction.ADD,
              property = "AVERTINE",
              interfaceType = "SHIP",
              remoteSystemName = "avertine_ship",
              profileId = Some("314"),
              profileType = ProfileType.TRAVEL,
              lastName = "Avertine Travel",
              emailAddresses = Vector(EmailAddress("travel@mailinator.com", primary = true)),
              postalAddresses = Vector(
                PostalAddress(
                  address1 = Some("1234 Jones St"),
                  address2 = Some("Suite C1"),
                  city = Some("San Francisco"),
                  state = Some("CA"),
                  country = Some("US"),
                  zipCode = Some("94108"),
                  addressType = AddressType.BUSINESS,
                  primary = true
                )
              ),
              phoneNumbers = Vector(
                PhoneNumber("555-555-1234", PhoneNumberType.BUSINESS, primary = true)
              )
            )
          ),
          guestNotes = Vector(
            GuestNote(
              title = Some("Background Note"),
              text = "Global Background Note",
              `type` = Some("Background Notes"),
              time = Some(parseDateTime("2014-01-16T11:30:38.000-08:00"))
            ),
            GuestNote(
              text = "RATE_FIXED=YES",
              `type` = Some("SYSTEM"),
              time = Some(parseDateTime("2009-08-15T16:52:40.000-07:00"))
            ),
            GuestNote(
              text = "DISCOUNT_PERCENTAGE=0",
              `type` = Some("SYSTEM"),
              time = Some(parseDateTime("2009-08-15T16:52:40.000-07:00"))
            )
          ),
          pmsDefinedFields = Vector(
            UserDefinedField("UDF01", "A"),
            UserDefinedField("UDF02", "1")
          ),
          propertyDefinedFields = Vector(
            UserDefinedField("PUDF01", "A1"),
            UserDefinedField("PUDF02", "1.3")
          )
        )
      }
    }
  }
}
