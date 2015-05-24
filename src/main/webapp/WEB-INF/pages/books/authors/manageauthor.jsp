<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="manageauthor.title" /></title>
<jsp:include page="../../resources.jsp" />
</head>
<body>
	<jsp:include page="../../header.jsp" />
	<div class="container">
		<div style="text-align: center" class="col-md-offset-3 col-md-6">
			<h2 class="form-signin-heading">
				<spring:message code="manageauthor.header" />
			</h2>
			<form:form method="POST" commandName="manageAuthorForm">
				<div class="form-group">
					<spring:message code="manageauthor.placeholder.name" var="name" />
					<form:input path="name" class="form-control input-sm"
						placeholder="${name}" />
					<form:errors path="name"></form:errors>

				</div>
				<div class="form-group">
					<spring:message code="manageauthor.placeholder.pseudonym"
						var="pseudonym" />
					<form:input path="pseudonym" class="form-control input-sm"
						placeholder="${pseudonym}" />
						<form:errors path="pseudonym"></form:errors>
				</div>
				<div class="form-group">
					<spring:message code="manageauthor.placeholder.yearOfBirth"
						var="yearOfBirth" />
					<form:input path="yearOfBirth" class="form-control input-sm"
						placeholder="${yearOfBirth}" />
						<form:errors path="yearOfBirth"></form:errors>
				</div>
				<form:errors></form:errors>
				<input type="submit"
					value=<spring:message code="manageauthor.button.save"/>
					class="btn btn-info btn-block">
			</form:form>
		</div>
	</div>
</html>