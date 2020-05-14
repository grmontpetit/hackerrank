package ordering

import scalaz.Order.order
import scalaz.Scalaz._

object Solution {

  def main(args: Array[String]): Unit = {
    val anOrder = order[Item] {case (item1, item2) => item1.content ?|? item2.content}
    val expected = Item(2, Some("content"))
    val list = List(Item(1), Item(2, Some("content")), Item(3))
    implicit val ordering: scala.math.Ordering[Item] = anOrder.toScalaOrdering

    assert(list.max == expected, s"got ${list.last} want $expected")
  }

}

case class Item(nb: Int, content: Option[String] = None)
