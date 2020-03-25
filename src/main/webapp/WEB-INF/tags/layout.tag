<%@ tag trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ attribute name="pageName" required="true" %>
<%@ attribute name="customScript" required="false" fragment="true"%>


<!doctype html>
<html>
<petclinic:htmlHeader/>

<body>
<petclinic:bodyHeader menuName="${pageName}"/>

<div class="container-fluid">
    <div class="container xd-container">
		
		<c:forEach items="${error}" var="errMsg"> 
    		<div class="alert alert-danger" role="alert"><p><c:out value="${errMsg}"/></p></div>
		</c:forEach>
		
    	<c:forEach items="${info}" var="infoMsg"> 
    		<div class="alert alert-info" role="alert"><p><c:out value="${infoMsg}"/></p></div>
    	</c:forEach>
    	<c:forEach items="${warning}" var="warningMsg"> 
    		<div class="alert alert-warning" role="alert"><p><c:out value="${warningMsg}"/></p></div>
    	</c:forEach>
    	<c:forEach items="${success}" var="successMsg"> 
    		<div class="alert alert-success" role="alert"><p><c:out value="${success}"/></p></div>
    	</c:forEach>
    	
    	
    	
        <jsp:doBody/>

        <petclinic:pivotal/>
    </div>
</div>
<petclinic:footer/>
<jsp:invoke fragment="customScript" />

</body>

</html>
