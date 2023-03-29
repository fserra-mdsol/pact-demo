package com.mdsol
import cats.effect.IO
import io.circe.{Decoder, Encoder}
import org.http4s.circe.{jsonEncoderOf, jsonOf}

object domain {

  case class Hero(
    id:         Int,
    name:       String,
    superpower: String,
    universe:   String
  )
  object Hero {
    implicit val decoder: Decoder[Hero] = Decoder.forProduct4("id","name", "superpower","universe")(Hero.apply)
    implicit val heroDecoder = jsonOf[IO, Hero]

    implicit val encoder: Encoder[Hero] = Encoder.forProduct4("id","name", "superpower","universe")(h =>
      (h.id,h.name,h.superpower,h.universe)
    )
    implicit val heroEncoder = jsonEncoderOf[IO,Hero]
  }

  case class RequestHero(
    name:       String,
    superpower: String,
    universe:   String
  )
  object RequestHero {
    implicit val decoder: Decoder[RequestHero] = Decoder.forProduct3("name", "superpower", "universe")(RequestHero.apply)
    implicit val requestHeroDecoder = jsonOf[IO, RequestHero]

    implicit val encoder: Encoder[RequestHero] = Encoder.forProduct3("name", "superpower", "universe")(h =>
      (h.name, h.superpower, h.universe)
    )
    implicit val requestHeroEncoder = jsonEncoderOf[IO, RequestHero]
  }

  val superman = Hero(
    42,
    "Superman",
    "flying",
    "DC"
  )
}
