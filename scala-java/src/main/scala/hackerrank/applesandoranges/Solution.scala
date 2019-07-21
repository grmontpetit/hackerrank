package hackerrank.applesandoranges

object Solution {

  /**
    *
    * @param s Starting point of Sam's house location.
    * @param t Ending location of Sam's house location.
    * @param a Apple tree position.
    * @param b Orange tree position.
    * @param apples
    * @param oranges
    */
  def countApplesAndOranges(s: Int, t: Int, a: Int, b: Int, apples: Array[Int], oranges: Array[Int]): Unit = {
    val applesOnHouse = apples.filter(distance => isOverHouse(distance, a, s, t))
    val orangesOnHouse = oranges.filter(distance => isOverHouse(distance, b, s, t))
    println(applesOnHouse.length)
    println(orangesOnHouse.length)
  }

  def isOverHouse(fruitDistance: Int, treePosition: Int, s: Int, t: Int): Boolean = {
    val fruitPosition = treePosition + fruitDistance
    fruitPosition >= s && fruitPosition <= t
  }

  def main(args: Array[String]): Unit = {

  }

}
