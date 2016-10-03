package com.revinate.ship.common

import com.revinate.ship.implicits._
import org.scalatest.prop.TableDrivenPropertyChecks
import org.scalatest.{Matchers, PropSpec}

class MonetaryAmountTest extends PropSpec with TableDrivenPropertyChecks with Matchers {

  property("a monetary amount can add to another monetary amount") {
    val examples = Table(
      ("left", "right"),
      (MonetaryAmount(150.usd, 10.usd), MonetaryAmount(100.usd, 15.usd)),
      (MonetaryAmount(200.usd, 25.usd), MonetaryAmount(50.usd, 0.usd)),
      (MonetaryAmount(250.usd, 25.usd), MonetaryAmount(MonetaryValue.zero, MonetaryValue.zero))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(MonetaryAmount(250.usd, 25.usd))
      right + left shouldEqual Some(MonetaryAmount(250.usd, 25.usd))
    }
  }

  property("a monetary amount can add to zeroes in any currency") {
    val examples = Table(
      ("left", "right"),
      (MonetaryAmount(250.usd, 25.usd), MonetaryAmount(0.eur, 0.eur)),
      (MonetaryAmount(MonetaryValue.zero, 25.usd), MonetaryAmount(250.usd, MonetaryValue.zero))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual Some(MonetaryAmount(250.usd, 25.usd))
      right + left shouldEqual Some(MonetaryAmount(250.usd, 25.usd))
    }
  }

  property("a monetary amount cannot add to another non-zero value with different currencies") {
    val examples = Table(
      ("left", "right"),
      (MonetaryAmount(150.usd, 10.usd), MonetaryAmount(100.eur, 15.eur)),
      (MonetaryAmount(200.usd, 25.usd), MonetaryAmount(50.eur, 0.eur)),
      (MonetaryAmount(240.usd, 25.usd), MonetaryAmount(10.eur, MonetaryValue.zero))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual None
      right + left shouldEqual None
    }
  }

  property("a monetary amount cannot add to another monetary amount and generate parts in different currencies") {
    val examples = Table(
      ("left", "right"),
      (MonetaryAmount(0.usd, 25.usd), MonetaryAmount(250.eur, 0.eur))
    )

    forAll(examples) { (left, right) =>
      left + right shouldEqual None
      right + left shouldEqual None
    }
  }

  property("a monetary amount can be multiplied by a scalar") {
    val examples = Table(
      ("amount", "multiplier", "result"),
      (MonetaryAmount(10.usd, 0.5.usd), BigDecimal(5), MonetaryAmount(50.usd, 2.5.usd))
    )

    forAll(examples) { (amount, multiplier, result) =>
      amount * multiplier shouldEqual result
    }
  }
}
