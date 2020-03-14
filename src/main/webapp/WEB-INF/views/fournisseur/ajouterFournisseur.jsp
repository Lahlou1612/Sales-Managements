<%@ include file="/WEB-INF/views/includes/includes.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GLOBAL BUSINESS APPLICATION</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/plugins/fontawesome-free/css/all.min.css">
<link rel="stylesheet"
	href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/dist/css/adminlte.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700"
	rel="stylesheet">
<!-- DataTables -->
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/resources/plugins/datatables-bs4/css/dataTables.bootstrap4.css">
<style type="text/css">
#hover:hover {
	color: white;
}
</style>
</head>
<body class="hold-transition sidebar-mini">
	<div class="wrapper">
		<%@ include file="/WEB-INF/views/menu_top/menu_top.jsp"%>
		<%@ include file="/WEB-INF/views/menu_left/menu_left.jsp"%>
		<div class="content-wrapper">
			<section class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1>
								<fmt:message code="common.gestion.fournisseur" />
							</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#"><fmt:message
											code="common.gestion.fournisseur" /></a></li>
								<li class="breadcrumb-item active"><fmt:message
										code="common.nouveau.fournisseur" /></li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<fmt:message code="common.nouveau.fournisseur" />
						</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool"
								data-card-widget="collapse" data-toggle="tooltip"
								title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
							<button type="button" class="btn btn-tool"
								data-card-widget="remove" data-toggle="tooltip" title="Remove">
								<i class="fas fa-times"></i>
							</button>
						</div>
					</div>
					<div class="card-body">
						<section class="content">
							<div class="row">
								<div class="col-12">
									<div class="card">
										<div class="card-body">
										<c:url value="/fournisseur/enregistrer" var="enregisterFournisseur" />
											<f:form modelAttribute="fournisseur" action="${enregisterFournisseur}" method="post" >
											<f:hidden path="idFournisseur"/>
												<div class="form-group">
													<label><fmt:message code="common.nom" /></label> 
													<f:input path="nom" class="form-control form-control-sm" type="text" placeholder="Entrez votre nom..." />
													<f:errors path="nom" cssClass="alert alert-warning" element="div" />
												</div>
												<div class="form-group">
													<label><fmt:message code="common.prenom" /></label>
													<f:input path="prenom" class="form-control form-control-sm" placeholder="Entrez votre prénom..." />
													<f:errors path="prenom" cssClass="alert alert-warning" element="div" />
												</div>
												<div class="form-group">
													<label><fmt:message code="common.adresse" /></label> 
													<f:input path="adresse" class="form-control form-control-sm" placeholder="Entrez votre adresse..." />
													<f:errors path="adresse" cssClass="alert alert-warning" element="div" />
												</div>
												<div class="form-group">
													<label><fmt:message code="common.mail" /></label> 
													<f:input path="mail" class="form-control form-control-sm" placeholder="Entrez votre mail..." />
													<f:errors path="mail" cssClass="alert alert-warning" element="div" />
												</div>
												
													<button type="submit" class="btn btn-info" style="font-size: 14px !important;">
														<i class="fa fa-save"> <fmt:message code="common.enregistrer" /></i>
													</button>
													<a href="<c:url value="/fournisseur/" />" class="btn btn-danger" style="font-size: 14px !important;"><i class="fa fa-times"> 
													<fmt:message code="common.annuler" /></i></a>
											
											</f:form>
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
			</section>
		</div>
		<%@ include file="/WEB-INF/views/footer/footer.jsp"%>
		<aside class="control-sidebar control-sidebar-dark"></aside>
	</div>
	<script
		src="<%=request.getContextPath()%>/resources/plugins/jquery/jquery.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/plugins/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/dist/js/adminlte.min.js"></script>
	<script src="<%=request.getContextPath()%>/resources/dist/js/demo.js"></script>

	<!-- DataTables -->
	<script
		src="<%=request.getContextPath()%>/resources/plugins/datatables/jquery.dataTables.js"></script>
	<script
		src="<%=request.getContextPath()%>/resources/plugins/datatables-bs4/js/dataTables.bootstrap4.js"></script>
	<!-- page script -->
	<script>
		$(function() {
			$("#example1").DataTable();
		});
	</script>
</body>
</html>
