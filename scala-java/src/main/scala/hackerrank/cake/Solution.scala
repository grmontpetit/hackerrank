package hackerrank.cake

import java.io.PrintWriter

object Solution {

  // Complete the birthdayCakeCandles function below.
  def birthdayCakeCandles(ar: Array[Int]): Int = {
    val max = ar.max
    ar.count(_ == max)
  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    //val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))

    //val arCount = stdin.readLine.trim.toInt

    val ar = stdin.readLine.split(" ").map(_.trim.toInt)
    val result = birthdayCakeCandles(ar)

    println(result)

    //printWriter.close()
  }

}
