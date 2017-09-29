package services;


import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;


import domain.GPS;
import domain.Lodge;
import domain.Stage;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class LodgeServicePositiveTest extends AbstractTest {

	// Service under test ------------------------------------------------

	
	@Autowired
	private LodgeService lodgeService;
	
	@Autowired
	private GPSService gPSService;
	
	@Autowired
	private StageService stageService;
	
	// Tests --------------------------------------------------------------

	@Test
	public void testPositiveListLodgesNotAuthenticate(){
		
		Collection<Lodge> lodges;
		Integer size;
		
		lodges=lodgeService.findAllLodgesWhoseRequestIsAcepted();
		size=lodges.size();
		
		Assert.isTrue(size==0);
		
		
	}
	//List of lodges sorted by descending number of bookings.
	
	@Test
	public void testPositiveListLodgesByDescNumberOfBooking() {

		this.authenticate("administrator1");
		
		ArrayList<Lodge> collection;
		
		collection = new ArrayList<>(lodgeService.findAllLodgesOrderedByDescendingNumberOfBooking());
		
		Assert.isTrue(collection.get(0).getId()==37);
		
		
		this.unauthenticate();
		
	}

	
	//List of lodges sorted by descending number of bookings.
	@Test
	public void testPositiveListLodgesByDescNumberOfBookingInnkeeper() {

		this.authenticate("innkeeper1");
		
		ArrayList<Lodge> collection;
		
		collection = new ArrayList<>(lodgeService.findAllLodgesSortedByDescendingNumberOfBookingsByInnkeeper());
		
		Assert.isTrue(collection.get(0).getId()==37);
		
		
		this.unauthenticate();
		
	}
	

	
	//List of lodges sorted by increasing average rating.
	
	
	@Test
	public void testPositiveListLodgesOrderByIncreasingRate() {

		this.authenticate("innkeeper1");
		
		ArrayList<Lodge> collection;
		
		collection = new ArrayList<>(lodgeService.findAllLodgesOrderedByIncreaseAverageRatingByInnkeeper());
		
		Assert.isTrue(collection.get(0).getId()==37);
		
		
		this.unauthenticate();
		
	}
	
	//List of lodges sorted by descending price, grouped by stage.
	@Test
	public void testPositiveListLodgesOrderByDescendingPrice() {

		this.authenticate("innkeeper1");
		
		ArrayList<Lodge> collection;
		
		collection = new ArrayList<>(lodgeService.findAllLodgesOrderedByDescendingPriceByInnkeeper());
		
		Assert.isTrue(collection.get(0).getId()==37);
		
		
		this.unauthenticate();
		
	}

	//List of pending stages, including the lodges that he or she's booked
	@Test
	public void testPositiveListLodgesBookedByPilgrim() {

		this.authenticate("pilgrim1");
		
		ArrayList<Lodge> collection;
		
		collection = new ArrayList<>(lodgeService.findAllLodgesByPilgrim());
		
		Assert.isTrue(collection.get(0).getId()==37);
		
		
		this.unauthenticate();
		
	}
	
	@Test
	public void testPositiveCreateLodge(){
		authenticate("innkeeper1");
		
		Lodge lodge;
		String name;
		String address;
		GPS coordinates;
		String contactPhone;
		String lodgeDescription;
		Integer numberOfBeds;
		Double price;
		Stage stage;
		
		lodge=lodgeService.create();
		name="name";
		address="address";
		coordinates=gPSService.findOne(9);
		contactPhone="900000000";
		lodgeDescription="lodgeDescription";
		numberOfBeds=1;
		price=1.0;
		stage=stageService.findOne(27);
		
		lodge.setName(name);
		lodge.setAddress(address);
		lodge.setCoordinates(coordinates);
		lodge.setContactPhone(contactPhone);
		lodge.setLodgeDescription(lodgeDescription);
		lodge.setNumberOfBeds(numberOfBeds);
		lodge.setPrice(price);
		lodge.setStage(stage);
		
		lodgeService.save(lodge);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveListLodges(){
		authenticate("innkeeper1");
		
		Collection<Lodge> lodges;
		Integer size;
		
		lodges=lodgeService.findAllLodgesByInnkeeper();
		size=lodges.size();
		
		Assert.isTrue(size==2);
		
		unauthenticate();
		
	}
	
	@Test
	public void testPositiveEditLodge(){
		authenticate("innkeeper1");
		
		Lodge lodge;
		
		lodge=lodgeService.findOne(37);
		
		lodge.setAddress("newaddress");
		
		lodgeService.save(lodge);
		
		unauthenticate();
	}

}
