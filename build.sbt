name := """securesocial-play2.3.2"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  jdbc,
  anorm,
  cache,
  ws,
  "ws.securesocial" %% "securesocial" % "master-SNAPSHOT"
)

resolvers ++= Seq(
  "Sonatype Snapshots" at "https://oss.sonatype.org/content/repositories/snapshots/",
  "Online Play Repository" at "http://repo.typesafe.com/typesafe/simple/maven-releases/",
  "Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/")