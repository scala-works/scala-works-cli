import works.scala.cmd.Cmd
import sttp.tapir.server.netty.NettyFutureServer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import sttp.tapir.{ emptyInput, filesServerEndpoints }
import scala.io.StdIn.readLine
import scala.concurrent.Await
import scala.concurrent.duration.Duration
import works.scala.cmd.Flag
import scala.util.Failure
import scala.util.Success

object Serve extends Cmd:

  override val name: String = "serve"

  override val description: String =
    "HTTP file server from the path started. Will default to 9000."

  override def flags: Seq[Flag[?]] = Seq(
    Port,
  )

  override def command(args: List[String]): Unit =
    val port    = Port.parseFirstFlagArg(args).getOrElse(9000)
    val thisDir = os.pwd
    val srv     = NettyFutureServer()
      .port(port)
      .addEndpoints(filesServerEndpoints[Future](emptyInput)(thisDir.toString))
      .start()
      .flatMap { n =>
        println(s"Serving http://localhost:$port")
        println("Press RETURN to exit")
        readLine()
        println("Shutting down...")
        n.stop()
      }
    Await.result(srv, Duration.Inf)
