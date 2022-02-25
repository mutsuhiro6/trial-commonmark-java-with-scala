package ext

import org.commonmark.renderer.html.HtmlNodeRendererContext
import org.commonmark.renderer.html.HtmlWriter
import org.commonmark.renderer.NodeRenderer
import org.commonmark.node.Node
import org.commonmark.node.FencedCodeBlock
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.renderer.html.HtmlNodeRendererFactory

class MermaidRenderer(context: HtmlNodeRendererContext) extends NodeRenderer:

  import scala.jdk.CollectionConverters._

  private val writer: HtmlWriter = context.getWriter

  private val Mermaid = "mermaid"

  override def getNodeTypes(): java.util.Set[Class[_ <: Node]] =
    Set[Class[? <: Node]](classOf[FencedCodeBlock]).asJava

  override def render(node: Node): Unit =
    node match
      case block: FencedCodeBlock =>
        if block.getInfo == Mermaid then
          writer.line
          writer.tag("div", Map("class" -> Mermaid).asJava)
          writer.text(block.getLiteral)
          writer.tag("/div")
          writer.line
        else renderFencedCodeBlock(block)

  /** Borrowed from [[org.commonmark.renderer.html.CoreHtmlNodeRenderer]]
    */
  private def renderFencedCodeBlock(fencedCodeBlock: FencedCodeBlock): Unit =
    val literal = fencedCodeBlock.getLiteral()
    val attributes = scala.collection.mutable.Map.empty[String, String]
    val info = fencedCodeBlock.getInfo()
    if info != null && !info.isEmpty() then
      val space = info.indexOf(" ")
      val language =
        if space == -1 then info
        else info.substring(0, space)
      attributes += "class" -> s"language-$language"
      writer.line()
      writer.tag("pre", Map.empty[String, String].asJava)
      writer.tag("code", attributes.asJava)
      writer.text(literal)
      writer.tag("/code")
      writer.tag("/pre")
      writer.line()

object MermaidExtension extends HtmlRenderer.HtmlRendererExtension:

  override def extend(builder: HtmlRenderer.Builder): Unit =
    builder.nodeRendererFactory {
      new HtmlNodeRendererFactory():
        override def create(context: HtmlNodeRendererContext): NodeRenderer =
          new MermaidRenderer(context)
    }
