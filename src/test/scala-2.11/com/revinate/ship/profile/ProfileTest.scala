package com.revinate.ship.profile

import java.time.LocalDate.{parse => parseDate}
import java.time.OffsetDateTime.{parse => parseDateTime}

import com.revinate.ship.JacksonSupport
import com.revinate.ship.common.CompanyInfo
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
          profileId = "28001",
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
          emailAddresses = List(
            EmailAddress("info@mailinator.com", true),
            EmailAddress("inactive@mailinator.com", false, Some(parseDateTime("2007-07-19T00:00:00.000-07:00")))
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
              primary = false
            )
          )
        )
      }
    }
  }
}
