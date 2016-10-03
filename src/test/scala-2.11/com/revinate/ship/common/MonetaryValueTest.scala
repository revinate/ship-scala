package com.revinate.ship.common

import com.revinate.ship.implicits._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

class MonetaryValueTest extends PropSpec with TableDrivenPropertyChecks with Matchers {

  property("a full monetary value can add to monetary value without currency") {
    val examples = Table(
      ("left", "right"),
      (100.usd, MonetaryValue.zero),
      (50.usd, MonetaryValue(50))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(100.usd)
      right + left shouldEqual Some(100.usd)
    }
  }

  property("a full monetary value can add to monetary value with the same currency") {
    val examples = Table(
      ("left", "right"),
      (75.usd, 25.usd)
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(100.usd)
      right + left shouldEqual Some(100.usd)
    }
  }

  property("a full monetary value cannot add to monetary value with different currency") {
    val examples = Table(
      ("left", "right"),
      (75.usd, 25.eur)
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual None
      right + left shouldEqual None
    }
  }

  property("two zero monetary amount can add independent of the currency") {
    val examples = Table(
      ("left", "right", "expected"),
      (0.usd, 0.usd, 0.usd),
      (0.usd, 0.eur, MonetaryValue.zero)
    )

    forAll(examples) { (left, right, expected) =>
      left + right shouldEqual Some(expected)
      right + left shouldEqual Some(expected)
    }
  }

  property("a full monetary value can add to zero with any currency") {
    val examples = Table(
      ("left", "right"),
      (100.usd, 0.usd),
      (100.usd, 0.eur)
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(100.usd)
      right + left shouldEqual Some(100.usd)
    }
  }

  property("a monetary amount can multiply by a scalar") {
    val examples = Table(
      ("amount", "multiplier", "result"),
      (MonetaryValue(25, "USD"), 4, 100.usd),
      (MonetaryValue(25), 4, MonetaryValue(100)),
      (MonetaryValue.zero, 4, MonetaryValue.zero)
    )

    forAll(examples) { (amount, multiplier, result) =>
      amount * multiplier shouldEqual result
    }
  }
}