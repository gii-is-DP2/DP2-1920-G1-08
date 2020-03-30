<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="dashboard">
	<h2>Información general para el administrador</h2>
	
	<table class="table table-striped">
		<tr>
			<th>Número de ofertas realizadas</th>
			<td><b><c:out value="${numOfertas}"></c:out></b></td>
		</tr>
		<tr>
			<th>Número de viviendas compradas</th>
			<td><b><c:out value="${viviendasCompradas}"></c:out></b></td>
		</tr>
	</table>
</petclinic:layout>