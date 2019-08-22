package dynamicprogrammingwithmemoization.minmaxheaps

object Main {

  // https://stackoverflow.com/questions/15319561/how-to-implement-a-median-heap/15319593
  def main(args: Array[String]): Unit = {
    val numbers: List[Int] = List(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)
    val heaps = new Heaps(numbers)
    println(heaps.maxHeap)
    if (heaps.minHeap.isDefined) println(heaps.minHeap.get)
  }

}
