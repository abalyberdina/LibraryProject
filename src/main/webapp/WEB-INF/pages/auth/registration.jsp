<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><spring:message code="registration.title" /></title>
<jsp:include page="../resources.jsp" />
</head>
<body>
<body><jsp:include page="../header.jsp" />
	<div class="container">
		<div style="text-align: center" class="col-md-offset-3 col-md-6">
			<h2 class="form-signin-heading">
				<spring:message code="registration.header" />
			</h2>
			<form:form method="POST" commandName="registerForm">
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="edituserdata.placeholder.firstname"
								var="firstname" />
							<form:input path="firstName" class="form-control input-sm"
								placeholder="${firstname}" />
							<form:errors path="firstName">
							</form:errors>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="registration.placeholder.lastname"
								var="lastname" />
							<form:input path="lastName" class="form-control input-sm"
								placeholder="${lastname}" />
							<form:errors path="lastName"></form:errors>
						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<spring:message code="registration.placeholder.login" var="login" />
						<form:input path="username" class="form-control input-sm"
							placeholder="${login}" />
						<form:errors path="username"></form:errors>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="registration.placeholder.password"
								var="password" />
							<form:password path="password" class="form-control input-sm"
								placeholder="${password}" />
							<form:errors path="password"></form:errors>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="registration.placeholder.confirmpassword"
								var="confirmpassword" />
							<form:password path="passwordConfirmation"
								class="form-control input-sm" placeholder="${confirmpassword}" />
							<form:errors path="passwordConfirmation"></form:errors>
						</div>
					</div>
				</div>
				<div class="row form-group">
					<div class=" col-xs-6 col-sm-6 col-md-6">
						<spring:message code="registration.placeholder.contactphone"
							var="contactphone" />
						<form:input path="contactPhone" class="form-control input-sm"
							placeholder="${contactphone}" />
						<form:errors path="contactPhone"></form:errors>
					</div>
				</div>
				<div class="row form-group">
					<div class=" col-xs-6 col-sm-6 col-md-6">
						<h4 align="center">
							<spring:message code="registration.label.address" />
							:
						</h4>
						<spring:message code="registration.placeholder.country"
							var="country" />
						<form:input path="country" class="form-control input-sm"
							placeholder="${country}" />
						<form:errors path="country"></form:errors>
					</div>
				</div>
				<div class="row">
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="registration.placeholder.city" var="city" />
							<form:input path="city" class="form-control input-sm"
								placeholder="${city}" />
							<form:errors path="city"></form:errors>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="registration.placeholder.street"
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
							<spring:message code="registration.placeholder.house" var="house" />
							<form:input path="house" class="form-control input-sm"
								placeholder="${house}" />
							<form:errors path="house"></form:errors>
						</div>
					</div>
					<div class="col-xs-6 col-sm-6 col-md-6">
						<div class="form-group">
							<spring:message code="registration.placeholder.apartment"
								var="apartment" />
							<form:input path="appartment" class="form-control input-sm"
								placeholder="${apartment}" />
							<form:errors path="appartment"></form:errors>
						</div>
					</div>
				</div>
				<input type="submit"
					value=<spring:message code="registration.button.registration" />
					class="btn btn-info btn-block">

			</form:form>
		</div>
	</div>
</body>
</html>