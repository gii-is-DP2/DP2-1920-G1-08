<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!--  >%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%-->
<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>


<nav class="navbar navbar-expand-lg navbar-dark bg-dark static-top">
	<div class="container">
		<a class="navbar-brand" href="/"> <img class="img-logo"
			src="/resources/images/Logo_inmocasa.png" alt=""
			style="margin-left: -100px">
		</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarResponsive" aria-controls="navbarResponsive"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/viviendas/allNew">Todas las viviendas <span
						class="sr-only">(current)</span>
				</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Pon tu
						anuncio</a></li>

				<sec:authorize access="!isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="/login">Acceso
							usuarios</a></li>
				</sec:authorize>



				<!-- Panel usuario -->
				<sec:authorize access="isAuthenticated()">
					<li class="nav-item"><a class="nav-link" href="#">Tus
							mensajes</a></li>
					<div class="btn-group">
						<button type="button" class="btn btn-success">
							<sec:authentication property="name" />
						</button>
						<button type="button"
							class="btn btn-danger dropdown-toggle dropdown-toggle-split"
							data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
							<span class="sr-only">Toggle Dropdown</span>
						</button>
						<div class="dropdown-menu">
							<a class="dropdown-item" href="#">Mi perfil</a> <a
								class="dropdown-item" href="#">Mis favoritos</a> <a
								class="dropdown-item" href="/usuario/misVisitas">Mis visitas</a>
							<sec:authorize access="hasAnyAuthority('admin')">
								<a class="dropdown-item" href="/dashboard">Estadísticas</a>
							</sec:authorize>
							<div class="dropdown-divider"></div>
							<a class="dropdown-item" href="<c:url value="/logout" />">Logout</a>
						</div>
					</div>
				</sec:authorize>
			</ul>
		</div>
	</div>

</nav>
<%-- <nav class="navbar navbar-default" role="navigation">
	<div class="dropdown">
		<button class="btn btn-secondary dropdown-toggle" type="button"
			id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
			aria-expanded="false">Dropdown button</button>
		<div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
			<a class="dropdown-item" href="#">Action</a> <a class="dropdown-item"
				href="#">Another action</a> <a class="dropdown-item" href="#">Something
				else here</a>
		</div>

		<div class="navbar-collapse collapse" id="main-navbar">
		
			<ul class="nav navbar-nav">

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Home</span>
				</petclinic:menuItem>

				<petclinic:menuItem active="${name eq 'viviendas'}"
					url="/vivienda/list" title="Viviendas">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Viviendas</span>
				</petclinic:menuItem>

				<sec:authorize access="hasAnyAuthority('admin')">
					<petclinic:menuItem active="${name eq 'dashboard'}"
						url="/dashboard" title="dashboard">
						<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
						<span>Estadisticas</span>
					</petclinic:menuItem>
				</sec:authorize>

				
				<petclinic:menuItem active="${name eq 'viviendas'}" url="/viviendas/allNew"
					title="viviendas">
					<span class="glyphicon glyphicon-th-list" aria-hidden="true"></span>
					<span>Viviendas</span>
				</petclinic:menuItem>
				
				<petclinic:menuItem active="${name eq 'error'}" url="/oups"
					title="trigger a RuntimeException to see how it is handled">
					<span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span>
					<span>Error</span>
				</petclinic:menuItem>

			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/users/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown"> <span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> <span
							class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-lg-4">
											<p class="text-center">
												<span class="glyphicon glyphicon-user icon-size"></span>
											</p>
										</div>
										<div class="col-lg-8">
											<p class="text-left">
												<strong><sec:authentication property="name" /></strong>
											</p>
											<p class="text-left">
												<a href="<c:url value="/logout" />"
													class="btn btn-primary btn-block btn-sm">Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
							<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-primary btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>




	</div>
</nav>

 --%>