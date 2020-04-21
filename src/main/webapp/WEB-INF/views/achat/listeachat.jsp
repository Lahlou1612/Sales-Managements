<%@ include file="/WEB-INF/views/includes/includes.jsp"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>GLOBAL BUSINESS APPLICATION</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

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
								<fmt:message code="common.achat.list.button" />
							</h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="#"><fmt:message code="common.achat.gestion"/></a></li>
								<li class="breadcrumb-item active"><fmt:message code="common.achat.list.archive" /></li>
							</ol>
						</div>
					</div>
				</div>
			</section>
			<section class="content">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">
							<button type="button" class="btn  btn-outline-primary btn-sm" style="margin-right: 5px;">
								<i class="fa fa-upload"></i> &nbsp;
								<a href="#" id="hover"> <fmt:message code="common.importer" /></a>
							</button>
							<button type="button" class="btn btn-outline-primary btn-sm" style="margin-right: 5px;">
								<i class="fas fa-download"></i> &nbsp;
								<a href="#" id="hover"> <fmt:message code="common.exporter" /></a>
							</button>
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
              <div class="card-header" style="background-color: #C2F7A9; ">
                <h3 class="card-title"><fmt:message code="common.achat.list.valide" /></h3>
              </div>
              <div class="card-body p-0">
                <table class="table table-striped table-bordered table-hover">
                  <thead>
                    <tr>
                      <th><fmt:message code="common.achat.code" /></th>
                      <th><fmt:message code="common.achat.date" /></th>
                      <th><fmt:message code="common.achat.idFournisseur" /></th>
                      <th><fmt:message code="common.achat.idCommande" /></th>
                      <th><fmt:message code="common.achat.statut" /></th>
                      <th><fmt:message code="common.achat.montant" /></th>
                    </tr>
                  </thead>
                  <tbody>
	                  <c:forEach items="${achatsValider}" var="valider">
		                  <tr>
		                  		<td>${valider.getCode()}</td>
		                  		<td>${valider.getDateAchat()}</td>
		                  		<td>${valider.getNomFournisseur()}</td>
		                  		<td>${valider.getIdCommande()}</td>
		                  		<td>${valider.getStatut()}</td>
		                  		<td>${valider.getMontantGlobal()}</td>
						</tr>
	                </c:forEach>
                  </tbody>
                </table>
              </div>
            </div>
						
			<div class="card" id="detailAchat">
              <div class="card-header" style="background-color: #F58198;">
                <h3 class="card-title"><fmt:message code="common.achat.list.rejete" /></h3>
              </div>
              <div class="card-body p-0">
                <table class="table table-striped table-bordered table-hover">
                  <thead>
                    <tr>
                      <th><fmt:message code="common.achat.code" /></th>
                      <th><fmt:message code="common.achat.date" /></th>
                      <th><fmt:message code="common.achat.idFournisseur" /></th>
                      <th><fmt:message code="common.achat.idCommande" /></th>
                      <th><fmt:message code="common.achat.statut" /></th>
                      <th><fmt:message code="common.achat.montant" /></th>
                    </tr>
                  </thead>
	                  <tbody>
						  <c:forEach items="${achatsRejeter}" var="rejeter">
		                  	<tr>
		                  		<td>${rejeter.getCode()}</td>
		                  		<td>${rejeter.getDateAchat()}</td>
		                  		<td>${rejeter.getNomFournisseur()}</td>
		                  		<td>${rejeter.getIdCommande()}</td>
		                  		<td>${rejeter.getStatut()}</td>
		                  		<td>${rejeter.getMontantGlobal()}</td>
							</tr>
	               		 </c:forEach>
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
