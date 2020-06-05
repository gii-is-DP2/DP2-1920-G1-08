package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ValoracionVisitaPerfomanceTest extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.png""", """.*.js""", """.*.ico""", """.*.css""", """.*.jpg"""), WhiteList())
		.acceptEncodingHeader("gzip, deflate")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")

	val headers_0 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_2 = Map(
		"Accept" -> "image/webp,image/apng,image/*,*/*;q=0.8",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Proxy-Connection" -> "keep-alive")

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Language" -> "es-ES,es;q=0.9",
		"Origin" -> "http://www.dp2.com",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_5 = Map(
		"A-IM" -> "x-bm,gzip",
		"Proxy-Connection" -> "keep-alive")

    val uri1 = "http://clientservices.googleapis.com/chrome-variations/seed"

	val scn = scenario("ValoracionVisitaPerfomanceTest")
		// Home
		.exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(6)
		// Login
		.exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.resources(http("Login")
			.get("/login")
			.headers(headers_2)))
		.pause(8)
		.exec(http("Login")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "bravo9")
			.formParam("password", "bravo9")
			.formParam("_csrf", "d4693879-adb8-43a2-ace6-aa8d373d158f"))
		.pause(10)
		// Visitas
		.exec(http("Lista_Visitas")
			.get("/usuario/misVisitas")
			.headers(headers_0))
		.pause(6)
		// Valorar Visita
		.exec(http("Formulario_Visita")
			.get(uri1 + "?osname=win&channel=stable&milestone=83")
			.headers(headers_5))
		.pause(10)
		.exec(http("Formulario_Visita")
			.get("/valoracion/2/new")
			.headers(headers_0))
		.pause(12)
		// Valorar
		.exec(http("Valorar_Visita")
			.post("/valoracion/save")
			.headers(headers_3)
			.formParam("puntuacion", "4")
			.formParam("comentario", "Muy buena")
			.formParam("visita", "2")
			.formParam("valoracionId", "")
			.formParam("_csrf", "02f89854-3251-44f4-a4be-510106fa8aaa"))

	setUp(scn.inject(atOnceUsers(100000))).protocols(httpProtocol)
}