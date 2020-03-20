<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>

<petclinic:layout pageName="compras">
	<jsp:body>
		<h2>
			<c:if test="${compra['new']}">Nueva</c:if> compra
		</h2>
		<form:form modelAttribute="compra" class="form-horizontal">
			<input type="hidden" name="id" value="${compra.id}" />
			<input type="hidden" name="estado" value="${compra.estado}" />
			<input type="hidden" name="vivienda" value="${compra.vivienda.id}" />
			<input type="hidden" name="cliente" value="${compra.cliente.id}" />
				<div class="form-group has-feedback">
                	<div class="form-group">
                   		<label class="col-sm-2 control-label">Vivienda</label>
                    	<div class="col-sm-10">
                    		<c:out value="${compra.vivienda.titulo}"></c:out>
                    	</div>
               		</div> 					
               		<petclinic:inputField label="Precio final"
					name="precioFinal" />
               		<petclinic:inputField label="Comentario"
					name="comentario" />
		            <div class="form-group">
		                <div class="col-sm-offset-2 col-sm-10">
		                    <c:choose>
		                        <c:when test="${compra['new']}">
		                            <button class="btn btn-default"
									type="submit">Comprar</button>
		                        </c:when>
		                       </c:choose>
		               	</div>
		           </div>
				</div> 
		</form:form>
	</jsp:body>
</petclinic:layout>