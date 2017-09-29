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

<display:table name="requests" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="request.code" var="code" />
	<display:column title="${code}" property="code" />

	<spring:message code="request.title" var="title" />
	<display:column title="${title}" property="title" />
	
	<spring:message code="request.creationMoment" var="title" />
	<display:column title="${title}" property="title" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="request.description" var="description" />
	<display:column title="${description}" property="description"/>
	
	<spring:message code="request.status" var="status" />
	<display:column title="${status}" property="status"/>
	
	<spring:message code="request.comments" var="comments" />
	<display:column title="${comments}" property="comments"/>
	
	<spring:message code="request.administrator.name" var="administrator" />
	<display:column title="${administrator}" property="administrator.name"/>
	
	<jstl:if test="${myRequests != true}">
		<spring:message code="request.innkeeper.name" var="innkeeper" />
		<display:column title="${name}" property="innkeeper.name"/>
	</jstl:if>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column><a href="request/administrator/edit.do?requestId=${row.id}"><input type="button" name="create"
		value="<spring:message code="request.edit"/>" /></a></display:column>
	</security:authorize>

</display:table>

<br />

<security:authorize access="hasRole('INNKEEPER')">
	<jstl:if test="${myRequests == true}">
		<a href="request/innkeeper/create.do"><input type="button" name="create"
		value="<spring:message code="request.create"/>" /></a>
	</jstl:if>
</security:authorize>

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="request.back"/>" /></a>
