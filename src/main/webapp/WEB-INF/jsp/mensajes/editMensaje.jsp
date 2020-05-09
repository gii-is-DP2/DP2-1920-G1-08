<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="editMensaje">

	<jsp:body>
        <h2>Crear mensaje</h2>

        <form:form modelAttribute="mensaje" class="form-horizontal"
			action="/mensajes/save">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Asunto" name="asunto" />
                <petclinic:inputField label="Cuerpo" name="cuerpo" />
           		<h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Destinatario </h4>
                <div class="col-sm-10"
					style="margin-bottom: 45px; left: 16%">
                <form:select class="form-control" name="Cliente"
						path="client" size="4" style="width: 20%">
                    <c:forEach var="cliente" items="${allClients}">
                        <option value="${cliente.id}">
                        <c:out value="${cliente.nombre} " /> <br>
                        <c:out value="${cliente.apellidos}" /></option>
                    </c:forEach>
                </form:select>
            <form:hidden path="client" />
            </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${mensaje.id}" />

                    <button class="btn btn-default" type="submit"><h4>Enviar Mensaje </h4></button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>