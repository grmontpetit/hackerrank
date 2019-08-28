package functionalprogramming.effects

import cats.effect.IO

object Solution {

  def main(args: Array[String]): Unit = {
    //effect.runAsync(_.fold(a => "notfound", b =>))
  }

  def effect: IO[String] = {
    IO("Hello")
  }
}
