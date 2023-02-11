import works.scala.cmd.BooleanFlag

object ScalaFix extends BooleanFlag:

  override val name: String = "scalafix"

  override val shortKey: String = "fix"

  override val description: String =
    "download a .scalafix.conf file to the current directory"
