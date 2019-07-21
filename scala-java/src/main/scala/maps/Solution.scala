package maps

object Solution {

  def main(args: Array[String]): Unit = {

    val aMap: Map[Int, Int] = Map(1 -> 1, 2 -> 2)
    val aSecondMap: Map[Int, Int] = Map(1 -> 1)

    val aNewMap = (aMap.toSeq ++ aSecondMap.toSeq).groupBy(_._1).view.mapValues(_.map(_._2).sum)
    println(aNewMap)

  }
}
