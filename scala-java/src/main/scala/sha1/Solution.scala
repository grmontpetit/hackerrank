package sha1
import RichStrings._

object Solution extends App {

  val str1 = "crossref_1"
  val str2 = "doaj_2"
  val str3 = "pmc_3"
  val str4 = "arxiv_4"

  val sha1s = List((str1, str1.toSha1), (str2, str2.toSha1), (str3, str3.toSha1), (str4, str4.toSha1))
  println(sha1s.sortBy(_._2))

}

object RichStrings {
  implicit class RichStringSha1(s: String) {

    private def computeSha1(data: Array[Byte]): String = {
      val md = java.security.MessageDigest.getInstance("SHA-1")
      md.digest(data).map("%02x".format(_)).mkString
    }
    def toSha1: String = computeSha1(s.getBytes("UTF-8"))
  }
}
