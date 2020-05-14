package regexes

object Solution {

  def main(args: Array[String]): Unit = {
    val aString = """http://export.arxiv.org/oai2?verb=GetRecord&metadataPrefix=oai_dc&identifier=oai:arXiv.org:1801.10376"""

    val regex = """arXiv.org[\\:]\d*.\d*""".r

    val result = regex.findFirstIn(aString).getOrElse("empty")
    println(result.split(":").last)
  }
}
