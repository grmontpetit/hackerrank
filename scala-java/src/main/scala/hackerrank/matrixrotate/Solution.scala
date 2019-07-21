package hackerrank.matrixrotate

object Solution {

  // Complete the rotLeft function below.
  def rotLeft(a: Array[Int], d: Int): Array[Int] = {
    val split = a.splitAt(d)
    split._2 ++ split._1
  }

  def main(args: Array[String]): Unit = {
    val in = """1 2 3 4 5"""
    val numRotations = 4
    val expected = """5 1 2 3 4"""

    val nd = in.split(" ")

    val a = nd.map(_.trim.toInt)
    val result = rotLeft(a, numRotations)

    assert(result.mkString(" ") == expected, s"got ${result.mkString(" ")} want $expected")
    val nextResult = rotLeft("""33 47 70 37 8 53 13 93 71 72 51 100 60 87 97""".split(" ").map(_.trim.toInt), 13)
    assert(nextResult.mkString(" ") == """87 97 33 47 70 37 8 53 13 93 71 72 51 100 60""", s"got ${nextResult.mkString(" ")} want 87 97 33 47 70 37 8 53 13 93 71 72 51 100 60")

    val anotherInput = """1 2 3 4 5""".split(" ").map(_.trim.toInt)
    val anotherExpected = """3 4 5 1 2"""
    val res = rotLeft(anotherInput, 2).mkString(" ")
    assert(res == anotherExpected, s"got $res want $anotherExpected")
  }
}