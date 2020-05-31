<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendasOferta">
	<jsp:body>
	<h2>Viviendas con oferta</h2>

	<table id="viviendaOfertaTable" class="table table-striped">
		<thead>
			<tr>			
				<th style="width: 200px;">Foto</th>
				<th style="width: 150px;">Zona</th>
				<th style="width: 200px;">Direccion</th>
				<th style="width: 150px;">Planta</th>
				<th style="width: 150px;">Precio</th>
				<th style="width: 150px;">Oferta</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${compras}" var="compra">
			
		<tr>
					<td><img src="${compra.vivienda.foto}" /> </td>
					 <td>
                    <spring:url value="/viviendas/{viviendaId}"
								var="viviendaUrl">
                        <spring:param name="viviendaId"
									value="${compra.vivienda.id}" />
                    </spring:url>
                    <a href="${fn:escapeXml(viviendaUrl)}"><c:out
									value="${compra.vivienda.zona} " /></a>
                </td>
					<td><c:out value="${compra.vivienda.direccion}" /></td>
					<td><c:out value="${compra.vivienda.planta}" /></td>
					<td><c:out value="${compra.vivienda.precio}" /> Euros</td>
					<td>
					  <spring:url value="/compras/{compraId}" var="compraUrl">
                        <spring:param name="compraId"
									value="${compra.id}" />
                    </spring:url>
                    <a href="${fn:escapeXml(compraUrl)}">Ver oferta</a>
                </td>
			</tr>
			</c:forEach>


		</tbody>
	</table>
	</jsp:body>
</petclinic:layout>
