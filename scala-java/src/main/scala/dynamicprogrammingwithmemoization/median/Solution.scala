package dynamicprogrammingwithmemoization.median

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

class MedianCalculator(private val list: ListBuffer[Int]) {

  private val mutableList: mutable.Seq[Int] = list

  def addNum(nb: Int): Unit = {
    mutableList :+ nb
  }

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

  private val pivotFn: mutable.Seq[Int] => Int = { xs =>
    val r = scala.util.Random
    r.nextInt(xs.length)
  }

  // https://rcoh.me/posts/linear-time-median-finding/
  def quickSelectMedian(): Double = {
    if (mutableList.length == 2) mutableList.sum / 2.0
    else if (mutableList.length == 3) mutableList.sortWith(_ > _)(1)
    else if (mutableList.length % 2 == 1) quickSelect(mutableList, mutableList.length / 2, pivotFn)
    else 0.5 * (quickSelect(mutableList, (mutableList.length / 2) - 1, pivotFn) + quickSelect(mutableList, mutableList.length / 2, pivotFn))
  }

}

object Solution {

  def main(args: Array[String]): Unit = {
    val list1 = ListBuffer(4, 2, 3)
    val list2 = ListBuffer(3, 2)
    val list3 = ListBuffer(9,1,0,2,3,4,6,8,7,10,5)

    val median1 = new MedianCalculator(list1)
    val median2 = new MedianCalculator(list2)
    val median3 = new MedianCalculator(list3)

    val result1 = median1.quickSelectMedian()
    val result2 = median2.quickSelectMedian()
    val result3 = median3.quickSelectMedian()

    assert(result1 == 3.0, s"got $result1 want 3.0")
    assert(result2 == 2.5, s"got $result1 want 2.5")
    assert(result3 == 5.0, s"got $result1 want 5.0")
  }

}
