package com.revinate.ship.gueststay

import com.fasterxml.jackson.module.scala.JsonScalaEnumeration
import com.revinate.ship.gueststay.Action.Action

case class GuestStay(@JsonScalaEnumeration(classOf[ActionType]) action: Action = null,
                     property: String = null,
                     interfaceType: String = null,
                     remoteSystemName: String = null
                    )
