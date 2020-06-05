package dp2

import scala.concurrent.duration._

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.jdbc.Predef._

class editPerfil extends Simulation {

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



	val scn = scenario("editPerfil")
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
		.pause(11)
		// login
		.exec(http("logged")
			.post("/login")
			.headers(headers_3)
			.formParam("username", "alonso7")
			.formParam("password", "alonso7")
			.formParam("_csrf", "96a39daa-db71-4cf2-8bfa-7076dd0a047b"))
		.pause(6)
		// logged
		.exec(http("mi-perfil")
			.get("/usuario/miPerfil")
			.headers(headers_0))
		.pause(7)
		// mi-perfil
		.exec(http("editar-perfil-form")
			.get("/clientes/10/edit")
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
		.pause(14)
		// editar-perfil-form
		.exec(http("editar-perfil-save")
			.post("/clientes/save")
			.headers(headers_3)
			.formParam("dni", "22781434A")
			.formParam("nombre", "Alonso")
			.formParam("apellidos", "Soler")
			.formParam("genero", "OTRO")
			.formParam("telefono", "925448965")
			.formParam("fechaNacimiento", "1976/01/04")
			.formParam("email", "alonso@gmail.com")
			.formParam("username", "alonso7")
			.formParam("password", "alonso7")
			.formParam("id", "10")
			.formParam("_csrf", "83233c52-d63a-4a41-95f7-53491e2c25e5"))
		.pause(8)
		// editar-perfil-save

	setUp(scn.inject(atOnceUsers(1))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(5000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(8000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(20000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(50000) during (10 seconds))).protocols(httpProtocol)
	//setUp(scn.inject(rampUsers(100000) during (10 seconds))).protocols(httpProtocol)
}