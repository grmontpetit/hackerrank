package dynamicprogrammingwithmemoization.permutations

object Solution {

  def main(args: Array[String]): Unit = {
    println(permutations("abc"))
  }

  def permutations(s: String): List[String] = {
    if (s.length() == 1) List(s)
    else
      permutations(s.substring(0, s.length - 1)).flatMap { p =>
        merge(p, s.charAt(s.length - 1))
      }
  }

  def merge(ins: String, c: Char): Seq[String] = {
    (0 to ins.length).map(i => ins.substring(0, i) + c + ins.substring(i, ins.length))
  }
}