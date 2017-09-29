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


<form:form action="${actionURI}" modelAttribute="lodge">

	<form:hidden path="id" />
    <form:hidden path="version" />
	<form:hidden path="bookings" />
	<form:hidden path="innkeeper" />
	<form:hidden path="lodgeCalendars" />

	<acme:textbox code ="lodge.name"  path="name"/>
	<br />
	<acme:textbox code ="lodge.address"  path="address"/>
	<br />
	<acme:select items="${gPSs}" itemLabel="title" code="lodge.coordinates" path="coordinates"/>
	<br />
	<acme:textbox code ="lodge.webSite"  path="webSite"/>
	<br />
	<acme:textbox code ="lodge.contactPhone"  path="contactPhone"/>
	<br />
	<acme:textarea code ="lodge.lodgeDescription"  path="lodgeDescription"/>
	<br />
	<acme:textbox code ="lodge.numberOfBeds"  path="numberOfBeds"/>
	<br />
	<acme:textbox code ="lodge.price"  path="price"/>
	<br />
	<acme:select items="${stages}" itemLabel="name" code="lodge.stage" path="stage"/>
	<br />
	
	<acme:submit code="lodge.save" name="save" />
	
	&nbsp;
	<security:authorize access="hasRole('INNKEEPER')">
		
		<jstl:if test="${lodge.id != 0}">
			<acme:submit code="lodge.delete" name="delete" />
		</jstl:if>
		&nbsp;
	</security:authorize>
	
	
	 <a href="lodge/innkeeper/list.do"><input type="button" name="cancel"
		value="<spring:message code="lodge.cancel"/>" /></a>
	
</form:form>
