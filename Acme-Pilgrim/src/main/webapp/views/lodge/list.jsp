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

<display:table name="lodges" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="lodge.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="lodge.address" var="address" />
	<display:column title="${address}" property="address" />
	
	<spring:message code="lodge.coordinates" var="coordinates" />
	<display:column title="${coordinates}" property="coordinates.title"/>
	
	<spring:message code="lodge.webSite" var="webSite" />
	<display:column title="${webSite}" property="webSite"/>
	
	<spring:message code="lodge.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone"/>
	
	<spring:message code="lodge.lodgeDescription" var="lodgeDescription" />
	<display:column title="${lodgeDescription}" property="lodgeDescription"/>
	
	<spring:message code="lodge.numberOfBeds" var="numberOfBeds" />
	<display:column title="${numberOfBeds}" property="numberOfBeds"/>
	
	<spring:message code="lodge.price" var="price" />
	<display:column title="${price}" property="price"/>
	
	<spring:message code="lodge.locationRating" var="locationRating" />
	<display:column title="${locationRating}" property="locationRating"/>
	
	<spring:message code="lodge.roomsRating" var="roomsRating" />
	<display:column title="${roomsRating}" property="roomsRating"/>
	
	<spring:message code="lodge.serviceRating" var="serviceRating" />
	<display:column title="${serviceRating}" property="serviceRating"/>
	
	<spring:message code="lodge.priceRating" var="priceRating" />
	<display:column title="${priceRating}" property="priceRating"/>
	
	<spring:message code="lodge.rating" var="rating" />
	<display:column title="${rating}" property="rating"/>
	
	<spring:message code="lodge.stage" var="stage" />
	<display:column title="${stage}" property="stage.name"/>
	
	
	<display:column><a href="innkeeper/list.do?innkeeperId=${row.innkeeper.id}"><input type="button" name="list"
		value="<spring:message code="lodge.innkeeper"/>" /></a></display:column>
		
		
		
	<security:authorize access="hasRole('INNKEEPER')">
		<jstl:if test="${myLodges == true}">
			<display:column><a href="lodge/innkeeper/edit.do?lodgeId=${row.id}"><input type="button" name="edit"
			value="<spring:message code="lodge.edit"/>" /></a></display:column>
		</jstl:if>
	</security:authorize>
	
	<security:authorize access="hasRole('PILGRIM')">
		<display:column><a href="booking/pilgrim/create.do?lodgeId=${row.id}"><input type="button" name="edit"
		value="<spring:message code="booking.create"/>" /></a></display:column>
	</security:authorize>
		

</display:table>


<br />

<security:authorize access="hasRole('INNKEEPER')">
	<jstl:if test="${myLodges == true}">
		<a href="lodge/innkeeper/create.do"><input type="button" name="create"
		value="<spring:message code="lodge.create"/>" /></a>
	</jstl:if>
</security:authorize>

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="lodge.back"/>" /></a>
