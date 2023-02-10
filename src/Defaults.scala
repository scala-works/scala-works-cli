import sttp.client3.*
import sttp.model.*
import works.scala.cmd.Cmd
import works.scala.cmd.Flag

object Defaults extends Cmd:

  override val name: String = "defaults"

  override val description: String =
    "Download reference config files for your project"

  override def flags: Seq[Flag[?]] = List(
    GitIgnore,
    ScalaFmt,
    Force,
  )

  val gitRoot: Uri =
    uri"https://raw.githubusercontent.com/scala-works/scala-defaults/main"

  val wd =
    os.pwd

  val client =
    SimpleHttpClient()

  override def command(args: List[String]): Unit =
    val shouldForce: Boolean = Force.isPresent(args)

    // Download .gitignore
    if GitIgnore.isPresent(args) then
      println("Downloading .gitignore...")
      if os.exists(wd / ".gitignore") && !shouldForce then
        println(
          s".gitignore exists! use -${ Force.shortKey } or --${ Force.name } to overwrite.",
        )
      else
        val _url     = gitRoot.addPath(".gitignore")
        val request  = basicRequest.get(_url).followRedirects(true)
        val response = client.send(request)
        response.body match
          case Left(err)      =>
            println(s"There was an error downloading $_url!")
            println(err)
          case Right(content) =>
            os.write.over(wd / ".gitignore", content)

    // Download .scalafmt.conf
    if ScalaFmt.isPresent(args) then
      println("Downloading .scalafmt.conf...")
      if os.exists(wd / ".scalafmt.conf") && !shouldForce then
        println(
          s".scalafmt.conf exists! use -${ Force.shortKey } or --${ Force.name } to overwrite.",
        )
      else
        val _url     = gitRoot.addPath(".scalafmt.conf")
        val request  = basicRequest.get(_url).followRedirects(true)
        val response = client.send(request)
        response.body match
          case Left(err)      =>
            println(s"There was an error downloading $_url!")
            println(err)
          case Right(content) =>
            os.write.over(wd / ".scalafmt.conf", content)

    ()
