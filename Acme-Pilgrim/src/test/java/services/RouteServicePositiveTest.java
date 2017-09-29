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

import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class RouteServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private RouteService routeService;
	

	
	// Tests --------------------------------------------------------------

	
	//The list of routes in descending order of registrations.

		@Test
		public void testPositiveListRoutesByDescOrderOfRegistration() {

			this.authenticate("administrator1");
			
			ArrayList<Route> collection;
			
			collection = new ArrayList<>(routeService.findAllNotDeletedRoutesInDescendingOrderOfRegistrations());
			
			Assert.isTrue(collection.get(0).getId()==24);
			
			
			this.unauthenticate();
			
		}
		
//		The list of routes, in ascending order of average rating, with an indication 
//		regarding whether they've been deleted. 
		
		@Test
		public void testPositiveListAscOrderAverage() {

			this.authenticate("administrator1");
			
			ArrayList<Route> collection;
			
			collection = new ArrayList<>(routeService.findAllRoutesDeleted());
			
			Assert.isTrue(collection.get(0).getId()==26);
			
			
			this.unauthenticate();
			
		}
		
		

		
	@Test
	public void testPositiveListRoutesNotCancelled(){
		Collection<Route> routes;
		Integer size;
		
		routes=routeService.findAllRoutesThatAreNotCancelled();
		size=routes.size();
		
		Assert.isTrue(size==3);
	}
	
	@Test
	public void testPositiveRoutesByPilgrim(){
		authenticate("pilgrim1");
		
		Collection<Route> routes;
		Integer size;
		
		routes=routeService.findAllNotDeletedRoutesByPilgrim(3);
		size=routes.size();
		
		Assert.isTrue(size==1);
		
		unauthenticate();
	}
		
	@Test
	public void testPositiveCreateRoute(){
		authenticate("administrator1");
		
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
	
	@Test
	public void testPositiveModifyRoute(){
		authenticate("administrator1");
		
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
	
	@Test
	public void testPositiveDeleteRoute(){
		authenticate("administrator1");
		
		Route route;
		
		route=routeService.findOne(26);
		
		routeService.deleteRoute(route);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveFindAllRoutes(){
		authenticate("administrator1");
		
		Collection<Route> routes;
		Integer size;
		
		routes=routeService.findAll();
		size=routes.size();
		
		Assert.isTrue(size==3);
		
		unauthenticate();
	}
	
}
