package hackerrank.queuesandstacks

import scala.collection.mutable.Queue
import scala.util.control.Breaks


class Solution {

  private val queue = Queue.empty[Char]
  private var stack: List[Char] = List.empty[Char]

  //Write your code here
  def pushCharacter(c: Char): Unit = {
    stack = stack :+ c
  }

  def enqueueCharacter(c: Char): Unit = {
    this.queue.enqueue(c)
  }

  def popCharacter(): Char = {
    val c = stack.last
    stack = stack.dropRight(1)
    c
  }

  def dequeueCharacter(): Char = queue.dequeue()

}

object Solution {
  def main(args: Array[String]): Unit = {
//    // read the string s
//    var s=scala.io.StdIn.readLine()
    val s = "racecar"
//    // create the Solution class object p
    val obj = new Solution()
//    var i = 0
    val len=s.length()
//    //push/enqueue all the characters of string s to stack
    for(i <- 0 to len - 1 ){
      obj.pushCharacter(s.charAt(i))
      obj.enqueueCharacter(s.charAt(i))
    }

    var isPalindrome = true
    /*pop the top character from stack
      dequeue the first character from queue
      compare both the characters*/

    val loop = new Breaks
    loop.breakable {
      for(i <- 0 to len/2){
        if(obj.popCharacter() != obj.dequeueCharacter()){
          isPalindrome=false
          loop.break
        }
      }
    }
    assert(isPalindrome, s"got $isPalindrome want ${true}")
    //finally print whether string s is palindrome or not
//    if(isPalindrome){
//      println("The word, "+s+", is a palindrome." )
//    }
//    else{
//      println("The word, "+s+", is not a palindrome." )
//    }
  }
}
