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

<display:table name="bookings" id="row" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="booking.creationMoment" var="creationMoment" />
	<display:column title="${creationMoment}" property="creationMoment" format="{0,date,dd/MM/yyyy}" />

	<spring:message code="booking.arrivalDate" var="arrivalDate" />
	<display:column title="${arrivalDate}" property="arrivalDate" format="{0,date,dd/MM/yyyy}" />
	
	<spring:message code="booking.numberOfNights" var="numberOfNights" />
	<display:column title="${numberOfNights}" property="numberOfNights"/>
	
	<spring:message code="booking.numberOfBedsRequested" var="numberOfBedsRequested" />
	<display:column title="${numberOfBedsRequested}" property="numberOfBedsRequested"/>
	
	<spring:message code="booking.price" var="price" />
	<display:column title="${price}" property="price"/>
	
	<spring:message code="booking.isCancelled" var="isCancelled" />
	<display:column title="${isCancelled}" property="isCancelled"/>
	
	<spring:message code="booking.comments" var="comments" />
	<display:column title="${comments}" property="comments"/>	
	
	<spring:message code="booking.lodge.name" var="name" />
	<display:column title="${name}" property="lodge.name"/>
	
	<security:authorize access="hasRole('PILGRIM')">
	<jstl:if test="${edit==true}">
	<display:column><a href="booking/pilgrim/edit.do?bookingId=${row.id}"><input type="button" name="list"
		value="<spring:message code="booking.edit"/>" /></a></display:column>
	<display:column><a href="booking/pilgrim/editbeds.do?bookingId=${row.id}"><input type="button" name="list"
		value="<spring:message code="booking.editbeds"/>" /></a></display:column>
	<display:column><a href="booking/pilgrim/editnights.do?bookingId=${row.id}"><input type="button" name="list"
		value="<spring:message code="booking.editnights"/>" /></a></display:column>
	</jstl:if>
	<display:column>
	<jstl:if test="${row.lodgeAssessment.id != null}">
	<a href="lodgeassessment/pilgrim/list.do?bookingId=${row.id}"><input type="button" name="edit"
		value="<spring:message code="booking.lodgeAssessment"/>" /></a>
	</jstl:if>
	</display:column>
		
	<jstl:if test="${assess==true}">
		<display:column><a href="lodgeassessment/pilgrim/create.do?bookingId=${row.id}"><input type="button" name="edit"
		value="<spring:message code="lodgeAssessment.create"/>" /></a></display:column>
	</jstl:if>
	</security:authorize>
		

</display:table>

<security:authorize access="hasRole('INNKEEPER')">

<br />

	<br />
	<form action="booking/innkeeper/search.do">
		<input type="text" name="date" placeholder="DD/MM/YYYY">
		
		<button type="submit">Date</button>
	</form>
</security:authorize>

<br />

		<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="booking.back"/>" /></a>
