package dynamicprogrammingwithmemoization.minmaxheaps

case class MaxNode(data: Int, left: Option[MaxNode] = None, right: Option[MaxNode] = None) extends Node {

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

  def levelOrder(parent: Int, t: String): Unit = {
    print(s"($parent)-> (${this.data} $t) ")
    if (left.isDefined) left.get.levelOrder(data, "l")
    if (right.isDefined) right.get.levelOrder(data, "r")
  }

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

}
