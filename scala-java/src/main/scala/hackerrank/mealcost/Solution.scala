package hackerrank.mealcost

object Solution {
  // Complete the solve function below.
  def solve(meal_cost: Double, tip_percent: Int, tax_percent: Int): Int = {
    val tip = meal_cost * (tip_percent.toDouble / 100)
    val tax = meal_cost * (tax_percent.toDouble / 100)
    val totalCost = meal_cost + tip + tax
    scala.math.round(totalCost).toInt
  }

  def main(args: Array[String]): Unit = {

    val x = solve(12.0, 20, 8)
    assert(x == 15)
//    val stdin = scala.io.StdIn
//
//    val meal_cost = stdin.readLine.trim.toDouble
//
//    val tip_percent = stdin.readLine.trim.toInt
//
//    val tax_percent = stdin.readLine.trim.toInt
//
//    solve(meal_cost, tip_percent, tax_percent)
  }
}
