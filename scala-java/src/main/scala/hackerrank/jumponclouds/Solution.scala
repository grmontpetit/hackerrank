package hackerrank.jumponclouds

object Solution {

  // Complete the jumpingOnClouds function below.
  def jumpingOnClouds(c: Array[Int]): Int = {
    def recurse(jumps: Int, remainingClouds: scala.List[Int]): Int = {
      remainingClouds match {
        case Nil => jumps
        case _ :: Nil => jumps
        case _ :: tail =>
          if (tail.tail != List() && tail.tail.head == 1) recurse(jumps + 1, tail)
          else recurse(jumps + 1, tail.tail)
      }
    }
    recurse(0, c.toList)
  }

  def main(args: Array[String]): Unit = {
//    val stdin = scala.io.StdIn
//
//    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
//
//    val n = stdin.readLine.trim.toInt
//
//    val c = stdin.readLine.split(" ").map(_.trim.toInt)
//    val result = jumpingOnClouds(c)
//
//    printWriter.println(result)
//
//    printWriter.close()
    val input = "0 0 1 0 0 1 0".split(" ").map(_.trim.toInt)
    val jumps = jumpingOnClouds(input)
    assert(jumps == 4, s"Expected 4 but got $jumps")

    val anotherInput = "0 0 0 1 0 0".split(" ").map(_.trim.toInt)
    val anotherJump = jumpingOnClouds(anotherInput)
    assert(anotherJump == 3, s"Expected 3 but got $anotherJump")
  }
}
