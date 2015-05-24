
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
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
			<a class="navbar-brand" href='<c:url value="/"/>'><spring:message
					code="header.home" /></a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
	
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/books/all"/>'><spring:message
							code="header.books" /></a></li>
				<li><a href='<c:url value="/authors/all"/>'><spring:message
							code="header.authors" /></a></li>
				<li><a href='<c:url value="/genres/all"/>'><spring:message
							code="header.genres" /></a></li>
				<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
					<li><a href='<c:url value="/orders/all"/>'><spring:message
								code="header.orders" /></a></li>
				</sec:authorize>
				<sec:authorize access="hasRole('ROLE_ADMINISTRATOR')">
					<li><a href='<c:url value="/users/all"/>'><spring:message
								code="header.users" /></a></li>
				</sec:authorize>
			</ul>
			<form class="navbar-form navbar-left" role="search" method="GET" action='<c:url value="/books/all"/>'>
				<div class="form-group">
					<spring:message code="header.placeholder.search" var="searchtext" />
					<input name="query" type="text" class="form-control"
						placeholder="${searchtext}">
				</div>
				<button type="submit" class="btn btn-default">
					<spring:message code="header.button.search" />
				</button>
			</form>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"><spring:message
							code="header.language" /> <span class="caret"></span> </a>
					<ul class="dropdown-menu" role="menu">
						<li><a href="?lang=en"><spring:message
									code="header.language.english" /></a></li>
						<li><a href="?lang=ru"><spring:message
									code="header.language.russian" /></a></li>
					</ul></li>
				<sec:authorize access="isAuthenticated()">
					<li><a href="<%=request.getContextPath()%>/myprofile"><sec:authentication
								property="principal.username" /></a></li>
					<li><a href='<c:url value="/logout"/>'><spring:message
								code="header.logout" /></a></li>
				</sec:authorize>
				<sec:authorize access="isAnonymous()">
					<li><a href="<%=request.getContextPath()%>/login"><spring:message
								code="header.reglogin" /></a></li>
				</sec:authorize>
			</ul>
		</div>
	</div>
</nav>

<c:if test="${dangerError != null}">
				<div class="alert alert-danger" role="alert">
				<spring:message code="${dangerError}"></spring:message>
				<% session.setAttribute("dangerError", null); %>
				</div>
				
			</c:if>
			
			<c:if test="${successAction != null}">
				<div class="alert alert-success" role="alert">
				<spring:message code="${successAction}"></spring:message>
				<% session.setAttribute("successAction", null); %>
				</div>
				
			</c:if>