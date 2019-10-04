package lists

import scala.util.Try

object Solution {

  case class Person(name: Option[String] = None)
  case class Elem(text: String)

  def main(args: Array[String]): Unit = {
    //println(factorial(5))

    val oddList = List(1, 3, 4, 2, 1)
    val evenList = List(3, 5, 3, 1, 8, 7)

    println(oddList.splitAt(3))
    println(evenList.splitAt(3))

    val names = List(Person(Some("name1")), Person(), Person(Some("name2")))
    println(names.flatMap(_.name).mkString("|"))

    //      Keyword(LocalizedText("Acromegaly")),
    //      Keyword(LocalizedText("Cancer")),
    //      Keyword(LocalizedText("thyroid cancer")),
    //      Keyword(LocalizedText("colorectal cancer")),
    //      Keyword(LocalizedText("breast cancer")),
    //      Keyword(LocalizedText("acromegaly")),
    //      Keyword(LocalizedText("cancer"))

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

    println(texts.distinctBy(_.text.toLowerCase))
    println(envOrElse())
  }

  def envOrElse(): Unit = {
//    sys.env("BOGUS_BOOLEAN").toBooleanOption.getOrElse(false)
//    val b: Option[String] = sys.env.get("BOGUS_BOOLEAN")
//    b.map(s => !s.isEmpty && s.toBoolean)
    println("env var: " + Try(sys.env("BOGUS_BOOLEAN").toBoolean).toOption.getOrElse(false))
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

}
