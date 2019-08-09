package dynamicprogrammingwithmemoization.minmaxheaps

class Heaps(initialNumbers: List[Int]) {

  val (maxHeap, minHeap) = (maxHeap(MaxNode(initialNumbers.head), initialNumbers.tail), minHeap(MinNode(initialNumbers.head), initialNumbers.tail))
//  val left: MaxNode = maxHeap(MaxNode(initialNumbers.head), initialNumbers.tail)
//  val right: MinNode = minHeap(MinNode(initialNumbers.head), initialNumbers.tail)

  private def minHeap(root: MinNode, numbers: List[Int]): MinNode = {
    numbers match {
      case Nil => root
      case head :: Nil => root.insert(head)
      case head :: tail => minHeap(root.insert(head), tail)
    }
  }

  private def maxHeap(root: MaxNode, numbers: List[Int]): MaxNode = {
    numbers match {
      case Nil => root
      case head :: Nil => root.insert(head)
      case head :: tail => maxHeap(root.insert(head), tail)
    }
  }

  private def quickSelectMedian():
}
