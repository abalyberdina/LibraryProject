<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="allusers.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<jsp:include page="../adminusersheader.jsp" />
			<h2 class="form-signin-heading">
				<spring:message code="allusers.header" />
			</h2>
			<jsp:include page="../pagination.jsp"></jsp:include>
			<table class="table">
				<tr>
					<th></th>
					<th><spring:message code="allusers.table.name" /></th>
					<th><spring:message code="allusers.table.role" /></th>
					<th><spring:message code="allusers.table.email" /></th>
					<th><spring:message code="allusers.table.contactphone" /></th>
					<th><spring:message code="allusers.table.address" /></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${users}" var="user">
					<tr>
						<td></td>
						<td><c:out value="${user.getName()}" /></td>
						<spring:message code="${user.getRole()}" var="userRole"></spring:message>
						<td><c:out value="${userRole}" /></td>
						<td><c:out value="${user.getEmail()}" /></td>
						<td><c:out value="${user.getContactPhone()}" /></td>
						<td><c:out value="${user.getAddress()}" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>