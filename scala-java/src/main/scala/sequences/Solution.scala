package sequences

object Solution {

  def main(args: Array[String]): Unit = {
    val stringSeq = Seq("", "bbb")
    val intSeq = Seq(1, 2)
//    unit(stringSeq => stringSeq)

  }


//  def unit[A](f: A => Seq[A]): GroupBuilder[A] = new GroupBuilder[A] {
//    override def apply(a: A): Seq[A] = a match {
//      case s: String =>
//        println("string")
//        f.apply(s)
//      case i: Int => f.apply(i)
//      case _ => f.apply(a)
//    }
//  }
}

trait GroupBuilder[A] { self =>
  def apply(a: A): Seq[A]
}
