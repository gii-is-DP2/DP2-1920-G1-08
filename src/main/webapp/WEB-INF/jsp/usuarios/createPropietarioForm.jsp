<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="propietarios">

	<jsp:attribute name="customScript">
        <script>
									$(function() {
										$("#fechaNacimiento").datepicker({
											dateFormat : 'yy/mm/dd'
										});
									});
								</script>
    </jsp:attribute>
	<jsp:body>
	<h2>
		<c:if test="${propietario['new']}">New </c:if>
		Propietario
	</h2>
	<form:form modelAttribute="propietario" class="form-horizontal"
			id="add-propietario-form">
		<div class="form-group has-feedback">
			<petclinic:inputField label="nombre" name="nombre" />
			<petclinic:inputField label="apellidos" name="apellidos" />
			<petclinic:inputField label="genero" name="genero" />
			<petclinic:inputField label="telefono" name="telefono" />
			<petclinic:inputField label="email" name="email" />
			<petclinic:inputField label="inmobiliaria" name="inmobiliaria" />
			<petclinic:inputField label="cif" name="cif" />
			<petclinic:inputField label="fechaNacimiento" name="fechaNacimiento" />

		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<c:choose>
					<c:when test="${propietario['new']}">
						<button class="btn btn-default" type="submit">Añadir
							Propietario</button>
					</c:when>
					<c:otherwise>
						<button class="btn btn-default" type="submit">Actualizar
							Propietario</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
	</form:form>
	</jsp:body>
</petclinic:layout>
