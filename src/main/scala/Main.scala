import ext._
import api._

@main def hello: Unit =
  val source = scala.io.Source.fromFile("inputs/test.md")
  val content = source.getLines.mkString("\n")
  val bootstrap5 = Bootstrap5Extension
  bootstrap5.configure(Bootstrap5TableOptions(true, false, true, false, Set()))
  bootstrap5.configure(Bootstrap5ImageOptions(false, true, false, Set()))
  val trn = Transformer()
  val document = trn.using(GitHubFlavor()).using(bootstrap5).using(MermaidExtension).parse(content)
  val pw = new java.io.PrintWriter("output.html")
  pw.print(trn.render(document))
  pw.close
