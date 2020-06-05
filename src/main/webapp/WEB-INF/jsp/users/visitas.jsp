<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="visitasViviendaForm">
   
    <jsp:body>
    
    	
    	
    	<h2>Próximas visitas</h2>
    
    	<c:forEach items="${proximasVisitas}" var="vis">
			<div class="panel panel-primary"
				style="width: 70%; margin: 0 auto; margin-top: 30px;">
				<div class="panel-heading">
					<h3 class="panel-title">
						<c:out value="${localDateTimeFormat.format(vis.fecha)} - ${vis.lugar}" />
					</h3>
				</div>
				
				<div class="panel-body">

					<img src="${vis.vivienda.foto}"
						style="margin-left: auto; margin-right: auto; display: block;" />
					<p>
						Dirección:
						<c:out value="${vis.vivienda.direccion}" />
					</p>
					
				</div>
			</div>
		
		</c:forEach>
    
    	<h2>Anteriores visitas</h2>
    	
    	<c:forEach items="${visitas}" var="vis">
			<div class="panel panel-primary"
				style="width: 70%; margin: 0 auto; margin-top: 30px;">
				<div class="panel-heading">
					<h3 class="panel-title">
						<c:out value="${localDateTimeFormat.format(vis.fecha)} - ${vis.lugar}" />
					</h3>
				</div>
				
				<div class="panel-body">

					<img src="${vis.vivienda.foto}"
						style="margin-left: auto; margin-right: auto; display: block;" />
					<p>
						Dirección:
						<c:out value="${vis.vivienda.direccion}" />
					</p>
					
					<p>
						<spring:url value="/valoracion/{visitaId}/new" var="visitaUrl">
							<spring:param name="visitaId" value="${vis.id}" />
						</spring:url>
						<a href="${fn:escapeXml(visitaUrl)}" class="btn btn-primary" role="button">
							<c:out value="Valorar visita" />
						</a>
					</p>
				</div>
			</div>
		
		</c:forEach>
    </jsp:body>
    
 </petclinic:layout>