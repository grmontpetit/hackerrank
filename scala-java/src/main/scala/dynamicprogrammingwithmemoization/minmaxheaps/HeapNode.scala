package dynamicprogrammingwithmemoization.minmaxheaps

trait Node[A] {
  val data: Int
  val left: Option[A]
  val right: Option[A]
}

trait HeapNode extends Node[HeapNode] {

  override def toString = toTree("", "").mkString("\n")

  /**
    * Inserts data into the current heap.
    * @param dataInsert The (Int) data to insert.
    * @return The new heap.
    */
  def insert(dataInsert: Int): HeapNode

  /**
    * Extract the minimum value of the current heap
    * and return a tuple that represents the value
    * extracted and the new Heap instance.
    * @return Min and new Instance of the heap.
    */
  def extractMin: (Int, Option[HeapNode])

  /**
    * Extract the maximum value of the current heap
    * and return a tuple that represents the value
    * extracted and the new Heap instance.
    * @return Max and new Instance of the heap.
    */
  def extractMax: (Int, Option[HeapNode])

  /**
    * Calculate the height of the tree
    * from the current node.
    * @return The height of the tree from the current node.
    */
  def height: Int = {
    val leftHeight = left.fold(1) { l =>
      1 + l.height
    }
    val rightHeight = right.fold(1) { r =>
      1 + r.height
    }
    if (leftHeight > rightHeight) leftHeight
    else rightHeight
  }

  /**
    * Formatting method to print a readable tree.
    * @param prefix The prefix.
    * @param childrenPrefix The children prefix.
    * @return The lines of the output.
    */
  def toTree(prefix: String, childrenPrefix: String): Seq[String] = {
    val firstLine = prefix + s"$data"

    val firstChildren = left.fold(Seq.empty[String]) { l =>
      l.toTree(childrenPrefix + "L└── ", childrenPrefix + "     ")
    }
    val lastChild = right.fold(Seq.empty[String]) { r =>
      r.toTree(childrenPrefix + "R├── ", childrenPrefix + " |    ")
    }
    firstLine +: lastChild ++: firstChildren
  }

  /**
    * Count the number of nodes from the current node.
    * @return The count of nodes from the current node.
    */
  def size: Int = {
    val leftSize = left.fold(0) { l =>
      1 + left.size
    }
    val rightSize = right.fold(0) { r =>
      1 + right.size
    }
    leftSize + rightSize
  }

}