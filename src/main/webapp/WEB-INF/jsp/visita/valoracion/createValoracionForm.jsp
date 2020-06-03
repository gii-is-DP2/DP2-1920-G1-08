<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>


<petclinic:layout pageName="visitasViviendaForm">
   
    <jsp:body>
    	<form:form modelAttribute="valoracion" action="/valoracion/save" class="form-horizontal">
            <div class="form-group has-feedback">
            	<petclinic:inputNumber label="Puntuación: " name="puntuacion" req="true"/>
               	<petclinic:inputField label="Comentario: " name="comentario"  req="true"/>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<input type="hidden" name="visita" value="${valoracion.visita.id }" />
                    <input type="hidden" name="valoracionId" value="${valoracion.id}"/>
                    <button class="btn btn-default" type="submit">Reservar visita</button>
                </div>
            </div>
        </form:form>
    
    </jsp:body>
</petclinic:layout>