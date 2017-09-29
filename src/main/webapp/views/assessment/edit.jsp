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


<form:form action="${actionURI}" modelAttribute="assessment">

	<form:hidden path="id" />
    <form:hidden path="version" />
	<form:hidden path="stageInstance" />
	<form:hidden path="creationMoment" />

	<form:label path="lengthRating">
		<spring:message code="assessment.lengthRating" />
	</form:label>
	<form:select path="lengthRating" >
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
	<form:errors cssClass="error" path="lengthRating"/>
	<br />
	
	<form:label path="difficultyRating">
		<spring:message code="assessment.difficultyRating" />
	</form:label>
	<form:select path="difficultyRating" >
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
	<form:errors cssClass="error" path="difficultyRating"/>
	<br />
	
	<acme:textarea code ="assessment.comment"  path="comment"/>
	<br />
	
	<acme:submit code="assessment.save" name="save" />
	
	&nbsp;
	
	
	 <a href="stageinstance/pilgrim/list.do"><input type="button" name="cancel"
		value="<spring:message code="assessment.cancel"/>" /></a>
	
</form:form>
