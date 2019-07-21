package kthorderstatistics.nthminimum

import scala.util.Try

/**
  * Given an unsorted list with no duplicates, find the nth minimum of the list.
  * You cannot use scala .min method
  * Example:
  * Given the list {5, 4, 8, 1, 2}
  * Find the 3rd minimum of the list
  * Answer: 4
  *
  */
object Solution {

  val list = List(5, 4, 8, 1, 2)
  val nth = 3
  val expected3 = 4

  def main(args: Array[String]): Unit = {
    val test1 = findMin(list, Int.MaxValue)
    assert(test1 == 1, s"got $test1 want 1")

    val test2 = findMinWithIndex(list, Int.MaxValue, 0, 0)
    val expected2 = (1, 3)
    assert(test2 == expected2, s"got $test2 want $expected2")

    val test3 = findNtMin(list, 3, 0, Int.MaxValue, 0)
    assert(test3 == expected3, s"got $test3 want $expected3")
  }

  /**
    * Find the Nth minimum inside a given list.
    * @param list The list to look into.
    * @param nth The nth minimum.
    * @param current The current minimum count.
    * @param lastMin The last found minimum.
    * @param lastIndex The last found index.
    * @return The nth minimum
    */
  def findNtMin(list: List[Int], nth: Int, current: Int, lastMin: Int, lastIndex: Int): Int = {
    if (current == nth) lastMin
    else {
      val (min, index) = findMinWithIndex(list, Int.MaxValue, 0, 0)
      val split = list.splitAt(index)
      val updatedList = split._1 ++ Try(split._2.tail).getOrElse(List.empty)
      findNtMin(updatedList, nth, current + 1, min, index)
    }
  }

  /**
    * Finds the minimum and the index of a minimum inside a list.
    * @param list The list to look into.
    * @param lastMin The last minimum found.
    * @param lastIndex The last index found.
    * @param currentIndex The current index location.
    * @return A tuple of (theMininum, theMinimumIndex)
    */
  def findMinWithIndex(list: List[Int], lastMin: Int, lastIndex: Int, currentIndex: Int): (Int, Int) = {
    list match {
      case Nil => (lastMin, lastIndex)
      case head :: Nil =>
        if (head < lastMin) (head, currentIndex)
        else (lastMin, lastIndex)
      case head :: tail =>
        if (head < lastMin) findMinWithIndex(tail, head, currentIndex, currentIndex + 1)
        else findMinWithIndex(tail, lastMin, lastIndex, currentIndex + 1)
    }
  }

  /**
    * Finds the minimum of given list.
    * @param list The list to search into.
    * @param lastMin The last minimum
    * @return The minimum of a list.
    */
  def findMin(list: List[Int], lastMin: Int): Int = {
    list match {
      case Nil => lastMin
      case head :: Nil =>
        if (head < lastMin) head
        else lastMin
      case head :: tail =>
        if (head < lastMin) findMin(tail, head)
        else findMin(tail, lastMin)
    }
  }

}
