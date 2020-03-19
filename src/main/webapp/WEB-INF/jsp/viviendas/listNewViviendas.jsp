<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>

<petclinic:layout pageName="viviendas">

	<c:forEach items="${viviendas}" var="viv">
		<div class="panel panel-primary" style="width: 70%; margin: 0 auto; margin-top: 30px;">
			<div class="panel-heading">
				<h3 class="panel-title"><c:out value="${viv.direccion}"/></h3>
			</div>
			
			<div class="panel-body">
				<img src="${viv.foto}" style="margin-left: auto; margin-right: auto; display: block;"/>
				<p>Fecha de publicación: <c:out value="${viv.fechaPublicacion}"/></p>
				<p>Zona: <c:out value="${viv.zona}"/></p>
				<p>Precio: <c:out value="${viv.precio}"/> €</p>
				<p>Dimensiones: <c:out value="${viv.dimensiones}"/></p>
				<p>Planta: <c:out value="${viv.planta}"/></p>
			</div>
		</div>
	
	</c:forEach>
	

</petclinic:layout>