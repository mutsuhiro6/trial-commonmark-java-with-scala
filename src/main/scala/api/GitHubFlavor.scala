package api

import org.commonmark.Extension
import org.commonmark.ext.autolink.AutolinkExtension
import org.commonmark.ext.gfm.strikethrough.StrikethroughExtension
import org.commonmark.ext.gfm.tables.TablesExtension
import org.commonmark.ext.heading.anchor.HeadingAnchorExtension
import org.commonmark.ext.front.matter.YamlFrontMatterExtension

object GitHubFlavor:
  def apply(): Seq[Extension] = Seq(
    AutolinkExtension.create,
    StrikethroughExtension.create,
    TablesExtension.create,
    HeadingAnchorExtension.create,
    YamlFrontMatterExtension.create
  )
