<aside class="main-sidebar sidebar-dark-primary elevation-4">
	<c:url value="/home/" var="home" />
	<a href="${home}" class="brand-link"> <img
		src="<%=request.getContextPath()%>/resources/dist/img/AdminLTELogo.png"
		alt="AdminLTE Logo" class="brand-image img-circle elevation-3"
		style="opacity: .8"> <span class="brand-text font-weight-light">Global
			Business</span>
	</a>
	<div class="sidebar">
		<div class="user-panel mt-3 pb-3 mb-3 d-flex">
			<div class="image">
				<img
					src="<%=request.getContextPath()%>/resources/dist/img/Adil_LAHLOU.jpg"
					class="img-circle elevation-2" alt="User Image">
			</div>
			<div class="info">
				<a href="#" class="d-block" style="font-weight: 600;">LAHLOU Adil</a>
			</div>
		</div>
		<nav class="mt-2">
			<!-- Home -->
			<ul class="nav nav-pills nav-sidebar flex-column"
				data-widget="treeview" role="menu" data-accordion="false">
				<c:url value="/home/" var="home" />
				<li class="nav-item"><a href="${home}" class="nav-link"> <i
						class="nav-icon fas fa-chart-line"></i>
						<p>
							<fmt:message code="common.dashboard" />
						</p>
				</a></li>
				<!-- Article -->
				<c:url value="/article/" var="article" />
				<li class="nav-item"><a href="${article}" class="nav-link">
						<i class="nav-icon fas fa-boxes"></i>
						<p>
							<fmt:message code="common.article" />
						</p>
				</a></li>
				<!-- Client -->
				<c:url value="/client/" var="client" />
				<li class="nav-item has-treeview">
				<a href="${client}" class="nav-link"><i class="nav-icon fas fa-chalkboard-teacher"></i>
					<p>
						<fmt:message code="common.client" /><i class="right fas fa-angle-left"></i>
					</p>
				</a>
				<!-- Client -->
				<ul class="nav nav-treeview">
				<c:url value="/client/" var="client" />
					<li class="nav-item"><a	href="${client}" class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>
									<fmt:message code="common.client" />
								</p>
						</a></li>
				<c:url value="/commandeclient/" var="cmdClient" />
					<li class="nav-item"><a href="${cmdClient}" class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>
									<fmt:message code="common.client.commande" />
								</p>
						</a></li>
				</ul>
				</li>
				<!-- Fournisseur -->
				<c:url value="/fournisseur/" var="fournisseur" />
				<li class="nav-item has-treeview"><a href="${fournisseur}" class="nav-link">
						<i class="nav-icon fas fa-address-card"></i>
						<p>
							<fmt:message code="common.fournisseur" />
							<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
						<li class="nav-item"><a
							href="${fournisseur}"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>
									<fmt:message code="common.fournisseur" />
								</p>
						</a></li>
						<c:url value="/commandefournisseur/" var="cmdFournisseur" />
						<li class="nav-item"><a
							href="${cmdFournisseur}"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>
									<fmt:message code="common.fournisseur.commande" />
								</p>
						</a></li>
					</ul></li>

				<c:url value="/stock/" var="stock" />	
				<li class="nav-item"><a href="${stock}" class="nav-link">
						<i class="nav-icon fas fa-box-open"></i>
						<p>
							<fmt:message code="common.stock" />
						</p>
				</a></li>
				<c:url value="/vente/" var="vente" />	
				<li class="nav-item"><a href="${vente}" class="nav-link">
						<i class="nav-icon fas fa-donate"></i>
						<p>
							<fmt:message code="common.vente" />
						</p>
				</a></li>
				<c:url value="/achat/" var="achat" />	
				<li class="nav-item"><a href="${achat}" class="nav-link">
						<i class="nav-icon fas fa-cart-arrow-down"></i>
						<p>
							<fmt:message code="common.achat" />
						</p>
				</a></li>
<!-- 				<c:url value="/parametrageCategory/" var="prmCategory" />	 -->
				<li class="nav-item has-treeview"><a href="#" class="nav-link">
						<i class="nav-icon fas fa-cogs"></i>
						<p>
							<fmt:message code="common.parametrage" />
							<i class="right fas fa-angle-left"></i>
						</p>
				</a>
					<ul class="nav nav-treeview">
					<c:url value="/category/" var="category" />
						<li class="nav-item"><a	href="${category}"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>
									<fmt:message code="common.parametrage.category" />
								</p>
						</a></li>
						<c:url value="/utilisateur/" var="prmUtilisateur" />
						<li class="nav-item"><a
							href="${prmUtilisateur}"
							class="nav-link"> <i class="far fa-circle nav-icon"></i>
								<p>
									<fmt:message code="common.parametrage.utilisateur" />
								</p>
						</a></li>
					</ul></li>

			</ul>
		</nav>
	</div>
</aside>