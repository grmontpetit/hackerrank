package hackerrank.hourglass

import scala.Iterator

object Solution {

  // Complete the hourglassSum function below.
  def hourglassSum(arr: Array[Array[Int]]): Int = {
    val hTopSums: Array[Iterator[Int]] = arr.dropRight(2).map(row => row.sliding(3, 1)).map(_.map(_.sum))
    val hMidSums: Array[Iterator[Int]] = arr.drop(1).dropRight(1).map(row => row.drop(1).dropRight(1).sliding(1, 1)).map(row => row.map(_.sum))
    val hBotSums: Array[Iterator[Int]] = arr.drop(2).map(_.sliding(3, 1)).map(row => row.map(_.sum))

    val size = arr.length - 2
    (0 until size).flatMap(i => {
      (0 until size).map(_ => {
        hTopSums(i).next + hMidSums(i).next + hBotSums(i).next
      })
    }).max
  }

  def main(args: Array[String]) {
    val in =
      """|1 1 1 0 0 0
         |0 1 0 0 0 0
         |1 1 1 0 0 0
         |0 0 2 4 4 0
         |0 0 0 2 0 0
         |0 0 1 2 4 0""".stripMargin

    val lines = in.split("\\n")

    val arr = lines.map(line => line.split(" ").map(_.trim.toInt))

    val result = hourglassSum(arr)

    assert(result == 19, s"got $result want 19")

  }
}

