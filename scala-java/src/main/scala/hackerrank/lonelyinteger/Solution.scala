package hackerrank.lonelyinteger

object Solution {

  // Complete the lonelyinteger function below.
  def lonelyinteger(a: Array[Int]): Int = {
    a.fold(0){_ ^ _}
  }

  def main(args: Array[String]) {
    val a = Array(3, 1, 3, 1)
    assert(lonelyinteger(a) == 0, s"got ${lonelyinteger(a)} want 0")
//    val stdin = scala.io.StdIn
//
//    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
//
//    val n = stdin.readLine.trim.toInt
//
//    val a = stdin.readLine.split(" ").map(_.trim.toInt)
//    val result = lonelyinteger(a)
//
//    printWriter.println(result)
//
//    printWriter.close()
  }
}
