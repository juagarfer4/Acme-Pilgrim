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


<form:form action="${actionURI}" modelAttribute="stage">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="lengthMilles" />
	<form:hidden path="stageInstances" />
	<form:hidden path="orderers" />
	<form:hidden path="lodges" />



	
	<acme:textbox code ="stage.name"  path="name"/> 
	<br />
	<acme:textarea code ="stage.description"  path="description"/> 
	<br />
	<acme:textbox code ="stage.length"  path="length"/> 
	<br />
	<acme:textbox code ="stage.difficultyLevel"  path="difficultyLevel"/> 
	<br />
	<acme:select items="${gPSs}" itemLabel="title" code="stage.sourceLocation" path="sourceLocation"/>
	<acme:select items="${gPSs}" itemLabel="title" code="stage.destinationLocation" path="destinationLocation"/>

	<acme:submit code="stage.save" name="save" />
	&nbsp;
	
	<jstl:if test="${stage.id != 0}">
		<acme:submit code="stage.delete" name="delete" />
	</jstl:if>
	
	&nbsp;
	
	 <a href="stage/administrator/list.do"><input type="button" name="cancel"
		value="<spring:message code="stage.cancel"/>" /></a>
	
</form:form>
