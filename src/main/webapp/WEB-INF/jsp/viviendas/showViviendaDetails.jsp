<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="Show Vivienda">
	<h2>Vivienda</h2>

	<table id="showVivienda" class="table table-striped">
	<tr>
			<th>Zona</th>
			<td><c:out value="${viviendas.zona}" /></td>
		</tr>
		<tr>
			<th>Direccion</th>
			<td><c:out value="${viviendas.direccion}" /></td>
		</tr>
		<tr>
			<th>Planta</th>
			<td><c:out value="${viviendas.planta}" /></td>
		</tr>
		<tr>
			<th>Características</th>
			<td><c:out value="${viviendas.caracteristicas}" /></td>
		</tr>

		<tr>
			<th>Equipamiento</th>
			<td><c:out value="${viviendas.equipamiento}" /></td>
		</tr>
		<tr>
			<th>Precio</th>
			<td><c:out value="${viviendas.precio}" /></td>
		</tr>
		<tr>
			<th>Horario de visita</th>
			<td><c:out value="${viviendas.horarioVisita}" /></td>
		</tr>
	</table>



</petclinic:layout>