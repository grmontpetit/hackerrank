package typeclasses.animal

object Main {
  import Extender._
  def main(args: Array[String]): Unit = {
    val dog = Dog("Rex")
    val cat = Cat("Tom")
    println(dog.animalType)
    println(cat.animalType)
  }
}

trait Animal {
  def name: String
}

case class Dog(name: String) extends Animal
case class Cat(name: String) extends Animal

trait Extender[A] {
  def animalType(a: A): String
}

object Extender {

  implicit class ExtenderOps[A](val a: A) extends AnyVal {
    def animalType(implicit ex: Extender[A]): String = ex.animalType(a)
  }

  def apply[A](implicit extender: Extender[A]): Extender[A] = extender
  def animalType[A: Extender](a: A): String = Extender.apply[A].animalType(a)

  implicit val dogAnimalType: Extender[Dog] = (dog: Dog) => s"${dog.name} is a dog which is a mammal"
  implicit val catAnimalType: Extender[Cat] = (cat: Cat) => s"${cat.name} is a cat which is a mammal"

}