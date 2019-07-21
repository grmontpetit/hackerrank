package hackerrank.anagrams

object Solution {


  /**
    * Count the number of discarded letters to form the same words within
    * 2 different char arrays.
    * @param word1
    * @param word2
    * @return An Int representing the sum of discarded letters in word1 and word2.
    */
  def discardedLettersCount(word1: Array[Char], word2: Array[Char]): Int = {
    val word1Map: Map[Char, Int] = word1.foldLeft(Map.empty[Char, Int]) { (acc, c) =>
      val count = acc.getOrElse(c, 0)
      acc.updated(c, count + 1)
    }

    val word2Map: Map[Char, Int] = word2.foldLeft(Map.empty[Char, Int]) { (acc, c) =>
      val count = acc.getOrElse(c, 0)
      acc.updated(c, count + 1)
    }

    word1Map.foldLeft(0) { case (acc, (k, v)) =>
      val c2 = word2Map.getOrElse(k, 0)
      acc + v - c2
    } + word2Map.foldLeft(0) { case (acc, (k, v)) =>
        val c1 = word1Map.getOrElse(k, 0)
        acc + v - c1
      }

  }

  def mergeMaps(c1: Map[Char, Int], c2: Map[Char, Int]): Map[Char, Int] = {
    c2 ++ c2
  }

  def main(args: Array[String]): Unit = {

    val pair1 = ("hello", "billion")
    val pair2 = ("glue", "legs")
    val pair3 = ("candy", "day")

    val got1 = discardedLettersCount(pair1._1.toCharArray, pair1._2.toCharArray)
    val got2 = discardedLettersCount(pair2._1.toCharArray, pair2._2.toCharArray)
    val got3 = discardedLettersCount(pair3._1.toCharArray, pair3._2.toCharArray)
    assert(got1 == 6, s"got $got1 want 6")
    assert(got2 == 2, s"got $got2 want 2")
    assert(got3 == 2, s"got $got3 want 2")
  }

}
