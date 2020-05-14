package dynamicprogrammingwithmemoization.minmaxheaps

trait Node {
  def insert(data: Int): Node
  def size: Int
}

case class MaxNode(data: Int, left: Option[MaxNode] = None, right: Option[MaxNode] = None) extends Node {

  def insert(data: Int): MaxNode = {
    if (data > this.data) {
      MaxNode(data, left = Some(this))
    } else {
      this.left.fold(this.copy(left = Some(MaxNode(data)))) { l =>
        if (data >= l.data) {
          val newLeft = MaxNode(data, left = Some(l))
          this.copy(left = Some(newLeft))
        } else {
          this.right.fold(this.copy(right = Some(MaxNode(data)))) { r =>
            if (data >= r.data) {
              val newRight = MaxNode(data, left = Some(r))
              this.copy(right = Some(newRight))
            } else {
              this.copy(right = Some(r.insert(data)))
            }
          }
        }
      }
    }
  }

  def insert(node: MaxNode): MaxNode = {
    if (node.data > this.data) {
      node.copy()
      MaxNode(data, left = Some(this))
    } else {
      this.left.fold(this.copy(left = Some(MaxNode(data)))) { l =>
        if (data >= l.data) {
          val newLeft = MaxNode(data, left = Some(l))
          this.copy(left = Some(newLeft))
        } else {
          this.right.fold(this.copy(right = Some(MaxNode(data)))) { r =>
            if (data >= r.data) {
              val newRight = MaxNode(data, left = Some(r))
              this.copy(right = Some(newRight))
            } else {
              this.copy(right = Some(r.insert(data)))
            }
          }
        }
      }
    }
  }

  def size: Int = {
    1 +
    this.left.fold(0) { l =>
      l.size
    } + this.right.fold(0) { r =>
      r.size
    }
  }

  /**
    * Returns a new copy of the Max-Heap with
    * the new root.
    * @return The value of the max and the new root
    *         MaxNode.
    */
  def extractMax: (Int, Option[MaxNode]) = {
    val rootValue = this.data
    val right = this.right
    val left = this.left
    val newRoot: Option[MaxNode] = right.fold(left){r =>
      left.fold[Option[MaxNode]](None){l =>
        Some(l.insert(r))
      }
    }
    (rootValue, newRoot)
  }

  override def toString: String = toTree("", "").mkString("\n")

  private def toTree(prefix: String, childrenPrefix: String): Seq[String] = {
    val firstLine = prefix + s"$data"

    val firstChildren = left.fold(Seq.empty[String]) { l =>
      l.toTree(childrenPrefix + "└── ", childrenPrefix + "    ")
    }
    val lastChild = right.fold(Seq.empty[String]) { r =>
      r.toTree(childrenPrefix + "├── ", childrenPrefix + "|    ")
    }
    firstLine +: lastChild ++: firstChildren
  }

}

case class MinNode(data: Int, left: Option[MinNode] = None, right: Option[MinNode] = None) extends Node {

  def insert(data: Int): MinNode = {
    if (data < this.data) {
      MinNode(data, left = Some(this))
    } else {
      this.left.fold(this.copy(left = Some(MinNode(data)))) { l =>
        if (data <= l.data) {
          val newLeft = MinNode(data, left = Some(l))
          this.copy(left = Some(newLeft))
        } else {
          this.right.fold(this.copy(right = Some(MinNode(data)))) { r =>
            if (data <= r.data) {
              val newRight = MinNode(data, left = Some(r))
              this.copy(right = Some(newRight))
            } else {
              this.copy(right = Some(r.insert(data)))
            }
          }
        }
      }
    }
  }

  def insert(node: MinNode): MinNode = {
    if (data < this.data) {
      MinNode(data, left = Some(this))
    } else {
      this.left.fold(this.copy(left = Some(MinNode(data)))) { l =>
        if (data <= l.data) {
          val newLeft = MinNode(data, left = Some(l))
          this.copy(left = Some(newLeft))
        } else {
          this.right.fold(this.copy(right = Some(MinNode(data)))) { r =>
            if (data <= r.data) {
              val newRight = MinNode(data, left = Some(r))
              this.copy(right = Some(newRight))
            } else {
              this.copy(right = Some(r.insert(data)))
            }
          }
        }
      }
    }
  }

  override def toString: String = toTree("", "").mkString("\n")

  private def toTree(prefix: String, childrenPrefix: String): Seq[String] = {
    val firstLine = prefix + s"$data"

    val firstChildren = left.fold(Seq.empty[String]) { l =>
      l.toTree(childrenPrefix + "└── ", childrenPrefix + "    ")
    }
    val lastChild = right.fold(Seq.empty[String]) { r =>
      r.toTree(childrenPrefix + "├── ", childrenPrefix + "|    ")
    }
    firstLine +: lastChild ++: firstChildren
  }

  def size: Int = {
    1 +
      this.left.fold(0) { l =>
        l.size
      } + this.right.fold(0) { r =>
      r.size
    }
  }

  /**
    * Returns a new copy of the Min-Heap with
    * the new root.
    * @return The value of the min and the new root
    *         MinNode.
    */
  def extractMin: (Int, Option[MinNode]) = {
    val rootValue = this.data
    val right = this.right
    val left = this.left
    val newRoot: Option[MinNode] = right.fold(left){r =>
      left.fold[Option[MinNode]](None){l =>
        Some(l.insert(r))
      }
    }
    (rootValue, newRoot)
  }

}

class Heaps(initialNumbers: List[Int]) {

  val left: MaxNode = maxHeap(MaxNode(initialNumbers.head), initialNumbers.tail)
  val right: MinNode = minHeap(MinNode(initialNumbers.head), initialNumbers.tail)

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

}

object Solution {
  // https://stackoverflow.com/questions/11361320/data-structure-to-find-median
  val numbers = List(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)

  def main(args: Array[String]): Unit = {
    val heaps = new Heaps(numbers)
    println(heaps.left)
    println(heaps.right)

  }

}
