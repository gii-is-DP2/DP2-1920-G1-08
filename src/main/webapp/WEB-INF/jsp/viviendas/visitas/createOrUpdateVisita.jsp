<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="visitasViviendaForm">
	<jsp:attribute name="customScript">
        <script>
			$(function() {
				$("#fecha").datepicker({
					dateFormat : 'yy/mm/dd'
				});
			});
		</script>
    </jsp:attribute>
	<jsp:body>
      
        <form:form modelAttribute="visita" action="/visita/save"
			class="form-horizontal">
            <div class="form-group has-feedback">
            <petclinic:inputField label="Fecha de la visita" name="fecha" />
            <petclinic:inputField label="Lugar de la visita" name="lugar" />
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                	<input type="hidden" name="vivienda"
						value="${visita.vivienda.id }" />
                	<input type="hidden" name="cliente"
						value="${visita.cliente.id }" />
                    <input type="hidden" name="visitaId"
						value="${visita.id}" />
                    <button class="btn btn-default" type="submit">Reservar visita</button>
                </div>
            </div>
        </form:form>

    </jsp:body>

</petclinic:layout>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
