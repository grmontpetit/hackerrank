package dynamicprogrammingwithmemoization.minmaxheaps

case class MinNode(data: Int, left: Option[MinNode] = None, right: Option[MinNode] = None) extends Node {

  override def toString = toTree("", "").mkString("\n")

  override def toTree(prefix: String, childrenPrefix: String): Seq[String] = {
    val firstLine = prefix + s"$data"

    val firstChildren = left.fold(Seq.empty[String]) { l =>
      l.toTree(childrenPrefix + "L└── ", childrenPrefix + "     ")
    }
    val lastChild = right.fold(Seq.empty[String]) { r =>
      r.toTree(childrenPrefix + "R├── ", childrenPrefix + " |    ")
    }
    firstLine +: lastChild ++: firstChildren
  }

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