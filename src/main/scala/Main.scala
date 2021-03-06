import ext._
import api._
import org.commonmark.ext.task.list.items.TaskListItemsExtension

@main def hello: Unit =
  val source = scala.io.Source.fromFile("inputs/test.md", "utf-8")
  val content = source.getLines.mkString("\n")
  Bootstrap5Extension.configure(
    Bootstrap5TableOptions(true, false, false, true, Set())
  )
  Bootstrap5Extension.configure(
    Bootstrap5ImageOptions(false, true, false, Set())
  )
  ImagePathResolver.configure("/inputs/images/")

  val html = Transformer()
    .using(GitHubFlavor())
    .using(Bootstrap5Extension)
    .using(MyFencedCodeBlockExtension)
    .using(ImagePathResolver)
    .using(TaskListItemsExtension.create)
    .transform(content)
  val pw = new java.io.PrintWriter("output.html", "utf-8")
  pw.print(headHtml)
  pw.print(html)
  pw.print(tailHtml)
  pw.close

val headHtml = """<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <!-- Google Fonts -->
    <link href="https://fonts.googleapis.com" rel="preconnect" />
    <link crossorigin="anonymous" href="https://fonts.gstatic.com" rel="preconnect" />
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+JP:wght@400;700&family=Ubuntu+Mono&display=swap" rel="stylesheet" />
    <!-- Begin highlight.js -->
    <link crossorigin="anonymous" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/styles/github.min.css" integrity="sha512-0aPQyyeZrWj9sCA46UlmWgKOP0mUipLQ6OZXu8l4IcAmD2u31EPEy9VcIMvl7SoAaKe8bLXZhYoMaE/in+gcgA==" referrerpolicy="no-referrer" rel="stylesheet" />
    <script crossorigin="anonymous" integrity="sha512-IaaKO80nPNs5j+VLxd42eK/7sYuXQmr+fyywCNA0e+C6gtQnuCXNtORe9xR4LqGPz5U9VpH+ff41wKs/ZmC3iA==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/highlight.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-ACi20n926dIM1hUsnDyspY9PRmtlKCePyMNhYCLGfCFnB8Uu/7I630naEYXAaDfynF/HDlTVBZ1oFMVn0gSLuA==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/python-repl.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-efcjzRpCxyR9hzqNDmYMy202NuRdmiGm80QQg4o6Dh3DdU+fbnC6BKGuJrRMbLLYm70+lR55mkCMiZETYHKGVw==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/python.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-yyMT1pQvQ9ZFAB8j/dUWeEjWTEQZBmV9pitXN7fmJYbqsoizQ0kVw8wONhrwBHE0Z/S8tKQyRcvbW4pG4m5cIw==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/bash.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-lzcjwENIEpri/9RQiWGJLxGxZ21Pr1qWvzEIpEa4yxPTSEderzTew+5Ff5XBbTC3OuLrb4P0qSwISoq1E/ddLg==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/c.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-vePi8kzEODboqUigQ4vAOL5ghcEv1rnZEv7ETk4XD1VOG7Ooo7ZsirvjF617LVYJ7StgbGkjXXiKALR/RrkM5w==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/java.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-Z5vOg00atIeZ04QhYxz3iRlFnU5qnrB0eYbBDmXMhHqJYx4dc6n1KY5qzh3fEfrFzaR05D34mOin6ObjPjuWdA==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/javascript.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-K7SF4Dv51TV6pT3PvQdm60JGplWBAIkxVYmrtzJqyjQo4mqz1CasVCtEi02aHVXPqu8WZsmSUZ71JDP1tQARIQ==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/scala.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-VWrc7ysMt7RMzxBjxK63LU+oYjGm6tKxAczhvxEqWzfEGjF33t0vwgPJMHKn8ZTPTWk78+XHqyteblhwdbg63Q==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/scheme.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-rK5Qtqig+ARU45vPqoVmAHD3wSMKWMpk/7Hd0htaAOJo7BX9BIvsxdBEPs3+XyofxMn52hQ0Sld3V5JJ1lrzDA==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/cpp.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-2S5x4UDJdymqDOBGIz88WSMwb/HC27t9gZyAus7RyUA2n3YXVJlYUrX606FkkU5+XrE+i12FNOhczUpPEh2XiQ==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/csharp.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-2EZCMzH/v5Q+9kXnio4UlG/iGM7Iy16NPjb4fFmtu501/gdvebqlP3sa2VvJsxnTJVWlFX8EN+BHxG4efqELWw==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/css.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-9cbJXQZEFDeJ6bnp8pAjk509daVdIpM4rGlOBnt2pljIpr3raGdKtXdpbzs5MjH55RY2wom5HHnHnNNppNKw9w==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/dockerfile.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-V6FxXzuyoSLgGXHJDF+BlY2TgnSEBt9ScqH9z4xxgeFQzxDdkSXEwiW0C9Rjlelhu9M4Sm4leK8AfNQJlZm3EA==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/dart.min.js"></script>
    <script crossorigin="anonymous" integrity="sha512-WThcD7sZmrZW146YDCVkTKkSs/El7dvTkRbPzOzfxg8PBIIEMdW9udtqap6sLONivb+kyS7NQCrg5ayAe0ElnQ==" referrerpolicy="no-referrer" src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.4.0/languages/gradle.min.js"></script>
    <!-- mermaid.js -->
    <script src="https://cdn.jsdelivr.net/npm/mermaid/dist/mermaid.min.js"></script>
    <!-- Bootstrap 5 CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.8.0/font/bootstrap-icons.css" />
    <title>{{ title }}</title>
  </head>
  <body class="bg-light">""".stripMargin

val tailHtml = """
    <script>
      mermaid.initialize({ startOnLoad: true });
      hljs.highlightAll();
    </script>
  </body>
</html>
""".stripMargin
