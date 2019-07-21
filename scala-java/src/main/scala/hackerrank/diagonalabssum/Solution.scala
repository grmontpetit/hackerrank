package hackerrank.diagonalabssum

import java.io.PrintWriter

object Solution {
  // Complete the diagonalDifference function below.
  def diagonalDifference(arr: Array[Array[Int]]): Int = {
    math.abs(arr(0).indices.map(i => {
      arr(i)(i) - arr.reverse(i)(i)
    }).sum)
  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = stdin.readLine.trim.toInt

    val arr = Array.ofDim[Int](n, n)

    for (i <- 0 until n) {
      arr(i) = stdin.readLine.split(" ").map(_.trim.toInt)
    }

    val result = diagonalDifference(arr)

    printWriter.println(result)

    printWriter.close()
  }
}
