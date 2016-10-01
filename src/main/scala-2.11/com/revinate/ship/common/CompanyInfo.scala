package com.revinate.ship.common

object CompanyInfo {
  def apply(name: String) = new CompanyInfo(Option(name))
}

case class CompanyInfo(name: Option[String])
