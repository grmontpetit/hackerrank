package patternmatching

object Solution {

  def main(args: Array[String]): Unit = {

    val teas = Seq(Tea(0), Tea(1))
    val serialized = serializeTeas(teas)
    println(serialized)
  }

  def serializeTeas(teas: Seq[Tea]): Seq[String] = teas match {
    // This function assumes that the article is Open Access
    case o if o.contains(Teas.earl_grey) => Seq("earl_grey", "tea")
    case o if o.contains(Teas.green) => Seq("green", "tea")
    case _ => Seq("it's water", "tea")
  }

}

object Teas extends Enumeration {
  val earl_grey = Tea(0)
  val green = Tea(1)
  val water = Tea(2)
}

case class Tea(index: Int) extends Ordered[Tea] {
  def compare(that: Tea): Int = this.index.compare(that.index)
}



