package json.circe.genericparser

import io.circe.Decoder
import io.circe.parser.decode

object Main {
  import CustomDecoder._
  def main(args: Array[String]): Unit = {
    val jsonBook = """{"subject": "Quantum Gravity Unification with General Relativity", "nbPages": 12}"""
    val result = CustomDecoder.decodeOpt[Book](Some(jsonBook))
    println(result)
  }

  def printGenericName[T](a: T): Unit = {
    println(a.getClass)
  }
}

object CustomDecoder {
  implicit val decodeBook: Decoder[Book] =
    Decoder.forProduct2("subject", "nbPages")(Book.apply)
  implicit val decodeBottle: Decoder[Bottle] =
    Decoder.forProduct2("content", "quantity")(Bottle.apply)
  def decodeOpt[A: Decoder](input: Option[String]): Either[Exception, A] =
    input.fold[Either[Exception, A]](Left(new Exception("Cannot parse.")))(a => decode[A](a))
}

case class Bottle(content: String, quantity: Int)
case class Book(subject: String, nbPages: Int)
