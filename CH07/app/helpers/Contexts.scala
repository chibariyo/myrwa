package helpers

import play.api.libs.concurrent.Akka
import scala.concurrent.ExecutionContext

import play.api.Play.current

/**
  * Created by david on 17/03/22.
  */
object Contexts {
  val database: ExecutionContext =
    Akka.system.dispatchers.lookup("contexts.database")
}
