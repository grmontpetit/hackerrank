package hackerrank.binarynumbers

object Solution {

  def main(args: Array[String]): Unit = {
    val stringBin = toBinRepresentation(13, Array.empty).mkString
    println(stringBin.split("0").max.size)
  }

  def toBinRepresentation(nb: Int, acc: Array[Int]): Array[Int] = {
    if (nb / 2 == 1) 1 +: (nb % 2) +: acc
    else {
      toBinRepresentation(nb / 2, nb % 2 +: acc)
    }
  }

}
