organization := "org.goldenport"

name := "pbj"

version := "0.0.2"

scalaVersion := "2.10.3"
// crossScalaVersions := Seq("2.10.39.2", "2.9.1")

scalacOptions += "-deprecation"

scalacOptions += "-unchecked"

scalacOptions += "-feature"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"

// resolvers += "GitHab releases 2019" at "https://raw.github.com/asami/maven-repository/2019/releases"

// resolvers += "GitHab releases 2020" at "https://raw.github.com/asami/maven-repository/2020/releases"

resolvers += "GitHab releases" at "https://raw.github.com/asami/maven-repository/2021/releases"

resolvers += "Asami Maven Repository" at "http://www.asamioffice.com/maven"

resolvers += "Local Maven Repository" at "file://"+Path.userHome.absolutePath+"/.m2/repository"

// override kaleidox
// libraryDependencies += "org.goldenport" %% "goldenport-scala-lib" % "1.2.75"

// override kaleidox
// libraryDependencies += "org.goldenport" %% "goldenport-record" % "1.3.30"

// override kaleidox
// libraryDependencies += "org.goldenport" %% "goldenport-sexpr" % "2.0.13"

// override kaleidox
// libraryDependencies += "org.smartdox" %% "smartdox" % "1.3.1"

libraryDependencies += "org.goldenport" %% "kaleidox" % "0.1.16"

libraryDependencies += "org.simplemodeling" %% "simplemodeler" % "1.0.0"

libraryDependencies += "org.goldenport" %% "arcadia" % "0.0.40"

libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.3"

libraryDependencies += "com.typesafe.play" %% "play-json" % "2.2.2"

libraryDependencies += "org.scalaj" %% "scalaj-http" % "2.4.1"

libraryDependencies += "commons-jxpath" % "commons-jxpath" % "1.3"

libraryDependencies += "cat.inspiracio" % "rhino-js-engine" % "1.7.7.1"

libraryDependencies += "org.apache.commons" % "commons-jexl3" % "3.0"

libraryDependencies += "org.scalanlp" %% "breeze" % "0.13.2"

libraryDependencies += "org.scalanlp" %% "breeze-viz" % "0.13.2"

libraryDependencies += "org.scalafx" %% "scalafx" % "8.0.181-R13"

libraryDependencies += "org.apache.camel" % "camel-core" % "2.23.1"

libraryDependencies += "com.amazonaws" % "aws-java-sdk" % "1.11.519"

libraryDependencies += "com.zaxxer" % "HikariCP-java7" % "2.4.13"

libraryDependencies += "mysql" % "mysql-connector-java" % "5.1.46"

libraryDependencies += "postgresql" %  "postgresql" % "8.4-702.jdbc4"

libraryDependencies += "com.h2database" % "h2" % "1.4.199"

libraryDependencies += "org.apache.spark" %% "spark-core" % "2.2.3" exclude("org.glassfish.hk2", "hk2-utils") exclude("org.glassfish.hk2", "hk2-locator") exclude("javax.validation", "validation-api") exclude("org.slf4j", "slf4j-log4j12") // Useing old version for Scala 2.10

libraryDependencies += "org.apache.spark" %% "spark-sql" % "2.2.3" exclude("org.glassfish.hk2", "hk2-utils") exclude("org.glassfish.hk2", "hk2-locator") exclude("javax.validation", "validation-api") exclude("org.slf4j", "slf4j-log4j12") // Useing old version for Scala 2.10

libraryDependencies += "org.apache.felix" % "org.apache.felix.main" % "5.4.0"

libraryDependencies += "org.eclipse.jetty" % "jetty-server" % "9.4.38.v20210224"

// libraryDependencies += "com.typesafe.akka" % "akka-http" % "10.2.2"

// libraryDependencies += "org.xerial" % "sqlite-jdbc" % "3.27.2.1"

libraryDependencies += "org.scalatest" %% "scalatest" % "2.2.6" % "test"

libraryDependencies += "junit" % "junit" % "4.10" % "test"

//
val mavenrepo = settingKey[String]("mavenrepo")

mavenrepo := sys.env.getOrElse("PUBLISH_MAVEN_REPO", default = "target/maven-repository")

publishTo <<= mavenrepo { v: String =>
  Some(Resolver.file("file", file(v)))
}

maintainer := "asami@asamioffice.com"

dockerBaseImage in Docker := "dockerfile/java"

// dockerExposedPorts in Docker := Seq(8080, 8080)

lazy val root = (project in file(".")).
  enablePlugins(BuildInfoPlugin).
  enablePlugins(JavaAppPackaging).
  settings(
    buildInfoKeys := Seq[BuildInfoKey](
      name, version, scalaVersion, sbtVersion,
      BuildInfoKey.action("build") {
        val fmt = new java.text.SimpleDateFormat("yyyyMMdd")
        fmt.setTimeZone(java.util.TimeZone.getTimeZone("JST"))
        fmt.format(new java.util.Date())
      }
    ),
    buildInfoPackage := "org.goldenport.kaleidox"
  )
