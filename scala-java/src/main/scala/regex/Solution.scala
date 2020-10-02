package regex

object Solution {

  def main(args: Array[String]): Unit = {
    val string1 = """https://orcid.org/0000-0002-5022-1675"""
    val regex = """\d{4}-\d{4}-\d{4}-\d{4}""".r
    val results = regex.findFirstIn(string1)

    assert(results.get == "0000-0002-5022-1675", s"got ${results.get} want 0000-0002-5022-1675")
    assert(parseDate() == "2020_08_30", s"got ${parseDate()} want 2020_08_30")

    def parseDate(name: String = "some_date_2020_08_30_2"): String = {
      val regex = """\d{4}_\d{2}_\d{2}""".r
      val results = regex.findFirstIn(name)
      results.get
    }
  }

}
