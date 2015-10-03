<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>
	<spring:message code="register.title" />
</h3>
<c:url value="/register" var="registerProcessingUrl" />
<form:form action="${registerProcessingUrl}" method="post"
	commandName="userRegistrationBean">
	<fieldset>
		<legend><spring:message code="register.legend" /></legend>
		<form:errors path="*" />

		<p>
			<label for="username"><spring:message code="register.username" /></label>
			<form:input type="text" id="username" name="username" path="userName" />
			<form:errors path="userName" />
		</p>
		<p>
			<label for="password"><spring:message code="register.password" /></label>
			<form:input type="password" id="password" name="password"
				path="password" />
			<form:errors path="password" />
		</p>
		<p>
			<label for="passwordConfirmation"><spring:message code="register.passwordConfirmation" /></label>
			<form:input type="password" id="passwordConfirmation"
				name="passwordConfirmation" path="passwordConfirmation" />
			<form:errors path="passwordConfirmation" />
		</p>

		<div>
			<button type="submit" class="btn" name="action" value="save"><spring:message code="register.save" /></button>
			<button type="submit" class="btn" name="action" value="cancel"><spring:message code="register.cancel" /></button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</fieldset>
</form:form>