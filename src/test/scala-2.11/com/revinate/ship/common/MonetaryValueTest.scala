package com.revinate.ship.common

import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

class MonetaryValueTest extends PropSpec with TableDrivenPropertyChecks with Matchers {

  property("a full monetary value can add to monetary value without currency") {
    val examples = Table(
      ("left", "right"),
      (MonetaryValue(100, "USD"), MonetaryValue.empty),
      (MonetaryValue(100, "USD"), MonetaryValue.zero),
      (MonetaryValue(50, "USD"), MonetaryValue(50))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(MonetaryValue(100, "USD"))
      right + left shouldEqual Some(MonetaryValue(100, "USD"))
    }
  }

  property("a full monetary value can add to monetary value with the same currency") {
    val examples = Table(
      ("left", "right"),
      (MonetaryValue(75, "USD"), MonetaryValue(25, "USD")),
      (MonetaryValue(100, "USD"), MonetaryValue(value = None, currency = Some("USD")))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(MonetaryValue(100, "USD"))
      right + left shouldEqual Some(MonetaryValue(100, "USD"))
    }
  }

  property("a full monetary value cannot add to monetary value with different currency") {
    val examples = Table(
      ("left", "right"),
      (MonetaryValue(75, "USD"), MonetaryValue(25, "EUR"))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual None
      right + left shouldEqual None
    }
  }

  property("two zero monetary amount can add independent of the currency") {
    val examples = Table(
      ("left", "right", "expected"),
      (MonetaryValue(0, "USD"), MonetaryValue(0, "USD"), MonetaryValue(0, "USD")),
      (MonetaryValue(0, "USD"), MonetaryValue(0, "EUR"), MonetaryValue.zero)
    )

    forAll(examples) { (left, right, expected) =>
      left + right shouldEqual Some(expected)
      right + left shouldEqual Some(expected)
    }
  }

  property("a full monetary value can add to zero with any currency") {
    val examples = Table(
      ("left", "right"),
      (MonetaryValue(100, "USD"), MonetaryValue(0, "USD")),
      (MonetaryValue(100, "USD"), MonetaryValue(0, "EUR")),
      (MonetaryValue(100, "USD"), MonetaryValue(value = None, currency = Some("USD"))),
      (MonetaryValue(100, "USD"), MonetaryValue(value = None, currency = Some("EUR")))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(MonetaryValue(100, "USD"))
      right + left shouldEqual Some(MonetaryValue(100, "USD"))
    }
  }

  property("a monetary amount can multiply by a scalar") {
    val examples = Table(
      ("amount", "multiplier", "result"),
      (MonetaryValue(25, "USD"), 4, MonetaryValue(100, "USD")),
      (MonetaryValue(25), 4, MonetaryValue(100)),
      (MonetaryValue.zero, 4, MonetaryValue.zero),
      (MonetaryValue(None, None), 4, MonetaryValue(None, None))
    )

    forAll(examples) { (amount, multiplier, result) =>
      amount * multiplier shouldEqual result
    }
  }
}