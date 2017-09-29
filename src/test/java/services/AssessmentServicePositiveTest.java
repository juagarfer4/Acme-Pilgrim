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

import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class AssessmentServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private AssessmentService assessmentService;
	

	
	// Tests --------------------------------------------------------------
	
	@Test
	public void testPositiveCreateAssessment(){
		authenticate("pilgrim2");
		
		Assessment assessment;
		Integer lengthRating;
		Integer difficultyRating;
		
		assessment=assessmentService.create(36);
		lengthRating=5;
		difficultyRating=5;
		
		assessment.setLengthRating(lengthRating);
		assessment.setDifficultyRating(difficultyRating);
		
		assessmentService.save(assessment);
		
		unauthenticate();
	}

	
}
