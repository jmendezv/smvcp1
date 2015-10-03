<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<h3>
	<spring:message code="home.title" />
</h3>
<c:url value="/admin" var="messageUrl" />
<spring:message code="home.welcome" />
<b><sec:authentication property="principal.username" /></b>
<sec:authorize ifAllGranted="ROLE_ADMIN">
	<a href="${messageUrl}"><spring:message code="home.adminArea" /></a>
</sec:authorize>
<form action="logout" method="post">
	<input type="submit" value="Logout" /> <input type="hidden"
		name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
<c:url value="/profile" var="profileUrl" />
<a href="${profileUrl}">Profile</a>
