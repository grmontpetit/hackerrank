package lists

object Solution {

  def main(args: Array[String]): Unit = {
    val list = List(2, 5, 3, 4, 6, 7, 9, 8)
    //println(f(list).mkString("\n"))
   // println(-1 % 2)

    println(factorial(5))
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
