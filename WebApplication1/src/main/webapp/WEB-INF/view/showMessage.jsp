<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
<meta charset="utf-8">
<title>Welcome</title>
</head>
<body>
	<form:form action="/WebApplication1/form" method="POST" commandName="student">
		<form:label path="name">Name:</form:label>
		<form:input path="name" />
		<form:errors path="name" />
		<form:label path="mark">Mark:</form:label>
		<form:input path="mark" />
		<form:errors path="mark" />
		<form:button name="action" value="send">Send</form:button>
	</form:form>

	<c:if test="${not empty student1}">
		${student1.name} ${student1.mark}
		</c:if>

	<h6>Current Locale : ${pageContext.response.locale}</h6>
</body>
</html>
