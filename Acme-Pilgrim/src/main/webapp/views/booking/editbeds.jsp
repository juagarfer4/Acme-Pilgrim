<%--
 * list.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="${actionURI}" modelAttribute="booking">

	<form:hidden path="id" />
    <form:hidden path="version" />
	<form:hidden path="pilgrim" />
	<form:hidden path="lodge" />
	<form:hidden path="creationMoment" />
	<form:hidden path="arrivalDate" />
	<form:hidden path="price" />
	<form:hidden path="isCancelled" />
	<form:hidden path="numberOfNights" />

	<acme:textbox code ="booking.numberOfBedsRequested"  path="numberOfBedsRequested"/>
	<br />
	
	<acme:submit code="booking.save" name="save" />
	
	&nbsp;
	
	 <a href="booking/pilgrim/list.do"><input type="button" name="cancel"
		value="<spring:message code="booking.cancel"/>" /></a>
	
</form:form>
