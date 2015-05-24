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
<title><spring:message code="genrelist.title" /></title>
<jsp:include page="../../resources.jsp" />
</head>
<body>
	<jsp:include page="../../header.jsp" />
	<div class="container">
		<div class="col-md-offset-1 col-md-10">
			
		
		
			<h2 class="form-signin-heading">
				<spring:message code="genrelist.header" />
			</h2>
			<jsp:include page="../../pagination.jsp"></jsp:include>
			<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
				<a class="btn btn-lg btn-primary btn-block"
					href='<c:url value="/genres/add"/>'><spring:message
						code="genrelist.button.add" /></a>
			</sec:authorize>
			<c:out value="${genrenotexist}"></c:out>
			<table class="table">
				<tr>
					<th></th>
					<th><spring:message code="genrelist.table.name" /></th>
					<!-- 				<th>Parent Genre</th> -->
				</tr>
				<c:forEach items="${genres}" var="genre">
					<tr>
						<td></td>
						<td><c:out value="${genre.getName()}" /></td>
						<sec:authorize access="hasRole('ROLE_LIBRARIAN')">
							<td><a
								href='<c:url value="/genres/update/${genre.getId()}"/>'><spring:message
										code="genrelist.href.edit" /></a></td>
							<td>
							<spring:message code="action.remove.text" var="removeText"/>
							<a
								href='<c:url value="/genres/remove/${genre.getId()}"/>'
								onclick="return confirm('${removeText}')" ><spring:message
										code="genrelist.href.delete" /></a></td>
						</sec:authorize>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>