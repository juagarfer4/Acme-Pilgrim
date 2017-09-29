package services;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

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
import domain.Registration;
import domain.Request;
import domain.Route;
import domain.Stage;

import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RegistrationServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private RegistrationService registrationService;
	

	
	// Tests --------------------------------------------------------------

	// Registrarse a una ruta como posadero. ------------------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeCreateRegistration(){
		authenticate("innkeeper1");
		
		Registration registration;
		
		registration=registrationService.create(24);
		
		registrationService.save(registration);
		
		unauthenticate();
	}
	
//	Route and stage history.

	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeRouteHistory() {

		this.authenticate("pilgrim1");
		
		ArrayList<Route> collection;
		
		collection = new ArrayList<>(registrationService.findAllRoutesByPilgrim());
		
		Assert.isTrue(collection.get(0).getId()==25);
		
		
		this.unauthenticate();
		
	}

	
}
