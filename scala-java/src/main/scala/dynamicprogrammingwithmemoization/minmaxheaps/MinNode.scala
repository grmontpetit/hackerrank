package dynamicprogrammingwithmemoization.minmaxheaps

case class MinNode(data: Int,
                   left: Option[MinNode] = None,
                   right: Option[MinNode] = None) extends HeapNode {

  /**
    * Insert a node that has been extracted from a heap
    * into the current node.
    * @param dataInsert The node to insert.
    * @return The new instance of the MaxNode.
    */
  def insert(dataInsert: MinNode): MinNode = {
    if (dataInsert.data < data) {
      MinNode(data = dataInsert.data, left = Some(this))
    } else {
      this.copy(right = Some(right.fold[MinNode](MinNode(dataInsert.data)) {
        case node: MinNode => node.insert(MinNode(dataInsert.data))
      }))
    }
  }

  override def insert(dataInsert: Int): MinNode = {
    if (dataInsert < data) {
      MinNode(dataInsert, left = Some(this))
    } else {
      left.fold(this.copy(left = Some(MinNode(dataInsert)))) { l =>
        this.copy(left = Some(l.insert(dataInsert)))
      }
    }
  }

  override def extractMax: (Int, Option[MinNode]) = extractMax(data, this)

  private def extractMax(lastMax: Int, lastNode: MinNode): (Int, Option[MinNode]) = {
    val (currentMax, currentNode) = if (lastMax > data) (lastMax, lastNode) else (data, this.copy(left = None, right = None))
    val (leftMin, leftNode) = left.fold[(Int, Option[MinNode])]((lastMax, Some(lastNode))){l => l.extractMax(lastMax, lastNode)}
    val (rightMin, rightNode) = right.fold[(Int, Option[MinNode])]((lastMax, Some(lastNode))){r => r.extractMax(lastMax, lastNode)}
    if (leftMin > rightMin) {
      if (leftMin > currentMax) (leftMin, leftNode)
      else (currentMax, Some(currentNode))
    } else {
      if (rightMin > currentMax) (rightMin, rightNode)
      else (currentMax, Some(currentNode))
    }
  }

  override def extractMin: (Int, Option[MinNode]) = {
    val left = this.left
    val right = this.right
    val newMinNode: Option[MinNode] = left.fold(right){l => Some(right.fold(l){r => l.insert(r)})}
    (this.data, newMinNode)
  }

}
