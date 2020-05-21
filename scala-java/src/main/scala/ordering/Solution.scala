package ordering

import scalaz.Order
import scalaz.Scalaz._
import Mocks._

object Solution {

  def main(args: Array[String]): Unit = {
//    val anOrder = order[Item] {case (item1, item2) => item1.content ?|? item2.content}
//    val expected = Item(2, Some("content"))
//    val list = List(Item(1), Item(2, Some("content")), Item(3), Item(4, Some("content")))
//    implicit val ordering: scala.math.Ordering[Item] = anOrder.toScalaOrdering
//
//    assert(list.max == expected, s"got ${list.last} want $expected")
//
//    println(list.groupBy(_.content))

    implicit val optStringDateOrdering: Ordering[Option[String]] = buildOptStringDateOrdering.toScalaOrdering
    println(allOptions.sorted)
    assert(allOptions.max.contains(s1), s"got ${allOptions.max} want ${Some(s1)}")
  }

  def buildOptStringDateOrdering: Order[Option[String]] = { (a, b) =>
    a.getOrElse("") ?|? b.getOrElse("")
  }

}

object Mocks {
  val s1 = "2015-09-26"
  val s2 = "2013-10-20"
  assert(s1 > s2)

  val s3 = "2015-09-25"
  val s4 = "2013"
  assert(s3 > s4)

  val s5 = "2011-09-26"
  val s6 = "2013"
  assert(s5 < s6)

  val s7 = "2011"
  val s8 = "2013"
  assert(s7 < s8)

  val s9 = "2015-05"
  val s10 = "2015-05-01"
  assert(s9 < s10, s"got $s9 want $s10")

  val all = Seq(s1, s2, s3, s4, s5, s6, s7, s8)
  private val result = all.sorted
  private val expected = Seq(s7, s5, s8, s6, s4, s2, s3, s1)
  assert(result == expected, s"got $result want $expected")

  private val greater = all.max
  assert(greater == s1, s"got $greater want ${result.last}")
  val allOptions: Seq[Option[String]] =
    Seq(Some(s1), Some(s2), Some(s3), Some(s4), Some(s5), Some(s6), Some(s7), Some(s8), None, Some(s9), Some(s10))
}
case class Item(nb: Int, content: Option[String] = None)
