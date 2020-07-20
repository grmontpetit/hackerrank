lazy val `scala-java` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      resolvers ++= res,
      libraryDependencies ++= Seq(
        "com.lihaoyi" %% "utest" % "0.7.1" % Test,
        "org.scalatest" %% "scalatest" % "3.0.8",
        "org.scalatest" %% "scalatest" % "3.0.8" % Test,
        "commons-codec" % "commons-codec" % "1.10",
        "org.typelevel" %% "cats-effect" % "2.0.0",
        "org.scalaz" %% "scalaz-core" % "7.2.27",
        "org.scalaz" %% "scalaz-effect" % "7.2.27",
        "com.propensive" % "rapture-json-jackson_2.11" % "1.1.0",
        "io.circe" %% "circe-core" % "0.12.1",
        "io.circe" %% "circe-generic" % "0.12.1",
        "io.circe" %% "circe-parser" % "0.12.1",
        "io.circe" %% "circe-shapes" % "0.12.1",
        "io.circe" %% "circe-generic-extras" % "0.12.1",
        "org.apache.hadoop" % "hadoop-client" % "2.8.5",
        "com.chuusai" %% "shapeless" % "2.3.3"
      )
    )

lazy val settings = commonSettings

lazy val res = Seq[Resolver](
  Resolver.DefaultMavenRepository
)

lazy val commonSettings =
  Seq(
    name := "scala-java",
    version := "0.1.0",
    scalaVersion := "2.12.11",
    organization := "grmontpetit",
    scalacOptions ++= Seq(
      "-unchecked",
      "-deprecation",
      "-language:_",
      "-target:jvm-1.8",
      "-encoding", "UTF-8",
    ),
    testFrameworks += new TestFramework("utest.runner.Framework")
  )

