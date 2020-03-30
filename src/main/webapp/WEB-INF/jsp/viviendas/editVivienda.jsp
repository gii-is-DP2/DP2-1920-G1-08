<%@ page session="false" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>


<petclinic:layout pageName="editVivienda">

    <jsp:attribute name="customScript">
        <script>
            $(function () {
                $("#fechaPublicacion").datepicker({dateFormat: 'yy/mm/dd'});
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <h2>Vivienda</h2>

        <form:form modelAttribute="vivienda" class="form-horizontal" action = "/viviendas/save">
            <div class="form-group has-feedback">
                <petclinic:inputField label="Fecha de publicacion" name="fechaPublicacion"/>
                <petclinic:inputField label="Dirección" name="direccion"/>
                <petclinic:inputField label="Zona" name="zona"/>
                <petclinic:inputField label="Precio" name="precio"/>
                <petclinic:inputField label="Dimensiones" name="dimensiones"/>
                <petclinic:inputField label="Amueblado" name="amueblado"/>
                <petclinic:inputField label="Planta" name="planta"/>
                <petclinic:inputField label="Comentario" name="comentario"/>
                <petclinic:inputField label="Foto" name="foto"/>
                <petclinic:inputField label="Caracteristicas" name="caracteristicas"/>
                <petclinic:inputField label="Equipamiento" name="equipamiento"/>
                <petclinic:inputField label="Horario de Visita" name="horarioVisita"/>
                
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="hidden" name="id" value="${vivienda.id}"/>
                    <input type="hidden" name="propietario" value="${vivienda.propietario.id}"/>
                 
                    <button class="btn btn-default" type="submit">Guardar Vivienda</button>
                </div>
            </div>
        </form:form>
    </jsp:body>

</petclinic:layout>
