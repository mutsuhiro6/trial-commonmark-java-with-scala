import org.commonmark.renderer.html.HtmlNodeRendererContext
import org.commonmark.renderer.html.HtmlWriter
import org.commonmark.renderer.NodeRenderer
import org.commonmark.node.IndentedCodeBlock
import org.commonmark.node.Node
import org.commonmark.node.FencedCodeBlock
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.renderer.html.HtmlNodeRendererFactory
import org.commonmark.renderer.html.CoreHtmlNodeRenderer

class MermaidRenderer(context: HtmlNodeRendererContext) extends NodeRenderer:

  import scala.jdk.CollectionConverters._

  private val writer: HtmlWriter = context.getWriter

  private val Mermaid = "mermaid"

  private val core = new CoreHtmlNodeRenderer(context)

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
        else core.visit(block)

object MermaidExtension extends HtmlRenderer.HtmlRendererExtension:
  override def extend(builder: HtmlRenderer.Builder): Unit =
    builder.nodeRendererFactory {
      new HtmlNodeRendererFactory() {
        override def create(context: HtmlNodeRendererContext): NodeRenderer =
          new MermaidRenderer(context)
      }
    }
