import works.scala.cmd.Flag

object Port extends Flag[Int]:

  override val name: String = "port"

  override val shortKey: String = "p"

  override val description: String = "The port to start the http server on."

  override def parseArgument: PartialFunction[String, Int] = _.toInt
