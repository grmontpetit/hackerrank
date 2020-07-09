package typeclasses

import typeclasses.Show.{whatIsType, _}

object Main {

  def main(args: Array[String]): Unit = {
    // prints: long 2
    println(2L.show)

    // prints: class java.lang.Long
    println(whatIsType(2L))

  }

}

trait Show[A] {
  def show(a: A): String
}

object Show {

  implicit class ShowOps[A](val a: A) extends AnyVal {
    def show(implicit sh: Show[A]): String = sh.show(a)
  }

  def apply[A](implicit sh: Show[A]): Show[A] = sh
  def show[A: Show](a: A): String = Show.apply[A].show(a)

  implicit val intCanShow: Show[Int] = (int: Int) => s"int $int"
  implicit val floatCanShow: Show[Float] = (float: Float) => s"float $float"
  implicit val doubleCanShow: Show[Double] = (double: Double) => s"double $double"
  implicit val longCanShow: Show[Long] = (long: Long) => s"long $long"

  def whatIsType[A: Show](a: A): String = a.getClass.toString
}
