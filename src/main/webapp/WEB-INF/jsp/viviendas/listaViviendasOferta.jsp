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
				<th style="width: 150px;">Precio en miles euros</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${viviendas}" var="vivienda">
		<tr>
					<td><img src="${vivienda.foto}" /> </td>
					 <td>
                    <spring:url value="/viviendas/{viviendaId}"
								var="viviendaUrl">
                        <spring:param name="viviendaId"
									value="${vivienda.id}" />
                    </spring:url>
                    <a href="${fn:escapeXml(viviendaUrl)}"><c:out
									value="${vivienda.zona} " /></a>
                </td>
					<td><c:out value="${vivienda.direccion}" /></td>
					<td><c:out value="${vivienda.planta}" /></td>
					<td><c:out value="${vivienda.precio}" /></td>
			</tr>
			</c:forEach>


		</tbody>
	</table>
	</jsp:body>
</petclinic:layout>
