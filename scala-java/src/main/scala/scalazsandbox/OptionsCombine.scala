package scalazsandbox

import scalaz._
import Scalaz._

object OptionsCombine {

  def main(args: Array[String]): Unit = {
    val option1 = Some(1)
    val option2 = None
    println(option2.orElse(option1))
    println(option1.orElse(option2))

    assert(option2.orElse(option1) == option1.orElse(option2))
  }

}
