<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendasOferta">
	<h2>Viviendas denunciadas</h2>
	<c:forEach items="${denuncias}" var="denun">
		<div class="panel panel-primary"
			style="width: 70%; margin: 0 auto; margin-top: 30px;">
			<div class="panel-heading">
				<h3 class="panel-title">
					<c:out value="${denun.vivienda.direccion}" />
				</h3>
			</div>

			<div class="panel-body">

				<img src="${denun.vivienda.foto}"
					style="margin-left: auto; margin-right: auto; display: block;" />
				<p>
					Fecha de publicacion:
					<c:out value="${denun.vivienda.fechaPublicacion}" />
				</p>

				<p>
					Precio:
					<c:out value="${denun.vivienda.precio}" />
				</p>
				<p>
					Dimensiones:
					<c:out value="${denun.vivienda.dimensiones}" />
				</p>
				<p>
					Planta:
					<c:out value="${denun.vivienda.planta}" />
				</p>
				
				<p>
					Motivo de la denuncia:
					<c:out value="${denun.justificacion}" />
				</p>
				<p>
					<spring:url value="/viviendas/{viviendaId}" var="viviendaUrl">
						<spring:param name="viviendaId" value="${denun.vivienda.id}" />
					</spring:url>
					<a href="${fn:escapeXml(viviendaUrl)}" class="btn btn-primary"
						role="button"><c:out value="Ver detalles" /></a>
				</p>
				
				<sec:authorize access="hasAnyAuthority('admin')">
					<spring:url value="/viviendas/delete/{viviendaId}" var="deleteUrl">
						<spring:param name="viviendaId" value="${denun.vivienda.id}" />
					</spring:url>
					<a href="${fn:escapeXml(deleteUrl)}" class="btn btn-primary">Borrar
						vivienda</a>
				</sec:authorize>
			</div>

		</div>

	</c:forEach>

</petclinic:layout>
