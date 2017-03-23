name := """CH07"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  jdbc,
  cache,
  ws,
  evolutions,
  specs2 % Test
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator

libraryDependencies ++= Seq(
  jdbc,
  "org.postgresql" % "postgresql" % "9.4-1201-jdbc41",
  "org.jooq" % "jooq" % "3.7.0",
  "org.jooq" % "jooq-codegen-maven" % "3.7.0",
  "org.jooq" % "jooq-meta" % "3.7.0"
)

val generateJOOQ = taskKey[Seq[File]]("Generate JooQ classes")

val generateJOOQTask = (baseDirectory, dependencyClasspath in Compile, runner in Compile, streams) map { (base, cp, r, s) =>
  toError(r.run(
    "org.jooq.util.GenerationTool",
    cp.files,
    Array("conf/chapter7.xml"),
    s.log))
  ((base / "app" / "generated") ** "*.scala").get
}

generateJOOQ <<= generateJOOQTask