version := "1.0.0"
name := "swagger-scala-akka-http-server"
organization := "io.swagger"
scalaVersion := "2.12.6"

val akkaVersion = "2.5.26"
val akkaHttpVersion = "10.1.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream"          % akkaVersion
)