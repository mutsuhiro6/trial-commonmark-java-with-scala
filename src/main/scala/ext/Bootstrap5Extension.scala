package ext

import org.commonmark.renderer.html.HtmlNodeRendererContext
import org.commonmark.renderer.html.HtmlWriter
import org.commonmark.renderer.NodeRenderer
import org.commonmark.node.Node
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.renderer.html.HtmlNodeRendererFactory
import org.commonmark.renderer.html.AttributeProvider
import org.commonmark.ext.gfm.tables.TableRow
import org.commonmark.ext.gfm.tables.TableBlock
import org.commonmark.renderer.html.AttributeProviderFactory
import org.commonmark.renderer.html.AttributeProviderContext
import org.commonmark.ext.gfm.tables.TableHead
import org.commonmark.ext.gfm.tables.TableBody
import org.commonmark.ext.gfm.tables.TableCell
import org.commonmark.node.BlockQuote
import org.commonmark.node.Image

object Bootstrap5Extension extends HtmlRenderer.HtmlRendererExtension:

  private var tableOptions = Bootstrap5TableOptions(
    false,
    false,
    false,
    false,
    Set()
  )

  private var imageOptions = Bootstrap5ImageOptions(
    false,
    false,
    false,
    Set()
  )

  def configure(
      tableOptions: Bootstrap5TableOptions,
      imageOptions: Bootstrap5ImageOptions
  ): Unit =
    configure(tableOptions)
    configure(imageOptions)

  def configure(tableOptions: Bootstrap5TableOptions): Unit =
    this.tableOptions = tableOptions

  def configure(imageOptions: Bootstrap5ImageOptions): Unit =
    this.imageOptions = imageOptions
  override def extend(builder: HtmlRenderer.Builder): Unit =
    builder.attributeProviderFactory(
      new AttributeProviderFactory():
        override def create(
            context: AttributeProviderContext
        ): AttributeProvider =
          new Bootstrap5StyledAttributeProvider(tableOptions, imageOptions)
    )

class Bootstrap5StyledAttributeProvider(
    tableOptions: Bootstrap5TableOptions,
    imageOptions: Bootstrap5ImageOptions
) extends AttributeProvider:

  private val tableAttrs = scala.collection.mutable.Set("table")

  if tableOptions.responsible then tableAttrs += "table-responsive"
  if tableOptions.hoverable then tableAttrs += "table-hover"
  if tableOptions.smallen then tableAttrs += "table-sm"
  if tableOptions.striped then tableAttrs += "table-striped"
  tableAttrs ++= tableOptions.additionalAttrs

  private val imageAttrs = scala.collection.mutable.Set[String]()

  if imageOptions.responsible then imageAttrs += "img-fluid"
  if imageOptions.rounded then imageAttrs += "rounded"
  if imageOptions.thumbnailed then imageAttrs += "img-thumbnail"
  imageAttrs ++= imageOptions.additionalAttrs

  override def setAttributes(
      node: Node,
      tagName: String,
      attributes: java.util.Map[String, String]
  ): Unit =
    node match
      case _: TableBlock => attributes.put("class", tableAttrs.mkString(" "))
      case cell: TableCell if cell.isHeader => attributes.put("scope", "col")
      case blockquote: BlockQuote => attributes.put("class", "blockquote")
      case img: Image => attributes.put("class", imageAttrs.mkString(" "))
      case _          => // do nothing

case class Bootstrap5TableOptions(
    responsible: Boolean,
    smallen: Boolean,
    striped: Boolean,
    hoverable: Boolean,
    additionalAttrs: Set[String]
)

case class Bootstrap5ImageOptions(
    responsible: Boolean,
    thumbnailed: Boolean,
    rounded: Boolean,
    additionalAttrs: Set[String]
)
