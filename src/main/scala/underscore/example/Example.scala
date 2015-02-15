package underscore.example

import org.http4s._
import org.http4s.server._
import org.http4s.dsl._
import org.http4s.argonaut._

import _root_.argonaut._

object Example {
  val service = HttpService {
    case req @ PUT -> Root / "login" =>
      // TODO: Proper error handling
      req.decode[Login] { l =>
        Ok(Response(s"Logged in ${l.name}"))
      }
    case PUT -> Root / "logout" =>
      Ok("ok")
    case GET -> Root / "view" / id =>
      Ok("ok")
  }
}

final case class Login(name: String, password: String)
object Login {
  implicit val loginCodecJson: CodecJson[Login] =
    Argonaut.casecodec2(Login.apply, Login.unapply)("name", "password")
  implicit val loginEntityDecoder: EntityDecoder[Login] =
    jsonOf[Login]
  implicit val loginEntityEncoder: EntityEncoder[Login] =
    jsonEncoderOf[Login]
}

final case class Response(msg: String)
object Response {
  implicit val responseCodecJson: CodecJson[Response] =
    Argonaut.casecodec1(Response.apply, Response.unapply)("msg")
  implicit val responseEntityDecoder: EntityDecoder[Response] =
    jsonOf[Response]
  implicit val responseEntityEncoder: EntityEncoder[Response] =
    jsonEncoderOf[Response]
}
