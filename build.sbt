name := "ScranScan.Backend"

version := "0.1"

scalaVersion := "2.12.8"


resolvers ++= Seq("Typesafe repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Spray Repository" at "http://repo.spray.io")



libraryDependencies += "org.scalaj" % "scalaj-http_2.12" % "2.3.0"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-actor" % "2.5.19",
  "com.typesafe.akka" %% "akka-testkit" % "2.5.19" % Test
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-stream" % "2.5.19",
  "com.typesafe.akka" %% "akka-stream-testkit" % "2.5.19" % Test
)

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % "10.1.5",
  "com.typesafe.akka" %% "akka-http-testkit" % "10.1.5" % Test
)

libraryDependencies += "io.spray" %% "spray-json" % "1.3.5"
