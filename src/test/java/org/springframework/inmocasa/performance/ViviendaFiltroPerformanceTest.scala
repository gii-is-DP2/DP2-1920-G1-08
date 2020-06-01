package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ViviendaFiltroPerformanceTest extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.ico""", """.*.png""", """.*.jpg"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.9,en;q=0.8,ko;q=0.7")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36")

	val headers_0 = Map("Proxy-Connection" -> "keep-alive")



	val scn = scenario("ViviendaFiltroPerformanceTest")
		.exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(14)
		// Home
		.exec(http("ListAllViviendas")
			.get("/viviendas/allNew")
			.headers(headers_0))
		.pause(48)
		// ListAllViviendas
		.exec(http("FiltroZona")
			.get("/viviendas/allNew?zona=Pino+Montano")
			.headers(headers_0))
		.pause(14)
		// FiltroZona

	setUp(scn.inject(rampUsers(5000) during (10 seconds))).protocols(httpProtocol)
}