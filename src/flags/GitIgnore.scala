import works.scala.cmd.BooleanFlag

object GitIgnore extends BooleanFlag:

  override val name: String = "gitignore"

  override val shortKey: String = "g"

  override val description: String =
    "download a .gitignore to the current folder"
