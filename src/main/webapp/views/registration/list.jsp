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

<display:table name="registrations" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="registration.moment" var="moment" />
	<display:column title="${moment}" property="moment" format="{0,date,dd/MM/yyyy}"  />

	<spring:message code="registration.route.name" var="name" />
	<display:column title="${name}" property="route.name" />
	
	<spring:message code="registration.route.description" var="description" />
	<display:column title="${description}" property="route.description" />
	
<%-- 	<spring:message code="registration.route.lengthRating" var="lengthRating" /> --%>
<%-- 	<display:column title="${lengthRating}" property="route.lengthRating" /> --%>
	
<%-- 	<spring:message code="registration.route.difficultyRating" var="difficultyRating" /> --%>
<%-- 	<display:column title="${difficultyRating}" property="route.difficultyRating" /> --%>
	
	<display:column><a href="stageinstance/pilgrim/create.do?registrationId=${row.id}"><input type="button" name="create"
	value="<spring:message code="stageInstance.create"/>" /></a></display:column>

</display:table>

<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="registration.back"/>" /></a>
