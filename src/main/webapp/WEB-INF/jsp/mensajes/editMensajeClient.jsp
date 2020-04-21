<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="editMensajeClient">

  
    <jsp:body>
        <h2>Crear mensaje </h2>

        <form:form modelAttribute="mensaje" class="form-horizontal" action = "/mensajes/save">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Asunto" name="asunto"/>
                <petclinic:inputField label="Cuerpo" name="cuerpo"/>
                
                <div class="col-sm-10" style="margin-bottom: 45px; left: 10px">
                <form:select class="form-control" name="Prop" path="prop" size="4"
                    style="width: 20%">
                    <c:forEach var="prop" items="${allProps}">
                        <option value="${prop.id}">
                        <c:out  value="${prop.nombre} "/>
                        <c:out value="${prop.apellidos}"/></option>
                    </c:forEach>
                </form:select>
            <form:hidden path="prop" />
            </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${mensaje.id}"/>
                  <!--   <input type="hidden" name="prop" value="${mensaje.prop}"/> --> 

                    <button class="btn btn-default" type="submit">Guardar Mensaje</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
