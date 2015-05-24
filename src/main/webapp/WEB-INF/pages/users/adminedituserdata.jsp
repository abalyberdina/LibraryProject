<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="edituserdata.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
<body><jsp:include page="../header.jsp" />
	<div class="container">
		<div style="text-align: center" class="col-md-offset-3 col-md-6">
			<h2 class="form-signin-heading">
				<spring:message code="edituserdata.header" />
			</h2>
			<form:form method="POST" commandName="editUserForm">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.firstname"
								var="firstname" />
							<form:input path="firstName" class="form-control input-sm"
								placeholder="${firstname}" />
							<form:errors path="firstName"></form:errors>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.lastname"
								var="lastname" />
							<form:input path="lastName" class="form-control input-sm"
								placeholder="${lastname}" />
							<form:errors path="lastName"></form:errors>

						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class=" col-xs-6 col-sm-6 col-md-6">
						<spring:message code="edituserdata.placeholder.contactphone"
							var="contactphone" />
						<form:input path="contactPhone" class="form-control input-sm"
							placeholder="${contactphone}" />
						<form:errors path="contactPhone"></form:errors>

					</div>
				</div>
				<div class="row form-group">
					<div class=" col-xs-6 col-sm-6 col-md-6">
						<h4 align="center">
							<spring:message code="edituserdata.address" />
							:
						</h4>
						<spring:message code="edituserdata.placeholder.country"
							var="country" />
						<form:input path="country" class="form-control input-sm"
							placeholder="${country}" />
						<form:errors path="country"></form:errors>

					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.city" var="city" />
							<form:input path="city" class="form-control input-sm"
								placeholder="${city}" />
							<form:errors path="city"></form:errors>

						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.street"
								var="street" />
							<form:input path="street" class="form-control input-sm"
								placeholder="${street}" />
							<form:errors path="street"></form:errors>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.house" var="house" />
							<form:input path="house" class="form-control input-sm"
								placeholder="${house}" />
							<form:errors path="house"></form:errors>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.apartment"
								var="apartment" />
							<form:input path="appartment" class="form-control input-sm"
								placeholder="${apartment}" />
							<form:errors path="appartment"></form:errors>
						</div>
					</div>
				</div>
				<form:errors />
				<input type="submit"
					value=<spring:message code="edituserdata.button.save" />
					class="btn btn-info btn-block" />
			</form:form>
		</div>
	</div>
</body>
</html>