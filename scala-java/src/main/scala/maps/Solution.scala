package maps

object Solution {

  def main(args: Array[String]): Unit = {
    masterMapWithNestedMaps
  }

  def mapsMerging: Unit = {
    // Merging maps, adding values togheter
    val aMap: Map[Int, Int] = Map(1 -> 1, 2 -> 2)
    val aSecondMap: Map[Int, Int] = Map(1 -> 10)

//    val aNewMap = (aMap.toSeq ++ aSecondMap.toSeq).groupBy(_._1).view.mapValues(_.map(_._2).sum)
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

  /**
    * Create a master map with 2 nested maps inside of it. If both
    * of the maps are empty, then the master map should be empty.
    */
  def masterMapWithNestedMaps: Unit = {
    println(case1.apply)
    println(case2.apply)
    println(case3.apply)
  }
}

object case1 {
  // both data are empty
  private val data1: Option[Int] = None
  private val data2: Option[String] = None
  def apply: Map[String, Any] = {
    (data1, data2) match {
      case (Some(a), Some(b)) => Map("data" -> Map("data1" -> a, "data2" -> b))
      case (Some(a), None) => Map("data" -> Map("data1" -> a))
      case (None, Some(b)) => Map("data" -> Map("data2" -> b))
      case _ => Map.empty[String, Any]
    }
  }
}

object case2 {
  // 1 of the data is empty, the other one isn't
  private val data1: Option[Int] = None
  private val data2: Option[String] = Some("non-empty-data")
  def apply: Map[String, Any] = {
    (data1, data2) match {
      case (Some(a), Some(b)) => Map("data" -> Map("data1" -> a, "data2" -> b))
      case (Some(a), None) => Map("data" -> Map("data1" -> a))
      case (None, Some(b)) => Map("data" -> Map("data2" -> b))
      case _ => Map.empty[String, Any]
    }
  }
}

object case3 {
  // both data are non-empty
  private val data1: Option[Int] = Some(999)
  private val data2: Option[String] = Some("non-empty-data")
  def apply: Map[String, Any] = {
    (data1, data2) match {
      case (Some(a), Some(b)) => Map("data" -> Map("data1" -> a, "data2" -> b))
      case (Some(a), None) => Map("data" -> Map("data1" -> a))
      case (None, Some(b)) => Map("data" -> Map("data2" -> b))
      case _ => Map.empty[String, Any]
    }
  }
}