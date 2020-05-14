package typebounds

object Solution {

  def main(args: Array[String]): Unit = {
    val person = Cat("Cat")
    println(animalBound(person))
  }

  def animalBound[A <: Animal](a: A): Option[String] = a match {
    case Dog(n) => Some(n)
    case Cat(n) => Some(n)
    case _ => None
  }
}

sealed trait Animal
case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal

sealed trait Human
case class Person(name: String) extends Human