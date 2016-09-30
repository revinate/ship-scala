package com.revinate.ship.gueststay

import com.revinate.ship.JacksonSupport
import org.scalatest.{FreeSpec, Matchers}

class GuestStayTest extends FreeSpec with Matchers with JacksonSupport {

  "SHIP Guest Stay" - {
    "deserialization" - {
      "should handle root level fields" in {
        val guestStay = loadToObject("/samples/guest-stays/guest-stay-full.json", classOf[GuestStay])

        guestStay should equal(GuestStay(
          action = Action.ADD,
          property = "AVERTINE",
          interfaceType = "SHIP",
          remoteSystemName = "avertine_ship",
          confirmationCode = "38001",
          statusCode = StatusCode.RESERVED
        ))
      }
    }
  }
}
