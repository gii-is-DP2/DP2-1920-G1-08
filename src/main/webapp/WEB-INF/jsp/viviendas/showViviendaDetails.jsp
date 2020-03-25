<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<petclinic:layout pageName="Show Vivienda">
	<h2>Vivienda</h2>

	<sec:authorize access="hasAnyAuthority('cliente, admin')">
		<spring:url value="/compras/create/{viviendaId}" var="compraUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(compraUrl)}" class="btn btn-primary">Comprar
			vivienda</a>
		<br />
		<br />
	</sec:authorize>
	
	<sec:authorize access="hasAnyAuthority('admin, propietario')">
		<spring:url value="/viviendas/delete/{viviendaId}" var="deleteUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-primary">Borrar vivienda</a>
	</sec:authorize>

	<sec:authorize access="isAuthenticated()">
		<spring:url value="/visita/vivienda/{viviendaId}/new"
			var="createVivUrl">
			<spring:param name="viviendaId" value="${vivienda.id}" />
		</spring:url>
		<a href="${fn:escapeXml(createVivUrl)}" class="btn btn-primary"
			role="button">Pedir cita</a>
	</sec:authorize>
	

	<h2>Informacion de vivienda</h2>

	<table class="table table-striped">
		<tr>
			<th>T�tulo</th>
			<td><b><c:out value="${vivienda.titulo}"></c:out></b></td>
		</tr>
		<tr>
			<th>Fecha de publicaci�n</th>
			<td><b><c:out value="${vivienda.fechaPublicacion}"></c:out></b></td>
		</tr>
		<tr>
			<th>Direcci�n</th>
			<td><b><c:out value="${vivienda.direccion}"></c:out></b></td>
		</tr>
		<tr>
			<th>Zona</th>
			<td><b><c:out value="${vivienda.zona}"></c:out></b></td>
		</tr>
		<tr>
			<th>Precio</th>
			<td><b><c:out value="${vivienda.precio}"></c:out> euros </b></td>
		</tr>
		<c:if test="${vivienda.horarioVisita != null}">
			<tr>
				<th>Horario de visita</th>
				<td><b><c:out value="${vivienda.horarioVisita}"></c:out></b></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.dimensiones != null}">
			<tr>
				<th>Dimensiones</th>
				<td><b><c:out value="${vivienda.dimensiones}"></c:out> m� </b></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.amueblado != null}">
			<tr>
				<th>�Est� amueblado?</th>
				<td><b> <c:if test="${vivienda.amueblado==true}">
							<c:out value="SI"></c:out>
						</c:if> <c:if test="${vivienda.amueblado==false}">
							<c:out value="NO"></c:out>
						</c:if>
				</b></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.planta != null}">
			<tr>
				<th>Planta</th>
				<td><b><c:out value="${vivienda.planta}"></c:out></b></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.foto != null}">
			<tr>
				<th>Foto</th>
				<td><b><img height="200px" width="300px"
						src="<c:out value="${vivienda.foto}"/>" /></b></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.caracteristicas != null}">
			<tr>
				<th>Caracter�sticas</th>
				<td><b><c:out value="${vivienda.caracteristicas}"></c:out></b></td>
			</tr>
		</c:if>
		<c:if test="${vivienda.equipamiento != null}">
			<tr>
				<th>Equipamiento</th>
				<td><b><c:out value="${vivienda.equipamiento}"></c:out></b></td>
			</tr>
		</c:if>
		<tr>
			<th>Propietario</th>
			<td><b><c:out
						value="${vivienda.propietario.nombre} ${vivienda.propietario.apellidos}"></c:out></b></td>
		</tr>
	</table>


</petclinic:layout>