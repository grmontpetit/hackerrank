package envvars

import scala.util.Try

object Solution {

  envOrElse()

  def envOrElse(): Unit = {
    println("env var: " + Try(sys.env("BOGUS_BOOLEAN").toBoolean).toOption.getOrElse(false))
  }

}
