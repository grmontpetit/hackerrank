package sets

object Sets {

  def main(args: Array[String]): Unit = {
    val aSet = Set("elem1", "elem2")

    val aSeqOfSets: Seq[Set[String]] = Seq(Set("a", "b"), Set("b", "c"))
    val k = aSeqOfSets.foldLeft(Set.empty[String])((acc, i) => acc.concat(i))
    println(k)

    val set1 = Set(Set("a", "b"), Set("b", "c"))
    val newSet = set1.foldLeft(Set.empty[String])((acc, i) => acc.union(i))
    println(newSet)

    val anotherSet = aSet + "elem3"
    println(anotherSet)

    println(Set.empty.nonEmpty)
  }

}
