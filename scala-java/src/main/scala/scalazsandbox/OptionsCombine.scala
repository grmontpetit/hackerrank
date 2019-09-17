package scalazsandbox

import scalaz.Order.order
import scalaz.Scalaz._
import scalaz.{Order, _}

object OptionsCombine {

  def main(args: Array[String]): Unit = {
    val option1 = Some(1)
    val option2 = none[Int]
    //println(option2.orElse(option1))
    //println(option1.orElse(option2))
    assert(option2.orElse(option1) == option1.orElse(option2))

    //println(option2.orElse(option1).orZero)

    val x: Option[Int] = 1.some
    val xx: Option[Int] = 2.some
    val y: Option[Int] = none

    println(s"result1 = ${x *> y}")
    println(s"result2 = ${y <* x}")
    println(s"result3 = ${x <* xx}")
    println(s"result4 = ${y <* y}")

  }

//  implicit def IntMonoid: Monoid[Option[Int]] = new Monoid[Option[Int]] {
//    override def zero: Option[Int] = none
//    override def append(f1: Option[Int], f2: => Option[Int]): Option[Int] = {
//      f1.map(x => f2.fold(x){y => x + y})
//    }
//  }

}
