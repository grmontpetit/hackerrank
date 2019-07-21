package hackerrank.minmaxsum

object Solution {
  // Complete the miniMaxSum function below.
  def miniMaxSum(arr: Array[Int]): Unit = {
    val bigInts = arr.map(i => BigInt(i))
    println(bigInts.sortWith(_ < _).dropRight(1).sum.toString + " " + bigInts.sortWith(_ > _).dropRight(1).sum)
  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val arr = stdin.readLine.split(" ").map(_.trim.toInt)
    miniMaxSum(arr)
  }
}
