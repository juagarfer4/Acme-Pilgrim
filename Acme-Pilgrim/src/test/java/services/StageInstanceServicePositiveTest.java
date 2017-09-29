package services;


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
import domain.StageInstance;

import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class StageInstanceServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private StageInstanceService stageInstanceService;
	
	@Autowired
	private StageService stageService;

	
	// Tests --------------------------------------------------------------

	@Test
	public void testPositiveCreateStageInstance(){
		authenticate("pilgrim1");
		
		StageInstance stageInstance;
		Date startingMoment;
		Date endingMoment;
		Stage stage;
		
		stageInstance=stageInstanceService.create(32);
		startingMoment=new Date(2014 - 1900, 11 - 1, 11);
		endingMoment=new Date(2014 - 1900, 11 - 1, 13);
		stage=stageService.findOne(27);
		
		stageInstance.setStartingMoment(startingMoment);
		stageInstance.setEndingMoment(endingMoment);
		stageInstance.setStage(stage);
		
		stageInstanceService.save(stageInstance);
		
		unauthenticate();
	}
}
