package overloading

import org.scalatest.Assertions

object Solution extends Assertions {

  def main(args: Array[String]): Unit = {
    val aSeq = Seq(Author("name1"), Author("name2"))
    val anotherSeq = Seq("name3", "name4")

    assertThrows[ClassCastException]{
      Authors(Seq.empty[Author]).withAuthors(aSeq)
    }
    assert(Authors(Seq.empty[Author]).withAuthors(anotherSeq) == Authors(List(Author("name3"), Author("name4"))))
  }

}

case class Authors(authors: Seq[Author]) {
  def withAuthors[T](xs: Seq[T]): Authors = {
    xs match {
      case a: Seq[String] =>
        Authors(a.map(x => x.toString).map(z => Author(z)))
      case a: Seq[Author] => Authors(a)
      case _ => Authors(Seq.empty[Author])
    }
  }
}
case class Author(name: String)