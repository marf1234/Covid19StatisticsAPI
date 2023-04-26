name := "Covid19Statistic"

version := "1.0"

scalaVersion := "3.2.2"

libraryDependencies ++= Seq(
  "org.apache.httpcomponents" % "httpclient" % "4.5.13",
  "org.json" % "json" % "20201115"
)

mainClass in Compile := Some("scala.Main")
