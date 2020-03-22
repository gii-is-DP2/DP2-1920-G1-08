<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendas">
	<h2>Vivienda</h2>
	<table class="table table-striped">
	
	<tr>
			<th>Zona</th>
			<td><c:out value="${vivienda.zona}" /></td>
		</tr>
		<tr>
			<th>Direccion</th>
			<td><b><c:out value="${vivienda.direccion}" /></b></td>
		</tr>
			<tr>
			<th>Planta</th>
			<td><b><c:out value="${vivienda.planta}" /></b></td>
		</tr>
		<tr>
			<th>Características</th>
			<td><b><c:out value="${vivienda.caracteristicas}" /></b></td>
		</tr>

		<tr>
			<th>Equipamiento</th>
			<td><b><c:out value="${vivienda.equipamiento}" /></b></td>
		</tr>
		<tr>
			<th>Precio</th>
			<td><b><c:out value="${vivienda.precio}" /></b></td>
		</tr>
		<tr>
			<th>Horario de visita</th>
			<td><b><c:out value="${vivienda.horarioVisita}" /></b></td>
		</tr>
	</table>

	<spring:url value="update?id={viviendaId}" var="editVivienda">
		<spring:param name="viviendaId" value="${vivienda.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editURL)}" class="btn btn-default">Edit(Poner solo para cliente)</a>

	<spring:url value="delete?id={viviendaId}" var="deleteURL">
		<spring:param name="viviendaId" value="${vivienda.id}" />
	</spring:url>
	<a href="${fn:escapeXml(deleteURL)}" class="btn btn-default">Delete</a>

</petclinic:layout>