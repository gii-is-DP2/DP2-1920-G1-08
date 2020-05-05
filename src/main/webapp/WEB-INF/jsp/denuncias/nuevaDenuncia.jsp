<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="denuncias">
	<jsp:body>
		<h2>
			Nueva denuncia
		</h2>		
		<form:form modelAttribute="denuncia" class="form-horizontal">
			<input type="hidden" name="id" value="${denuncia.id}" />
			<input type="hidden" name="vivienda" value="${denuncia.vivienda.id}" />
				<div class="form-group has-feedback">					
               		<petclinic:inputField label="Justificación"
					name="justificacion" />
		            <div class="form-group">
		                <div class="col-sm-offset-2 col-sm-10">
		                            <button class="btn btn-default"
									type="submit">Denunciar</button>
		               	</div>
		           </div>
				</div> 
		</form:form>
	</jsp:body>
</petclinic:layout>