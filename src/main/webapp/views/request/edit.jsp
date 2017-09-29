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


<form:form action="${actionURI}" modelAttribute="request">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="innkeeper" />
	<form:hidden path="code" />
	<form:hidden path="creationMoment" />
	<jstl:if test="${request.id == 0}">
		<form:hidden path="status" />

	<acme:textbox code ="request.title"  path="title"/>
	<br />
	<acme:textarea code ="request.description"  path="description"/>
	<br />
	<acme:select items="${administrators}" itemLabel="name" code="request.administrator" path="administrator"/>
	</jstl:if>
	<jstl:if test="${request.id != 0}">
		<form:hidden path="title" />
		<form:hidden path="description" />
		<form:hidden path="administrator" />
		<form:label path="status">
			<spring:message code="request.status" />
		</form:label>
		<form:select path="status" >
			<form:option label="----" value="0" />
			<form:option label="ACCEPTED" value="ACCEPTED"></form:option>
			<form:option label="REJECTED" value="REJECTED"></form:option>
		</form:select>
		<form:errors cssClass="error" path="status"/>
		<br />
		<acme:textbox code ="request.comments"  path="comments"/>
	</jstl:if>
	<br />
	
	<input type="submit" name="save" value="<spring:message code="request.save"/>" />
	
	&nbsp;
	
	 <a href="<spring:url value='/' />"><input type="button" name="Cancel"
		value="<spring:message code="request.cancel"/>" /></a>
	
</form:form>
