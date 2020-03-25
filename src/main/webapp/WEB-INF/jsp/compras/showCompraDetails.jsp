<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="Show Compra">
	<h2>Oferta de compra</h2>

	<table id="showCompra" class="table table-striped">
		<tr>
			<th>Precio</th>
			<td><c:out value="${compras.precioFinal}" /> Euros</td>
		</tr>
		<tr>
			<th>Comentario al respecto</th>
			<td><c:out value="${compras.comentario}" /></td>
		</tr>
		<tr>
			<th>Cliente</th>
			<td><c:out
					value="${compras.cliente.nombre} ${compras.cliente.apellidos}" /></td>
		</tr>
		<tr>
	</table>
	<td><spring:url value="/compras/{viviendaId}/aceptar"
			var="aceptarCompra">
			<spring:param name="viviendaId" value="${compras.vivienda.id}" />
		</spring:url> <a href="${fn:escapeXml(aceptarCompra)}">Aceptar oferta</a></td>
		
		</>
		
		
		<td><spring:url value="/compras/{viviendaId}/rechazar"
			var="rechazarCompra">
			<spring:param name="viviendaId" value="${compras.vivienda.id}" />
		</spring:url> <a href="${fn:escapeXml(rechazarCompra)}">Rechazar oferta</a></td>
</petclinic:layout>