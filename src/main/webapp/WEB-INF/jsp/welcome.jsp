<%@ page session="false" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<!-- %@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %-->

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
	<div class="container">
		<a class="navbar-brand" href="#"> <img
			src="http://placehold.it/150x50?text=Logo" alt="">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link" href="#">Home
						<span class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Sobre
						nosotros</a></li>
				<li class="nav-item"><a class="nav-link" href="/login">Acceso
						a usuarios</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Contacto</a></li>
			</ul>
		</div>
	</div>
</nav>

<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>


<section class="search-sec">
	<div class="container">
		<form action="#" method="post" novalidate="novalidate">
			<div class="row">
				<div class="col-lg-12">
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-12 p-0">
							<input type="text" class="form-control search-slt"
								placeholder="Alquilar">
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 p-0">
							<input type="text" class="form-control search-slt"
								placeholder="Comprar">
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 p-0">
							<select class="form-control search-slt"
								id="exampleFormControlSelect1">
								<option>Seleccionar el tipo de vivienda</option>
								<option>Vivienda</option>
								<option>Habitaciones</option>
								<option>Garaje</option>

							</select>
						</div>
						<div class="col-lg-3 col-md-3 col-sm-12 p-0">
							<button type="button" class="btn btn-danger wrn-btn">Buscar</button>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</section>

<footer class="page-footer font-small blue">

  <!-- Copyright -->
  <div class="footer-copyright text-center py-3">© 2020 Copyright:
    <a href="#"> Inmocasa</a>
  </div>
  <!-- Copyright -->

</footer>

