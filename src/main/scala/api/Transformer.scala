package api

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer
import org.commonmark.node.Node
import org.commonmark.Extension
import org.commonmark.parser.Parser.ParserExtension
import org.commonmark.renderer.html.HtmlRenderer.HtmlRendererExtension

object Transformer:
  def apply(): Transformer = new Transformer

class Transformer:

  import scala.jdk.CollectionConverters._

  private val parserBuilder = Parser.builder
  private val rendererBuilder = HtmlRenderer.builder

  def parse(content: String): Node =
    parserBuilder.build.parse(content)

  def render(document: Node): String =
    rendererBuilder.build.render(document)

  def transform(content: String): String =
    render(parse(content))

  def using(ext: Extension): Transformer =
    parserBuilder.extensions(Seq(ext).asJava)
    rendererBuilder.extensions(Seq(ext).asJava)
    this

  def using(exts: Seq[Extension]): Transformer =
    parserBuilder.extensions(exts.asJava)
    rendererBuilder.extensions(exts.asJava)
    this

  def parsing(ext: ParserExtension): Transformer =
    parserBuilder.extensions(Seq(ext).asJava)
    this

  def parsing(exts: Seq[ParserExtension]): Transformer =
    parserBuilder.extensions(exts.asJava)
    this

  def rendering(ext: HtmlRendererExtension): Transformer =
    rendererBuilder.extensions(Seq(ext).asJava)
    this

  def rendering(exts: Seq[HtmlRendererExtension]): Transformer =
    rendererBuilder.extensions(exts.asJava)
    this
