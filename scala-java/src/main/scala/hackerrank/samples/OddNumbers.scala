package hackerrank.samples

object OddNumbers {
  def oddNumbers(l: Int, r: Int): Array[Int] = {
    (l to r).filter(n => n%2 != 0).toArray
  }
}
