lazy val `scala-java` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      libraryDependencies ++= Seq(
        "com.lihaoyi" %% "utest" % "0.7.1" % Test,
        "commons-codec" % "commons-codec" % "1.10"
      )
    )

lazy val settings = commonSettings

lazy val commonSettings =
  Seq(
    name := "scala-java",
    version := "0.1.0",
    scalaVersion := "2.13.0",
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

