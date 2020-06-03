<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<petclinic:layout pageName="Show Vivienda">

	<input type="hidden" name="viviendaId" value="${vivienda.id}" />
	<input type="hidden" name="propietarioId" value="${propietarioId}" />

	
	<sec:authorize access="hasAnyAuthority('cliente, admin')">
		<spring:url value="/compras/create/{viviendaId}" var="compraUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(compraUrl)}" class="btn btn-primary">Comprar
			vivienda</a>
		<br />
		<br />
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('admin')">
		<spring:url value="/viviendas/delete/{viviendaId}" var="deleteUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-primary">Borrar
			vivienda</a>
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('propietario')">
		<c:if test="${vivienda.propietario.id == propietarioId}">
		<spring:url value="/viviendas/delete/{viviendaId}" var="deleteUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-primary">Borrar
			vivienda</a>
		</c:if>
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('cliente')">
		<spring:url value="/visita/vivienda/{viviendaId}/new"
			var="createVivUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(createVivUrl)}" class="btn btn-primary"
			role="button">Pedir cita</a>
	</sec:authorize>
	<br/>
	<br/>

	<h2>Informacion de vivienda</h2>

	<table class="table table-striped">

		<tr>
			<th>Título</th>
			<td><c:out value="${vivienda.titulo}"></c:out></td>
		</tr>
		<tr>
			<th>Fecha de publicación</th>
			<td><c:out value="${localDateFormat.format(vivienda.fechaPublicacion)}"></c:out></td>
		</tr>
		<tr>
			<th>Dirección</th>
			<td><c:out value="${vivienda.direccion}"></c:out></td>
		</tr>
		<tr>
			<th>Zona</th>
			<td><c:out value="${vivienda.zona}"></c:out></td>
		</tr>
		<tr>
			<th>Precio</th>
			<td><c:out value="${vivienda.precio}"></c:out> euros </td>
		</tr>
		<c:if test="${vivienda.horarioVisita != null}">
			<tr>
				<th>Horario de visita</th>
				<td><c:out value="${vivienda.horarioVisita}"></c:out></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.dimensiones != null}">
			<tr>
				<th>Dimensiones</th>
				<td><c:out value="${vivienda.dimensiones}"></c:out> m² </td>
			</tr>
		</c:if>
		<c:if test="${vivienda.amueblado != null}">
			<tr>
				<th>¿Está amueblado?</th>
				<td> <c:if test="${vivienda.amueblado==true}">
							<c:out value="SI"></c:out>
						</c:if> <c:if test="${vivienda.amueblado==false}">
							<c:out value="NO"></c:out>
						</c:if>
				</td>
			</tr>
		</c:if>
		<c:if test="${vivienda.planta != null}">
			<tr>
				<th>Planta</th>
				<td><c:out value="${vivienda.planta}"></c:out></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.foto != null}">
			<tr>
				<th>Foto</th>
				<td><img height="200px" width="300px"
						src="<c:out value="${vivienda.foto}"/>" /></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.caracteristicas != null}">
			<tr>
				<th>Características</th>
				<td><c:out value="${vivienda.caracteristicas}"></c:out></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.equipamiento != null}">
			<tr>
				<th>Equipamiento</th>
				<td><c:out value="${vivienda.equipamiento}"></c:out></td>
			</tr>
		</c:if>
		<tr>
			<th>Propietario</th>
			<td><c:out
						value="${vivienda.propietario.nombre} ${vivienda.propietario.apellidos}"></c:out></td>
		</tr>
	</table>
	
	<sec:authorize access="hasAnyAuthority('propietario, admin')">
		<c:if test="${vivienda.propietario.id == propietarioId}">
			<spring:url value="/pay/{viviendaId}" var="payUrl">
				<spring:param name="viviendaId" value="${vivienda.id}" />
			</spring:url>
			<form:form method="post" action="${fn:escapeXml(payUrl)}">
				<button type="submit"><b>¡Publicita tu vivienda pulsando aqui!</b><br>
					<img alt="Publicitar" src="/resources/images/paypal.jpg">
				</button>
			</form:form>
		</c:if>
	</sec:authorize>

	<sec:authorize access="hasAnyAuthority('cliente')">
		<spring:url value="/denuncias/create/{viviendaId}"
			var="denunciarVivienda">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(denunciarVivienda)}" class="btn btn-primary"
			role="button">Denunciar</a>
	</sec:authorize>

	
	<c:if test="${!vivienda.fav}">
		<sec:authorize access="hasAnyAuthority('cliente')">	
		<spring:url value="/clientes/{viviendaId}/favoritos" var="favoritoUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(favoritoUrl)}" class="btn btn-primary">Añadir a favoritos
			</a>
	</sec:authorize>
	</c:if>


</petclinic:layout>