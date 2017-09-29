package services;


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

import domain.Administrator;
import domain.CreditCard;
import domain.Innkeeper;
import domain.Lodge;
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
@TransactionConfiguration(defaultRollback = true)
public class RequestServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AdministratorService administratorService;

	// Tests --------------------------------------------------------------

	//Manage a request to publish a lodge. ------------------------
	
	
	@Test(expected = java.lang.IllegalArgumentException.class )
	public void testNegativeManageRequest() {


		this.authenticate("administrator2");
		
		Request request;
		
		request = requestService.findOne(23);
		
		request.setStatus("BAD_SATATUS");
		
		requestService.saveAdministrator(request);
		
		this.unauthenticate();
		
		
		
	}
	
	// Crear una request teniendo una pendiente. -------------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeCreateRequest(){
		authenticate("innkeeper2");
		
		Request request;
		String title;
		String description;
		Administrator administrator;
		
		request=requestService.create();
		title="title";
		description="description";
		administrator=administratorService.findOne(15);
		
		request.setTitle(title);
		request.setDescription(description);
		request.setAdministrator(administrator);
		
		requestService.saveInnkeeper(request);
		
		unauthenticate();
	}

	
}
