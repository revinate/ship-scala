package com.revinate.ship.profile

import java.time.LocalDate.{parse => parseDate}

import com.revinate.ship.JacksonSupport
import com.revinate.ship.common.CompanyInfo
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
          mailOptOut = Some(true)
        )
      }
    }
  }
}
