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

<display:table name="stageInstances" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="stageInstance.startingMoment" var="startingMoment" />
	<display:column title="${startingMoment}" property="startingMoment" />
	
	<spring:message code="stageInstance.endingMoment" var="endingMoment" />
	<display:column title="${endingMoment}" property="endingMoment" />
	
	<spring:message code="stageInstance.comments" var="comments" />
	<display:column title="${comments}" property="comments" />
	
	<spring:message code="stageInstance.stage.name" var="name" />
	<display:column title="${name}" property="stage.name" />
	
	<spring:message code="stageInstance.stage.description" var="description" />
	<display:column title="${description}" property="stage.description" />
	
	<spring:message code="stageInstance.stage.length" var="length" />
	<display:column title="${length}" property="stage.length" />
	
	<spring:message code="stageInstance.stage.lengthMilles" var="lengthMilles" />
	<display:column title="${lengthMilles}" property="stage.lengthMilles" />
	
	<spring:message code="stageInstance.stage.difficultyLevel" var="difficultyLevel" />
	<display:column title="${difficultyLevel}" property="stage.difficultyLevel" />
	
	<security:authorize access="hasRole('PILGRIM')">
		<display:column><a href="assessment/pilgrim/create.do?stageInstanceId=${row.id}"><input type="button" name="create"
		value="<spring:message code="assessment.create"/>" /></a></display:column>
	</security:authorize>
	
</display:table>

<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="stageInstance.back"/>" /></a>
