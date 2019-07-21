package dynamicprogrammingwithmemoization.staircase

object Solution {

  val nbStairs = 4
  val steps = List(1, 3, 5)

  def main(args: Array[String]): Unit = {
    println(countWays(nbStairs, steps))
  }

  def countWays(nbStairs: Int, steps: List[Int]): Int = {
    if (nbStairs == 0) {
      1
    } else if (nbStairs < 0 || steps.isEmpty) {
      0
    } else {
      countWays(nbStairs, steps.tail) + countWays(nbStairs - steps.head, steps)
    }
  }
}
