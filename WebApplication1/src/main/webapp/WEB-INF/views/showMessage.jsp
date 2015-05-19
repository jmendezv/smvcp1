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
<link href="<c:url value='/resources/css/main.css' />" rel="stylesheet" type="text/css" />
<style>
	.error {font-style: italic; color: red;}
	.message {font-weight: bold; color: blue;}
</style>
<title>Welcome</title>
</head>
<body>
	<h1>WebApplication1</h1>
	<h2>Student Form</h2>
	<form:form action="/WebApplication1/form" method="POST" commandName="student">
		<p><form:label path="name">Name:</form:label>
		<form:input path="name" />
		<form:errors cssClass="error" path="name" /></p>
		<p><form:label path="mark">Mark:</form:label>
		<form:input path="mark" />
		<form:errors cssClass="error" path="mark" /></p>
		<form:button name="action" value="send">Send</form:button>
	</form:form>

	<c:if test="${not empty student1}">
		<p class="message">${student1.name} ${student1.mark} was added</p>
		</c:if>

	<h6>Current Locale : ${pageContext.response.locale}</h6>
</body>
</html>
