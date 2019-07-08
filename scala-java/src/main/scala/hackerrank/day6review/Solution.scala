package hackerrank.day6review

object Solution {

  def main(args: Array[String]): Unit = {
    val stdin = scala.io.StdIn

    // read length
    val t = stdin.readLine.trim.toInt

    val lines = readLines(t, Array.empty[String])

    lines.foreach { l =>
      println(s"${findEven(l)} ${findOdd(l)}")
    }

  }

  def findEven(line: String): String = (0 until line.length by 2).map(i => line.charAt(i)).mkString

  def findOdd(line: String): String = (1 until line.length by 2).map(i => line.charAt(i)).mkString

  def readLines(remainingLines: Int, currentLines: Array[String]): Array[String] = {
    if (remainingLines == 0) currentLines
    else {
      val stdin = scala.io.StdIn
      readLines(remainingLines - 1, currentLines ++ stdin.readLine.split(" ").map(_.trim))
    }
  }
}
