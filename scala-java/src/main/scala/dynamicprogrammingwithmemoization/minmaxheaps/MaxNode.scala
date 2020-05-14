package dynamicprogrammingwithmemoization.minmaxheaps

case class MaxNode(override val data: Int,
                   override val left: Option[MaxNode] = None,
                   override val right: Option[MaxNode] = None) extends HeapNode {

  /**
    * Insert a node that has been extracted from a heap
    * into the current node.
    * @param dataInsert The node to insert.
    * @return The new instance of the MaxNode.
    */
  def insert(dataInsert: MaxNode): MaxNode = {
    if (dataInsert.data > data) {
      MaxNode(data = dataInsert.data, left = Some(this))
    } else {
      this.copy(right = Some(right.fold[MaxNode](MaxNode(dataInsert.data)) {
        case node: MaxNode => node.insert(MaxNode(dataInsert.data))
      }))
    }
  }

  override def insert(dataInsert: Int): MaxNode = {
    if (dataInsert > data) {
      MaxNode(dataInsert, right = Some(this))
    } else {
      left.fold(this.copy(left = Some(MaxNode(dataInsert)))) { l =>
        this.copy(left = Some(l.insert(dataInsert)))
      }
    }
  }

  override def extractMax: (Int, Option[MaxNode]) = {
    val left = this.left
    val right = this.right
    val newMaxNode: Option[MaxNode] = left.fold(right){l => Some(right.fold(l){r => l.insert(r)})}
    (this.data, newMaxNode)
  }

  override def extractMin: (Int, Option[MaxNode]) = extractMin(data, this)

  private def extractMin(lastMin: Int, lastNode: MaxNode): (Int, Option[MaxNode]) = {
    val (currentMin, currentNode) = if (lastMin < data) (lastMin, lastNode) else (data, this.copy(left = None, right = None))
    val (leftMin, leftNode) = left.fold[(Int, Option[MaxNode])]((lastMin, Some(lastNode))){l => l.extractMin(lastMin, lastNode)}
    val (rightMin, rightNode) = right.fold[(Int, Option[MaxNode])]((lastMin, Some(lastNode))){r => r.extractMin(lastMin, lastNode)}
    if (leftMin < rightMin) {
      if (leftMin < currentMin) (leftMin, leftNode)
      else (currentMin, Some(currentNode))
    } else {
      if (rightMin < currentMin) (rightMin, rightNode)
      else (currentMin, Some(currentNode))
    }
  }

}
