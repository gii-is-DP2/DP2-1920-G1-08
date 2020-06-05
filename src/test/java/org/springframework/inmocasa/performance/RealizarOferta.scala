package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class RealizarOferta extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.js""", """.*.png""", """.*.jpeg""", """.*.ico""", """.*.jpg"""), WhiteList())
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


	object Home {
		val home = exec(http("Home")
			.get("/")
			.headers(headers_0))
		.pause(7)
	}

	object Login {
		val login = exec(http("Login")
			.get("/login")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(10)
		.exec(http("Logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "rodrigo")
			.formParam("password", "rodrigo")
			.formParam("_csrf", "${stoken}"))
		.pause(11)
	}

	object ListViviendas {
		val listViviendas = exec(http("ListViviendas")
			.get("/viviendas/allNew")
			.headers(headers_0))
		.pause(7)
	}

	object ShowVivienda {
		val showVivienda = exec(http("ShowVivienda")
			.get("/viviendas/2")
			.headers(headers_0))
		.pause(7)
	}

	object FormOferta {
		val formOferta = exec(http("FormOferta")
			.get("/compras/create/2")
			.headers(headers_0)
			.check(css("input[name=_csrf]", "value").saveAs("stoken"))
		).pause(17)
		.exec(http("RealizarOferta")
			.post("/compras/create/2")
			.headers(headers_3)
			.formParam("id", "")
			.formParam("estado", "PENDIENTE")
			.formParam("vivienda", "2")
			.formParam("cliente", "8")
			.formParam("precioFinal", "100000")
			.formParam("comentario", "Quiero comprar esta casa")
			.formParam("_csrf", "${stoken}"))
		.pause(12)
	}


	val realizarOfertaScn = scenario("RealizarOferta").exec(Home.home,
									  				Login.login,
									  				ListViviendas.listViviendas,
									  				ShowVivienda.showVivienda,
													FormOferta.formOferta)
	

	setUp(
		realizarOfertaScn.inject(rampUsers(5000) during (10 seconds))
	).protocols(httpProtocol)
		.assertions(
        global.responseTime.max.lt(5000),    
        global.responseTime.mean.lt(1000),
        global.successfulRequests.percent.gt(95)
    )
}