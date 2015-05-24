<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="home.title" /></title>
<jsp:include page="resources.jsp" />
<style type="text/css">
body {
	background-image:
		url("<%=request.getContextPath()%>/resources/img/bg.png");
	background-position: center;
	background-repeat: no-repeat;
	background-position: center center;
	background-size: 34%;
}

html, body {
	min-height: 100% !important;
	height: 100%;
}
</style>
</head>
<body><jsp:include page="header.jsp" />

	<div class="container">
		<div class="col-md-offset-1 col-md-10 text-center">
			<h2><spring:message code="home.header" /></h2>
			<spring:message code="home.text" />
		</div>

	</div>
</body>
</html>