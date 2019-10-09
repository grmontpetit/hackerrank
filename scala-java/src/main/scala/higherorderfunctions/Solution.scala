package higherorderfunctions

import Builder._

object Solution {

  def main(args: Array[String]): Unit = {

  }

}

object Builder {
  def unit[A](f: Set[A] => Set[Set[A]]): Builder[A] = new Builder[A] {
    override def apply(sa: Set[A]): Set[Set[A]] = f(sa)
  }
}

trait Builder[A] { self =>
  def apply(sa: Set[A]): Set[Set[A]]

  def byDepth(b: => Builder[A]): Builder[A] = unit { sa =>
    self.apply(sa).flatMap(b.apply)
  }

}

