lazy val `scala-java` =
  project
    .in(file("."))
    .settings(settings)
    .settings(
      resolvers ++= res,
      libraryDependencies ++= Seq(
        "com.lihaoyi" %% "utest" % "0.7.1" % Test,
        "commons-codec" % "commons-codec" % "1.10",
        "org.typelevel" % "cats-effect_2.12" % "1.4.0"
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

