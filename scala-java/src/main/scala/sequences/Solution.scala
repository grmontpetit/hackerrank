package sequences

object Solution {

  def main(args: Array[String]): Unit = {
    val aSet = Set("a", "a", "b", "c", "c")
    println(aSet)

    println(aSet.toSeq: Seq[String])
  }

}
