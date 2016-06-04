name := "IOSubjectDownloader"

version := "1.0"

scalaVersion := "2.11.7"


libraryDependencies += "com.typesafe.play" %% "play-json" % "2.4.6"

libraryDependencies += "com.typesafe.slick" %% "slick" % "3.1.1"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.38"

// http://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
libraryDependencies += "org.apache.httpcomponents" % "httpclient" % "4.5.2"

// http://mvnrepository.com/artifact/org.apache.httpcomponents/httpasyncclient
libraryDependencies += "org.apache.httpcomponents" % "httpasyncclient" % "4.1.1"

// http://mvnrepository.com/artifact/org.apache.commons/commons-io
libraryDependencies += "org.apache.commons" % "commons-io" % "1.3.2"


/*libraryDependencies += "org.scalatra" %% "scalatra" % "2.3.1"

libraryDependencies += "org.scala-lang" % "scala-reflect" % "2.11.7"

libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "1.0.4"

libraryDependencies += "org.scala-lang.modules" %% "scala-parser-combinators" % "1.0.4"*/
