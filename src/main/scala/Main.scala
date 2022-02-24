import org.commonmark.node.AbstractVisitor
import org.commonmark.node.Text
import org.commonmark.node.Node
@main def hello: Unit =
  val source = scala.io.Source.fromFile("inputs/test.md")
  val content = source.getLines.mkString("\n")
  val document = GitHubFlavoredTransformer.transform(content)
  val pw = new java.io.PrintWriter("output.html")
  pw.println(document)
  pw.close
  //document.accept(Visitor)

object Visitor extends AbstractVisitor:

  override def visit(node: Text): Unit =
    println(node)
    visitChildren(node)

    