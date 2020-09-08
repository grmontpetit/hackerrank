package option


object Main extends App {
  val hello: Option[String] = Some("Hello")
  val aList: Seq[String] = Seq()
  val test = Option(aList).filter(_.nonEmpty).map(x => (x, x))
  val secondTest = Option(aList).filter(_.nonEmpty)
  println(test)
  println(secondTest)
}
