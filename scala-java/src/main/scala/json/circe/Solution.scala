package json.rapture

import io.circe._, io.circe.generic.auto._, io.circe.parser._, io.circe.syntax._

object Solution {

  def main(args: Array[String]): Unit = {
    val house = House(3, Address(222, "st-laurent"))
    println(house.asJson)
  }
}
case class Address(civicNb: Int, street: String)
case class House(nbFloors: Int, address: Address)