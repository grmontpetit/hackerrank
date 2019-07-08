package hackerrank.samples

object FindNumber {

  def findNumber(arr: Array[Int], k: Int): String = {
    arr.find(_ == k).fold("NO")(_ => "YES")
  }
}
