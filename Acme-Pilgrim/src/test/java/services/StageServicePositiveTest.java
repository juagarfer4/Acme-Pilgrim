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
import domain.GPS;
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
public class StageServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private GPSService gPSService;
	
	// Tests --------------------------------------------------------------

	//List of stages, in ascending order of average rating.
	@Test
	public void testPositiveListStagesByAscAverageRating() {

		this.authenticate("administrator1");
		
		ArrayList<Stage> collection;
		
		collection = new ArrayList<>(stageService.findAllStagesOrderedByAscendingAverageRating());
		
		Assert.isTrue(collection.get(0).getId()==28);
		
		
		this.unauthenticate();
		
	}
	
	//List of pending stages, including the lodges that he or she's booked.
	
	@Test
	public void testPositiveListPendingStages() {

		this.authenticate("pilgrim1");
		
		ArrayList<Stage> collection;
		
		collection = new ArrayList<>(stageService.findAllPendingStageByPilgrim());
		
		Assert.isTrue(collection.get(0).getId()==27);
		
		
		this.unauthenticate();
		
	}
	
	
	//Route and stage history.

	
	@Test
	public void testPositiveListStageHistory() {

		this.authenticate("pilgrim1");
		
		ArrayList<Stage> collection;
		
		collection = new ArrayList<>(stageService.findAllStagesHistoryByPilgrim());
		
		Assert.isTrue(collection.get(0).getId()==27);
		
		
		this.unauthenticate();
		
	}
	
	
	@Test
	public void testPositiveCreateStage(){
		authenticate("administrator1");
		
		Stage stage;
		String name;
		String description;
		GPS sourceLocation;
		GPS destinationLocation;
		Double length;
		Integer difficultyLevel;
		
		stage=stageService.create();
		name="name";
		description="description";
		sourceLocation=gPSService.findOne(9);
		destinationLocation=gPSService.findOne(10);
		length=1.0;
		difficultyLevel=0;
		
		stage.setName(name);
		stage.setDescription(description);
		stage.setSourceLocation(sourceLocation);
		stage.setDestinationLocation(destinationLocation);
		stage.setLength(length);
		stage.setDifficultyLevel(difficultyLevel);
		
		stageService.save(stage);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveModifyStage(){
		authenticate("administrator2");
		
		Stage stage;
		String name;
		String description;
		
		stage=stageService.findOne(27);
		name="newName";
		description="newDescription";
		
		stage.setName(name);
		stage.setDescription(description);
		
		stageService.save(stage);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveDeleteStage(){
		authenticate("administrator1");
		
		Stage stage;
		
		stage=stageService.findOne(29);
		
		stageService.delete(stage);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveFindAllStages(){
		authenticate("administrator1");
		
		Collection<Stage> stages;
		Integer size;
		
		stages=stageService.findAll();
		size=stages.size();
		
		Assert.isTrue(size==3);
		
		unauthenticate();
	}
	
}
