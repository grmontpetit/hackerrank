package hackerrank.hopper

import java.io.{File, PrintWriter}

object Solution {
  case class Heading(weight: Int, text: String) {
    def key: String = {
      s"${this.weight}.${this.text}"
    }
  }
  case class Node(heading: Heading, children: List[Node])

  final val root = Heading(0, "")

  def main(args: Array[String]): Unit = {
    val content =
      """
        |H1 This is the main title
        |H2 The Quick
        |H3 The Fast
        |H2 The Lazy
        |H3 The Hard Working
        |H3 The Absent
        |H5 The Last Effort
        |H5 The Final Judgment
        |H1 The Reincarnation
        |H2 The Redo
        |H2 The Awakening
      """.stripMargin

//    val headings: Iterator[Heading] = scala.io.Source.stdin.getLines.flatMap(parse)
    val headings: Iterator[Heading] = content.split("\\n").flatMap(parse).iterator
    val outline: Node = toOutline(headings)
    val html: String = toHtml(outline).trim
    write(html)

  }

  def write(s: String): Unit = {
    val writer = new PrintWriter(new File("output.html"))
    writer.write(s)
    writer.close()
  }

  /** Converts a list of input headings into nested nodes */
  def toOutline(headings: Iterator[Heading]): Node = {
    // Implement this function. Sample code below builds an
    // outline of only the first heading.

    def recurse(pastElements: List[Heading], remainingElements: List[Heading], acc: Map[Heading, Array[Heading]]): Map[Heading, Array[Heading]] = {
      remainingElements match {
        case Nil => acc
        case head :: Nil =>
          val parentIndex = findParent(pastElements, head, acc)
          insertAt(parentIndex, head, acc)
        case head :: tail =>
          val parentIndex = findParent(pastElements, head, acc)
          recurse(pastElements :+ head, tail, insertAt(parentIndex, head, acc))
      }
    }

    def findParent(pastElements: List[Heading], currentIndex: Heading, acc: Map[Heading, Array[Heading]]): Heading = {
      if (pastElements.nonEmpty) {
        if (pastElements.last.weight < currentIndex.weight) pastElements.last
        else if (pastElements.last.weight > currentIndex.weight) findParent(pastElements.dropRight(1), currentIndex, acc)
        else if (pastElements.last.weight == currentIndex.weight) findParent(pastElements.dropRight(1), currentIndex, acc)
        else root
      }
      else root
    }

    def insertAt(parentIndex: Heading, element: Heading, acc: Map[Heading, Array[Heading]]): Map[Heading, Array[Heading]] = {
      val parent = acc.get(parentIndex)
      parent.fold(acc ++ Map(parentIndex -> Array(element))){ array => acc ++ Map(parentIndex -> (array :+ element))}
    }

    def treeItr(parent: Heading, tree: Map[Heading, Array[Heading]]): List[Node] = {
      val children = tree.get(parent)
      if (children.isDefined) {
        children.get.map(c => Node(c, treeItr(c, tree))).toList
      } else {
        // it's a leaf
        List()
      }
    }

    val tree: Map[Heading, Array[Heading]] = recurse(List.empty, headings.toList, Map.empty)
    Node(root, treeItr(root, tree))

  }

  /** Parses a line of input.
    This implementation is correct for all predefined test cases. */
  def parse(record: String): Option[Heading] = {
    val H = "H(\\d+)".r
    record.split(" ", 2) match {
      case Array(H(level), text) =>
        scala.util.Try(Heading(level.toInt, text)).toOption
      case _ => None
    }
  }

  /** Converts a node to HTML.
    This implementation is correct for all predefined test cases. */
  def toHtml(node: Node): String = {
    val childHtml = node.children match {
      case Nil => ""
      case children =>
        "<ol>" + children.map(
          child => "<li>" + toHtml(child) + "</li>"
        ).mkString("\n") + "</ol>"
    }
    val heading =
      if (node.heading.text.isEmpty) ""
      else node.heading.text + "\n"
    heading + childHtml
  }
}

