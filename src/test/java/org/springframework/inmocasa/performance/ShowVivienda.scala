package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class ShowVivienda extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.ico""", """.*.js""", """.*.css""", """.*.png"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es,es-ES;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6")
		.userAgentHeader("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/83.0.4103.61 Safari/537.36 Edg/83.0.478.37")

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

	object Home {
		val home = exec(http("home")
			.get("/")
			.headers(headers_0))
		.pause(6)
	}

	object Login {
		val login = exec(http("login")
			.get("/login")
			.headers(headers_0)
			.resources(http("request_2")
			.get("/login")
			.headers(headers_2)))
		.pause(9)
	}

	object Logged {
		val logged = exec(http("logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "gilmar")
			.formParam("password", "gilmar")
			.formParam("_csrf", "3eb73d9e-c61a-4e15-a9ed-c1e49e178b69"))
		.pause(12)
	}
	 
	object MisViviendas {
		val misViviendas = exec(http("mis-viviendas")
			.get("/viviendas/mis-viviendas")
			.headers(headers_0))
		.pause(8)
	}

	object ShowVivienda {
		val showVivienda = exec(http("show-vivienda")
			.get("/viviendas/1")
			.headers(headers_0))
		.pause(6)
	}

	val scn = scenario("Propietarios").exec(Home.home,
											Login.login,
											Logged.logged,
											MisViviendas.misViviendas,
											ShowVivienda.showVivienda)


		

	//setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(5000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(8000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(20000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(50000) during (10 seconds))).protocols(httpProtocol)
	setUp(scn.inject(rampUsers(100000) during (10 seconds))).protocols(httpProtocol)
	
}