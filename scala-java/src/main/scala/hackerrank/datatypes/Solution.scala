package hackerrank.datatypes

import scala.io.StdIn

object Solution {

  def main(args: Array[String]): Unit = {
    val i = 4
    val d = 4.0
    val s = "HackerRank "

    //println("Input an integer:")
    val a = StdIn.readInt()
   // println("Input a double:")
    val b = StdIn.readDouble()
    //println("Input a string:")
    val c = StdIn.readLine()

    println(i + a)
    println(d + b)
    println(s + c)
  }
}
