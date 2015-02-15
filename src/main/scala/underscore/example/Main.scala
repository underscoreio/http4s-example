package underscore.example

import org.http4s.server.blaze.BlazeBuilder

object Main extends App {
 BlazeBuilder.bindHttp(8080)
   .mountService(Example.service, "/")
   .run
   .awaitShutdown()
}
