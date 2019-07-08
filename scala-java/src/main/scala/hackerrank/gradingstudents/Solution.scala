package hackerrank.gradingstudents

object Solution {

  def gradingStudents(grades: Array[Int]): Array[Int] = {
    // Write your code here
    grades.map(customRound)
  }

  def customRound(grade: Int): Int = {
    if (grade < 38) grade
    else if ((5 - (grade % 5)) < 3) grade + (5 - (grade % 5))
    else grade
  }

  def main(args: Array[String]): Unit = {
    println(gradingStudents(Array(73)).mkString(" "))
  }

}
