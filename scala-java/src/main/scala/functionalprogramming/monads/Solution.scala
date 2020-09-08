package functionalprogramming.monads

object Solution {
  def main(args: Array[String]): Unit = {
    val m = Monad("StringContent")

    assert(Seq("", "").forall(_.isEmpty))
    assert(m.map(a => a.replaceAll("S", "aS")).unit == "aStringContent")
    assert(!m.isEmpty)
    assert(Monad("").isEmpty)
    assert(!Monad(Seq("String")).isEmpty)
    assert(Monad(List.empty).isEmpty)
    assert(!Monad("").nonEmpty)
    assert(Monad(Seq("a", "b", "")).nonEmpty)
    assert(!Monad(Seq("", "", "")).nonEmpty)
    assert(Monad(Monad(Seq.empty)).isEmpty)
    assert(Monad(Monad(Seq(""))).isEmpty)
  }
}
