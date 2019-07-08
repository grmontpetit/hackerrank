package hackerrank.bitwiseand

object Solution {

  def bitWiseMax(n: Int, k: Int): Int = {
    val values = (0 until n).map(i => i + 1).toList
    max(k, 0, values)
    //group(k, values.head, values.tail).max
  }


  def max(k: Int, lastMax: Int, tail: scala.List[Int]): Int = {
    if (tail.isEmpty) lastMax
    else {
      val current = tail.head
      val newMax = tail.tail.foldLeft(lastMax) { (acc, i) =>
        val bitwiseAnd = current & i
        if (bitwiseAnd > lastMax && bitwiseAnd < k) bitwiseAnd
        else acc
      }
      max(k, newMax, tail.tail)
    }
  }

  def group(k: Int, current: Int, tail: scala.List[Int]): scala.List[Int] = {
    if (tail.isEmpty) List()
    else {
      tail.foldLeft(List.empty[Int]) { (acc, i) => {
        val bitwiseAnd = current & i
        if (bitwiseAnd < k) acc :+ bitwiseAnd
        else acc
      }} ++ group(k, tail.head, tail.tail)
    }
  }

  def main(args: Array[String]): Unit = {

    val result1 = bitWiseMax(5, 2)
    val expected1 = 1
    assert(result1 == expected1, s"got $result1 want $expected1")

    val result2 = bitWiseMax(8, 5)
    val expected2 = 4
    assert(result2 == expected2, s"got $result2 want $expected2")

    val result3 = bitWiseMax(2, 2)
    val expected3 = 0
    assert(result3 == expected3, s"got $result3 want $expected3")

  }

}
