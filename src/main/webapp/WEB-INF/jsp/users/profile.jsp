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
			<td><c:out value="${user.nombre}"/></td>
		</tr>
		
		<tr>
			<th>Apellidos:</th>
			<td><c:out value="${user.apellidos}"/></td>
		</tr>
		
		<tr>
			<th>Nombre de Usuario:</th>
			<td><c:out value="${user.username}"/></td>
		</tr>
		
		<tr>
			<th>Genero:</th>
			<td><c:out value="${user.genero}"/></td>
		</tr>
		
		<tr>
			<th>Fecha de Nacimiento:</th>
			<td><c:out value="${user.fechaNacimiento}"/></td>
		</tr>
		
		<tr>
			<th>Telefono:</th>
			<td><c:out value="${user.telefono}"/></td>
		</tr>
		
		<tr>
			<th>Email:</th>
			<td><c:out value="${user.email}"/></td>
		</tr>
		
		<c:if test="${propietario != null }">
			<tr>
				<th>�Es Inmobiliaria?</th>
				<c:if test="${user.esInmobiliaria == true }">
					<td>Si</td>
				</c:if>
				<c:if test="${user.esInmobiliaria == false }">
					<td>No</td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${user.esInmobiliaria == true }">
					<th>Nombre de la Inmobiliaria</th>
					<td><c:out value="${user.inmobiliaria}"/></td>
				</c:if>
			</tr>
			<tr>
				<c:if test="${user.esInmobiliaria == true }">
					<th>CIF</th>
					<td><c:out value="${user.cif}"/></td>
				</c:if>
			</tr>
		</c:if>
		
	</table>
	
	
	<spring:url value="/usuario/delete/{usuarioId}" var="deleteAllUrl">
			<spring:param name="usuarioId" value="${user.id}" />
	</spring:url>
	<a href="${fn:escapeXml(deleteAllUrl)}" class="btn btn-primary">
		Borrar todos mis datos de la p�gina</a>
	
</petclinic:layout>