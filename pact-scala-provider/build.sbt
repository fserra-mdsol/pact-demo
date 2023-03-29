import Dependencies._
import Libraries._

ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.10"

lazy val root = (project in file("."))
  .settings(
    name := "pact-scala-provider",
    libraryDependencies ++= Seq(
      circeCore,
      http4sDsl,
      http4sServer,
      http4sClient,
      http4sCirce,
      derevoCirce,
      derevoCore,
      pact4sMunitCatsEffect,
      munitCats
    )
  )
