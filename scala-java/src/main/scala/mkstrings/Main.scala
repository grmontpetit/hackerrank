package mkstrings

object Main extends App {
//  val x = List("feature1", "feature2", "feature3")
//
//  println(x.mkString(" "))
  val params: List[(String, String)] = List(
    "TEMPLATE_ID" -> "TEMPLATE_ID",
    "TEMPLATE_TAG" -> "TEMPLATE_TAG",
    "TEMPLATE_VERSION" -> "TEMPLATE_VERSION",
    "TEMPLATE_EXCERPT" -> "TEMPLATE_EXCERPT",
    "CHANNEL_ID" -> "CHANNEL_ID",
    "CHANNEL_VERSION" -> "CHANNEL_VERSION",
    "COMPONENTS" -> "COMPONENTS",
    "ORG_ID" -> "ORG_ID",
    "FORMATS" -> "FORMATS",
    "ENVIRONMENT" -> "ENVIRONMENT",
    "APP_NAME" -> "OpenDaylight"
  )
    def jTuple(name: String, value: String): String = s"""{"name":"$name","value":"$value"}"""
    val x = params.map(p => jTuple(p._1, p._2)).mkString(",")
    println(s"""json={"parameter"=[$x]}""")
}
