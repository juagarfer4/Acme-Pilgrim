<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="${actionURI}" modelAttribute="pilgrimForm">

	<acme:textbox code ="pilgrim.userAccount.username"  path="username"/>
	<br />
	<acme:password code ="pilgrim.userAccount.password"  path="password"/>
	<br />
	<acme:password code ="pilgrim.userAccount.passwordVerificada"  path="passwordVerificada"/>
	<br />
		<acme:textbox code ="pilgrim.name"  path="name"/>
	<br />
		<acme:textbox code ="pilgrim.surname"  path="surname"/>
	<br />
		<acme:textbox code ="pilgrim.email"  path="email"/>
	<br />
		<acme:textbox code ="pilgrim.contactPhone"  path="contactPhone"/>
	<br />
		<acme:textbox code ="pilgrim.homePage"  path="homePage"/>
	<br />
	
	<h3><spring:message code="pilgrim.creditCard.title" /></h3>
		<acme:textbox code ="pilgrim.creditCard.holderName"  path="creditCard.holderName"/>
	<br />
		<acme:textbox code ="pilgrim.creditCard.brandName"  path="creditCard.brandName"/>
	<br />
		<acme:textbox code ="pilgrim.creditCard.expirationMonth"  path="creditCard.expirationMonth"/>
	<br />
		<acme:textbox code ="pilgrim.creditCard.expirationYear"  path="creditCard.expirationYear"/>
	<br />
		<acme:textbox code ="pilgrim.creditCard.cVVCode"  path="creditCard.cVVCode"/>
	<br />
		<acme:textbox code ="pilgrim.creditCard.cardNumber"  path="creditCard.cardNumber"/>
	<br />
		<acme:textbox code ="pilgrim.birthDate"  path="birthDate"/>
	<br />
		<acme:textbox code ="pilgrim.nationality"  path="nationality"/>
	<br />
	
	<acme:checkbox path="condition" url="privacy/lopd-lssi.do" code="pilgrim.laws" />
	<br />
		<acme:submit code="pilgrim.register" name="save" />
	&nbsp;
	
	<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="pilgrim.cancel"/>" /></a>





	
	
</form:form>
