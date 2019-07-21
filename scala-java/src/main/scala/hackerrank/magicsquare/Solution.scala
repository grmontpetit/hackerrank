package hackerrank.magicsquare

object Solution {
  // Complete the formingMagicSquare function below.
  def formingMagicSquare(s: Array[Array[Int]]): Int = {
    val rowSums: Array[Int] = s.map(row => row.sum)
    rowSums.foreach(x => print(s"$x "))
    println
    val colSums: Array[Int] = s.transpose.map(col => col.sum)
    colSums.foreach(x => print(s"$x "))
    println
    println(rowSums.zip(colSums).mkString(" "))
    val zip = rowSums.zip(colSums)
    zip.map(z => {
      if (z._1 == z._2) {
        magicConstant(s.length) - z._1
      } else {
        val diff = math.abs(z._1 - z._2)
        diff / 2
      }
    }).sum
  }

  val magicConstant: Int => Int =  { n =>
    n * (math.pow(n, 2).toInt + 1) / 2
  }

  def main(args: Array[String]): Unit = {
//    val anInput0 = Array(Array(4, 9, 2), Array(3, 5, 7), Array(8, 1, 5))
//    val anExpectedOutput0 = 1
//    val aResult0 = formingMagicSquare(anInput0)
//    assert(aResult0 == anExpectedOutput0, s"got $aResult0 want $anExpectedOutput0")

//    val anInput1 = Array(Array(4, 8, 2), Array(4, 5, 7), Array(6, 1, 6))
//    val anExpectedOutput1 = 4
//    val aResult1 = formingMagicSquare(anInput1)
//    assert(aResult1 == anExpectedOutput1, s"got $aResult1 want $anExpectedOutput1")

    val anInput2 = Array(Array(4, 1, 5), Array(7, 6, 4), Array(7, 2, 2))
    val anExpectedOutput2 = 21
    val aResult2 = formingMagicSquare(anInput2)
    assert(aResult2 == anExpectedOutput2, s"got $aResult2 want $anExpectedOutput2")

//    val stdin = scala.io.StdIn
//
//    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
//
//    val s = Array.ofDim[Int](3, 3)
//
//    for (i <- 0 until 3) {
//      s(i) = stdin.readLine.split(" ").map(_.trim.toInt)
//    }
//
//    val result = formingMagicSquare(s)
//
//    printWriter.println(result)
//
//    printWriter.close()
  }
}
