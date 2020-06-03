package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class EditVivienda extends Simulation {

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

	val headers_6 = Map(
		"Accept" -> "*/*",
		"Origin" -> "http://www.dp2.com",
		"Proxy-Connection" -> "keep-alive")



	val scn = scenario("EditVivienda")
		.exec(http("home")
			.get("/")
			.headers(headers_0))
		.pause(5)
		// home
		.exec(http("login")
			.get("/login")
			.headers(headers_0)
			.resources(http("request_2")
			.get("/login")
			.headers(headers_2)))
		.pause(8)
		// login
		.exec(http("logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "gilmar")
			.formParam("password", "gilmar")
			.formParam("_csrf", "d688cea0-f901-4c29-9c12-72cde6534dac"))
		.pause(8)
		// logged
		.exec(http("mis-viviendas")
			.get("/viviendas/mis-viviendas")
			.headers(headers_0))
		.pause(7)
		// mis-viviendas
		.exec(http("edit-vivienda-form")
			.get("/viviendas/1/edit")
			.headers(headers_0)
			.resources(http("request_6")
			.get("/resources/fonts/glyphicons-halflings-regular.woff2")
			.headers(headers_6)
			.check(status.is(404)),
            http("request_7")
			.get("/resources/fonts/glyphicons-halflings-regular.woff")
			.headers(headers_6)
			.check(status.is(404)),
            http("request_8")
			.get("/resources/fonts/glyphicons-halflings-regular.ttf")
			.headers(headers_6)
			.check(status.is(404))))
		.pause(11)
		// edit-vivienda-form
		.exec(http("vivienda-saved")
			.post("/viviendas/save")
			.headers(headers_3)
			.formParam("titulo", "Vivienda en San Julian")
			.formParam("direccion", "Calle Juzgado, Barrio San Juli?n, Sevilla")
			.formParam("zona", "Centro")
			.formParam("precio", "215000")
			.formParam("dimensiones", "72")
			.formParam("amueblado", "1")
			.formParam("planta", "Primera planta")
			.formParam("comentario", "Este es un comentariooooo")
			.formParam("foto", "https://st3.idealista.com/news/archivos/styles/news_detail/public/2018-01/consejos_publicacion_foto_3.jpg")
			.formParam("caracteristicas", "La entrada a la vivienda se encuentra en la planta primera del edificio (Sin ascensor). En la planta baja del piso se encuentra la cocina, el sal?n, una habitaci?n con armario empotrado y un cuarto de ba?o completo con ba?era.")
			.formParam("equipamiento", "Aire acondicionado")
			.formParam("horarioVisita", "Martes 8:00-13:00")
			.formParam("id", "1")
			.formParam("propietario", "1")
			.formParam("_csrf", "d0fa2095-a8d3-4b63-8a63-c8f6c7ce1652"))
		.pause(5)
		// vivienda-saved

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(5000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(8000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(20000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(50000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(100000) during (10 seconds))).protocols(httpProtocol)
}