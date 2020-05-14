package maps

object Solution {

  def main(args: Array[String]): Unit = {

    // Merging maps, adding values togheter
    val aMap: Map[Int, Int] = Map(1 -> 1, 2 -> 2)
    val aSecondMap: Map[Int, Int] = Map(1 -> 10)

    val aNewMap = (aMap.toSeq ++ aSecondMap.toSeq).groupBy(_._1).view.mapValues(_.map(_._2).sum)
//    aNewMap.foreach(println)

    // Merging maps, resolving conflicts
    val anotherMap = aMap ++ aSecondMap
//    anotherMap.foreach(println)

    // Same, but within a Seq
    val seqMap = Seq(aMap, aSecondMap)
    seqMap.foldLeft(Map.empty[Int, Int])((acc, i) => acc ++ i).foreach(println)

    val someSeq = Seq(("http://url.com", "a"), ("https://url.com", "b"))
    println(someSeq.map(st => st._1.replaceAll("http://", "https://")))
  }
}
