package hackerrank.minswaps

import java.io.PrintWriter

object Solution {

  type Swaps = Int

  // Complete the minimumSwaps function below.
  def minimumSwaps(arr: Array[Int]): Int = {
    val (_, swaps) = itr(0, arr, 0, true)
    swaps
  }

  def itr(index: Int, arr: Array[Int], swaps: Swaps, hasSwapped: Boolean): (Array[Int], Swaps) = {
    if (index == arr.length - 1) {
      if (hasSwapped) itr(0, arr, swaps, false)
      else (arr, swaps)
    } else {
      val current = arr(index)
      val next = arr(index + 1)
      if (current > next) {
        arr(index) = next
        arr(index + 1) = current
        itr(index + 1, arr, swaps + 1, true)
      } else {
        itr(index + 1, arr, swaps, hasSwapped)
      }
    }
  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val n = stdin.readLine.trim.toInt

    val arr = stdin.readLine.split(" ").map(_.trim.toInt)
    val res = minimumSwaps(arr)

    printWriter.println(res)

    printWriter.close()
  }
}
