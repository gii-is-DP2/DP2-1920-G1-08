<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="visitasViviendaForm">
   
    <jsp:body>
      
        <form:form modelAttribute="visita" action="/visita/save" class="form-horizontal">
            <div class="form-group has-feedback">
            	<petclinic:inputField label="Fecha de la visita" name="fecha" />
               	<petclinic:inputField label="Lugar de la visita" name="lugar"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<input type="hidden" name="vivienda" value="${visita.vivienda.id }"/>
                	<input type="hidden" name="cliente" value="${visita.cliente.id }"/>
                    <input type="hidden" name="visitaId" value="${visita.id}"/>
                    <button class="btn btn-default" type="submit">Reservar visita</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>
