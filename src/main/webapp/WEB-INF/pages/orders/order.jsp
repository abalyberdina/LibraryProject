<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="order.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2 class="form-signin-heading">
				<spring:message code="order.header" />
			</h2>
			<form:form method="POST" commandName="order">
				<table>
					<tr>
						<td><spring:message code="order.bookcode" /></td>
						<td>"${order.getBookID()}"</td>
					</tr>
					<tr>
						<td><spring:message code="order.bookname" />:</td>
						<td>"${order.getBookName()}"</td>
					</tr>
					<tr>
						<td><spring:message code="order.bookauthors" />:</td>
						<td>"${order.getBookAuthors()}"</td>
					</tr>
					<tr>
						<td><spring:message code="order.bookgenres" />:</td>
						<td>"${order.getBookGenres()}"</td>
					</tr>
					<tr>
						<td><spring:message code="order.orderdate" />:</td>
						<td>"${order.getDateTaken()}"</td>
					</tr>
					<tr>
						<td><spring:message code="order.datetoreturn" />:</td>
						<td>-</td>
					</tr>
				</table>
				<button class="btn btn-lg btn-success btn-block" type="submit">
					<spring:message code="order.button.save" />
				</button>
				<a class="btn btn-lg btn-primary btn-block"
					href="<%=request.getContextPath()%>/books/all"><spring:message
						code="order.button.cancel" /></a>
			</form:form>

		</div>
	</div>
</body>
</html>