<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> -->
<title><spring:message code="managebook.title" /></title>
<jsp:include page="../resources.jsp" />

</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div style="text-align: center" class="col-md-offset-3 col-md-6">
			<h2 class="form-signin-heading">
				<spring:message code="managebook.header" />
			</h2>
			<c:out value="${authornotexist}"></c:out>
			<c:out value="${genrenotexist}"></c:out>
			<form:form method="POST" commandName="addBookForm">
				<div class="form-group">
					<spring:message code="managebook.placeholder.bookName"
						var="bookName" />
					<form:input path="bookName" class="form-control input-sm"
						placeholder="${bookName}" />
					<form:errors path="bookName"></form:errors>

				</div>

				<div class="form-group">
					<spring:message code="managebook.placeholder.numOfBooks"
						var="numOfBooks" />
					<form:input path="numOfBooks" class="form-control input-sm"
						placeholder="${numOfBooks}" />
					<form:errors path="numOfBooks"></form:errors>

				</div>
				<div class="form-group">
					<spring:message code="managebook.placeholder.authors" var="authors" />
					<form:input path="authors" class="form-control input-sm"
						placeholder="${authors}" />
					<form:errors path="authors"></form:errors>

				</div>
				<div class="form-group">
					<spring:message code="managebook.placeholder.genres" var="genres" />
					<form:input path="genres" class="form-control input-sm"
						placeholder="${genres}" />
					<form:errors path="genres"></form:errors>

				</div>
				<form:errors />
				<input type="submit"
					value=<spring:message code="managebook.button.save" />
					class="btn btn-info btn-block">
			</form:form>
		</div>
	</div>
</body>
</html>