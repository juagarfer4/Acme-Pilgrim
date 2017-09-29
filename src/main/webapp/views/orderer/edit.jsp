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

<form:form action="${actionURI}" modelAttribute="orderer">

	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<form:hidden path="identifier" />
	<form:hidden path="stage" />


	<form:label path="route">
				<spring:message code="orderer.route" />
			</form:label>
			<form:select path="route" >
				<form:option label="-----------" value="0"/>
				<form:options items="${routes}" itemLabel="name" itemValue="id" />
			</form:select>
			<form:errors cssClass="error" path="route" />
			
			<br />
	
	
	<input type="submit" name="save" value="<spring:message code="orderer.register" />" />
	&nbsp;
	<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="orderer.cancel"/>" /></a>
	
</form:form>
