package listconversions

object Main extends App {

  val x = List("component1", "component2")

  val query = "(components, " + x.fold("[")((last, current) => last + "\'" + current + "\',").dropRight(1) + "])"

  println(query)
}
