<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title><spring:message code="myprofile.title" /></title>
<jsp:include page="../resources.jsp" />
<style>
.table {
	counter-reset: none;
}
.table tr td:first-child::before {
	counter-increment: none;
	content: '';
	min-width: 0em;
	margin-right: 0em;
}
.table > tbody > tr > td{
	border-top-width: 0px;
	
}
</style>
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="col-md-offset-2 col-md-8">
		
			<h2 class="form-signin-heading">
				<spring:message code="myprofile.header" />
			</h2>
			<a class="btn btn-lg btn-primary btn-success btn-block"
				href="<%=request.getContextPath()%>/myprofile/myorders"><spring:message
					code="myprofile.button.myorders" /></a>
			<table class="table">
				<tr>
					<td><spring:message code="myprofile.firstname" />:</td>
					<td><c:out value="${profile.getFirstName()}" /></td>
					<td><spring:message code="myprofile.lastname" />:</td>
					<td><c:out value="${profile.getLastName()}" /></td>
				</tr>
				<tr>
					<td><spring:message code="myprofile.login" />:</td>
					<td><c:out value="${profile.getLogin()}" /></td>
					<td><spring:message code="myprofile.contactphone" />:</td>
					<td><c:out value="${profile.getContactPhone()}" /></td>
				</tr>

				<tr>
					<td><spring:message code="myprofile.addresslabel" />:</td>
				</tr>
				<tr>
					<td><spring:message code="myprofile.country" />:</td>
					<td><c:out value="${profile.getCountry()}" /></td>
					<td><spring:message code="myprofile.city" />:</td>
					<td><c:out value="${profile.getCity()}" /></td>
				</tr>
				<tr>
					<td><spring:message code="myprofile.street" />:</td>
					<td><c:out value="${profile.getStreet()}" /></td>
					<td><spring:message code="myprofile.house" />:</td>
					<td><c:out value="${profile.getHouse()}" /></td>
					<td><spring:message code="myprofile.appartment" />:</td>
					<td><c:out value="${profile.getAppartment()}" /></td>
				</tr>
				<tr>
					<td><spring:message code="myprofile.role" />:</td>
					<spring:message code="${profile.getUserType()}" var="userRole"></spring:message>
					<td><c:out value="${userRole}" /></td>
				</tr>
			</table>

			<a class="btn btn-lg btn-primary btn-success btn-block"
				href='<c:url value="/myprofile/edit"/>'><spring:message
					code="myprofile.button.edit" /></a> <a
				class="btn btn-lg btn-primary btn-block"
				href='<c:url value="/myprofile/delete"/>'><spring:message
					code="myprofile.button.delete" /></a>
		</div>
	</div>
</body>
</html>