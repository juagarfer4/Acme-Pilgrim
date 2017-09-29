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

<display:table name="lodgeAssessments" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="lodgeAssessment.creationMoment" var="creationMoment" />
	<display:column title="${creationMoment}" property="creationMoment" format="{0,date,dd/MM/yyyy}" />

	<spring:message code="lodgeAssessment.locationAppreciation" var="locationAppreciation" />
	<display:column title="${locationAppreciation}" property="locationAppreciation"/>
	
	<spring:message code="lodgeAssessment.roomsAppreciation" var="roomsAppreciation" />
	<display:column title="${roomsAppreciation}" property="roomsAppreciation"/>
	
	<spring:message code="lodgeAssessment.serviceAppreciation" var="serviceAppreciation" />
	<display:column title="${serviceAppreciation}" property="serviceAppreciation"/>
	
	<spring:message code="lodgeAssessment.priceAppreciation" var="priceAppreciation" />
	<display:column title="${priceAppreciation}" property="priceAppreciation"/>
	
	<spring:message code="lodgeAssessment.comments" var="comments" />
	<display:column title="${comments}" property="comments"/>			

</display:table>

		<a href="<spring:url value='booking/pilgrim/list.do' />"><input type="button" name="Back"
		value="<spring:message code="lodgeAssessment.back"/>" /></a>
