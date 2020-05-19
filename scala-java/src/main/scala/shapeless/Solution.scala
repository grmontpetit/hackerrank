package shapeless

object Solution {
  def main(args: Array[String]): Unit = {
    val gen = Generic[UserWithAge]
    val u = UserWithAge("Julien", 30)

    val h = gen.to(u) // returns Julien :: 30 :: HNil
    val j: UserWithAge = gen.from(h) // return UserWithAge("Julien", 30)
  }
}

case class UserWithAge(name: String, age: Int)
