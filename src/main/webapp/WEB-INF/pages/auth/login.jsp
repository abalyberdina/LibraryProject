<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<title><spring:message code="login.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
<body><jsp:include page="../header.jsp" />
	<div class="container">
		<form class="form-signin col-md-offset-3 col-md-6" method="POST">
			<h2 class="form-signin-heading">
				<spring:message code="login.header" />
			</h2>
			<input type="text" id="username" name="username" class="form-control"
				placeholder=<spring:message
					code="login.placeholder.login" />
				required autofocus> <br />
			<input type="password" id="password" name="password"
				class="form-control"
				placeholder=<spring:message
					code="login.placeholder.password" />
				required> <br />
			<c:if test="${param.error == 1}">
				<spring:message code="login.error" />
			</c:if>

			<button class="btn btn-lg btn-success btn-block" type="submit">
				<spring:message code="login.button.signin" />
			</button>
			<a class="btn btn-lg btn-primary btn-block"
				href='<c:url value="/registration"/>'><spring:message
					code="login.button.register" /></a>
		</form>
	</div>
</body>
</html>