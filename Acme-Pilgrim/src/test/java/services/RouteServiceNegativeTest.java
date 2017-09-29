package services;


import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.*;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import com.mchange.util.AssertException;

import domain.CreditCard;
import domain.Innkeeper;
import domain.Lodge;
import domain.LodgeAssessment;
import domain.Pilgrim;
import domain.Request;
import domain.Route;
import domain.Stage;

import repositories.RouteRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RouteServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private RouteService routeService;
	
	// Tests --------------------------------------------------------------

	@Test( expected  =  java.lang.IllegalArgumentException.class)
	public void testNegativeListRoutesByDescOrderOfRegistration() {

		this.authenticate("administrator1");
		
		ArrayList<Route> collection;
		
		collection = new ArrayList<>(routeService.findAllNotDeletedRoutesInDescendingOrderOfRegistrations());
		
		Assert.isTrue(collection.get(0).getId()==23);
		
		
		this.unauthenticate();
		
	}
	
	// Número equivocado de rutas disponibles. --------------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeListRoutesNotCancelled(){
		Collection<Route> routes;
		Integer size;
		
		routes=routeService.findAllRoutesThatAreNotCancelled();
		size=routes.size();
		
		Assert.isTrue(size==8);
	}
	
	// Número incorrecto de rutas recorridas por un peregrino. ------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testPositiveRoutesByPilgrim(){
		authenticate("pilgrim1");
		
		Collection<Route> routes;
		Integer size;
		
		routes=routeService.findAllNotDeletedRoutesByPilgrim(3);
		size=routes.size();
		
		Assert.isTrue(size==6);
		
		unauthenticate();
	}
	
	// Crear una ruta sin estar autenticado como administrador. -------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeCreateRoute(){
		authenticate("pilgrim1");
		
		Route route;
		String name;
		String description;
		
		route=routeService.create();
		name="name";
		description="description";
		
		route.setName(name);
		route.setDescription(description);
		
		routeService.save(route);
		
		unauthenticate();
	}
	
	// Editar una ruta sin estar autenticado como administrador. -------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeModifyRoute(){
		authenticate("pilgrim1");
		
		Route route;
		String name;
		String description;
		
		route=routeService.findOne(24);
		name="newName";
		description="newDescription";
		
		route.setName(name);
		route.setDescription(description);
		
		routeService.save(route);
		
		unauthenticate();
	}
	
	// Borrar una ruta sin estar autenticado como administrador. -------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeDeleteRoute(){
		authenticate("innkeeper1");
		
		Route route;
		
		route=routeService.findOne(24);
		
		routeService.deleteRoute(route);
		
		unauthenticate();
	}
	
	// Tamaño de número de rutas incorrecto. ---------------
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeFindAllRoutes(){
		authenticate("administrator1");
		
		Collection<Route> routes;
		Integer size;
		
		routes=routeService.findAll();
		size=routes.size();
		
		Assert.isTrue(size==9);
		
		unauthenticate();
	}

	//	Primer elemento equivocado. -----------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testPositiveListAscOrderAverage() {

		this.authenticate("administrator1");
		
		ArrayList<Route> collection;
		
		collection = new ArrayList<>(routeService.findAllRoutesDeleted());
		
		Assert.isTrue(collection.get(0).getId()==25);
		
		
		this.unauthenticate();
		
	}
	
}
