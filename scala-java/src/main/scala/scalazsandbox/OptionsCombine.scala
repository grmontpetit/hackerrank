package scalazsandbox

import scalaz.{Applicative, Monoid}
import scalaz.Scalaz._

object OptionsCombine {

  def main(args: Array[String]): Unit = {
    val option1 = Some(1)
    val option2 = none[Int]
    println(option2.orElse(option1))
    println(option1.orElse(option2))
    assert(option2.orElse(option1) == option1.orElse(option2))

    println(option2.orElse(option1).orZero)

    val x = none
    val y = 1.some
    println(s"result = ${x <* y}")
  }

  implicit def IntMonoid: Monoid[Option[Int]] = new Monoid[Option[Int]] {
    override def zero: Option[Int] = none
    override def append(f1: Option[Int], f2: => Option[Int]): Option[Int] = {
      f1.map(x => f2.fold(x){y => x + y})
    }
  }


}
