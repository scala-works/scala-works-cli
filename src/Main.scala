import works.scala.cmd.MultiCmdApp
import works.scala.cmd.MultiCmdApp
import works.scala.cmd.Cmd

object Main extends MultiCmdApp:

  override def cmdList: Seq[Cmd] = List(
    Defaults,
  )
