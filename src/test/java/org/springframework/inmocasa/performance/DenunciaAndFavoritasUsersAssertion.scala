package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class DenunciaAndFavoritasUsersAssertion extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.png""", """.*.js""", """.*.ico""", """.*.ide""", """.*.jpg"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_2 = Map("Origin" -> "http://www.dp2.com")

	object Home {
		val home = exec(http("Home")
				.get("/"))
			.pause(7)
	}

	object Logged {
		val logged = exec(http("Loggin")
				.get("/login"))
			.pause(14)
			.exec(http("request_2")
				.post("/login")
				.headers(headers_2)
				.formParam("username", "alejandra")
				.formParam("password", "alejandra")
				.formParam("_csrf", "d13f3a89-66f3-4abf-bc4d-2e2ac0a9bc63"))
			.pause(21)
	}

	object AllViviendas {
		val viviendas = exec(http("Todas las viviendas")
				.get("/viviendas/allNew"))
			.pause(12)
	}

	object Vivienda {
		val vivienda = exec(http("Vivienda detalles")
				.get("/viviendas/4"))
			.pause(11)

		val segunda = exec(http("vivienda detalles 2")
				.get("/viviendas/5"))
			.pause(11)
	}

	object Denuncia{
		val form = exec(http("denuncia form")
				.get("/denuncias/create/4"))
			.pause(20)

		val save = exec(http("guardar denuncia")
			.post("/denuncias/create/4")
			.headers(headers_2)
			.formParam("id", "")
			.formParam("vivienda", "4")
			.formParam("justificacion", "El due�o no es realmente el propietario")
			.formParam("_csrf", "901cd3b6-adbe-4b51-98e1-9ed9e3908772"))

		val saved = exec(http("denuncia guardada")
				.get("/viviendas/5"))
			.pause(11)
	} 

	object Favoritas {
		val addFav = exec(http("Añadir a favoritas")
				.get("/clientes/5/favoritos"))
			.pause(12)

		val lista = exec(http("Liusta de favoritas")
				.get("/clientes/lista/favoritas"))
			.pause(5)
			.exec(http("request_10")
				.get("/viviendas/8"))
			.pause(11)
	}



	val scnDenuncia = scenario("Denuncia")
		.exec(Home.home,
			Logged.logged,
			AllViviendas.viviendas,
			Vivienda.vivienda,
			Denuncia.form,
			Denuncia.save,
			Denuncia.saved)

	val scnFavoritas = scenario("Favoritas")
		.exec(Home.home,
			Logged.logged,
			AllViviendas.viviendas,
			Vivienda.segunda,
			Favoritas.addFav,
			Favoritas.lista)

	setUp(scnDenuncia.inject(rampUsers(75000) during (10 seconds)),
			scnFavoritas.inject(rampUsers(75000) during (10 seconds)))
		.protocols(httpProtocol)
		.assertions(
			global.responseTime.max.lt(5000),
			global.responseTime.mean.lt(1000),
			global.successfulRequests.percent.gt(95)
		)
}