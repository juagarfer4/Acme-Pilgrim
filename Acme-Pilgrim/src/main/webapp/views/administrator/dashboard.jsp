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

<!-- The list of routes in descending order of registrations -->

<h3>
	<spring:message code="administrator.dashboard.req1" />
</h3>

<display:table name="routes1" id="route" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="route.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="route.description" var="description" />
	<display:column title="${description}" property="description" />

	<spring:message code="route.lengthRating" var="lengthRating" />
	<display:column title="${lengthRating}" property="lengthRating" />

	<spring:message code="route.difficultyRating" var="difficultyRating" />
	<display:column title="${difficultyRating}" property="difficultyRating" />

</display:table>

<br />

<!-- The list of pilgrims in descending order of registrations -->

<h3>
	<spring:message code="administrator.dashboard.req2" />
</h3>

<display:table name="pilgrims1" id="pilgrim" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="pilgrim.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="pilgrim.surname" var="surname" />
	<display:column title="${surname}" property="surname" />

	<spring:message code="pilgrim.email" var="email" />
	<display:column title="${email}" property="email" />

	<spring:message code="pilgrim.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone" />

	<spring:message code="pilgrim.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage" />

	<spring:message code="pilgrim.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="creditCard.holderName" />

	<spring:message code="pilgrim.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="creditCard.brandName" />

	<spring:message code="pilgrim.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="creditCard.cardNumber" />

	<spring:message code="pilgrim.creditCard.expirationMonth"
		var="expirationMonth" />
	<display:column title="${expirationMonth}"
		property="creditCard.expirationMonth" />

	<spring:message code="pilgrim.creditCard.expirationYear"
		var="expirationYear" />
	<display:column title="${expirationYear}"
		property="creditCard.expirationYear" />

	<spring:message code="pilgrim.creditCard.cVVCode" var="cVVCode" />
	<display:column title="${cVVCode}" property="creditCard.cVVCode" />

	<spring:message code="pilgrim.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" />

</display:table>

<br />

<!-- The list of routes, in ascending order of average rating, with an indication regarding whether they've been deleted -->

<h3>
	<spring:message code="administrator.dashboard.req3" />
</h3>

<display:table name="routes2" id="route" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="route.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="route.description" var="description" />
	<display:column title="${description}" property="description" />

	<spring:message code="route.lengthRating" var="lengthRating" />
	<display:column title="${lengthRating}" property="lengthRating" />

	<spring:message code="route.difficultyRating" var="difficultyRating" />
	<display:column title="${difficultyRating}" property="difficultyRating" />

	<spring:message code="route.isDeleted" var="isDeleted" />
	<display:column title="${isDeleted}" property="isDeleted" />

</display:table>

<br />


<!-- List of innkeepers, in descending order of number of lodges managed. -->

<h3>
	<spring:message code="administrator.dashboard.req9" />
</h3>

<display:table name="innkeepers1" id="innkeeper"
	requestURI="${requestURI}" pagesize="5" class="displaytag">

	<spring:message code="innkeeper.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="innkeeper.surname" var="surname" />
	<display:column title="${surname}" property="surname" />

	<spring:message code="innkeeper.email" var="email" />
	<display:column title="${email}" property="email" />

	<spring:message code="innkeeper.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone" />

	<spring:message code="innkeeper.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage" />

	<spring:message code="innkeeper.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="creditCard.holderName" />

	<spring:message code="innkeeper.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="creditCard.brandName" />

	<spring:message code="innkeeper.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="creditCard.cardNumber" />

	<spring:message code="pilgrim.creditCard.expirationMonth"
		var="expirationMonth" />
	<display:column title="${expirationMonth}"
		property="creditCard.expirationMonth" />

	<spring:message code="pilgrim.creditCard.expirationYear"
		var="expirationYear" />
	<display:column title="${expirationYear}"
		property="creditCard.expirationYear" />

	<spring:message code="pilgrim.creditCard.cVVCode" var="cVVCode" />
	<display:column title="${cVVCode}" property="creditCard.cVVCode" />


</display:table>

<br />

<!-- List of lodges, in descending order of number of bookings. -->

<h3>
	<spring:message code="administrator.dashboard.req10" />
</h3>

<display:table name="lodges1" id="lodge" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="lodge.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="lodge.address" var="address" />
	<display:column title="${address}" property="address" />

	<spring:message code="lodge.coordinates" var="coordinates" />
	<display:column title="${coordinates}" property="coordinates.title" />

	<spring:message code="lodge.webSite" var="webSite" />
	<display:column title="${webSite}" property="webSite" />

	<spring:message code="lodge.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone" />

	<spring:message code="lodge.lodgeDescription" var="lodgeDescription" />
	<display:column title="${lodgeDescription}" property="lodgeDescription" />

	<spring:message code="lodge.numberOfBeds" var="numberOfBeds" />
	<display:column title="${numberOfBeds}" property="numberOfBeds" />

	<spring:message code="lodge.price" var="price" />
	<display:column title="${price}" property="price" />

	<spring:message code="lodge.stage" var="stage" />
	<display:column title="${stage}" property="stage.name" />


</display:table>

<!-- List of stages, in ascending order of average rating. -->


<h3>
	<spring:message code="administrator.dashboard.req11" />
</h3>

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


<!-- List of pilgrims, in descending order of birth date.  -->
<h3>
	<spring:message code="administrator.dashboard.req12" />
</h3>

<display:table name="pilgrims4" id="pilgrim" requestURI="${requestURI}"
	pagesize="5" class="displaytag">

	<spring:message code="pilgrim.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="pilgrim.surname" var="surname" />
	<display:column title="${surname}" property="surname" />

	<spring:message code="pilgrim.email" var="email" />
	<display:column title="${email}" property="email" />

	<spring:message code="pilgrim.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone" />

	<spring:message code="pilgrim.homePage" var="homePage" />
	<display:column title="${homePage}" property="homePage" />

	<spring:message code="pilgrim.creditCard.holderName" var="holderName" />
	<display:column title="${holderName}" property="creditCard.holderName" />

	<spring:message code="pilgrim.creditCard.brandName" var="brandName" />
	<display:column title="${brandName}" property="creditCard.brandName" />

	<spring:message code="pilgrim.creditCard.cardNumber" var="cardNumber" />
	<display:column title="${cardNumber}" property="creditCard.cardNumber" />

	<spring:message code="pilgrim.creditCard.expirationMonth"
		var="expirationMonth" />
	<display:column title="${expirationMonth}"
		property="creditCard.expirationMonth" />

	<spring:message code="pilgrim.creditCard.expirationYear"
		var="expirationYear" />
	<display:column title="${expirationYear}"
		property="creditCard.expirationYear" />

	<spring:message code="pilgrim.creditCard.cVVCode" var="cVVCode" />
	<display:column title="${cVVCode}" property="creditCard.cVVCode" />

	<spring:message code="pilgrim.birthDate" var="birthDate" />
	<display:column title="${birthDate}" property="birthDate" />

</display:table>

<br />


<a href="<spring:url value='/' />"><input type="button" name="Back"
	value="<spring:message code="administrator.back"/>" /></a>
