import works.scala.cmd.BooleanFlag

object Force extends BooleanFlag:

  override val name: String = "force"

  override val shortKey: String = "f"

  override val description: String = "Force override an option"
