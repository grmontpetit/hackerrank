package hackerrank.bigsum

import java.io.PrintWriter

object Solution {

  // Complete the aVeryBigSum function below.
  def aVeryBigSum(ar: Array[Long]): Long = {
    ar.tail.sum
  }

  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val arCount = stdin.readLine.trim.toInt

    val ar = stdin.readLine.split(" ").map(_.trim.toLong)
    val result = aVeryBigSum(ar)

    printWriter.println(result)

    printWriter.close()
  }
}
