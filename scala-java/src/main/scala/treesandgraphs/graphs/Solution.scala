package treesandgraphs.graphs

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

  }
}

class Graph(val edges: List[Edge], val size: Int) {

  val adjMap: Map[Int, List[Int]] = edges.foldLeft(Map.empty[Int, List[Int]]) { (acc, a) =>
    val source = a.source
    val destination = a.destination
    updateNeibors(source, destination, acc)
  }

//  def bfs(queue1: List[Int], queue2: List[Int]): Int = {
//
//  }
//  def bfs(nodeStart: Int, visitedMap: Map[Int, Boolean], acc: List[List[Int]]): List[List[Int]] = {
//    val neibors = adjMap.getOrElse(nodeStart, List.empty)
//    neibors.
//
//
//    neibors.iterator.flatMap(n => {
//      val isVisited = visitedMap.getOrElse(n, false)
//      if (!isVisited) {
//        bfs(n, visitedMap, acc)
//      } else {
//        acc
//      }
//    }).toList
//
//  }

  def shortestPath(source: Node, destination: Node): List[Node] = {
    List.empty
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