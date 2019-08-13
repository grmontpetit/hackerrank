package dynamicprogrammingwithmemoization.median

import scala.collection.mutable

class QuickSelect(private val list: List[Int]) {

  private val numbers: mutable.Buffer[Int] = list.toBuffer[Int]

  def addNum(nb: Int): Unit = {
    numbers :+ nb
  }

  /**
    * Quick select the median of an unordered mutable sequence
    * in best case O(n) and O(n exp 2) worst case.
    * @param seq The sequence to quick select from.
    * @param k The kth smallest element.
    * @param pivotFn The pivot function used to generate a random int that
    *                acts as a splitter for the sequence.
    * @return The median of the sequence
    */
  private def quickSelect(seq: mutable.Seq[Int], k: Int, pivotFn: mutable.Seq[Int] => Int): Int = {
    if (seq.length == 1) {
      assert(k == 0)
      seq(0)
    }
    else {
      val pivot = pivotFn(seq)

      val lows = seq.filter(_ < pivot)
      val highs = seq.filter(_ > pivot)
      val pivots = seq.filter(_ == pivot)

      if (k < lows.length) quickSelect(lows, k, pivotFn)
      else if (k < (lows.length + pivots.length)) pivots(0)
      else quickSelect(highs, k - lows.length - pivots.length, pivotFn)
    }
  }

  /**
    * Takes a mutable sequence and generate a random
    * integer that is used to split the sequence in half.
    */
  private val pivotFn: mutable.Seq[Int] => Int = { xs =>
    val r = scala.util.Random
    r.nextInt(xs.length)
  }

  /**
    * Find the median in linear time.
    * https://rcoh.me/posts/linear-time-median-finding/
    * @return The median of the mutable sequence
    */
  def median: Double = {
    if (numbers.length == 2) numbers.sum / 2.0
    else if (numbers.length == 3) numbers.sortWith(_ > _)(1)
    else if (numbers.length % 2 == 1) quickSelect(numbers, list.length / 2, pivotFn)
    else 0.5 * (quickSelect(numbers, (list.length / 2) - 1, pivotFn) + quickSelect(numbers, list.length / 2, pivotFn))
  }

}

object QuickSelect {
  def apply(list: List[Int]): QuickSelect = new QuickSelect(list)
}