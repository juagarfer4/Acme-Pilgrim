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
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>


<form:form action="${actionURI}" modelAttribute="lodgeAssessment">

	<form:hidden path="id" />
    <form:hidden path="version" />
	<form:hidden path="booking" />
	<form:hidden path="creationMoment" />
	
	<acme:textarea code ="lodgeAssessment.comments"  path="comments"/>
	<br />

	<form:label path="locationAppreciation">
		<spring:message code="lodgeAssessment.locationAppreciation" />
	</form:label>
	<form:select path="roomsAppreciation" >
		<form:option label="----" value="0" />
		<form:option label="0" value="0"></form:option>
		<form:option label="1" value="1"></form:option>
		<form:option label="2" value="2"></form:option>
		<form:option label="3" value="3"></form:option>
		<form:option label="4" value="4"></form:option>
		<form:option label="5" value="5"></form:option>
		<form:option label="6" value="6"></form:option>
		<form:option label="7" value="7"></form:option>
		<form:option label="8" value="8"></form:option>
		<form:option label="9" value="9"></form:option>
		<form:option label="10" value="10"></form:option>
	</form:select>
	<form:errors cssClass="error" path="locationAppreciation"/>
	<br />
	
	<form:label path="roomsAppreciation">
		<spring:message code="lodgeAssessment.roomsAppreciation" />
	</form:label>
	<form:select path="roomsAppreciation" >
		<form:option label="----" value="0" />
		<form:option label="0" value="0"></form:option>
		<form:option label="1" value="1"></form:option>
		<form:option label="2" value="2"></form:option>
		<form:option label="3" value="3"></form:option>
		<form:option label="4" value="4"></form:option>
		<form:option label="5" value="5"></form:option>
		<form:option label="6" value="6"></form:option>
		<form:option label="7" value="7"></form:option>
		<form:option label="8" value="8"></form:option>
		<form:option label="9" value="9"></form:option>
		<form:option label="10" value="10"></form:option>
	</form:select>
	<form:errors cssClass="error" path="roomsAppreciation"/>
	<br />
	
	<form:label path="serviceAppreciation">
		<spring:message code="lodgeAssessment.serviceAppreciation" />
	</form:label>
	<form:select path="serviceAppreciation" >
		<form:option label="----" value="0" />
		<form:option label="0" value="0"></form:option>
		<form:option label="1" value="1"></form:option>
		<form:option label="2" value="2"></form:option>
		<form:option label="3" value="3"></form:option>
		<form:option label="4" value="4"></form:option>
		<form:option label="5" value="5"></form:option>
		<form:option label="6" value="6"></form:option>
		<form:option label="7" value="7"></form:option>
		<form:option label="8" value="8"></form:option>
		<form:option label="9" value="9"></form:option>
		<form:option label="10" value="10"></form:option>
	</form:select>
	<form:errors cssClass="error" path="serviceAppreciation"/>
	<br />
	
	<form:label path="priceAppreciation">
		<spring:message code="lodgeAssessment.priceAppreciation" />
	</form:label>
	<form:select path="priceAppreciation" >
		<form:option label="----" value="0" />
		<form:option label="0" value="0"></form:option>
		<form:option label="1" value="1"></form:option>
		<form:option label="2" value="2"></form:option>
		<form:option label="3" value="3"></form:option>
		<form:option label="4" value="4"></form:option>
		<form:option label="5" value="5"></form:option>
		<form:option label="6" value="6"></form:option>
		<form:option label="7" value="7"></form:option>
		<form:option label="8" value="8"></form:option>
		<form:option label="9" value="9"></form:option>
		<form:option label="10" value="10"></form:option>
	</form:select>
	<form:errors cssClass="error" path="priceAppreciation"/>
	<br />
	
	<acme:submit code="lodgeAssessment.save" name="save" />
	
	&nbsp;
	
	
	 <a href="booking/pilgrim/list.do"><input type="button" name="cancel"
		value="<spring:message code="lodgeAssessment.cancel"/>" /></a>
	
</form:form>
