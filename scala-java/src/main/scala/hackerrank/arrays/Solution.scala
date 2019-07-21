package hackerrank.arrays

object Solution {

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    val arr = stdin.readLine.split(" ").map(_.trim.toInt)

    println(arr.reverse.mkString(" "))
  }

}
