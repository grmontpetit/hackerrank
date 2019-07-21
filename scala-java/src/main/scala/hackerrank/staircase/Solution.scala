package hackerrank.staircase

object Solution {
  // Complete the staircase function below.
  def staircase(n: Int): Unit = {
    (1 to n).foreach{ i =>
      println(s"${(1 to n - i).map(_ => " ").mkString}${(1 to i).map(_ => "#").mkString}")
    }
  }

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    val n = stdin.readLine.trim.toInt

    staircase(n)
  }
}
