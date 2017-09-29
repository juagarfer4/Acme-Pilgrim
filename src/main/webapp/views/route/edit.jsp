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



<form:form action="${actionURI}" modelAttribute="route">

    <form:hidden path="id" />
    <form:hidden path="version" />
    <form:hidden path="registrations" />
    <form:hidden path="orderers" />
    <form:hidden path="isDeleted" />
    <form:hidden path="lengthRating" />
    <form:hidden path="difficultyRating" />


    
    <acme:textbox code ="route.name"  path="name"/>
    <br />
    <acme:textarea code ="route.description"  path="description"/>
    <br />
    
    <jstl:if test="${route.id != 0}">
		<acme:submit code="route.delete" name="delete" />
	</jstl:if>
    
        <acme:submit code="route.save" name="save" />
    &nbsp;
    
     <a href="route/list.do"><input type="button" name="cancel"
        value="<spring:message code="route.cancel"/>" /></a>
    
</form:form>