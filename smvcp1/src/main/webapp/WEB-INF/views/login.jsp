<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
	
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>


<h3>
	<spring:message code="login.title" />
</h3>
<c:url value="/login" var="loginProcessingUrl" />
<form action="${loginProcessingUrl}" method="post">
	<fieldset>
		<legend><spring:message code="login.form" /></legend>
		<!-- use param.error assuming 
         FormLoginConfigurer#failureUrl contains the query 
         parameter error -->
		<c:if test="${param.error != null}">
			<div>
				<p class="colorao">
					<spring:message code="login.error.validacion" />
					<c:if test="${SPRING_SECURITY_LAST_EXCEPTION != null}">
                  <spring:message code="login.reason" />: <c:out
							value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
					</c:if>
				</p>
			</div>
		</c:if>
		<!-- the configured 
         LogoutConfigurer#logoutSuccessUrl is /login?logout and 
         contains the query param logout -->
		<c:if test="${param.logout != null}">
			<div><spring:message code="login.loggedout" /></div>
		</c:if>
		<p>
			<label for="username"><spring:message code="login.name" /></label> <input type="text" id="username"
				name="username" value="jmendez1@xtec.cat" />
		</p>
		<p>
			<label for="password"><spring:message code="login.password" /></label> <input type="password"
				id="password" name="password" value="123456" />
		</p>
		<!-- if using RememberMeConfigurer make sure 
         remember-me matches 
         RememberMeConfigurer#rememberMeParameter -->
		<p>
			<label for="remember_me"><spring:message code="login.rememberme" /></label> <input type="checkbox"
				id="remember_me" name="remember-me" />
		</p>
		<div>
			<button type="submit" class="btn"><spring:message code="login.login" /></button>
		</div>
		<input type="hidden" name="${_csrf.parameterName}"
			value="${_csrf.token}" />
	</fieldset>
</form>
<c:url value="/register" var="registerUrl" />
<a href="${registerUrl}"><spring:message code="login.register" /></a>
