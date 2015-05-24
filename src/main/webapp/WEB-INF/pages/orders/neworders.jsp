<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="neworders.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="col-md-offset col-md-12">
			<jsp:include page="../librarianordersheader.jsp" />
			<h2 class="form-signin-heading">
				<spring:message code="neworders.header" />
			</h2>
			<jsp:include page="../pagination.jsp"></jsp:include>
			<table class="table">
				<tr>
					<th></th>
					<th><spring:message code="neworders.table.orderid" /></th>
					<th><spring:message code="neworders.table.whomade" /></th>
					<th><spring:message code="neworders.table.email" /></th>
					<th><spring:message code="neworders.table.phone" /></th>
					<th><spring:message code="neworders.table.bookcode" /></th>
					<th><spring:message code="neworders.table.numofbookinstock" /></th>
					<th><spring:message code="neworders.table.orderdate" /></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${orders}" var="order">
					<tr>
						<td></td>
						<td><c:out value="${order.getOrderID()}" /></td>
						<td><c:out value="${order.getUserName()}" /></td>
						<td><c:out value="${order.getUserEmail()}" /></td>
						<td><c:out value="${order.getUserPhone()}" /></td>
						<td><c:out value="${order.getBookID()}" /></td>
						<td><c:out value="${order.getBookCount()}" /></td>
						<td><c:out value="${order.getOrderDate()}" /></td>
						<td><a
							href='<c:url value="/orders/confirm/${order.getOrderID()}"/>'><spring:message
									code="neworders.href.confirm" /></a></td>
						<td><a href='<c:url value="/orders/cancel/${order.getOrderID()}"/>'><spring:message
									code="neworders.href.cancel" /></a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>