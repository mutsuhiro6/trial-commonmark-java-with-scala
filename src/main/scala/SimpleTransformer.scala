import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.renderer.Renderer
import org.commonmark.renderer.text.TextContentRenderer
import org.commonmark.node.Node

object SimpleTransformer:

  private val parser = Parser.builder.build
  private val renderer = HtmlRenderer.builder.build

  def parse(content: String): Node =
    parser.parse(content)

  def render(document: Node): String =
    renderer.render(document)

  def transform(content: String): String =
    render(parse(content))
