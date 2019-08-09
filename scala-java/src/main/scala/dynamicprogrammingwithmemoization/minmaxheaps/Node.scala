package dynamicprogrammingwithmemoization.minmaxheaps

trait Node {
  def toTree(prefix: String, childrenPrefix: String): Seq[String]
  def insert(data: Int): Node
  def size: Int
}
