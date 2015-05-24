<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><spring:message code="authorlist.title" /></title>
<jsp:include page="../../resources.jsp" />
</head>
<body>
	<jsp:include page="../../header.jsp" />
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			<h2 class="form-signin-heading">
				<spring:message code="authorlist.header" />
			</h2>
			<jsp:include page="../../pagination.jsp"></jsp:include>
			<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
				<a class="btn btn-lg btn-primary btn-block"
					href='<c:url value="/authors/add"/>'><spring:message
						code="authorlist.button.add" /></a>
			</sec:authorize>
			<c:out value="${authornotexist}"></c:out>
			<table class="table">
				<tr>
					<th></th>
					<th><spring:message code="authorlist.table.name" /></th>
					<th><spring:message code="authorlist.table.pseudonym" /></th>
					<th><spring:message code="authorlist.table.yearofbirth" /></th>
					<th></th>
					<th></th>
				</tr>
				<c:forEach items="${authors}" var="author">
					<tr>
						<td></td>
						<td><c:out value="${author.getName()}" /></td>
						<td><c:out value="${author.getPseudonym()}" /></td>
						<td><c:out value="${author.getYearOfBirth()}" /></td>
						<sec:authorize access="hasRole('ROLE_LIBRARIAN')">

							<td><a
								href='<c:url value="/authors/update/${author.getId()}"/>'><spring:message
										code="authorlist.href.edit" /></a></td>
							<spring:message code="action.remove.text" var="removeText" />

							<td><a
								href='<c:url value="/authors/remove/${author.getId()}" />'
								onclick="return confirm('${removeText}')"><spring:message
										code="authorlist.href.delete" /></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>