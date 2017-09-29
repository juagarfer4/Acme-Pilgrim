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

<display:table name="stages" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="stage.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="stage.description" var="description" />
	<display:column title="${description}" property="description" />
	
	<spring:message code="stage.sourceLocation.title" var="title" />
	<display:column title="${title}" property="sourceLocation.title"/>
		
	<spring:message code="stage.sourceLocation.description" var="description" />
	<display:column title="${description}" property="sourceLocation.description"/>
	
	<spring:message code="stage.sourceLocation.longitude" var="longitude" />
	<display:column title="${longitude}" property="sourceLocation.longitude"/>
	
	<spring:message code="stage.sourceLocation.latitude" var="latitude" />
	<display:column title="${latitude}" property="sourceLocation.latitude"/>
	
	<spring:message code="stage.sourceLocation.altitude" var="altitude" />
	<display:column title="${altitude}" property="sourceLocation.altitude"/>
	
	<spring:message code="stage.destinationLocation.title" var="title" />
	<display:column title="${title}" property="destinationLocation.title"/>
		
	<spring:message code="stage.destinationLocation.description" var="description" />
	<display:column title="${description}" property="destinationLocation.description"/>
	
	<spring:message code="stage.destinationLocation.longitude" var="longitude" />
	<display:column title="${longitude}" property="destinationLocation.longitude"/>
	
	<spring:message code="stage.destinationLocation.latitude" var="latitude" />
	<display:column title="${latitude}" property="destinationLocation.latitude"/>
	
	<spring:message code="stage.destinationLocation.altitude" var="altitude" />
	<display:column title="${altitude}" property="destinationLocation.altitude"/>
	
	<spring:message code="stage.length" var="length" />
	<display:column title="${length}" property="length"/>
	
	<spring:message code="stage.lengthMilles" var="lengthMilles" />
	<display:column title="${lengthMilles}" property="lengthMilles"/>
	
	<spring:message code="stage.difficultyLevel" var="difficultyLevel" />
	<display:column title="${difficultyLevel}" property="difficultyLevel"/>
	
	<security:authorize access="hasRole('ADMINISTRATOR')">
		<display:column><a href="stage/administrator/edit.do?stageId=${row.id}"><input type="button" name="create"
		value="<spring:message code="stage.edit"/>" /></a></display:column>
		
<%-- 		<display:column><a href="orderer/administrator/edit.do?stageId=${stage.id},routes=${routes}"><input type="button" name="associate" --%>
<%-- 		value="<spring:message code="stage.associate"/>" /></a></display:column> --%>

		<display:column><a href="orderer/administrator/create.do?stageId=${row.id}"><input type="button" name="associate"
		value="<spring:message code="stage.associate"/>" /></a></display:column>
	</security:authorize>
	
</display:table>

<br />

<security:authorize access="hasRole('ADMINISTRATOR')">
		<a href="stage/administrator/create.do"><input type="button" name="create"
		value="<spring:message code="stage.create"/>" /></a>
</security:authorize>

<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="stage.back"/>" /></a>
