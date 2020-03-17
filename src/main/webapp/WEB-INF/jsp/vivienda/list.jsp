<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendas">
	<h2>Viviendas</h2>

	<table id="viviendaTable" class="table table-striped">
		<thead>
			<tr>
				<th>Titulo</th>
				<th>Fecha publicacion</th>
				<th>Direccion</th>
				<th>Zona</th>
				<th>Precio</th>
				<th>Dimensiones</th>
				<th>Caracteristicas</th>
				<th>Foto</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${viviendas}" var="vivienda">
				<tr>
					<td><spring:url value="/viviendas/{viviendaId}"
							var="viviendaUrl">
							<spring:param name="viviendaId" value="${vivienda.id}" />
						</spring:url> <a href="${fn:escapeXml(viviendaUrl)}"><c:out
								value="${vivienda.titulo}" /></a>
					</td>
					<td>
						<c:out value="${vivienda.fechaPublicacion}" />
					</td>
					<td>
						<c:out value="${vivienda.direccion}" />
					</td>
					<td>
						<c:out value="${vivienda.zona}" />
					</td>
					<td>
						<c:out value="${vivienda.precio}" />
					</td>
					<td>
						<c:out value="${vivienda.dimensiones}" />
					</td>
					<td>
						<c:out value="${vivienda.caracteristicas}" />
					</td>
					<td>
						<img  height="100px" width="200px" src="<c:out value="${vivienda.foto}"/>"/>
					</td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</petclinic:layout>