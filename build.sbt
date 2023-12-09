ThisBuild / scalaVersion := "2.13.12"

lazy val root = (project in file("."))
  .settings(
    name := "ScalaServicePostgress" ,
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http" % "10.2.6",
      "com.typesafe.akka" %% "akka-stream" % "2.6.16",
      "com.typesafe.slick" %% "slick" % "3.3.3",
      "org.postgresql" % "postgresql" % "42.2.5",
      "de.heikoseeberger" %% "akka-http-json4s" % "1.37.0",
      // Оставляем только json4s-jackson, так как akka-http-json4s совместим с Jackson
      "org.json4s" %% "json4s-jackson" % "4.0.3",
      "com.typesafe.akka" %% "akka-actor" % "2.6.16",
    )
  )
