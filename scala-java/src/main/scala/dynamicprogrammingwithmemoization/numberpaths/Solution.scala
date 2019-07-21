package dynamicprogrammingwithmemoization.numberpaths

/**
  * Given an N x N grid, find the number of paths
  * from a starting point to an end point.
  * A grid may have some blocks so that the way
  * cannot be used.
  *
  */
object Solution {

  final val N = 4
  val grid: Array[Array[Int]] = Array.ofDim[Int](N, N)

  def main(args: Array[String]): Unit = {
    setRoadBlocks(grid)
//    printGrid(grid)

    val expected1 = 11
    val result1 = nbOfPaths(grid, 0, 0)
    assert(result1 == expected1, s"got $result1 want $expected1")
  }

  def nbOfPaths(grid: Array[Array[Int]], row: Int, col: Int): Int = {
    if (row == N - 1 && col == N - 1) {
      grid(col)(row)
    } else if (row == N - 1) {
      grid(row)(col + 1)
    } else if (col == N - 1) {
      grid(row + 1)(col)
    } else if (grid(row)(col) == 1) {
      nbOfPaths(grid, row + 1, col) + nbOfPaths(grid, row, col + 1)
    } else {
      0
    }
  }

  def printGrid(grid: Array[Array[Int]]): Unit = {
    println(setRoadBlocks(grid).map(_.mkString(" ")).mkString("\n"))
  }

  def setRoadBlocks(grid: Array[Array[Int]]): Array[Array[Int]] = {
    grid.indices.foreach(row => grid(row).indices.foreach(col => if (row == 2 && col == 1) grid(row)(col) = 0 else grid(row)(col) = 1))
    grid
  }

}
