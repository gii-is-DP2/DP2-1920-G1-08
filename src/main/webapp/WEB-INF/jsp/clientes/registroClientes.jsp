<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="clientes">

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
		<c:if test="${cliente['new']}">New </c:if>
		Cliente
	</h2>
	<form:form modelAttribute="cliente" class="form-horizontal"
			action="/clientes/save">
		<div class="form-group has-feedback">
			<petclinic:inputField label="DNI" name="dni" req="true"/>
			<petclinic:inputField label="Nombre" name="nombre" req="true"/>
			<petclinic:inputField label="Apellidos" name="apellidos" />
			<label class="col-sm-2 control-label">Género</label>
			<form:select id="genero" path="genero" style="width: 200px;">
                <form:options items="${generos}" />
            </form:select>
            <br/>
            <br/>
			<petclinic:inputField label="Telefono" name="telefono" />
			<petclinic:inputField label="Fecha de nacimiento"
					name="fechaNacimiento" />
			<petclinic:inputField label="Email" name="email" />
			<petclinic:inputField label="Usuario" name="username" req="true" />
			<petclinic:inputFieldPassword label="Contraseña" name="password" req="true" />
			

		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
					<input type="hidden" name="id" value="${cliente.id}" />
					<button class="btn btn-default" type="submit"> Save cliente</button>
					
			</div>
		</div>
	
	</form:form>
	</jsp:body>
</petclinic:layout>
