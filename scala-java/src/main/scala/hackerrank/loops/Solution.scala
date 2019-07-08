package hackerrank.loops

object Solution {
  final val max = 10
  def main(args: Array[String]): Unit = {

    val n = 2

    (1 to 10).foreach{ i =>
      println(s"$n x $i = ${n * i}")
    }
  }

}
