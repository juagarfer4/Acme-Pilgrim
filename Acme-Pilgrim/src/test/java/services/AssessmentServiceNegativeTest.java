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

import domain.Assessment;
import domain.CreditCard;
import domain.Innkeeper;
import domain.Lodge;
import domain.Pilgrim;
import domain.Request;
import domain.Route;
import domain.Stage;

import repositories.AssessmentRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AssessmentServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private AssessmentService assessmentService;
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	

	
	// Tests --------------------------------------------------------------
	
	// Crear una valoración de una instancia de etapa que ya tiene una. -----------------
	
	@Test(expected=org.springframework.dao.DataIntegrityViolationException.class)
	public void testNegativeCreateAssessment(){
		authenticate("pilgrim1");
		
		Assessment assessment;
		Integer lengthRating;
		Integer difficultyRating;
		
		assessment=assessmentService.create(34);
		lengthRating=5;
		difficultyRating=5;
		
		assessment.setLengthRating(lengthRating);
		assessment.setDifficultyRating(difficultyRating);
		
		assessmentService.save(assessment);
		
		unauthenticate();
	}


	// Crear una valoración con un lenghtRating no Valido. -----------------
	
		@Test(expected=javax.validation.ConstraintViolationException.class)
		public void testNegativeCreateAssessmentLenghtRating(){
			authenticate("pilgrim2");
			
			Assessment assessment;
			Integer lengthRating;
			Integer difficultyRating;
			
			assessment=assessmentService.create(36);
			lengthRating=12;
			difficultyRating=5;
			
			assessment.setLengthRating(lengthRating);
			assessment.setDifficultyRating(difficultyRating);
			
			assessmentService.save(assessment);
			assessmentRepository.flush();
			
			unauthenticate();
		}
		
		// Crear una valoración con un difficultyRating no Valido. -----------------

		@Test(expected=javax.validation.ConstraintViolationException.class)
		public void testNegativeCreateAssessmentDifficultyRating(){
			authenticate("pilgrim2");
			
			Assessment assessment;
			Integer lengthRating;
			Integer difficultyRating;
			
			assessment=assessmentService.create(36);
			lengthRating=5;
			difficultyRating=12;
			
			assessment.setLengthRating(lengthRating);
			assessment.setDifficultyRating(difficultyRating);
			
			assessmentService.save(assessment);
			
			unauthenticate();
		}

	
}
