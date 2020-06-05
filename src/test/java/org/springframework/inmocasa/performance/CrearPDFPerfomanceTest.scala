
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class CrearPDFPerfomanceTest extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.jpg""", """.*.css""", """.*.ico""", """.*.png""", """.*.js"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9")
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



	val scn = scenario("CrearPDFPerfomanceTest")
		// Home
		.exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(10)
		// Login
		.exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.resources(http("request_2")
			.get("/login")
			.headers(headers_2)))
		.pause(10)
		.exec(http("Login")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "bravo9")
			.formParam("password", "bravo9")
			.formParam("_csrf", "d8077aec-95b4-4ce2-b606-3af60c7fd4db"))
		.pause(12)
		// Mi Perfil
		.exec(http("Mi_Perfil")
			.get("/clientes/miPerfil")
			.headers(headers_0))
		.pause(13)
		// Descarga
		.exec(http("Descarga_PDF")
			.get("/usuario/exportPDF")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(100000))).protocols(httpProtocol)
}