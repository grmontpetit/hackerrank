package regexes

import Mocks._

object Solution {

  def main(args: Array[String]): Unit = {
    val aString = """http://export.arxiv.org/oai2?verb=GetRecord&metadataPrefix=oai_dc&identifier=oai:arXiv.org:1801.10376"""
    val regex = """arXiv.org[\\:]\d*.\d*""".r
    val result = regex.findFirstIn(aString).getOrElse("empty")
    assert(result.split(":").last == "1801.10376", s"got $result want 1801.10376")

    val res1 = extractDatePatterns(s1)
    println(res1)
    val res2 = extractDatePatterns(s2)
    println(res2)
    val res3 = extractDatePatterns(s3)
    println(res3)
    val res4 = extractDatePatterns(s4)
    println(res4)
    val res5 = extractDatePatterns(s5)
    println(res5)
    val res6 = extractDatePatterns(s6)
    println(res6)
    val res7 = extractDatePatterns(s7)
    assert(res7 == (None, None, None))
  }

  def extractDatePatterns(s: String): (Option[Int], Option[Int], Option[Int]) = {
    val yearMonthDay = "([0-9]{4})-([0-9]{2})-([0-9]{2})".r
    val yearMonth = "([0-9]{4})-([0-9]{2})".r
    val year = "([0-9]{4})".r
    s match {
      case yearMonthDay(year, month, day) => (Some(year.toInt), Some(month.toInt), Some(day.toInt))
      case yearMonth(year, month) => (Some(year.toInt), Some(month.toInt), None)
      case year(year) => (Some(year.toInt), None, None)
      case _ => (None, None, None)
    }
  }
}

object Mocks {
  // valids
  val s1 = "2015"
  val s2 = "2015-09"
  val s3 = "2015-09-26"
  // invalids
  val s4 = "2015-13"
  val s5 = "2015-01-41"
  val s6 = "2024-09-26"
  val s7 = "11"
}
