<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendas">

	<style>
.slidecontainer {
	width: 25%
}

.slider {
	-webkit-appearance: none;
	width: 25%;
	height: 25px;
	background: #d3d3d3;
	outline: none;
	opacity: 0.7;
	-webkit-transition: .2s;
	transition: opacity .2s;
}

.slider::-webkit-slider-thumb {
	-webkit-appearance: none;
	appearance: none;
	width: 25px;
	height: 25px;
	background: #4CAF50;
	cursor: pointer;
}

.slider::-moz-range-thumb {
	width: 25px;
	height: 25px;
	background: #4CAF50;
	cursor: pointer;
}
</style>

	<form>
		<div class="slidecontainer">
			<h3>Precio</h3>
			<!-- <input type="text" id="precioMin" name="precioMin" required>
		<input type="text" id="precioMax" name="precioMax" required> -->
			<input type="range" class="slider" id="precioMin" name="precioMin"
				value="50" min="0" max="2000">
			<p>
				Precio Minimo: <span id="valueMin"></span>
			</p>
			<input type="range" class="slider" id="precioMax" name="precioMax"
				value="1000" min="0" max="2000" />
			<p>
				Precio Maximo: <span id="valueMax"></span>
			</p>
			<input type="submit" value="Buscar">
		</div>
	</form>
	<script>
		var sliderMin = document.getElementById("precioMin");
		var sliderMax = document.getElementById("precioMax");
		var outputMin = document.getElementById("valueMin");
		var outputMax = document.getElementById("valueMax");
		outputMin.innerHTML = sliderMin.value;
		outputMax.innerHTML = sliderMax.value;
		sliderMin.oninput = function() {
			outputMin.innerHTML = this.value;
		}
		sliderMax.oninput = function() {
			outputMax.innerHTML = this.value;
		}
	</script>

	<sec:authorize access="hasAnyAuthority('cliente')">
		<spring:url value="/viviendas/new" var="crearUrl">
		</spring:url>
		<a href="${fn:escapeXml(crearUrl)}" class="btn btn-primary"
			role="button">Crear Vivienda</a>
	</sec:authorize>


	<sec:authorize access="hasAnyAuthority('propietario')">
      <spring:url value="/viviendas/new" var="crearUrl">
                    </spring:url>
                    <a href="${fn:escapeXml(crearUrl)}" class="btn btn-primary" role="button"  >Crear Vivienda</a>
                   	</sec:authorize>
                   
                                        
	<c:forEach items="${viviendas}" var="viv">
		<div class="panel panel-primary"
			style="width: 70%; margin: 0 auto; margin-top: 30px;">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:out value="${viv.direccion}" />
				</h3>
			</div>

			<div class="panel-body">

				<img src="${viv.foto}"
					style="margin-left: auto; margin-right: auto; display: block;" />
				<p>
					Fecha de publicación:
					<c:out value="${viv.fechaPublicacion}" />
				</p>

				<p>
					Precio:
					<c:out value="${viv.precio}" />
				</p>
				<p>
					Dimensiones:
					<c:out value="${viv.dimensiones}" />
				</p>
				<p>
					Planta:
					<c:out value="${viv.planta}" />
				</p>
				<p>
					<spring:url value="/viviendas/{viviendaId}" var="viviendaUrl">
						<spring:param name="viviendaId" value="${viv.id}" />
					</spring:url>
					<a href="${fn:escapeXml(viviendaUrl)}" class="btn btn-primary"
						role="button"><c:out value="Ver detalles" /></a>
				</p>
			</div>

		</div>

	</c:forEach>
<<<<<<< HEAD

</petclinic:layout>
