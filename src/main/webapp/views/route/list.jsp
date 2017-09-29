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

<display:table name="routes" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="route.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="route.description" var="description" />
	<display:column title="${description}" property="description" />
	
	<spring:message code="route.lengthRating" var="lengthRating" />
	<display:column title="${lengthRating}" property="lengthRating"/>
	
	<spring:message code="route.difficultyRating" var="difficultyRating" />
	<display:column title="${difficultyRating}" property="difficultyRating"/>
	
	<spring:message code="route.isDeleted" var="isDeleted" />
	<display:column title="${isDeleted}" property="isDeleted"/>
	
	<display:column><a href="stage/list.do?routeId=${row.id}"><input type="button" name="create"
		value="<spring:message code="route.stages"/>" /></a></display:column>
		
	<security:authorize access="hasRole('PILGRIM')">
		<jstl:if test="${myRoutes != true}">
		<display:column><a href="registration/pilgrim/create.do?routeId=${row.id}"><input type="button" name="create"
		value="<spring:message code="registration.create"/>" /></a></display:column>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column><a href="route/administrator/edit.do?routeId=${row.id}"><input type="button" name="create"
		value="<spring:message code="route.edit"/>" /></a></display:column>
	</security:authorize>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
	<jstl:if test="${delete == true}">
	<display:column><a href="route/administrator/delete.do?routeId=${row.id}"><input type="button" name="delete"
		value="<spring:message code="route.delete"/>" /></a></display:column>
	</jstl:if>
	</security:authorize>

</display:table>

<br />

<security:authorize access="hasRole('ADMINISTRATOR')">
		<a href="route/administrator/create.do"><input type="button" name="create"
		value="<spring:message code="route.create"/>" /></a>
</security:authorize>

<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="route.back"/>" /></a>
