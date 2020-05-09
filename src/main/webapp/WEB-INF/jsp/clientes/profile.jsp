<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<petclinic:layout pageName="profile">
	<h2>Datos Personales</h2>
	
	
	<table class="table table-striped">
	
		<tr>
			<th>Nombre:</th>
			<td><c:out value="${cliente.nombre}"/></td>
		</tr>
		
		<tr>
			<th>Apellidos:</th>
			<td><c:out value="${cliente.apellidos}"/></td>
		</tr>
		
		<tr>
			<th>Nombre de Usuario:</th>
			<td><c:out value="${cliente.username}"/></td>
		</tr>
		
		<tr>
			<th>Password:</th>
			<td><c:out value="${cliente.password}"/></td>
		</tr>
		
		<tr>
			<th>Genero:</th>
			<td><c:out value="${cliente.genero}"/></td>
		</tr>
		
		<tr>
			<th>Fecha de Nacimiento:</th>
			<td><c:out value="${cliente.fechaNacimiento}"/></td>
		</tr>
		
		<tr>
			<th>Telefono:</th>
			<td><c:out value="${cliente.telefono}"/></td>
		</tr>
		
		<tr>
			<th>Email:</th>
			<td><c:out value="${cliente.email}"/></td>
		</tr>
		
		
		
	</table>
	
	
	<spring:url value="/clientes/{clienteId}/edit" var="editUrl">
			<spring:param name="clienteId" value="${cliente.id}" />
	</spring:url>
	<a href="${fn:escapeXml(editUrl)}" class="btn btn-primary">
		Editar perfil</a>
	
</petclinic:layout>