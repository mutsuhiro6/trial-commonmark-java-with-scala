package api

import org.commonmark.Extension
import org.commonmark.ext.ins.InsExtension
import ext._

object MyCustomFlavor:
  def apply(): Seq[Extension] =
    Seq(MermaidExtension, Bootstrap5Extension, InsExtension.create)
