package scalatestmixin

trait Output {

  val stream = new java.io.ByteArrayOutputStream()
  def print(message: String): Unit = {
    Console.withOut(stream) {
      //all printlns in this block will be redirected
      println(message)
    }
  }

}
