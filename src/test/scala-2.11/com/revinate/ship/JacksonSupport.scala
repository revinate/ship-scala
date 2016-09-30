package com.revinate.ship

trait JacksonSupport {

  def loadFile(name: String) = getClass.getResourceAsStream(name)

  def loadToObject[T](name: String, clazz: Class[T]): T = objectMapper.readValue(loadFile(name), clazz)
}
