import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.node.Node
import org.commonmark.ext.autolink.AutolinkExtension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension
import org.commonmark.ext.front.matter.YamlFrontMatterExtension
import org.commonmark.Extension
import scala.jdk.CollectionConverters._

object GitHubFlavoredTransformer {

  private val GitHubFlavored: Seq[Extension] = Seq(
    AutolinkExtension.create,
    StrikethroughExtension.create,
    TablesExtension.create,
    HeadingAnchorExtension.create,
    YamlFrontMatterExtension.create,
    MermaidExtension,
    Bootstrap5Extension
  )

  private val parser = Parser.builder.extensions(GitHubFlavored.asJava).build
  private val renderer =
    HtmlRenderer.builder.extensions(GitHubFlavored.asJava).build

  def parse(content: String): Node =
    parser.parse(content)

  def render(document: Node): String =
    renderer.render(document)

  def transform(content: String): String =
    render(parse(content))

}
