  <nav class="main-header navbar navbar-expand navbar-white navbar-light">
    <ul class="navbar-nav">
      <li class="nav-item">
        <a class="nav-link" data-widget="pushmenu" href="#"><i class="fas fa-bars"></i></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link"><fmt:message code="common.acceuil"/></a>
      </li>
      <li class="nav-item d-none d-sm-inline-block">
        <a href="#" class="nav-link"><fmt:message code="common.contact.us" /></a>
      </li>
    </ul>
<!--     <form class="form-inline ml-3"> -->
<!--       <div class="input-group input-group-sm"> -->
<!--         <input class="form-control form-control-navbar" type="search" placeholder="Rechercher..." aria-label="Search"> -->
<!--         <div class="input-group-append"> -->
<!--           <button class="btn btn-navbar" type="submit"> -->
<!--             <i class="fas fa-search"></i> -->
<!--           </button> -->
<!--         </div> -->
<!--       </div> -->
<!--     </form> -->
    
    <ul class="navbar-nav ml-auto">
    <li class="nav-item dropdown">
        <a class="nav-link" data-toggle="dropdown" href="#" aria-expanded="false">
          <i class="fa fa-language" style="font-size: 28px;"></i>
        </a>
        <div class="dropdown-menu dropdown-menu-lg dropdown-menu-right">
		<c:url value="/changelocale/fr" var="frUrl"/>
		<c:url value="/changelocale/en" var="enUrl"/>
          <div class="dropdown-divider"></div>
          <a href="${frUrl}" class="dropdown-item">
            <!-- Message Start -->
            <div class="media">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                 <i class="fa fa-globe fa-fw"> <fmt:message code="locale.fr" /></i>
                </h3>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <a href="${enUrl}" class="dropdown-item">
            <!-- Message Start -->
            <div class="media">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  <i class="fa fa-globe fa-fw"> <fmt:message code="locale.en" /></i>
                </h3>
              </div>
            </div>
            <!-- Message End -->
          </a>
          <div class="dropdown-divider"></div>
          <c:url value="/j_spring_security_logout" var="logout"/>
          <a href="${logout}" class="dropdown-item">
            <div class="media">
              <div class="media-body">
                <h3 class="dropdown-item-title">
                  <i class="fas fa-sign-out-alt"> <fmt:message code="common.Logout"/></i>
                </h3>
              </div>
            </div>
          </a>
        </div>
      </li>
    </ul>
    
 </nav>