package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BorrarUsuarioPerformanceTest extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9,en;q=0.8,ko;q=0.7")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")

	val headers_0 = Map("Proxy-Connection" -> "keep-alive")

	val headers_2 = Map(
		"Origin" -> "http://www.dp2.com",
		"Proxy-Connection" -> "keep-alive")



	val scn = scenario("BorrarUsuarioPerformanceTest")
		.exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(9)
		// Home
		.exec(http("Login")
			.get("/login")
			.headers(headers_0))
		.pause(12)
		//Login
		.exec(http("Logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "inversionesreina")
			.formParam("password", "inversionesreina")
			.formParam("_csrf", "34fad13e-f4e5-4b26-8419-d2ed53f1bf88"))
		.pause(13)
		// Logged
		.exec(http("ShowPerfil")
			.get("/usuario/miPerfil")
			.headers(headers_0))
		.pause(12)
		// ShowPerfil
		.exec(http("UsuarioDeleted")
			.get("/usuario/delete/4")
			.headers(headers_0))
		.pause(11)
		// UsuarioDeleted
		.exec(http("LoogedOut")
			.post("/logout")
			.headers(headers_2)
			.formParam("_csrf", "f59ab9c1-6f2f-4ddf-8efa-827e350fa86a"))
		.pause(8)
		// LoggedOut

	setUp(scn.inject(atOnceUsers(5000))).protocols(httpProtocol)
}