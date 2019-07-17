package hackerrank.newyearchaos

import scala.collection.Map
import scala.io.Source

object Solution2 {

  type Person = Int
  type Swaps = Int
  type SwapsMap = Map[Person, Swaps]
  type IsChaotic = Boolean

  def minimumBribes(q: Array[Int]): Unit = {
    itr(0, q, true, Map.empty, 0)
  }

  def itr(index: Int, queue: Array[Int], hasSwapped: Boolean, swaps: SwapsMap, swapsCount: Int): Array[Int] = {
    if (index == queue.length - 1) {
      if (hasSwapped) itr(0, queue, false, swaps, swapsCount)
      else {
        println(swapsCount)
        queue
      }
    } else if(queue(index) > queue(index + 1)) {
      val (updatedSwapsMap, isChaotic) = incrementSwaps(index, swaps, queue)
      if (isChaotic) {
        println("Too chaotic")
        queue
      }
      else itr(index + 1, unbribe(index, queue), true, updatedSwapsMap, swapsCount + 1)
    } else {
      itr(index + 1, queue, hasSwapped, swaps, swapsCount)
    }
  }

  def incrementSwaps(index: Int, swaps: SwapsMap, queue: Array[Int]): (SwapsMap, IsChaotic) = {
    val oldCount = swaps.getOrElse(queue(index), 0)
    val count: Int = oldCount + 1
    (swaps - queue(index) ++ Map(queue(index) -> count), count > 2)
  }

  def unbribe(index: Int, current: Array[Person]): Array[Person] = {
    val x = current(index)
    val y = current(index + 1)
    current(index) = y
    current(index + 1) = x
    current
  }

  def main(args: Array[String]) {

//    val queue1 = """2 1 5 3 4""".split(" ").map(_.trim.toInt)
//    val count1 = time(minimumBribes(queue1))
//    assert(count1 == Right(3), s"got $count1 want 3")
//
//    val queue2 = """2 5 1 3 4""".split(" ").map(_.trim.toInt)
//    val count2 = time(minimumBribes(queue2))
//    assert(count2 == Left(message), s"got $count2 want $message")
//
//    val queue3 = """1 2 5 3 4 7 8 6""".split(" ").map(_.trim.toInt)
//    val count3 = time(minimumBribes(queue3))
//    assert(count3 == Right(4), s"got ${count3.getOrElse("unknown")} want 4")
//
//    val queue4 = """1 2 5 3 7 8 6 4""".split(" ").map(_.trim.toInt)
//    val count4 = time(minimumBribes(queue4))
//    assert(count4 == Right(7), s"got ${count4.getOrElse("unknown")} want 7")
//
//    val queue5 = """5 1 2 3 7 8 6 4""".split(" ").map(_.trim.toInt)
//    val count5 = time(minimumBribes(queue5))
//    assert(count5 == Left(message), s"got $count5} want $message")

    val queues = Source.fromResource("input08.txt").getLines.toArray
    val lines = queues.tail
    val arrays: IndexedSeq[Array[Int]] = (1 to lines.length by 2)
      .map(i => lines(i).split(" ")
        .map(_.toInt))

    val expectedSource = """|119814
                            |120175
                            |119810
                            |119827
                            |Too chaotic""".stripMargin.split("\\n")

    arrays.indices.foreach { i =>
      //val expected = Try(expectedSource(i).toInt)

      val q = arrays(i)
      minimumBribes(q)

//      if (expected.isSuccess) {
//        assert(result == Right(expected.get), s"got $result want ${expected.get}")
//      } else {
//        assert(result == Left("Too chaotic"), s"got $result want ${expected.get}")
//      }
    }

//    val stdin = scala.io.StdIn
//
//    val t = stdin.readLine.trim.toInt
//
//    for (tItr <- 1 to t) {
//      val n = stdin.readLine.trim.toInt
//
//      val q = stdin.readLine.split(" ").map(_.trim.toInt)
//      minimumBribes(q)
//    }
  }
}
