package hackerrank.flatlandspacestations

import java.io.PrintWriter

import scala.io.Source

object Solution {

  /**
    *
    * @param n number of cities
    * @param c indices of cities containing a space station
    * @return maximum distance to travel to a city that has a space station
    */
  def flatlandSpaceStations(n: Int, c: Array[Int]): Int = {
    val len = c.length
    val sortedStations = c.sorted.toList
    if (len == 2) (sortedStations.last - sortedStations.head) - 2
    else if (n == c.length) 0
    else maxDistance(sortedStations.head, sortedStations.tail :+ n, sortedStations.head)
  }

  def maxDistance(lastIndex: Int, remaining: scala.List[Int], lastMax: Int): Int = {
    remaining match {
      case Nil => lastMax
      case head :: Nil =>
        val distance = math.abs(head - lastIndex - 1)
        if (distance >= lastMax) distance
        else lastMax
      case head :: tail =>
        val distance = math.abs(head - lastIndex).toFloat / 2
        if (distance >= lastMax) maxDistance(head, tail, distance.toInt)
        else maxDistance(head, tail, lastMax)
    }
  }

  def main(args: Array[String]): Unit = {
//    val stdin = scala.io.StdIn
//
//    val printWriter = new PrintWriter(sys.env("OUTPUT_PATH"))
//
//    val nm = stdin.readLine.split(" ")
//
//    val n = nm(0).trim.toInt
//
//    val m = nm(1).trim.toInt
//
//    val c = stdin.readLine.split(" ").map(_.trim.toInt)
//    val result = flatlandSpaceStations(n, c)
//
//    printWriter.println(result)
//
//    printWriter.close()
    assert(flatlandSpaceStations(5, Array(0, 4)) == 2, s"got ${flatlandSpaceStations(5, Array(0, 4))} want 2")
    //assert(flatlandSpaceStations(6, Array(0, 5)) == 0, s"got ${flatlandSpaceStations(6, Array(0, 5))} want 0")

    val stations = Source.fromResource("flatlandspacesstations.input").getLines.toArray
    val s = stations.head.split(" ").map(_.trim.toInt)
    val nbTown = 99999
    val mm = 1000
    val expected = 305
    val result = flatlandSpaceStations(nbTown, s)
    assert(result == expected, s"got $result want $expected")

    //val moreStations = Array(28000, 58701, 43043, 24909, 28572)
    val moreStations = Array(24909, 28000, 28572, 43043, 58701)
    val moreTowns = 99998
    val moreResult = flatlandSpaceStations(moreTowns, moreStations)
    val moreExpected = 41296
    assert(moreResult == moreExpected, s"got $moreResult want $moreExpected")
  }
}
