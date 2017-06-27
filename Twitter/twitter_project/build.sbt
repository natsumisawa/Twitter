name := """twitter_project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  "com.typesafe.slick" % "slick-codegen_2.11" % "3.1.1",
  "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test,
  "com.typesafe.play" %% "play-slick" % "2.0.2",
  "com.typesafe.play" %% "play-slick-evolutions" % "2.0.2",
  "mysql" % "mysql-connector-java" % "5.1.36"
)

// Slick code generator
//slickCodeGen <<= slickCodeGenTask // register sbt command
////(compile in Compile) <<= (compile in Compile) dependsOn (slickCodeGenTask) // register automatic code generation on compile
//lazy val slickCodeGen = taskKey[Seq[File]]("slick-codegen")
//lazy val slickCodeGenTask = (sourceManaged, dependencyClasspath in Compile, runner in Compile, streams) map { (dir, cp, r, s) =>
//  val slickDriver = "slick.driver.MySQLDrive"
//  val jdbcDriver = "com.mysql.jdbc.Driver"
//  val url = "jdbc:mysql://localhost:3306/twitter"
//  val username = "root"
//  val password = ""
//  val outputDir = "/"
//  val pkg = "models"
//  toError(r.run("slick.codegen.SourceCodeGenerator", cp.files, Array(slickDriver, jdbcDriver, url, outputDir, pkg), s.log))
//  val fname = outputDir + "/Tables.scala"
//  Seq(file(fname))
//}

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"