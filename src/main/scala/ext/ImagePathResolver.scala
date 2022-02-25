package ext

import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.renderer.html.HtmlNodeRendererContext
import org.commonmark.renderer.NodeRenderer
import org.commonmark.renderer.html.HtmlWriter
import org.commonmark.node.Node
import org.commonmark.node.Image
import org.commonmark.renderer.html.HtmlNodeRendererFactory
import org.commonmark.parser.PostProcessor
import org.commonmark.parser.Parser.ParserExtension
import org.commonmark.parser.Parser
import java.util.IllegalFormatException
import org.commonmark.node.AbstractVisitor

class ImageVisitor(root: String) extends AbstractVisitor:

  override def visit(node: Image): Unit =
    val filename =
      node.getDestination.substring(node.getDestination.lastIndexOf("/") + 1)
    if (filename == null || filename.equalsIgnoreCase("")) then
      throw RuntimeException(
        s"Invalid path to image, ${node.getDestination}."
      )
    node.setDestination(root + filename)

class ImagePathResolver(root: String) extends PostProcessor:

  require(
    root.endsWith("/"),
    "The argument \"root\" should be ended with \"/\"."
  )

  override def process(node: Node): Node =
    node.accept(new ImageVisitor(root))
    node

object ImagePathResolver extends ParserExtension:
  private var imagePathRoot = "/"

  def configure(imagePathRoot: String): Unit =
    if imagePathRoot.endsWith("/") then this.imagePathRoot = imagePathRoot
    else this.imagePathRoot = imagePathRoot + "/"

  override def extend(builder: Parser.Builder): Unit =
    builder.postProcessor(new ImagePathResolver(imagePathRoot))
