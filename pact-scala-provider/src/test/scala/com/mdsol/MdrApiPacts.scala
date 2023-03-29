package com.mdsol

import cats.effect.IO
import com.comcast.ip4s.{Host, Port}
import munit.CatsEffectSuite
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.server.Server
import pact4s.munit.PactVerifier
import pact4s.provider.{PactSource, ProviderInfoBuilder, ProviderTags, PublishVerificationResults}
import MdrApi._

import scala.concurrent.duration.DurationInt

class MdrApiPacts extends CatsEffectSuite with PactVerifier {
  val server: Fixture[Server] = ResourceSuiteLocalFixture(
    "provider-server",
    EmberServerBuilder
      .default[IO]
      .withHost(Host.fromString("localhost").get)
      .withPort(Port.fromInt(1235).get)
      .withHttpApp(heroService)
      .build
  )

  override def munitFixtures: Seq[Fixture[_]] = Seq(server)

  val provider: ProviderInfoBuilder = ProviderInfoBuilder(
    name = "mdr-api",

    PactSource.PactBrokerWithSelectors("http://localhost")
              .withInsecureTLS(true)
  ).withHost("localhost")
   .withPort(1235)


  test("Verify pacts") {
    verifyPacts(
      publishVerificationResults = Some(
        PublishVerificationResults(
          // Normally this would be a version supplied by the build system, e.g. the Git commit hash, or a semantic version
          // like "1.0.0". See: https://docs.pact.io/getting_started/versioning_in_the_pact_broker
          providerVersion = "SNAPSHOT",
          // Normally this would be the git branch, e.g. "main" or "master"
          // See: https://docs.pact.io/pact_broker/tags/
          providerTags = ProviderTags("master")
        )
      ),
      providerVerificationOptions = Nil,
      verificationTimeout = Some(10.seconds)
    )
  }
}
