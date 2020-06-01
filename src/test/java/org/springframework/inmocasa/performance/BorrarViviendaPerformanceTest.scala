package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class BorrarViviendaPerformanceTest extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg"""), WhiteList())
		.acceptEncodingHeader("gzip, deflate")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")

	val headers_0 = Map(
		"A-IM" -> "x-bm,gzip",
		"Proxy-Connection" -> "keep-alive")

	val headers_1 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Language" -> "es-ES,es;q=0.9,en;q=0.8,ko;q=0.7",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

	val headers_3 = Map(
		"Accept" -> "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9",
		"Accept-Language" -> "es-ES,es;q=0.9,en;q=0.8,ko;q=0.7",
		"Origin" -> "http://www.dp2.com",
		"Proxy-Connection" -> "keep-alive",
		"Upgrade-Insecure-Requests" -> "1")

    val uri2 = "http://clientservices.googleapis.com/chrome-variations/seed"

	val scn = scenario("BorrarViviendaPerformanceTest")
		.exec(http("Home")
			.get("/")
			.headers(headers_1))
		.pause(13)
		// Home
		.exec(http("Login")
			.get("/login")
			.headers(headers_1))
		.pause(21)
		// Login
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "inversionesreina")
			.formParam("password", "inversionesreina")
			.formParam("_csrf", "9cebf81c-d26f-41a8-9f55-b21e24aaa464"))
		.pause(11)
		// Logged
		.exec(http("MisViviendasList")
			.get("/viviendas/mis-viviendas")
			.headers(headers_1))
		.pause(20)
		// MisViviendasList
		.exec(http("ShowVivienda")
			.get("/viviendas/4")
			.headers(headers_1))
		.pause(9)
		// ShowVivienda
		.exec(http("ViviendaDeleted")
			.get("/viviendas/delete/4")
			.headers(headers_1))
		.pause(12)
		// ViviendaDeleted

	setUp(scn.inject(atOnceUsers(5000))).protocols(httpProtocol)
}