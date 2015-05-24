<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="ordersinprogress.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<jsp:include page="../librarianordersheader.jsp" />
			<h2 class="form-signin-heading">
				<spring:message code="ordersinprogress.header" />
			</h2>
			<jsp:include page="../pagination.jsp"></jsp:include>
			<table class="table">
				<tr>
					<th></th>
					<th><spring:message code="ordersinprogress.table.orderid" /></th>
					<th><spring:message code="ordersinprogress.table.whomade" /></th>
					<th><spring:message code="ordersinprogress.table.bookcode" /></th>
					<th><spring:message code="ordersinprogress.table.bookname" /></th>
					<th><spring:message code="ordersinprogress.table.orderdate" /></th>
					<th><spring:message code="ordersinprogress.table.datetoreturn" /></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td></td>
						<td><c:out value="${order.getOrderID()}" /></td>
						<td><c:out value="${order.getUserName()}" /></td>
						<td><c:out value="${order.getBookID()}" /></td>
						<td><c:out value="${order.getBookName()}" /></td>
						<td><c:out value="${order.getOrderDate()}" /></td>
						<td><c:out value="${order.getDateToReturn()}" /></td>
						<td><a
							href='<c:url value="/orders/close/${order.getOrderID()}"/>'><spring:message
									code="ordersinprogress.href.close" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>