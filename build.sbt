name := "ship-scala"

version := "1.0"

scalaVersion := "2.11.8"

val jacksonVersion = "2.8.1"
val scalaTestVersion = "3.0.0"

libraryDependencies += "org.scalatest" % "scalatest_2.11" % scalaTestVersion % Test
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonVersion % Test
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % jacksonVersion

logBuffered in Test := false
