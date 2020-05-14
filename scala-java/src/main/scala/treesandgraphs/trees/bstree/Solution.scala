package treesandgraphs.trees.bstree

/*
 * Implementation of a Binary Search using the DSW Algorithm.
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

  def size: Int = {
    val leftSize = left.fold(0) { l =>
      l.size
    }
    val rightSize = right.fold(0) { r =>
      r.size
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
    * Performs a left rotation assuming this
    * BSTNode is the grandfather.
    * lvl0    10               20
    *        /  \  left-rot   /  \
    *       /    \           /    \
    * lvl1  5   20    =>    10     25
    *          /  \        /  \   /  \
    * lvl2    15  23      5   15 23  30
    *              \                /  \
    * lvl3         25              28  40
    *               \
    *               28
    *                \
    *                30
    *                 \
    *                 40
    *
    *    Before      After
    * lvl0    Gr          Gr
    *          \           \
    * lvl1     Par         Ch
    *         /  \        /  \
    * lvl2   Ch   Z      X   Par
    *       /  \            /  \
    * lvl3 X    Y          Y    Z
    *
    * @return The updated grandfather node.
    */
  def rotateLeft2: BSTNode = {
    // before
    val oldGr: BSTNode = this
    val oldPar: Option[BSTNode] = this.right
    val oldCh: Option[BSTNode] = oldPar.flatMap(_.left)
    val oldZ: Option[BSTNode] = oldPar.flatMap(_.right)
    val oldX: Option[BSTNode] = oldCh.flatMap(_.left)
    val oldY: Option[BSTNode] = oldCh.flatMap(_.right)
    // after
    val newY: Option[BSTNode] = oldY.map(_.copy())
    val newZ: Option[BSTNode] = oldZ.map(_.copy())
    val newPar: Option[BSTNode] = oldPar.map(_.copy(left = newY, right = newZ))
    val newX: Option[BSTNode] = oldX
    val newCh: Option[BSTNode] = oldCh.map(_.copy(left = newX, right = newPar))
    BSTNode(oldGr.data, right = newCh)
    //oldGr.copy(right = newCh)
  }

//  def leftLRotate(grandParent: Option[BSTNode], parent: BSTNode, rightChild: BSTNode): BSTNode = {
//    if (grandParent.isDefined) grandParent.foreach(_.right = Some(rightChild))
//    else {
//      this = rightChild
//    }
//    parent.right = rightChild.left
//    rightChild.left = Some(parent)
//    grandParent.getOrElse(this)
//  }

  def rotateLeft: BSTNode = {
    val oldRoot = this.copy(right = this.right.flatMap(_.left))
    val newGr: Option[BSTNode] = this.right.map(_.copy(left = Some(oldRoot)))
    newGr.get
  }

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

  def insert(dataInsert: Int): BSTNode = {
    if (dataInsert > data) {
      right.fold(this.copy(right = Some(BSTNode(dataInsert)))) { r =>
        this.copy(right = Some(r.insert(dataInsert)))
      }
    } else {
      left.fold(this.copy(left = Some(BSTNode(dataInsert)))) { l =>
        this.copy(left = Some(l.insert(dataInsert)))
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

  val bst: BSTNode = createBst(BSTNode(numbers.head), numbers.tail)
  //val bst: BSTNode = createPerfectTree()

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

  def makeRotations(bound: Int): BSTNode = {
    ???
//    if (bound == 0) root
//    else {
//      makeRotations(root.rotateLeft, bound - 1)
//    }
  }

  private def createBst(root: BSTNode, numbers: List[Int]): BSTNode = {
    numbers match {
      case Nil => root
      case head :: Nil => root.insert(head)
      case head :: tail => createBst(root.insert(head), tail)
    }
  }

  /**
    * Time complexity: log(n)
    * return the index of most significant set bit: index of
    * least significant bit is 0
    */
  def msb(n: Int, idx: Int): Int = {
    if (1 < n) msb(n >> 1, idx + 1)
    else idx
  }

  def greatestPowerOf2LessThanN(n: Int): Int = {
    1<<msb(n, 0)
  }

}

object Solution {

  //val numbers = List(9, 1, 0, 2, 3, 4, 6, 8, 7, 10, 5)
  val numbers = List(5, 10, 15, 20, 23, 25, 28, 30, 40)

  def main(args: Array[String]): Unit = {
    val bst = new BinaryTree(numbers)
    println("Backbone:")
    println(bst.backBone)
    println
    println("2 rotations:")
//    println(bst.makeRotations(bst.backBone, 2))
  }

}
