package treesandgraphs.trees.avltree

sealed trait Node

sealed trait BSTNode extends Node {
  def insert(data: Int): BSTNode
}

case class AVLNode(data: Int, left: Option[AVLNode] = None, right: Option[AVLNode] = None) extends BSTNode {

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

  def insert(dataInsert: Int): AVLNode = {
    if (dataInsert > data) {
      left.fold(this.copy(left = Some(AVLNode(dataInsert)))) { l =>
        this.copy(left = Some(l.insert(dataInsert)))
      }
    } else {
      right.fold(this.copy(right = Some(AVLNode(dataInsert)))) { r =>
        this.copy(right = Some(r.insert(dataInsert)))
      }
    }
  }

  def console: Unit = {
    simplePrint(data, "root")
  }

  private def simplePrint(parent: Int, t: String): Unit = {
    print(s"($parent)-> ($data $t) ")
    if (left.isDefined) left.get.simplePrint(data, "l")
    if (right.isDefined) right.get.simplePrint(data, "r")
  }

}

class BinaryTree(numbers: List[Int]) {

  val bst: AVLNode = createBst(AVLNode(numbers.head), numbers.tail)

  private def createBst(root: AVLNode, numbers: List[Int]): AVLNode = {
    numbers match {
      case Nil => root
      case head :: Nil => root.insert(head)
      case head :: tail => createBst(root.insert(head), tail)
    }
  }

  def printBst: Unit = {
    print(bst)
  }

  def simplePrint: Unit = bst.console
}

object Solution {

  val numbers = List(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)

  def main(args: Array[String]): Unit = {
    val bst = new BinaryTree(numbers)
    bst.printBst
  }

}
