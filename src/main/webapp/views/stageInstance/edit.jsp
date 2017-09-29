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


<form:form action="${actionURI}" modelAttribute="stageInstance">

	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="registration" />

	<acme:textbox code ="stageInstance.startingMoment"  path="startingMoment"/>
	<br />
	<acme:textbox code ="stageInstance.endingMoment"  path="endingMoment"/>
	<br />
	<acme:textarea code ="stageInstance.comments"  path="comments"/>
	<br />
	<acme:select items="${stages}" itemLabel="name" code="stageInstance.stage" path="stage"/>
	<br />
	
	<acme:submit code="stageInstance.save" name="save" />
	
	&nbsp;
	
	 <a href="registration/pilgrim/list.do"><input type="button" name="cancel"
		value="<spring:message code="stageInstance.cancel"/>" /></a>
	
</form:form>
