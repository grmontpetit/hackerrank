package strings

import scala.util.Random

object Solution {

  def main(args: Array[String]): Unit = {
    val aPath = "/root/bin/lib"
    println(aPath.split("/").mkString("|"))

    val s1 = """The Last Effort"""
    val s2 = """The Absent"""

    println(randomize(s1))
    println(randomize(s1))

    val h = """1.Hello"""
    println(h.split("\\.").head)
  }

  def randomize(s: String): String = {
    val random = scala.util.Random
    val noSpaces = s.replace(" ", "")
    val min = 0
    val max = noSpaces.length
      s"${Random.alphanumeric.filter(_.isLetter).head}${Random.alphanumeric.filter(_.isLetter).head}"
  }


}
