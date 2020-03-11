<%@ include file="/WEB-INF/views/includes/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GLOBAL BUSINESS APPLICATION | Log in</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/plugins/icheck-bootstrap/icheck-bootstrap.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/dist/css/adminlte.min.css">

<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/dist/css/style-dark-2.css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
</head>
<body class="hold-transition login-page">
	<header>
		<div id="borders"></div>
		<div id="particles-holder">
			<canvas id="the-sea-canvas"></canvas>
		</div>
		<div class="center-container-home">
			<div class="center-block">
				<div class="intro-wrapper">
					<div class="login-box">
						<img src="/test/resources/dist/img/AdminLTELogo.png"
							alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
							style="opacity: .8">
						<div class="login-logo">
							<a href="#"><b>Global Business</b> Application</a>
						</div>
						<div class="card">
							<div class="card-body login-card-body">
								<p class="login-box-msg">Authentification</p>

								<form action="<c:url value='j_spring_security_check'/>"
									method="post">
									<div class="input-group mb-3">
										<input type="email" class="form-control"
											placeholder="Entrez votre email..." name="j_username"
											id="username" autofocus>
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-envelope"></span>
											</div>
										</div>
									</div>
									<div class="input-group mb-3">
										<input type="password" class="form-control"
											placeholder="Entrez votre mot de passe..." name="j_password">
										<div class="input-group-append">
											<div class="input-group-text">
												<span class="fas fa-lock"></span>
											</div>
										</div>
									</div>
									<div class="row">
										<div class="col-12">
											<input type="submit" type="submit" value="S'authentifier"
												class="btn btn-primary btn-block" />
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/resources/plugins/jquery/jquery.min.js"></script>
	<!-- Bootstrap 4 -->
	<script
		src="<%=request.getContextPath()%>/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<!-- AdminLTE App -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/adminlte.min.js"></script>
	<!-- Sea -->
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/plugins.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/the-sea.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/bootstrap.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/particles-light.js"></script>
</body>
</html>
