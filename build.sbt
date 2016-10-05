organization := "com.revinate"
name := "ship-scala"

version := "1.1.0-SNAPSHOT"

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

/* Maven Publishing */

publishMavenStyle := true

publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "content/repositories/snapshots")
  else
    Some("releases" at nexus + "service/local/staging/deploy/maven2")
}

credentials += Credentials(
  realm = "Sonatype Nexus Repository Manager",
  host = "oss.sonatype.org",
  userName = sys.env.getOrElse("SONATYPE_USER", ""),
  passwd = sys.env.getOrElse("SONATYPE_PASS", "")
)

pomExtra := (
  <url>https://github.com/revinate/ship-scala</url>
  <licenses>
    <license>
      <name>MIT License</name>
      <url>http://opensource.org/licenses/MIT</url>
      <distribution>repo</distribution>
    </license>
  </licenses>
  <developers>
    <developer>
      <id>revinate</id>
      <name>Revinate, Inc</name>
    </developer>
  </developers>
  <scm>
    <connection>scm:git:git://github.com/revinate/ship-scala.git</connection>
    <developerConnection>scm:git:ssh://git@github.com:revinate/ship-scala.git</developerConnection>
    <url>https://github.com/revinate/ship-scala</url>
  </scm>
)

pomIncludeRepository := { _ => false }

// for jar signing
pgpPassphrase := sys.env.get("PGP_PASSPHRASE").map(_.toCharArray)
