package treesandgraphs.graphs

import scala.collection.mutable

case class Node(value: Int, adjList: List[Node] = List.empty)
case class Edge(source: Node, destination: Node)

object Solution {

  def main(args: Array[String]): Unit = {
    val edges = List(
      Edge(Node(1), Node(2)),
      Edge(Node(1), Node(4)),
      Edge(Node(4), Node(5)),
      Edge(Node(5), Node(7)),
      Edge(Node(7), Node(6)),
      Edge(Node(3), Node(6)),
      Edge(Node(1), Node(6)),
      Edge(Node(2), Node(6)),
      Edge(Node(2), Node(3))
    )

    val graph = new Graph(edges, 9)
    graph.printAdj()
    val bfs = graph.bfs(7)
    println(bfs)
  }
}

class Graph(val edges: List[Edge], val size: Int) {

  val adjMap: Map[Int, List[Int]] = edges.foldLeft(Map.empty[Int, List[Int]]) { (acc, a) =>
    val source = a.source
    val destination = a.destination
    updateNeibors(source, destination, acc)
  }

  def bfs(initialVertex: Int): List[Int] = {
    val mutableVisited: mutable.Stack[Int] = new scala.collection.mutable.Stack[Int].empty
    val mutableNotVisited: mutable.Queue[Int] = new scala.collection.mutable.Queue[Int].empty

    recurseBfs(mutableNotVisited.enqueue(initialVertex), mutableVisited)
  }

  def recurseBfs(notVisited: mutable.Queue[Int], visited: mutable.Stack[Int]): List[Int] = {
    if (notVisited.isEmpty) visited.toList
    else {
      val front = notVisited.dequeue()
      visited.push(front)
      val neibors = adjMap.getOrElse(front, List.empty).filterNot(n => visited.contains(n) || notVisited.contains(n))
      neibors.foreach(n => notVisited.enqueue(n))
      recurseBfs(notVisited, visited)
    }
  }

  def paths(startNode: Int, visited: List[Int], notVisited: List[Int]): List[Int] = {
    val neibors: List[Int] = adjMap.getOrElse(startNode, List.empty)
    val visitList: List[Int] = neibors.filterNot(i => visited.contains(i))

    val updatedVisited: List[Int] = visited :+ startNode
    val updatedNotVisited: List[Int] = notVisited.filterNot(_ == startNode) ++ visitList

    visitList
      .iterator
      .flatMap(node => {
        if (visited.contains(node)) {
          List.empty
        } else {
          updatedVisited ++ paths(node, updatedVisited, updatedNotVisited)
        }
      }).toList
  }

  def updateNeibors(source: Node, destination: Node, map: Map[Int, List[Int]]): Map[Int, List[Int]] = {
    val updatedSourceNeibors = map.getOrElse(source.value, List.empty[Int]) :+ destination.value
    val updatedDestinationNeibors = map.getOrElse(destination.value, List.empty[Int]) :+ source.value
    map.updated(source.value, updatedSourceNeibors).updated(destination.value, updatedDestinationNeibors)
  }

  def printAdj(): Unit = {
    adjMap.foreach(println)
  }

}