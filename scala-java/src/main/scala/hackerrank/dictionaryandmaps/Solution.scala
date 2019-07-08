package hackerrank.dictionaryandmaps

import scala.io.StdIn

object Solution {

  type Name = String
  type PhoneNb = Int
  type PhoneBook = Map[Name, PhoneNb]
  type PhoneBookEntry = (Name, PhoneNb)

  def main(args: Array[String]): Unit = {
    val nbQueries = StdIn.readInt()

    val phoneBook: PhoneBook = (0 until nbQueries).map{ _ =>
      processLine(StdIn.readLine)
    }.toMap

    val initialLine = StdIn.readLine
    processLines(initialLine, phoneBook)
  }

  def processLine(line: String): PhoneBookEntry = {
    val split = line.split(" ")
    (split.head, split.last.toInt)
  }

  def processLines(line: String, phoneBook: PhoneBook): String = {
    if (line.isEmpty || line == "") line
    else {
      println(phoneBook.get(line).fold("Not found") {phone => s"$line=$phone"})
      processLines(StdIn.readLine, phoneBook)
    }
  }

}
