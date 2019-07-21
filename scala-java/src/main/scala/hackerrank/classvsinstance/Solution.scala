package hackerrank.classvsinstance

object Solution {
  def main(args: Array[String]): Unit = {
    var T=scala.io.StdIn.readInt()
    var i=0
    for(i<-1 to T){
      var age=scala.io.StdIn.readInt()
      var p=new Person(age)
      p.amIOld()
      var j=0
      for(j<-1 to 3){
        p.yearPasses()
      }
      p.amIOld()
      System.out.println()

    }


  }
}

class Person {

  final val invalidAge = "Age is not valid, setting age to 0."
  final val young = "You are young."
  final val teenager = "You are a teenager."
  final val old = "You are old."

  var age: Int = 0

  def this(initialAge: Int) = {
    // Add some more code to run some checks on initialAge
    this()
    if (initialAge > 0) this.age = initialAge
    else {
      this.age = 0
      println(invalidAge)
    }
  }

  def amIOld(): Unit = {
    // Do some computations in here and print out the correct statement to the console
    if (this.age < 13) println(young)
    else if (this.age >= 13 && age < 18) println(teenager)
    else println(old)
  }

  def yearPasses(): Unit = {
    // Increment the age of the person in here
    this.age = this.age + 1
  }

}