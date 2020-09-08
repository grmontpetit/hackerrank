package recursion

import scala.annotation.tailrec

object Solution extends App {

  type GroupKey = String

  val x = Stats("a", "a", Some("a"), Some(List(Stats("a", "a", Some("a")), Stats("b", "b", Some("b")))))
  val res = countSameGroupKeys(x.children.getOrElse(List.empty), Map(x.gk.get -> 1))
  println(res.toSeq.sortWith(_._2 > _._2))
  val m = Map("key" -> List(("gk", "id")))
  m.to



  def isSha1(gk: String): Boolean = {
    gk.matches("^[a-fA-F0-9]{40}$")
  }

  def countSameGroupKeys(stats: List[Stats], acc: Map[GroupKey, Int]): Map[String, Int] = stats match {
    case head :: tail =>
      if (head.gk.isDefined) {
        val key = head.gk.get
        val incValue = acc.get(key).fold(1){v => v + 1}
        countSameGroupKeys(tail, acc.updated(key, incValue))
      } else {
        countSameGroupKeys(tail, acc)
      }
    case head :: Nil =>
      if (head.gk.isDefined) {
        val key = head.gk.get
        val incValue = acc.get(key).fold(1){v => v + 1}
        acc.updated(key, incValue)
      } else {
        acc
      }
    case Nil => acc
  }

  @tailrec
  def recursiveDeNest(stats: List[Stats], acc: List[String]): List[String] = stats match {
    case head :: tail => recursiveDeNest(tail, acc :+ head.gk.getOrElse("und"))
    case head :: Nil => acc :+ head.gk.getOrElse("und")
    case Nil => acc
  }

}

case class Stats(sha1: String, id: String, gk: Option[String] = None, children: Option[List[Stats]] = None) extends Serializable