package hackerrank.minmaxsum

object Solution {
  // Complete the miniMaxSum function below.
  def miniMaxSum(arr: Array[Int]) {
    val bigInts = arr.map(i => BigInt(i))
    println(bigInts.sortWith(_ < _).dropRight(1).sum + " " + bigInts.sortWith(_ > _).dropRight(1).sum)
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val arr = stdin.readLine.split(" ").map(_.trim.toInt)
    miniMaxSum(arr)
  }
}
