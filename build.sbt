organization := "com.revinate"
name := "ship-scala"

version := "1.1.0"

scalaVersion := "2.11.8"

val jacksonVersion = "2.8.1"
val scalaTestVersion = "3.0.0"

libraryDependencies += "com.fasterxml.jackson.core" % "jackson-annotations" % jacksonVersion % Provided
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-core" % jacksonVersion % Provided
libraryDependencies += "com.fasterxml.jackson.core" % "jackson-databind" % jacksonVersion % Provided
libraryDependencies += "com.fasterxml.jackson.module" % "jackson-module-scala_2.11" % jacksonVersion % Provided
libraryDependencies += "com.fasterxml.jackson.datatype" % "jackson-datatype-jsr310" % jacksonVersion % Provided

libraryDependencies += "org.scalatest" % "scalatest_2.11" % scalaTestVersion % Test

logBuffered in Test := false

/* Scalastyle config */

// this config is necessary for sbt 0.13 because the source folders include src/main/scala-2.11
scalastyleSources := (sourceDirectories in Compile).value
(scalastyleSources in Test) := (sourceDirectories in Test).value

// run scalastyle as part of the compilation - the config is here http://www.scalastyle.org/sbt.html
lazy val compileScalastyle = taskKey[Unit]("compileScalastyle")
compileScalastyle := org.scalastyle.sbt.ScalastylePlugin.scalastyle.in(Compile).toTask("").value
(compile in Compile) <<= (compile in Compile) dependsOn compileScalastyle

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
