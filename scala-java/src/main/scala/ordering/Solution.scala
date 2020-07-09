package ordering

import scalaz.Order
import scalaz.Scalaz._
import Mocks._
import scalaz.Order.order

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

    val intOrdering = order[Int] { (a, b) =>
      a ?|? b
    }
    val stringOrdering = order[String]{ (a, b) =>
      a ?|? b
    }
    val integers = List(2, 4, 52, 4, 5, 77, 12)
    println(integers.reduceOption(intOrdering.min))
    println(integers.sorted(intOrdering.toScalaOrdering).head)
    println(intOrdering(4, 1))
    println(stringOrdering("b", "a"))
    println(intOrdering(4, 1) |+| stringOrdering("a", "b"))

    tupleOfRanksOrder
    tOrder("a", "b")
    booleanOrder
    println(List(("a", "aaa"), ("b", "bbb")).sorted(emptyOrder[String](_ == "aaa").toScalaOrdering))
    tupleOrdering
  }

  def tupleOfRanksOrder: Unit = {
    assert(List(None, Some(2)) ?|? List(Some(2), None) == scalaz.Ordering.LT)
    assert(List[Option[Int]](Some(3), Some(3)) ?|? List[Option[Int]](Some(1), Some(3)) == scalaz.Ordering.GT)
    assert(List[Option[Int]](None, None) ?|? List[Option[Int]](None, None) == scalaz.Ordering.EQ)
    assert(List[Option[Int]](Some(1), Some(1)) ?|? List[Option[Int]](Some(1), Some(1)) == scalaz.Ordering.EQ)
    assert(List[Option[Int]](Some(1), Some(1)) ?|? List[Option[Int]](Some(2), Some(1)) == scalaz.Ordering.LT)
    assert(List[Option[Int]](Some(1), Some(1)) ?|? List[Option[Int]](Some(1), Some(2)) == scalaz.Ordering.LT)
  }

  def tupleOrdering: Unit = {
    val t1 = (1, Some(0))
    val t2 = (0, Some(1))
    val t3 = (1, None)
    val list1: List[(Int, Option[Int])] = List(t1, t2, t3)
    val list2: List[(Int, Option[Int])] = List(t2, t3)
    val list3: List[(Int, Option[Int])] = List(t3)
    val list4: List[(Int, Option[Int])] = List(t2)
    assert(list1 ?|? list2 == scalaz.Ordering.GT)
    assert(list3 ?|? list4 == scalaz.Ordering.GT)
  }

  def booleanOrder: Unit = {
    assert((true ?|? false) == scalaz.Ordering.GT)
    assert((false ?|? true) == scalaz.Ordering.LT)
    assert((true ?|? true) == scalaz.Ordering.EQ)
    assert((false ?|? false) == scalaz.Ordering.EQ)
  }

  def tOrder[T: Order](a: T, b: T): Unit = {
    println(a ?|? b)
  }

  def buildOptStringDateOrdering: Order[Option[String]] = { (a, b) =>
    a.getOrElse("") ?|? b.getOrElse("")
  }

  private def alwaysFalse[T](t: T) = false
  def emptyOrder[T: Order](emptyValue: T => Boolean = alwaysFalse _): Order[(String, T)] = {

    val naturalOrder = order[(String, T)]{ case ((_, t1), (_, t2)) => implicitly[Order[T]].order(t1, t2) }
    val nonEmpty = order[(String, T)] {
      case ((_, t1), (_, t2)) => Order[Boolean].order(emptyValue(t1), emptyValue(t2))
    }
    nonEmpty |+| naturalOrder
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
