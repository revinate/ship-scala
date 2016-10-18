package com.revinate.ship.profile

import java.time.LocalDate.{parse => parseDate}
import java.time.OffsetDateTime.{parse => parseDateTime}

import com.revinate.ship.JacksonSupport
import com.revinate.ship.common.{CompanyInfo, GuestNote}
import com.revinate.ship.profile.PhoneNumber.PhoneNumberType
import com.revinate.ship.profile.PostalAddress.AddressType
import com.revinate.ship.profile.Profile.{ProfileAction, ProfileGender, ProfileType}
import org.scalatest.{FreeSpec, Matchers}

class ProfileTest extends FreeSpec with Matchers with JacksonSupport {

  "SHIP profile" - {
    "deserialization" - {
      "should contain all the mapped fields" in {
        val profile = loadToObject("/samples/profiles/profile-full.json", classOf[Profile])

        profile shouldEqual Profile(
          action = ProfileAction.ADD,
          property = "AVERTINE",
          interfaceType = "SHIP",
          remoteSystemName = "avertine_ship",
          profileId = Some("28001"),
          profileType = ProfileType.GUEST,
          title = Some("Mr."),
          firstName = Some("John"),
          middleName = Some("Clancy"),
          lastName = "Smith",
          gender = Some(ProfileGender.MALE),
          dateOfBirth = Some(parseDate("1989-02-01")),
          vipStatus = Some("2"),
          primaryLanguage = Some("EN"),
          companyInfo = Some(CompanyInfo("Avertine")),
          emailOptOut = Some(false),
          mailOptOut = Some(true),
          emailAddresses = Vector(
            EmailAddress("info@mailinator.com", primary = true),
            EmailAddress("inactive@mailinator.com", primary = false, Some(parseDateTime("2007-07-19T00:00:00.000-07:00")))
          ),
          postalAddresses = Vector(
            PostalAddress(
              address1 = Some("1234 Jones St"),
              address2 = Some("2nd Floor"),
              city = Some("San Francisco"),
              state = Some("CA"),
              country = Some("US"),
              zipCode = Some("94108"),
              addressType = AddressType.HOME,
              primary = true
            ),
            PostalAddress(
              address1 = Some("2640 Parkway De la Puerta De oro"),
              address2 = Some("Habitación 315"),
              city = Some("Nápoles"),
              state = Some("FL"),
              country = Some("US"),
              zipCode = Some("34105"),
              addressType = AddressType.BUSINESS,
              primary = false,
              inactiveDate = Some(parseDateTime("2007-07-19T00:00:00.000-07:00"))
            )
          ),
          phoneNumbers = Vector(
            PhoneNumber("555-123-4567", PhoneNumberType.BUSINESS, primary = true),
            PhoneNumber("415-555-1234", PhoneNumberType.HOME, primary = false, Some(parseDateTime("2007-07-19T00:00:00.000-07:00")))
          ),
          memberships = Vector(
            Membership("4800123", Some("ENCORE")),
            Membership("66760000", Some("OCIS"), Some("GOLD"), Some(parseDate("2010-12-31")))
          ),
          creditCards = Vector(
            CreditCard(
              creditCardLast4 = Some("1234"),
              creditCardExpirationDate = Some(parseDate("2015-12-31")),
              creditCardType = Some("AX"),
              primary = true
            ),
            CreditCard(
              creditCardExpirationDate = Some(parseDate("2012-12-31")),
              creditCardType = Some("MC"),
              primary = false
            ),
            CreditCard(
              creditCardExpirationDate = Some(parseDate("2013-04-30")),
              creditCardType = Some("VA"),
              primary = false
            )
          ),
          guestNotes = Vector(
            GuestNote(
              title = Some("Profile Background Note"),
              text = "Profile Background Note Text",
              `type` = Some("Profile Background Notes"),
              time = Some(parseDateTime("2013-05-02T16:55:48.000-07:00"))
            ),
            GuestNote(
              title = Some("General Note"),
              text = "General Note Text",
              `type` = Some("General Notes"),
              time = Some(parseDateTime("2013-05-02T16:55:57.000-07:00"))
            )
          )
        )
      }

      "should handle minimal version" in {
        val profile = loadToObject("/samples/profiles/profile-required-fields-only.json", classOf[Profile])

        profile shouldEqual Profile(
          action = ProfileAction.ADD,
          property = "AVERTINE",
          interfaceType = "SHIP",
          remoteSystemName = "avertine_ship",
          profileType = ProfileType.GUEST,
          lastName = "Smith"
        )
      }
    }
  }
}
