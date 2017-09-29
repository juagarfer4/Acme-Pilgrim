<%--
 * action-1.jsp
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- 
List of pilgrims who have booked one of his or her lodges, grouped by
nationality. 
List of pilgrim/s who has/have made more bookings, grouped by nationali if there are more than one. 
List of pilgrims sorted by descending birth date.
List of lodges sorted by descending number of bookings.
List of lodges sorted by increasing average rating.
List of lodges sorted by descending price, grouped by stage.
Occupancy rate of their lodges regarding the next month. 
 -->

<!--  -->

<h3><spring:message code="innkeeper.dashboard.req1" /></h3>

<display:table name="pilgrims1" id="pilgrim" requestURI="${requestURI}" pagesize="5" class="displaytag">	
	
	<spring:message code="pilgrim.name" var="name" />
	<display:column title="${name}" property="name" />

	<spring:message code="pilgrim.surname" var="surname" />
	<display:column title="${surname}" property="surname" />
	
	<spring:message code="pilgrim.email" var="email" />
	<display:column title="${email}" property="email"/>
	
	<spring:message code="pilgrim.contactPhone" var="contactPhone" />
	<display:column title="${contactPhone}" property="contactPhone"/>
	
	<spring:message code="pilgrim.nationality" var="nationality" />
	<display:column title="${nationality}" property="nationality"/>
	
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

<br />
<!--  -->

<h3><spring:message code="innkeeper.dashboard.req2" /></h3>

<display:table name="pilgrims2" id="pilgrim" requestURI="${requestURI}" pagesize="5" class="displaytag">	
	
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

<br />
<!--  -->

<h3><spring:message code="innkeeper.dashboard.req3" /></h3>

<display:table name="pilgrims3" id="pilgrim" requestURI="${requestURI}" pagesize="5" class="displaytag">	
	
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

<br />

<!--  -->

<h3><spring:message code="innkeeper.dashboard.req4" /></h3>

<display:table name="lodges1" id="lodge" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	<spring:message code="lodge.stage" var="stage" />
	<display:column title="${stage}" property="stage.name"/>
	

</display:table>


<!--  -->

<h3><spring:message code="innkeeper.dashboard.req5" /></h3>

<display:table name="lodges2" id="lodge" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	<spring:message code="lodge.stage" var="stage" />
	<display:column title="${stage}" property="stage.name"/>
	

</display:table>


<!--  -->

<h3><spring:message code="innkeeper.dashboard.req6" /></h3>

<display:table name="lodges3" id="lodge" requestURI="${requestURI}" pagesize="5" class="displaytag">

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
	
	<spring:message code="lodge.stage" var="stage" />
	<display:column title="${stage}" property="stage.name"/>
	

</display:table>

<!-- -->

<!-- <div> -->
<%-- 	<h3><spring:message code="innkeeper.dashboard.req7" /></h3> --%>
<!-- 	<div> -->
<!-- 		<p> -->
<%-- 			<jstl:out value="${occupancyRate}"/> --%>
<!-- 		</p> -->
<!-- 	</div> -->
<!-- </div> -->

<!-- <br /> -->

<div>
				<jstl:if test="${rates != null }">
					<h4>
						<spring:message code="dashboard.rating" />
					</h4>

					<h4>
						<jstl:out value="${rates}"></jstl:out>

					</h4>
				</jstl:if>
				<jstl:if test="${rates == null }">
					<h3>
						<spring:message code="dashboard.no.rating" />
					</h3>
				</jstl:if>

</div>

<br />



<a href="<spring:url value='/' />"><input type="button" name="Back"
		value="<spring:message code="administrator.back"/>" /></a>
