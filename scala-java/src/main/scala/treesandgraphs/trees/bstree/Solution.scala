package treesandgraphs.trees.bstree

/*
 * Implementation of a Binary Search Tree using a mix of AVL and DSW.
 * This version is 100% scala based.
 *
 * Good slides: http://courses.cs.vt.edu/cs2604/spring05/mcpherson/note/BalancingTrees.pdf
 *
 * Read this implementation too: http://www.geekviewpoint.com/java/bst/dsw_algorithm
 * and the author is: Isai Damier
 *
 * We use the DSW Algorithm to create the initial tree but don't use
 * the algorithm to rebalance. The goal is to have a perfectly balanced
 * binary tree on odd number of nodes:
 *
 *        [ R ]
 *       /     \
 *     [a]      [b]
 *    /   \     /  \
 *  [c]   [d] [e]  [f]
 *
 * Balanced: You can say it is balanced because the height of the left and right
 * subtrees from every node differ by 1 or less (0 in this case).
 *
 * Perfect: You can say it is perfect because the number of nodes is equal
 * to 2^(n+1)-1 with n being the height of the tree, in this case (2^3) - 1 = 7
 *
 */
sealed trait Node

// A None parent node indicates that the node is the root of the tree
case class BSTNode(data: Int, var left: Option[BSTNode] = None, var right: Option[BSTNode] = None) extends Node {

  override def toString: String = toTree("", "").mkString("\n")

  /**
    * Count the height of the current node.
    * Example:
    *      5
    *     / \
    *    /   \
    *   2    10
    *       /  \
    *      2   17
    * If current node is 10, then height is 1
    * If current node is 5, then height is 2
    * @return The height of the current node. Can also be though of like
    *         the level of the node.
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
    * Count the number of nodes from the current node,
    * recursively.
    * Example:
    *      5
    *     / \
    *    /   \
    *   2    10
    *       /  \
    *      2   17
    * If current node is 10, then node count is 3.
    * If current node is 5, then node count is 5.
    * @return The node count of the current node.
    */
  def nodeCount: Int = {
    val leftSize = left.fold(0) { l =>
      l.nodeCount
    }
    val rightSize = right.fold(0) { r =>
      r.nodeCount
    }
    leftSize + rightSize + 1
  }

  /************************************************************************
    *   Before      After
    *    Gr          Gr
    *     \           \
    *     Par         Ch
    *    /  \        /  \
    *   Ch   Z      X   Par
    *  /  \            /  \
    * X    Y          Y    Z
    *
    * Gr: parent of the current node
    * Par: current node
    * Ch: right child node of the current node
    ***********************************************************************/
  def rotateRight: Option[BSTNode] = {
    val oldPar = this
    val oldCh = this.left
    oldCh.map(ch => ch.copy(right = Some(oldPar.copy(left = oldCh.flatMap(o => o.right)))))
  }

  /**
    * Rotate the current tree left for 1 iteration.
    * The rotation takes every second node and link nodes together.
    * @return The new balance tree root BSTNode
    */
  def leftRotateOnce: BSTNode = takeEvery2Left(this.right, Some(this)).getOrElse(this)

  /**
    * Creates a new instance of the tree by take every second node, performing
    * one rotation to its children and left linking every second node together.
    * Example:
    *     Original             Rotated Once
    *       10                      20
    *      /  \                    /  \
    *     5   20                  /    \
    *        /  \                10     \
    *       15  28              /  \    40
    *            \             5   15  /  \
    *            40                   /   50
    *           /  \                 28   /
    *          30  50               /  \ 45
    *              /               23  30
    *             45
    * @param current The current node to rotate from.
    * @param previous The previous node of the current node.
    * @return The current node rotated.
    */
  private def takeEvery2Left(current: Option[BSTNode], previous: Option[BSTNode]): Option[BSTNode] = {
    current.fold[Option[BSTNode]](previous) { node =>
      val currentLeft: Option[BSTNode] = current.flatMap(_.left)
      val newLeft: Option[BSTNode] = previous.map(n => n.copy(right = currentLeft))
      val next: Option[BSTNode] = current.flatMap(n => n.right.flatMap(_.right))
      val newRight = takeEvery2Left(next, current.flatMap(_.right))

      Some(node.copy(left = newLeft, right = newRight))
    }
  }

  /**
    * Utility function to print a binary tree.
    * @param prefix Is a branch representation, example: └── ... or ├── ...
    * @param childrenPrefix The prefix of the children.
    * @return A sequence of string representing the rows
    *         of the printable tree
    */
  private def toTree(prefix: String, childrenPrefix: String): Seq[String] = {
    val firstLine = prefix + s"$data"

    val firstChildren = left.fold(Seq.empty[String]) { l =>
      l.toTree(childrenPrefix + "L└── ", childrenPrefix + "     ")
    }
    val lastChild = right.fold(Seq.empty[String]) { r =>
      r.toTree(childrenPrefix + "R├── ", childrenPrefix + " |    ")
    }
    firstLine +: lastChild ++: firstChildren
  }

  def insert(dataInsert: Int): BSTNode = {
    if (dataInsert > data) {
      right.fold(this.copy(right = Some(BSTNode(dataInsert)))) { r =>
        this.copy(right = Some(r.insert(dataInsert)))
      }
    } else {
      left.fold(this.copy(left = Some(BSTNode(dataInsert)))) { l =>
        this.copy(left = Some(l.insert(dataInsert)))
      }
    } else {
      right.fold(this.copy(right = Some(BSTNode(dataInsert)))) { r =>
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

  val backBone: BSTNode = createBackBone(numbers)

  //val bst: BSTNode = createBst(BSTNode(numbers.head), numbers.tail)
  val bst: BSTNode = createBalancedTree(backBone)

  /**
    * Backbone takes a list of unordered numbers,
    * sort them using quicksort and creates the backbone
    * or vine.
    * Example of a backbone:
    * 5
    *  \
    *   6
    *    \
    *     7
    *      \
    *       8
    * In essence, it's a linked list that only uses
    * the right side.
    * @param numbers The unordered list of numbers.
    * @return A BSTNode which is the first node (root).
    */
  private def createBackBone(numbers: List[Int]): BSTNode = {
    def linkedList(root: BSTNode, list: List[Int]): BSTNode = {
      list match {
        case Nil => root
        case head :: Nil => root.insert(head)
        case head :: tail => linkedList(root.insert(head), tail)
      }
    }
    val sorted = numbers.sortWith(_ < _)
    linkedList(BSTNode(sorted.head), sorted.tail)
  }

  /**
    * Creates an unbalanced, un-perfect binary tree. The creation
    * of the nodes is dependent on the order at which the nodes
    * are inserted.
    * @param root The root of the tree, this root is then returned
    *             with all connected children.
    * @param numbers The list of numbers to insert into the root.
    * @return The root of the binary tree.
    */
  private def createBst(root: BSTNode, numbers: List[Int]): BSTNode = {
    numbers match {
      case Nil => root
      case head :: Nil => root.insert(head)
      case head :: tail => createBst(root.insert(head), tail)
    }
  }

  /**
    * Using the initial backbone, rotate X number of times.
    * The number of times X is defined as while left.height - right.height > 1.
    * @param unbalanced The un-balanced iterative versions of the backbone.
    * @return The balanced tree
    */
  private def createBalancedTree(unbalanced: BSTNode): BSTNode = {
    val leftHeight: Int = unbalanced.left.fold(0) {l => l.height}
    val rightHeight: Int = unbalanced.right.fold(0) {r => r.height}
    if (rightHeight - leftHeight > 1) createBalancedTree(unbalanced.leftRotateOnce)
    else unbalanced
  }

  def greatestPowerOf2LessThanN(n: Int): Int = {
    1<<msb(n, 0)
  }

  def printBst(): Unit = {
    print(bst)
  }

  def leftRightSizePrint(): Unit = {
    println
    if (bst.left.isDefined) println(s"left size: ${bst.left.get.size}")
    if (bst.right.isDefined) println(s"right size: ${bst.right.get.size}")
  }

  def simplePrint(): Unit = bst.console
}

object Solution {

  //val numbers = List(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)
  val numbers = List(5, 10, 15, 20, 23, 28, 30, 40, 45, 50)

  def main(args: Array[String]): Unit = {
    val bst = new BinaryTree(numbers)
    println("Backbone:")
    println(bst.backBone)

    val rotated = bst.backBone.leftRotateOnce.leftRotateOnce
    println("Left Rotated 2 times:")
    println(rotated)

    println(bst.bst)
  }

}
