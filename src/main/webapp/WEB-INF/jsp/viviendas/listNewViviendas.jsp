<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendas">

	<div class="filtros">

	<form:form id="formFiltros" modelAttribute="filtro" method="GET" action="/viviendas/allNewFiltros">
			
		<label>Precio mínimo</label>
		<form:input type="number" step="1" min="0" id="min" class="form-control" path="min" onblur="filtroHabitaciones.submit()" style="width: 150px;" />
		<label>Precio máximo</label>
		<form:input type="number" step="1" min="0" id="max" class="form-control" path="max" onblur="filtroHabitaciones.submit()" style="width: 150px;" />
		
		<div class="filtroZona">
		<h3>Zona</h3>
		<form:select class="select" id="zona" path="zona" onchange="formFiltros.submit()">
			<form:options items="${zonas}"/>
		</form:select>
		</div>

		<div class="filtroHabitacion">
			<h3>Numero de habitaciones</h3>
			<form:select class="select" id="numhabitacion" path="habitaciones" onchange="filtroHabitaciones.submit()">
				<form:option value="">Seleccione el numero de habitaciones:</form:option>
				<form:option value="1">1</form:option>
				<form:option value="2">2</form:option>
				<form:option value="3">3</form:option>
				<form:option value="4">4</form:option>
			</form:select>
		</div>
	</form:form>
	
	</div>
	<br>
	<br>
	
	
	<sec:authorize access="hasAnyAuthority('propietario')">
		<spring:url value="/viviendas/new" var="crearUrl">
		</spring:url>
		<a href="${fn:escapeXml(crearUrl)}" class="btn btn-primary"
			role="button">Crear Vivienda</a>
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
					Fecha de publicacion:
					<c:out value="${localDateFormat.format(viv.fechaPublicacion)}" />
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


</petclinic:layout>
