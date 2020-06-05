package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class createViviendaTestUsersAssertions extends Simulation {

	val httpProtocol = http
		.baseUrl("http://www.dp2.com")
		.inferHtmlResources(BlackList(""".*.css""", """.*.png""", """.*.js""", """.*.ico""", """.*.ide""", """.*.jpg"""), WhiteList())
		.acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8")
		.acceptEncodingHeader("gzip, deflate")
		.acceptLanguageHeader("es-ES,es;q=0.8,en-US;q=0.5,en;q=0.3")
		.upgradeInsecureRequestsHeader("1")
		.userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:76.0) Gecko/20100101 Firefox/76.0")

	val headers_2 = Map("Origin" -> "http://www.dp2.com")



	val scn = scenario("createViviendaTest")
		.exec(http("home")
			.get("/"))
		.pause(9)
		// home
		.exec(http("login")
			.get("/login"))
		.pause(8)
		// 
		.exec(http("logged")
			.post("/login")
			.headers(headers_2)
			.formParam("username", "gilmar")
			.formParam("password", "gilmar")
			.formParam("_csrf", "3b4ca1b4-f758-4273-80bf-726a434274b3"))
		.pause(8)
		// logged
		.exec(http("Todas las viviendas")
			.get("/viviendas/allNew"))
		.pause(9)
		// 
		.exec(http("form nueva vivienda")
			.get("/viviendas/new"))
		.pause(47)
		// form nueva vivienda
		.exec(http("vivienda creada")
			.post("/viviendas/save")
			.headers(headers_2)
			.formParam("titulo", "Nueva vivienda")
			.formParam("direccion", "Sevilla, cualquier lado")
			.formParam("zona", "Triana")
			.formParam("precio", "120000")
			.formParam("dimensiones", "70")
			.formParam("amueblado", "0")
			.formParam("planta", "bajo")
			.formParam("comentario", "Es una casa muy bonita con mucha luminosidad")
			.formParam("foto", "https://img3.idealista.com/blur/WEB_LISTING/0/id.pro.es.image.master/c7/4c/31/750112795.jpg")
			.formParam("caracteristicas", "cocina amplia")
			.formParam("equipamiento", "todo")
			.formParam("horarioVisita", "de 10 a 13")
			.formParam("id", "")
			.formParam("propietario", "1")
			.formParam("_csrf", "52e71d13-7b89-40ee-aaf6-682474777064"))
		.pause(20)
		
		// vivienda creada
		.exec(http("show vivienda details")
			.get("/viviendas/10"))
		.pause(9)
		// show vivienda details

	setUp(scn.inject(rampUsers(400000) during (10 seconds)))
		.protocols(httpProtocol)
		.assertions(
			global.responseTime.max.lt(5000),
			global.responseTime.mean.lt(1000),
			global.successfulRequests.percent.gt(95)
		)
}