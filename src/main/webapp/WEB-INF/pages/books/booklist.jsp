<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="booklist.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
	<jsp:include page="../header.jsp" />
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2 class="form-signin-heading">
				<spring:message code="booklist.header" />
			</h2>
			<jsp:include page="../pagination.jsp"></jsp:include>
			<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
				<a class="btn btn-lg btn-primary btn-block"
					href='<c:url value="/books/add"/>'><spring:message
						code="booklist.button.add" /></a>
			</sec:authorize>
			<table class="table">
				<tr>
					<th></th>
					<th><spring:message code="booklist.table.bookname" /></th>
					<th><spring:message code="booklist.table.bookcount" /></th>
					<th><spring:message code="booklist.table.bookgenre" /></th>
					<th><spring:message code="booklist.table.bookauthor" /></th>
				</tr>
				<c:forEach items="${books}" var="book">
					<tr>
						<td></td>
						<td><c:out value="${book.getName()}" /></td>
						<td><c:out value="${book.getNumber()}" /></td>
						<td><c:out value="${book.getGenres()}" /></td>
						<td><c:out value="${book.getAuthors()}" /></td>
						<sec:authorize access="isAuthenticated()">
							<td><a href='<c:url value="/books/order/${book.getId()}"/>'><spring:message
										code="booklist.href.order" /></a></td>
						</sec:authorize>
						<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
							<td><a href='<c:url value="/books/update/${book.getId()}"/>'><spring:message
										code="booklist.href.edit" /></a></td>
							<spring:message code="action.remove.text" var="removeText" />
							<td><a href='<c:url value="/books/remove/${book.getId()}"/>'
								onclick="return confirm('${removeText}')"><spring:message
										code="booklist.href.delete" /></a></td>
						</sec:authorize>
						<td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>