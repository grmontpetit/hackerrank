package hackerrank.dynamicarray

import scala.io.Source
import scala.util.Try

object Solution {

  // Complete the dynamicArray function below.
  def dynamicArray(n: Int, queries: scala.Array[Array[Int]]): scala.Array[Int] = {
    val seqList = (0 to n).map(_ => scala.Array.empty[Int]).toArray
    recurse(n, queries.toList, seqList, scala.Array.empty)
  }

  def recurse(n: Int, queries: scala.List[scala.Array[Int]], seqList: scala.Array[scala.Array[Int]], lastAnswers: scala.Array[Int]): scala.Array[Int] = {
    queries match {
      case Nil => lastAnswers
      case head :: Nil =>
        val (t, x, y) = (head(0), head(1), head(2))
        val idx = (x ^ zeroOrLast(lastAnswers)) % n
        val seq = seqList(idx)
        if (t == 1) {
          seqList(idx) = seq :+ y
          lastAnswers
        } else {
          val elem = seq(y % seq.length)
          lastAnswers :+ elem
        }
      case head :: tail =>
        val (t, x, y) = (head(0), head(1), head(2))
        val idx = (x ^ zeroOrLast(lastAnswers)) % n
        val seq = emptyOrSequence(seqList, idx)
        //val seq = seqList(idx)
        if (t == 1) {
          seqList(idx) = seq :+ y
          recurse(n, tail, seqList, lastAnswers)
        } else {
          val elem = seq(y % seq.length)
          recurse(n, tail, seqList, lastAnswers :+ elem)
        }
    }
  }

  def emptyOrSequence(seqList: scala.Array[scala.Array[Int]], idx: Int): Array[Int] = {
    Try{seqList(idx)}.fold(_ => Array.empty[Int], r => r)
  }

  def zeroOrLast(array: scala.Array[Int]): Int = {
    if (array.isEmpty) 0
    else array.last
  }

  def main(args: Array[String]) {

    val n = 2

    val queries1 = Array(Array(1, 0, 5),
                        Array(1, 1, 7),
                        Array(1, 0, 3),
                        Array(2, 1, 0),
                        Array(2, 1, 1))

    val result1 = dynamicArray(n, queries1)
    val expected1 = Array(7, 3)
    assert(result1.sameElements(expected1), s"got ${result1.mkString(" ")} want ${expected1.mkString(" ")}")

    val input = Source.fromResource("dynamicarrayinput.txt").getLines.toArray
    val queries2 = input.map(_.split(" ").map(_.trim.toInt))
    val result2 = dynamicArray(100, queries2)
    val expected2 = """|855677723
                      |75865401
                      |763845832
                      |75865401
                      |709571211
                      |645102173
                      |112869154
                      |763845832
                      |449768243
                      |757848208
                      |91038209
                      |205970905
                      |783321829
                      |372160176
                      |384358662
                      |67467208
                      |935143105
                      |384358662
                      |309720694
                      |138234911""".stripMargin.split("\n").map(_.trim.toInt)
    assert(result2.sameElements(expected2), s"got ${result1.mkString(" ")} want ${expected1.mkString(" ")}")

  }

}
