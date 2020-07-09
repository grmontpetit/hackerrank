package typeclasses.id

trait HasId[A] {
  def getId(a: A): Int
}

final case class User(id: Int, name: String)
final case class House(civicNb: Int, street: String)

object Main {
  implicit val userHasId: HasId[User] = (user: User) => user.id
  implicit val houseHasId: HasId[House] = (house: House) => house.civicNb

  def main(args: Array[String]): Unit = {
    val u = User(1, "1")
    val h = House(2, "2")
    printId(u)
    printId(h)
  }

  def printId[A](a: A)(implicit hia: HasId[A]): Unit = println(s"Found id ${hia.getId(a)}")
}
