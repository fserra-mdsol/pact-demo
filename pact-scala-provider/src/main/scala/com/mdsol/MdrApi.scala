package com.mdsol

import cats.effect._
import com.comcast.ip4s._
import org.http4s.HttpRoutes
import org.http4s.dsl.io._
import org.http4s.implicits._
import org.http4s.ember.server._
import org.http4s.circe.CirceEntityEncoder._
import org.http4s.circe.CirceEntityDecoder._
import domain._


object MdrApi extends IOApp {


  val heroService = HttpRoutes.of[IO] {
    case GET -> Root / "heroes" / IntVar(id) =>
      if (id == 42)
        Ok(superman)
      else
        NotFound(s"didn't get 42 but $id")

    case req @ POST -> Root / "heroes" =>
      for {
        hero <- req.as[RequestHero]
        resp <- Created(Hero(
          if (hero.name == "Superman") 42 else 1,
          hero.name,
          hero.superpower,
          hero.universe
        ))
      } yield resp
  }.orNotFound

  def run(args: List[String]): IO[ExitCode] =
    EmberServerBuilder
      .default[IO]
      .withHost(ipv4"0.0.0.0")
      .withPort(port"8080")
      .withHttpApp(heroService)
      .build
      .use(_ => IO.never)
      .as(ExitCode.Success)
}
