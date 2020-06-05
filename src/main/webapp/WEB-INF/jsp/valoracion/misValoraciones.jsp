<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="viviendas">

	<h2>Valoraciones a mis viviendas</h2>
	<c:forEach items="${valoraciones}" var="valo">
		<div class="panel panel-primary"
			style="width: 70%; margin: 0 auto; margin-top: 30px;">
			<div class="panel-heading">
				<h3 class="panel-title">
					Visita dia <c:out value="${localDateTimeFormat.format(valo.visita.fecha)}" />, por <c:out value="${valo.visita.cliente.nombre}"/> 
						<c:out value="${valo.visita.cliente.apellidos}"/>
				</h3>
			</div>

			<div class="panel-body">

				<img src="${valo.visita.vivienda.foto}"
					style="margin-left: auto; margin-right: auto; display: block;" />
				
				<p>
					Vivienda: <c:out value="${valo.visita.vivienda.titulo }"></c:out>
				</p>
				
				<p>
					Valoración: <c:out value="${valo.puntuacion }"></c:out>
				</p>
				
				<p>
					Comentario: <c:out value="${valo.comentario }"></c:out>
				</p>
			</div>

		</div>

	</c:forEach>


</petclinic:layout>
