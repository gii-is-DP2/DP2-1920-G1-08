package dp2Tests

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class CreateMensaje extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.png""", """.*.ico""", """.*.css""", """.*.js"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9,en;q=0.8")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")

	val headers_0 = Map(
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Proxy-Connection" -> "keep-alive")

	val headers_3 = Map(
		"Origin" -> "http://www.dp2.com",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")



	val scn = scenario("CreateMensaje")
		.exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(9)
		// Home
		.exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.resources(http("request_2")
			.get("/login")
			.headers(headers_2)))
		.pause(11)
		// Login
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "gilmar")
			.formParam("password", "gilmar")
			.formParam("_csrf", "dbb56a02-568d-4c85-a190-1b77bd3ee669"))
		.pause(12)
		// Logged
		.exec(http("CreateMensajeForm")
			.get("/mensajes/new")
			.headers(headers_0))
		.pause(25)
		// CreateMensajeForm
		.exec(http("MensajesEnviados")
			.post("/mensajes/save")
			.headers(headers_3)
			.formParam("asunto", "Hola")
			.formParam("cuerpo", "Prueba de rendimiento")
			.formParam("client", "12")
			.formParam("client", "")
			.formParam("id", "")
			.formParam("_csrf", "6b9a2c4e-d048-4126-8c79-77ee7efea1e6"))
		.pause(18)
		// MensajesEnviados

	setUp(scn.inject(rampUsers(5000) during (10 seconds))).protocols(httpProtocol)
}