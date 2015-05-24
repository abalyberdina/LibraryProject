<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand"
				href='<c:url value="/users/all"/>'><spring:message
					code="adminuserheader.home" /></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/users/readers"/>'><spring:message
							code="adminuserheader.raders" /></a></li>
				<li><a href='<c:url value="/users/librarians"/>'><spring:message
							code="adminuserheader.librarians" /></a></li>
				<li><a href='<c:url value="/users/administrators"/>'><spring:message
							code="adminuserheader.administrators" /></a></li>
			</ul>
			<form class="navbar-form navbar-left" role="search" method="GET"
				action='<c:url value="/users/all"/>'>
				<spring:message code="adminuserheader.placeholder.search"
					var="searchtext" />
				<div class="form-group">
					<input name="query" type="text" class="form-control" placeholder="${searchtext}">
				</div>
				<button type="submit" class="btn btn-default">
					<spring:message code="header.button.search" />
				</button>
			</form>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid -->
</nav>