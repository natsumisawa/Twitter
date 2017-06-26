name := """twitter_project"""
organization := "com.example"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.11"

libraryDependencies += guice
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.0.0" % Test

//play-slickとplay-slick-evolutions(sqlをなんかするやつ)とmysqlを追加する
//xx % 名前 % バージョン
//xx %%(バージョンはscalaに合わせますよ) 名前 % バージョン
libraryDependencies ++= Seq(
  "com.typesafe.play" % "play-slick_2.11" % "2.0.2",
  "com.typesafe.play" % "play-slick-evolutions_2.11" % "2.0.2",
  "mysql" % "mysql-connector-java" % "5.1.36"
)

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "com.example.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "com.example.binders._"
