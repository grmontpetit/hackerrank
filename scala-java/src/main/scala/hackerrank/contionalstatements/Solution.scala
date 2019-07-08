package hackerrank.contionalstatements

object Solution {
  def main(args: Array[String]) {
    val stdin = scala.io.StdIn

    val N = stdin.readLine.trim.toInt
    if (N % 2 > 0) println("Weird")
    if (N % 2 == 0 && (2 to 5).exists(_ == N)) println("Not Weird")
    if (N % 2 == 0 && (6 to 20).exists(_ == N)) println("Weird")
    if (N % 2 == 0 && N > 20) println("Not Weird")
  }
}
