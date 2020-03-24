<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<!------ Include the above in your HEAD tag ---------->
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->


<petclinic:layout pageName="home">

<section class="search-sec" style="margin-left: 100px;">
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

	<!-- <!-- Footer 
	<footer class="page-footer font-small indigo" style="margin-left: 70px;">
	
		Footer Links
		<div class="container text-center text-md-left">
	
			Grid row
			<div class="row">
	
				Grid column
				<div class="col-md-3 mx-auto">
	
					Links
					<h5 class="font-weight-bold text-uppercase mt-3 mb-4">Links</h5>
	
					<ul class="list-unstyled">
						<li><a href="#!">Very long link 1</a></li>
						<li><a href="#!">Very long link 2</a></li>
						<li><a href="#!">Very long link 3</a></li>
						<li><a href="#!">Very long link 4</a></li>
					</ul>
	
				</div>
				Grid column
	
				<hr class="clearfix w-100 d-md-none">
	
				Grid column
				<div class="col-md-3 mx-auto">
	
					Links
					<h5 class="font-weight-bold text-uppercase mt-3 mb-4">Links</h5>
	
					<ul class="list-unstyled">
						<li><a href="#!">Link 1</a></li>
						<li><a href="#!">Link 2</a></li>
						<li><a href="#!">Link 3</a></li>
						<li><a href="#!">Link 4</a></li>
					</ul>
	
				</div>
				Grid column
	
				<hr class="clearfix w-100 d-md-none">
	
				Grid column
				<div class="col-md-3 mx-auto">
	
					Links
					<h5 class="font-weight-bold text-uppercase mt-3 mb-4">Links</h5>
	
					<ul class="list-unstyled">
						<li><a href="#!">Link 1</a></li>
						<li><a href="#!">Link 2</a></li>
						<li><a href="#!">Link 3</a></li>
						<li><a href="#!">Link 4</a></li>
					</ul>
	
				</div>
				Grid column
	
				<hr class="clearfix w-100 d-md-none">
	
				Grid column
				<div class="col-md-3 mx-auto">
	
					Links
					<h5 class="font-weight-bold text-uppercase mt-3 mb-4">Links</h5>
	
					<ul class="list-unstyled">
						<li><a href="#!">Link 1</a></li>
						<li><a href="#!">Link 2</a></li>
						<li><a href="#!">Link 3</a></li>
						<li><a href="#!">Link 4</a></li>
					</ul>
	
				</div>
				Grid column
	
			</div>
			Grid row
	
		</div>
		Footer Links
	
		Copyright
		<div class="footer-copyright text-center py-3">
			© 2020 Copyright: <a href="#"> Inmocasa</a>
		</div>
		Copyright
	
	</footer>
Footer -->

</petclinic:layout>


