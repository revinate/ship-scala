package com.revinate.ship.gueststay

import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.gueststay.Action._
import com.revinate.ship.gueststay.StatusCode._

case class GuestStay(@JsonScalaEnumeration(classOf[ActionType]) action: Action = null,
                     property: String = null,
                     interfaceType: String = null,
                     remoteSystemName: String = null,
                     confirmationCode: String = null,
                     @JsonScalaEnumeration(classOf[StatusCodeType]) statusCode: StatusCode = null
                    )
