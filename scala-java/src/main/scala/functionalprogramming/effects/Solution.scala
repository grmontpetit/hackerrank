package functionalprogramming.effects

import cats.effect.IO

object Solution {

  def main(args: Array[String]): Unit = {
    printToConsole("Enter a number:\n").unsafeRunSync()
    val x = readFromConsole.unsafeRunSync()
    printToConsole(x).unsafeRunSync()
    // chaining
  }

  def printToConsole(message: String): IO[Unit] = {
    IO(println(message))
  }

  def readFromConsole: IO[String] = IO(scala.io.StdIn.readLine())
}
