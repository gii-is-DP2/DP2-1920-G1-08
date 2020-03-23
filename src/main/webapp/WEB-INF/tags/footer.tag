<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- Placed at the end of the document so the pages load faster --%>
<spring:url value="/webjars/jquery/2.2.4/jquery.min.js" var="jQuery"/>
<script src="${jQuery}"></script>

<%-- jquery-ui.js file is really big so we only load what we need instead of loading everything --%>
<spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.js" var="jQueryUiCore"/>
<script src="${jQueryUiCore}"></script>

<%-- Bootstrap --%>
<spring:url value="/webjars/bootstrap/3.3.6/js/bootstrap.min.js" var="bootstrapJs"/>
<script src="${bootstrapJs}"></script>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	
<footer class="page-footer font-small blue">

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="https://mdbootstrap.com/"> MDBootstrap.com</a>
  </div>
  <!-- Copyright -->

</footer>

