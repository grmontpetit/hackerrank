package `try`

import scala.util.{Success, Try}

object Solution {
  def main(args: Array[String]): Unit = {
    val s = "http://www.earth-syst-dynam-discuss.net/esd-2016-11/"
    val f = Try(new java.net.URL(s))
    f match {
      case Success(v) => println(v)
      case _ => println("failure")
    }
  }
}
