import sbt._

object Dependencies {
  object V {
    val http4s = "0.23.13"
    val derevo = "0.12.6"
    val circe = "0.14.1"
    val pact4s = "0.9.0"
    val munit = "1.0.7"

  }

  def http4s(artifact: String): ModuleID = "org.http4s"      %% s"http4s-$artifact"   % V.http4s
  def derevo(artifact: String): ModuleID = "tf.tofu"         %% s"derevo-$artifact"   % V.derevo
  def circe(artifact: String): ModuleID = "io.circe"         %% s"circe-$artifact"    % V.circe
  def pact4s(artifact: String): ModuleID = "io.github.jbwheatley" %% s"pact4s-$artifact" % V.pact4s

  object Libraries {
    val circeCore = circe("core")
    val http4sDsl = http4s("dsl")
    val http4sServer = http4s("ember-server")
    val http4sClient = http4s("ember-client")
    val http4sCirce = http4s("circe")
    val derevoCirce = derevo("circe-magnolia")
    val derevoCore = derevo("core")

    val pact4sMunitCatsEffect = pact4s("munit-cats-effect")
    val pact4sCirce = pact4s("circe")

    val munitCats = "org.typelevel"             %% "munit-cats-effect-3"     % V.munit
  }

}
