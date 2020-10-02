package scalautiltry

import scala.util.{Failure, Success, Try}

object Solution {
  def main(args: Array[String]): Unit = {
    fetchUrlSuccess
    forceErrorTry
  }

  def forceErrorTry: Unit = {
    val tryError: Try[String] = Try(throw new Exception("This is an exception."))
    tryError match {
      case Success(v) => println(v)
      case Failure(exception) => println(s"there was an exception: $exception")
      case _ => println("default match")
    }
  }

  def fetchUrlSuccess: Unit = {
    val s = "http://www.earth-syst-dynam-discuss.net/esd-2016-11/"
    val f = Try(new java.net.URL(s))
    f match {
      case Success(v) => println(v)
      case _ => println("failure")
    }
  }
}
