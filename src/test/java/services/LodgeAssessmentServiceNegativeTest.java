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

import domain.CreditCard;
import domain.Innkeeper;
import domain.Lodge;
import domain.LodgeAssessment;
import domain.Pilgrim;
import domain.Request;
import domain.Route;
import domain.Stage;

import repositories.LodgeAssessmentRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LodgeAssessmentServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private LodgeAssessmentService lodgeAssessmentService;
	
	@Autowired
	private LodgeAssessmentRepository lodgeAssessmentRepository;
	

	
	// Tests --------------------------------------------------------------

	//Se crea un lodgeAssesment de un Booking que ya tiene uno
	
	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class )
	public void testNegativeCreateLodgeAssessment(){
		authenticate("pilgrim1");
		
		LodgeAssessment lodgeAssessment;
		Integer locationAppreciation;
		Integer roomsAppreciation;
		Integer serviceAppreciation;
		Integer priceAppreciation;
		String comments;
		lodgeAssessment=lodgeAssessmentService.create(49);
		locationAppreciation=6;
		roomsAppreciation=6;
		serviceAppreciation=6;
		priceAppreciation=6;
		comments="comments";
		
		
		lodgeAssessment.setLocationAppreciation(locationAppreciation);
		lodgeAssessment.setRoomsAppreciation(roomsAppreciation);
		lodgeAssessment.setServiceAppreciation(serviceAppreciation);
		lodgeAssessment.setPriceAppreciation(priceAppreciation);
		lodgeAssessment.setComments(comments);
		
		lodgeAssessmentService.save(lodgeAssessment);
		
		unauthenticate();
	}
	
	//Crear un lodgeAssessment con un locationAppreciation incorrecto
	
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateLodgeAssessmentLocationAppreciation(){
		authenticate("pilgrim1");
		
		LodgeAssessment lodgeAssessment;
		Integer locationAppreciation;
		Integer roomsAppreciation;
		Integer serviceAppreciation;
		Integer priceAppreciation;
		String comments;
		lodgeAssessment=lodgeAssessmentService.create(53);
		locationAppreciation=12;
		roomsAppreciation=6;
		serviceAppreciation=6;
		priceAppreciation=6;
		comments="comments";
		
		
		lodgeAssessment.setLocationAppreciation(locationAppreciation);
		lodgeAssessment.setRoomsAppreciation(roomsAppreciation);
		lodgeAssessment.setServiceAppreciation(serviceAppreciation);
		lodgeAssessment.setPriceAppreciation(priceAppreciation);
		lodgeAssessment.setComments(comments);
		
		lodgeAssessmentService.save(lodgeAssessment);
		lodgeAssessmentRepository.flush();
		
		unauthenticate();
	}
	
	//Crear un lodgeAssessment con un roomsAppreciation incorrecto
	
		@Test(expected = javax.validation.ConstraintViolationException.class)
		public void testNegativeCreateLodgeAssessmentRoomsAppreciation(){
			authenticate("pilgrim1");
			
			LodgeAssessment lodgeAssessment;
			Integer locationAppreciation;
			Integer roomsAppreciation;
			Integer serviceAppreciation;
			Integer priceAppreciation;
			String comments;
			lodgeAssessment=lodgeAssessmentService.create(53);
			locationAppreciation=6;
			roomsAppreciation=12;
			serviceAppreciation=6;
			priceAppreciation=6;
			comments="comments";
			
			
			lodgeAssessment.setLocationAppreciation(locationAppreciation);
			lodgeAssessment.setRoomsAppreciation(roomsAppreciation);
			lodgeAssessment.setServiceAppreciation(serviceAppreciation);
			lodgeAssessment.setPriceAppreciation(priceAppreciation);
			lodgeAssessment.setComments(comments);
			
			lodgeAssessmentService.save(lodgeAssessment);
			lodgeAssessmentRepository.flush();
			
			unauthenticate();
		}
		
		
		//Crear un lodgeAssessment con un serviceAppreciation incorrecto
		
		@Test(expected = javax.validation.ConstraintViolationException.class)
		public void testNegativeCreateLodgeAssessmentServiceAppreciation(){
			authenticate("pilgrim1");
			
			LodgeAssessment lodgeAssessment;
			Integer locationAppreciation;
			Integer roomsAppreciation;
			Integer serviceAppreciation;
			Integer priceAppreciation;
			String comments;
			lodgeAssessment=lodgeAssessmentService.create(53);
			locationAppreciation=6;
			roomsAppreciation=6;
			serviceAppreciation=12;
			priceAppreciation=6;
			comments="comments";
			
			
			lodgeAssessment.setLocationAppreciation(locationAppreciation);
			lodgeAssessment.setRoomsAppreciation(roomsAppreciation);
			lodgeAssessment.setServiceAppreciation(serviceAppreciation);
			lodgeAssessment.setPriceAppreciation(priceAppreciation);
			lodgeAssessment.setComments(comments);
			
			lodgeAssessmentService.save(lodgeAssessment);
			lodgeAssessmentRepository.flush();
			
			unauthenticate();
		}
		
		
		//Crear un lodgeAssessment con un priceAppreciation incorrecto
		
		@Test(expected = javax.validation.ConstraintViolationException.class)
		public void testNegativeCreateLodgeAssessmentPriceAppreciation(){
			authenticate("pilgrim1");
			
			LodgeAssessment lodgeAssessment;
			Integer locationAppreciation;
			Integer roomsAppreciation;
			Integer serviceAppreciation;
			Integer priceAppreciation;
			String comments;
			lodgeAssessment=lodgeAssessmentService.create(53);
			locationAppreciation=6;
			roomsAppreciation=6;
			serviceAppreciation=6;
			priceAppreciation=12;
			comments="comments";
			
			
			lodgeAssessment.setLocationAppreciation(locationAppreciation);
			lodgeAssessment.setRoomsAppreciation(roomsAppreciation);
			lodgeAssessment.setServiceAppreciation(serviceAppreciation);
			lodgeAssessment.setPriceAppreciation(priceAppreciation);
			lodgeAssessment.setComments(comments);
			
			lodgeAssessmentService.save(lodgeAssessment);
			lodgeAssessmentRepository.flush();
			
			unauthenticate();
		}
	
}
