import works.scala.cmd.BooleanFlag

object ScalaFmt extends BooleanFlag:

  override val name: String = "scalafmt"

  override val shortKey: String = "fmt"

  override val description: String =
    "download a .scalafmt.conf file to the current directory"
