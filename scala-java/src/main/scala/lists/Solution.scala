package lists

object Solution {

  case class Person(name: Option[String] = None)

  def main(args: Array[String]): Unit = {
    val list = List(2, 5, 3, 4, 6, 7, 9, 8)
    //println(f(list).mkString("\n"))
   // println(-1 % 2)

    //println(factorial(5))

    val oddList = List(1, 3, 4, 2, 1)
    val evenList = List(3, 5, 3, 1, 8, 7)

    println(oddList.splitAt(3))
    println(evenList.splitAt(3))

    val names = List(Person(Some("name1")), Person(), Person(Some("name2")))
    println(names.flatMap(_.name).mkString("|"))
  }

  //def f(arr: List[Int]): List[Int] = (1 until arr.size by 2).map(idx => arr(idx)).toList

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
