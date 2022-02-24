val scala3Version = "3.1.1"
val commonmarkJavaVersion = "0.18.1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "trial-commonmark-java-with-scala",
    version := "0.1.0-SNAPSHOT",
    scalaVersion := scala3Version,
    libraryDependencies ++= Seq(
      "org.commonmark" % "commonmark" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-autolink" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-gfm-strikethrough" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-gfm-tables" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-heading-anchor" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-task-list-items" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-ins" % commonmarkJavaVersion,
      "org.commonmark" % "commonmark-ext-yaml-front-matter" % commonmarkJavaVersion
    )
  )
