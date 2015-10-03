<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>
	<spring:message code="profile.title" />
</h3>
<c:url value="/profile" var="profileProcessingUrl" />
<form:form action="${profileProcessingUrl}" method="post"
	commandName="profileBean">
	<fieldset class="container">
		<legend>
			<spring:message code="profile.legend" />
		</legend>
		<form:errors path="*" />
		<p>
			<label for="name"><spring:message code="profile.name" /></label>
			<form:input type="text" id="name" name="name" path="name" />
			<form:errors path="name" />
		</p>
		<p>
			<label for="city"><spring:message code="profile.city" /></label>
			<form:input type="text" id="city" name="city" path="city" />
			<form:errors path="city" />
		</p>
		<p>
			<label for="profession"><spring:message
					code="profile.profession" /></label>
			<form:input type="text" id="profession" name="profession"
				path="profession" />
			<form:errors path="profession" />
		</p>
		<p>
			<label for="phone"><spring:message code="profile.phone" /></label>
			<form:input type="text" id="phone" name="phone" path="phone" />
			<form:errors path="phone" />
		</p>
		<div>
			<button type="submit" class="btn" name="action" value="save">
				<spring:message code="profile.action.save" />
			</button>
			<button type="submit" class="btn" name="action" value="cancel">
				<spring:message code="profile.action.cancel" />
			</button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</fieldset>
</form:form>