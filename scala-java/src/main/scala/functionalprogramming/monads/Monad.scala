package functionalprogramming.monads

//trait Monad[A] {
//  def flatMap[B](f: A => Monad[B]): Monad[B]
//}

object Monad {
  def apply[A](v: A) = new Monad(v)
}
class Monad[A](value: A) {
  def nonEmpty: Boolean = value match {
    case Some(_) => true
    case s: Seq[String] => s.exists(_.nonEmpty)
    case a: Iterable[_] => a.nonEmpty
    case g: Monad[_] => g.nonEmpty
    case s: String => if (s == "") false else true
    case _ => false
  }
  def isEmpty: Boolean = value match {
    case Some(_) => false
    case s: Seq[String] => s.forall(_.isEmpty)
    case a: Iterable[_] => a.isEmpty
    case g: Monad[_] => g.isEmpty
    case s: String => if (s == "") true else false
    case _ => true
  }
  def unit: A = value
  def flatMap[B](f: A => Monad[B]): Monad[B] = {
    f(unit)
  }
  def map[B](f: A => B): Monad[B] = {
    new Monad(f(unit))
  }
  def liftOpt: Monad[Option[A]] = Monad(Some(value))

  def liftSeq: Monad[Seq[A]] = this.flatMap(a => Monad(Seq(a)))

  //  def from[B](f: B => A): GroupBuilderMonad[B] = new GroupBuilderMonad[B](f(unit))
  def or(m: Monad[A]): Monad[A] = {
    if (this.isEmpty) m
    else this
  }
}