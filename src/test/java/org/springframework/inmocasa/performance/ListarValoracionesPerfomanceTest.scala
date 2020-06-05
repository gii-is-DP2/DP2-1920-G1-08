
import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ListarValoracionesPerfomanceTest extends Simulation {

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



	val scn = scenario("ListarValoracionesPerfomanceTest")
		// Home
		.exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(11)
		// Login
		.exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.resources(http("Login")
			.get("/login")
			.headers(headers_2)))
		.pause(5)
		.exec(http("Login")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "gilmar")
			.formParam("password", "gilmar")
			.formParam("_csrf", "beb07246-8963-4274-b04a-79f3502a73ae"))
		.pause(16)
		// Lista de las valoraciones
		.exec(http("Lista_Valoraciones")
			.get("/valoracion/misValoraciones")
			.headers(headers_0))

	setUp(scn.inject(atOnceUsers(100000))).protocols(httpProtocol)
}