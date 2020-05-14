package dynamicprogrammingwithmemoization.minmaxheaps

import scala.annotation.tailrec

class Heaps(initialNumbers: List[Int]) {

  val (maxHeap: MaxNode, minHeap: Option[MinNode]) = createHeaps(initialNumbers)

  private def createHeaps(numbers: List[Int]): (MaxNode, Option[MinNode]) = {
    val maxHeap: MaxNode = createMaxHeap(MaxNode(numbers.head), numbers.tail)

    def balance(maxHeap: MaxNode, minHeap: Option[MinNode]): (MaxNode, Option[MinNode]) = {
      if (math.abs(minHeap.fold(0){m => m.size} - maxHeap.size) > 1) {
        val (max, newMaxHeap) = maxHeap.extractMax
        newMaxHeap.fold(maxHeap, minHeap){h =>
          val newMinHeap: MinNode = minHeap.fold(MinNode(max)){m => m.insert(max)}
          balance(h, Some(newMinHeap))
        }
      } else {
        (maxHeap, minHeap)
      }
    }
    balance(maxHeap, None)
  }

  @tailrec
  private def createMaxHeap(root: MaxNode, numbers: List[Int]): MaxNode = {
    numbers match {
      case Nil => root
      case head :: Nil => root.insert(head)
      case head :: tail => createMaxHeap(root.insert(head), tail)
    }
  }
}
