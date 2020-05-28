<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
PetClinic :: a Spring Framework demonstration
--%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags --%>

    <spring:url value="/resources/images/favicon.png" var="favicon"/>
    <link rel="shortcut icon" type="image/x-icon" href="${favicon}">

    <title>PetClinic :: a Spring Framework demonstration</title>

    <%-- CSS generated from LESS --%>
    <spring:url value="/resources/css/petclinic.css" var="petclinicCss"/>
    <link href="${petclinicCss}" rel="stylesheet"/>


    <%-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries --%>
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <!-- Only datepicker is used -->
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.min.css" var="jQueryUiCss"/>
    <link href="${jQueryUiCss}" rel="stylesheet"/>
    <spring:url value="/webjars/jquery-ui/1.11.4/jquery-ui.theme.min.css" var="jQueryUiThemeCss"/>
    <link href="${jQueryUiThemeCss}" rel="stylesheet"/>
    
    <link
		href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
		rel="stylesheet" id="bootstrap-css">
	<script
		src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
	<script
		src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
		
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
		<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
		<script src="https://cdn.quilljs.com/1.3.6/quill.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->


    <!-- <script type="text/javascript" src="/bower_components/jquery/jquery.min.js"></script>
 	<script type="text/javascript" src="/bower_components/moment/min/moment.min.js"></script>
  	<script type="text/javascript" src="/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="/bower_components/eonasdan-bootstrap-datetimepicker/build/js/bootstrap-datetimepicker.min.js"></script>
  	<link rel="stylesheet" href="/bower_components/bootstrap/dist/css/bootstrap.min.css" />
  	<link rel="stylesheet" href="/bower_components/eonasdan-bootstrap-datetimepicker/build/css/bootstrap-datetimepicker.min.css" /> -->
</head>
