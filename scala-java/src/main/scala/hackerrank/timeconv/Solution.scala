package hackerrank.timeconv

object Solution {
  /*
   * Complete the timeConversion function below.
   */
  def timeConversion(s: String): String = {
    /*
     * Write your code here.
     */
    val pattern = "AM|PM".r
    val time = "([0-9]{2})".r
    val matches: List[String] = time.findAllMatchIn(s).map(_.toString).toList

    pattern.findFirstIn(s) match {
      case Some("AM") =>
        if (matches.head == "12") ("00" :: matches.tail).mkString(":")
        else matches.mkString(":")
      case Some("PM") =>
        if (matches.head == "12") matches.mkString(":")
        else ((matches.head.toInt + 12) :: matches.tail).mkString(":")
      case _ => "Unknown Format"
    }
  }

  def main(args: Array[String]): Unit = {
    val scan = scala.io.StdIn

    //val fw = new PrintWriter(sys.env("OUTPUT_PATH"))

    val s = scan.readLine

    val result = timeConversion(s)

    println(result)

    // fw.close()
  }
}
