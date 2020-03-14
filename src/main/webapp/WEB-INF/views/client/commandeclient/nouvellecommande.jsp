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
								<fmt:message code="common.commande.nouvelle" />
							</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#"><fmt:message code="common.gestion.commande" /></a></li>
								<li class="breadcrumb-item active"><fmt:message code="common.commande.nouvelle" /></li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<fmt:message code="common.liste.detail.client" />
						</h3>
						<div class="card-tools">
							<button type="button" class="btn btn-tool" data-card-widget="collapse" data-toggle="tooltip" title="Collapse">
								<i class="fas fa-minus"></i>
							</button>
							<button type="button" class="btn btn-tool" data-card-widget="remove" data-toggle="tooltip" title="Remove">
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
											<form action="" method="post" >
												<div class="form-row">
													<div class="col-md-4 mb-3">
														<label><fmt:message code="common.client.commande.code" /></label> 
														<input class="form-control form-control-sm" type="text" value="${codeCde}" disabled/>
													</div>
													<div class="col-md-4 mb-3">
														<label><fmt:message code="common.client.commande.date" /></label>
														<input class="form-control form-control-sm" value="${dateCde}" disabled/>
													</div>
													<div class="col-md-4 mb-3">
														<label><fmt:message code="common.client" /></label> 
														<select class="form-control form-control-sm" id="listClients">
															<c:forEach items="${clients}" var="clt">														
																<option value="${clt.getIdClient()}">${clt.getNom()}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											
											
												<button type="submit" class="btn btn-info" style="font-size: 14px !important;">
														<i class="fa fa-save"> <fmt:message code="common.enregistrer" /></i>
												</button>
												<a href="<c:url value="/commandeclient/" />" class="btn btn-danger" style="font-size: 14px !important;"><i class="fa fa-times"> 
													<fmt:message code="common.annuler" /></i>
												</a>
											</form>
											
										</div>
									</div>
								</div>
							</div>
						</section>
					</div>
					
					<div class="card-body" style="margin-top: -40px !important;">
						<section class="content">
							<div class="row">
								<div class="col-12">
									<!-- Détails Commande -->
									<div class="card">
						              <div class="card-header" style="background-color: #bfdae0;">
						                <h3 class="card-title"><fmt:message code="common.liste.detail.client" /></h3>
						              </div>
						              
						              <div class="card-body">

						      			
							              <div class="form-group"style="width: 250px !important;">
											<input type="text" id="idArticle_search" class="form-control form-control-sm" placeholder="Article à chercher..." />
							             	<label><fmt:message code="common.article.not.found"/></label>
							              </div>
							          		
							          	
						              
						                <table class="table table-striped table-bordered table-hover">
						                  <thead>
						                    <tr>
						                      <th><fmt:message code="common.article" /></th>
						                      <th><fmt:message code="common.qte" /></th>
						                      <th><fmt:message code="common.article.prixTTC" /></th>
						                      <th><fmt:message code="common.client.commande.total" /></th>
						                    </tr>
						                  </thead>
							                  <tbody id="detailNouvelleCommande">
							                  	<tr></tr>
							                  </tbody>
						                </table>
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
	<script
		src="<%=request.getContextPath()%>/resources/javascript/commandeClient.js"></script>
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
