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
  //val grid: Array[Array[Int]] = setRoadBlocks(Array.ofDim[Int](N, N))
  val grid: Array[Array[Int]] = Array.ofDim[Int](N, N)

  def main(args: Array[String]): Unit = {

    //val start = (0, 0)
   // val end = (7, 7)

    //val paths = findPaths(start, end)
    //println(paths)

    //println(countPaths((0, 0), (1, 1)))
   // println(paths(3, 3))

    val expected1 = 20
    val result1 = paths((0, 0), (4, 4))
    assert(expected1 == result1, s"got $expected1 want $result1")
  }

//  def nbPaths(): Int = {
//
//  }

  def paths(start: (Int, Int), end: (Int, Int)): Int = {
    val (startRow, startCol) = start
    val (endRow, endCol) = end
    
    1
  }

  def countPaths(start: (Int, Int), end: (Int, Int)): Int = {
    val (row, col) = start
    val (endRow, endCol) = end
    if (endRow == N || endCol == N) 1
    else {
      countPaths(start, (endRow - 1, endCol)) + countPaths(start, (endRow, endCol - 1))
    }
  }

  def findPaths(start: (Int, Int), end: (Int, Int)): Int = {
    if (isSame(start, end)) 1
    else {
      val (row, col) = start
      val nextRow = (row + 1, col)
      val nextCol = (row, col + 1)
      val rowCount = if (isBlocked(grid, nextRow)) 0 else {
        grid(nextRow._1)(nextRow._2) = 1
        findPaths(nextRow, end)
      }
      val colCount = if (isBlocked(grid, nextCol)) 0 else {
        grid(nextCol._1)(nextCol._2) = 1
        findPaths(nextCol, end)
      }
      rowCount + colCount
    }
  }

  def isSame(start: (Int, Int), end: (Int, Int)): Boolean = {
    val (startRow, startCol) = start
    val (endRow, endCol) = end
    startRow == endRow && startCol == endCol
  }

  def isBlocked(grid: Array[Array[Int]], next: (Int, Int)): Boolean = {
    val (row, col) = next
    if (!isOutOfBounds(grid, next)) {
      isVisited(grid, next) && grid(row)(col) == 9
    } else true
  }

  def isOutOfBounds(grid: Array[Array[Int]], point: (Int, Int)): Boolean = {
    val (row, col) = point
    if (row >= grid.length || col >= grid.length) true
    else false
  }

  def isVisited(grid: Array[Array[Int]], point: (Int, Int)): Boolean = {
    val (row, col) = point
    grid(row)(col) == 1
  }

  def setRoadBlocks(grid: Array[Array[Int]]): Array[Array[Int]] = {
    grid(1)(2) = 9
    grid(1)(6) = 9
    grid(2)(4) = 9
    grid(3)(0) = 9
    grid(3)(2) = 9
    grid(3)(5) = 9
    grid(4)(2) = 9
    grid(5)(3) = 9
    grid(5)(4) = 9
    grid(5)(6) = 9
    grid(6)(5) = 9
    grid
  }

}
