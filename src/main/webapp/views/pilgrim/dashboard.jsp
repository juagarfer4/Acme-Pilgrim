<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Booking history, including his or her assessments. -->

<h3>
	<spring:message code="pilgrim.dashboard.req1" />
</h3>

<display:table name="bookings" id="booking" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="booking.creationMoment" var="creationMoment" />
	<display:column title="${creationMoment}" property="creationMoment" />

	<spring:message code="booking.arrivalDate" var="arrivalDate" />
	<display:column title="${arrivalDate}" property="arrivalDate" />
	
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
	
	<display:column>
	<jstl:if test="${booking.lodgeAssessment.id != null}">
	<a href="lodgeassessment/pilgrim/list.do?bookingId=${booking.id}"><input type="button" name="edit"
		value="<spring:message code="booking.lodgeAssessment"/>" /></a>
	</jstl:if>
	</display:column>

</display:table>
<br />

<display:table name="lodgeAssessments" id="lodgeAssessment" requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="lodgeAssessment.creationMoment" var="creationMoment" />
	<display:column title="${creationMoment}" property="creationMoment" />
	
	<spring:message code="lodgeAssessment.comments" var="comments" />
	<display:column title="${comments}" property="comments"/>	

	<spring:message code="lodgeAssessment.locationAppreciation" var="locationAppreciation" />
	<display:column title="${locationAppreciation}" property="locationAppreciation" />
	
	<spring:message code="lodgeAssessment.roomsAppreciation" var="roomsAppreciation" />
	<display:column title="${roomsAppreciation}" property="roomsAppreciation"/>
	
	<spring:message code="lodgeAssessment.serviceAppreciation" var="serviceAppreciation" />
	<display:column title="${serviceAppreciation}" property="serviceAppreciation"/>
	
	<spring:message code="lodgeAssessment.priceAppreciation" var="priceAppreciation" />
	<display:column title="${priceAppreciation}" property="priceAppreciation"/>
	

</display:table>
<br />
<!-- Route and stage history. -->

<h3>
	<spring:message code="pilgrim.dashboard.req2" />
</h3>

<display:table name="routes" id="route" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
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


</display:table>

<br />

<display:table name="stages1" id="stage" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
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
	
	
</display:table>

<br />


<!-- List of pending stages, including the lodges that he or she's booked.. -->

<h3>
	<spring:message code="pilgrim.dashboard.req3" />
</h3>

<display:table name="stages2" id="stage" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
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
	
	
</display:table>

<br />

<display:table name="lodges" id="lodge" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	</display:table>

<br />

<!-- List of pilgrims sorted by descending birth date.  -->

<h3>
	<spring:message code="pilgrim.dashboard.req4" />
</h3>

<display:table name="pilgrims" id="pilgrim" requestURI="${requestURI}" pagesize="5" class="displaytag">
	
	<spring:message code="pilgrim.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="pilgrim.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="pilgrim.email" var="email" />
	<display:column title="${email}" property="email"/>
	
	<spring:message code="pilgrim.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone"/>
	
	<spring:message code="pilgrim.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage"/>
	
	<spring:message code="pilgrim.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="creditCard.holderName"/>
	
	<spring:message code="pilgrim.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="creditCard.brandName"/>
	
	<spring:message code="pilgrim.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="creditCard.cardNumber"/>
	
	<spring:message code="pilgrim.creditCard.expirationMonth" var="expirationMonth" />
	<display:column title="${expirationMonth}" property="creditCard.expirationMonth"/>
	
	<spring:message code="pilgrim.creditCard.expirationYear" var="expirationYear" />
	<display:column title="${expirationYear}" property="creditCard.expirationYear"/>
	
	<spring:message code="pilgrim.creditCard.cVVCode" var="cVVCode" />
	<display:column title="${cVVCode}" property="creditCard.cVVCode"/>
	
	<spring:message code="pilgrim.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate"/>
	
</display:table>

<a href="<spring:url value='/' />"><input type="button" name="Back"
	value="<spring:message code="pilgrim.back"/>" /></a>
