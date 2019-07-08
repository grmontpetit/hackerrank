package hackerrank.triplets

import java.io.PrintWriter

import scala.io.StdIn

object Solution {
  // If a[i] > b[i], then Alice is awarded  point.
  // If a[i] < b[i], then Bob is awarded  point.
  // If a[i] == b[i], then neither person receives a point.
  // Complete the compareTriplets function below.
  def compareTriplets(a: Array[Int], b: Array[Int]): Array[Int] = {
    val s = a.zip(b).foldLeft((0, 0)){ (acc, x) =>
      if (x._1 > x._2) (acc._1 + 1, acc._2)
      else if (x._1 < x._2) (acc._1, acc._2 + 1)
      else (acc._1, acc._2)
    }
    Array(s._1, s._2)
  }

  def main(args: Array[String]) {
    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    val a = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)

    val b = StdIn.readLine.replaceAll("\\s+$", "").split(" ").map(_.trim.toInt)
    val result = compareTriplets(a, b)

    printWriter.println(result.mkString(" "))

    printWriter.close()
  }
}
