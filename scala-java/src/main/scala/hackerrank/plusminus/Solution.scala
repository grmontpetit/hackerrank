package hackerrank.plusminus

object Solution {

  // Complete the plusMinus function below.
  def plusMinus(arr: Array[Int]) {
    println(arr.count(p => p > 0) / arr.length.toFloat)
    println(arr.count(p => p < 0) / arr.length.toFloat)
    println(arr.count(p => p == 0) / arr.length.toFloat)
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    val arr = stdin.readLine.split(" ").map(_.trim.toInt)
    plusMinus(arr)
  }
}

