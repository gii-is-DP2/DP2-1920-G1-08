<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>



<petclinic:layout pageName="misMensajes">
	<jsp:body>
	<h2>Mis mensaje</h2>
	
	<table id="MensajesTable" class="table table-striped">
  <thead>
    <tr>
     <th style="width: 200px;">Asunto</th>
				<th style="width: 200px;">Cuerpo</th>
				<th style="width: 200px;">Cliente</th>
				<th style="width: 200px;">Propietario</th>
				
				
    </tr>
  </thead>
  <tbody>
   <c:forEach items="${mensaje}" var="mensaje">
			
		<tr>		
					<td><c:out value="${mensaje.asunto}" /></td>
					<td><c:out value="${mensaje.cuerpo}" /></td>
					<td><c:out value="${mensaje.client.nombre}" /><br>  <c:out value="${mensaje.client.apellidos}"/> </td>
					<td><c:out value="${mensaje.prop.nombre}" /> <br>  <c:out value="${mensaje.prop.apellidos}"/> </td>		
			</tr>
			</c:forEach>
		
  </tbody>
 
</table>

	
	</jsp:body>

</petclinic:layout>
