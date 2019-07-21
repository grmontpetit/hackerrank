package dynamicprogrammingwithmemoization.countchange

object Solution {
  val coins = List(1, 2)
  val amount = 4

  def main(args: Array[String]): Unit = {
    val result1 = countChange(amount, coins)
    assert(result1 == 3, s"got $result1 want 3")
  }

  def countChange(money: Int, coins: List[Int]): Int = {
    if (money == 0) {
      1
    } else if (money < 0 || coins.isEmpty) {
      0
    } else {
      countChange(money, coins.tail) + countChange(money - coins.head, coins)
    }
  }
}
