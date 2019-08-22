package strings

import org.apache.commons.codec.binary.Base64

object Base64Encoder {

  def main(args: Array[String]): Unit = {
    val message = "hello, world!"
    // BASE64Encoder doesn't seen to be included in the 1.10 JDK
    // val encoded1 = new sun.misc.BASE64Encoder().encode(message.getBytes("utf-8"))
    val encoded1: String = Base64.encodeBase64String(message.getBytes("utf-8"))
    val encoded2: String = java.util.Base64.getEncoder.encodeToString(message.getBytes("utf-8"))
    assert(encoded1 == encoded2, s"got $encoded1 want $encoded2")
  }

}
