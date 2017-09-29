<%--
 * header.jsp
 *
 * Copyright (C) 2014 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Pilgrim, Inc." />
</div>

<div>
	<ul id="jMenu">
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMINISTRATOR')">
			<li><a class="fNiv"><spring:message	code="master.page.administrator" /></a>
				<ul>
					<li class="arrow"></li>
					<li><a href="administrator/administrator/list.do"><spring:message code="master.page.list.administrator.principal" /></a></li>
					<li><a href="administrator/administrator/create.do"><spring:message code="master.page.register.administrator" /></a></li>
					<li><a href="pilgrim/administrator/listpilgrims.do"><spring:message code="master.page.list.administrator.pilgrims" /></a></li>
					<li><a href="stage/administrator/list.do"><spring:message code="master.page.list.administrator.stages" /></a></li>
					<li><a href="stage/administrator/listtodelete.do"><spring:message code="master.page.list.administrator.stages.delete" /></a></li>
					<li><a href="route/administrator/listtodelete.do"><spring:message code="master.page.list.administrator.route.delete" /></a></li>
					<li><a href="request/administrator/list.do"><spring:message code="master.page.list.administrator.request" /></a></li>
					<li><a href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('PILGRIM')">
<%-- 			<li><a class="fNiv"><spring:message	code="master.page.pilgrim" /></a> --%>
<!-- 			</li> -->
			<li><a class="fNiv"><spring:message	code="master.page.pilgrim" /></a>
				<ul>
					<li><a href="pilgrim/pilgrim/list.do"><spring:message code="master.page.list.pilgrim.principal" /></a></li>
					<li><a href="route/pilgrim/list.do"><spring:message code="master.page.list.routes.pilgrim" /></a></li>
					<li><a href="registration/pilgrim/list.do"><spring:message code="master.page.pilgrim.registration.list" /></a></li>
					<li><a href="stageinstance/pilgrim/list.do"><spring:message code="master.page.pilgrim.stageinstance.list" /></a></li>
					<li><a href="booking/pilgrim/list.do"><spring:message code="master.page.list.booking.pilgrim" /></a></li>
					<li><a href="booking/pilgrim/listtoassess.do"><spring:message code="master.page.list.bookingtoassess.pilgrim" /></a></li>
					<li><a href="pilgrim/dashboard.do"><spring:message code="master.page.pilgrim.dashboard" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="hasRole('INNKEEPER')">
<%-- 			<li><a class="fNiv"><spring:message	code="master.page.pilgrim" /></a> --%>
<!-- 			</li> -->
			<li><a class="fNiv"><spring:message	code="master.page.innkeeper" /></a>
				<ul>
					<li><a href="request/innkeeper/list.do"><spring:message code="master.page.list.requests.innkeeper" /></a></li>
					<li><a href="lodge/innkeeper/list.do"><spring:message code="master.page.list.lodges.innkeeper" /></a></li>
					<li><a href="lodge/innkeeper/listtodelete.do"><spring:message code="master.page.listtodelete.lodges.innkeeper" /></a></li>
					<li><a href="booking/innkeeper/list.do"><spring:message code="master.page.list.booking.innkeeper" /></a></li>
					<li><a href="innkeeper/dashboard.do"><spring:message code="master.page.innkeeper.dashboard" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="pilgrim/create.do"><spring:message code="master.page.register.pilgrim" /></a></li>
			<li><a class="fNiv" href="innkeeper/create.do"><spring:message code="master.page.register.innkeeper" /></a></li>
			<li><a class="fNiv" href="route/list.do"><spring:message code="master.page.listofroutes" /></a></li>
			<li><a class="fNiv" href="lodge/list.do"><spring:message code="master.page.listoflodges" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.legal" /></a>
				<ul>
					<li><a href="privacy/lopd-lssi.do"><spring:message code="master.page.legal.lopd-lssi" /></a></li>
					<li><a href="privacy/cookies.do"><spring:message code="master.page.legal.transpositions" /></a></li>
					<li><a href="privacy/unsubscribe.do"><spring:message code="master.page.legal.unsubscribe" /></a></li>
				</ul>
			</li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
			<li><a class="fNiv" href="route/list.do"><spring:message code="master.page.listofroutes" /></a></li>
			<li><a class="fNiv" href="lodge/list.do"><spring:message code="master.page.listoflodges" /></a></li>
			<li><a class="fNiv"><spring:message	code="master.page.legal" /></a>
				<ul>
					<li><a href="privacy/lopd-lssi.do"><spring:message code="master.page.legal.lopd-lssi" /></a></li>
					<li><a href="privacy/cookies.do"><spring:message code="master.page.legal.transpositions" /></a></li>
					<li><a href="privacy/unsubscribe.do"><spring:message code="master.page.legal.unsubscribe" /></a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

