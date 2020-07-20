package lists

import scala.annotation.tailrec
import scala.collection.{IterableLike, Seq, mutable}
import scala.collection.generic.CanBuildFrom

object Solution {

  case class Person(name: Option[String] = None, sex: Option[String] = None)
  case class Elem(text: String)

  def main(args: Array[String]): Unit = {
    //println(factorial(5))

    val oddList = List(1, 3, 4, 2, 1)
    val evenList = List(3, 5, 3, 1, 8, 7)

    println(oddList.splitAt(3))
    println(evenList.splitAt(3))

    val names = List(
      Person(Some("name1"), sex = Some("male")),
      Person(sex = Some("female")),
      Person(Some("name2"), sex = Some("female")),
      Person()
    )

    val noEmptyNames = List(
      Person(Some("name1"), sex = Some("male")),
      Person(Some("name2")),
      Person(Some("name3"), sex = Some("female")),
      Person(Some("name4"))
    )

    val lastIsEmpty = List(
      Person(Some("name1"), sex = Some("male")),
      Person()
    )

    assert(names.allDefinedByOrNone(n => n.name).isEmpty)
    assert(noEmptyNames.allDefinedByOrNone(n => n.name).isDefined)
    assert(lastIsEmpty.allDefinedByOrNone(n => n.name).isEmpty)

    val texts = Seq(
      Elem("Cancer"),
      Elem("thyroid cancer"),
      Elem("colorectal cancer"),
      Elem("breast cancer"),
      Elem("acromegaly"),
      Elem("cancer"),
      Elem("Acromegaly"))

    val lowerCase = texts.map(x => Elem(x.text.toLowerCase)).distinct
    val diff = texts.diff(lowerCase)
    val diffLower = diff.map(x => Elem(x.text.toLowerCase))
    println(diff ++ lowerCase.diff(diffLower))
    //println(texts.intersect(lowerCase))

//    println(texts.distinctBy(_.text.toLowerCase))
    println(names.groupBy(_.sex))

  }

  def f(arr: List[Int]): List[Int] = {
    def itr(remaining: List[Int]): List[Int] = {
      remaining match {
        case Nil => List()
        case head :: Nil => List(head)
        case head :: tail => itr(tail) :+ head
      }
    }
    itr(arr)
  }

  // size of an array
  def size(arr: List[Int]): Int = {
    arr.foldLeft(0){ (acc, _) =>
      acc + 1
    }
  }

  def eX(x: Double): Double = {
    (0 until 10).foldLeft(0.0) { (acc, i) =>
      (math.pow(x, i) / factorial(i)) + acc
    }
  }

  def factorial(n: Int): Int = {
    if (n == 0) 1
    else n * factorial(n - 1)
  }

  implicit class RichCollection[A, Repr](xs: IterableLike[A, Repr]) {
    def distinctBy[B, That](f: A => B)(implicit cbf: CanBuildFrom[Repr, A, That]): That = {
      val builder = cbf(xs.repr)
      val i = xs.iterator
      var set = Set[B]()
      while (i.hasNext) {
        val o = i.next()
        val b = f(o)
        if (!set(b)) {
          set += b
          builder += o
        }
      }
      builder.result()
    }
    def allDefinedByOrNone[B, That](f: A => Option[B])(implicit cbf: CanBuildFrom[Repr, A, That]): Option[That] = {
      @tailrec
      def recurse(i: Iterator[A], builder: mutable.Builder[A, That]): Option[That] = {
        if (i.hasNext) {
          val o = i.next()
          val b = f(o)
          if (b.isEmpty) None
          else recurse(i, builder += o)
        } else Some(builder.result())
      }
      val i = xs.iterator
      recurse(i, cbf(xs.repr))
    }
  }

  implicit class OptionCollection[A, Repr](xs: IterableLike[Option[A], Repr]) {
    def somes[That](implicit cbf: CanBuildFrom[Repr, A, That]): That = xs.collect {
      case Some(v) => v
    }
    def allDefinedOrNone[That](implicit cbf: CanBuildFrom[Repr, A, That]): Option[That] = {
      if (xs.exists(_.isEmpty)) None
      else Some(xs.somes)
    }
  }
}
