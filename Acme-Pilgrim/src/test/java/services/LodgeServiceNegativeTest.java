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
import domain.Pilgrim;
import domain.Request;
import domain.Route;
import domain.Stage;

import repositories.LodgeRepository;
import security.LoginService;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LodgeServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private LodgeService lodgeService;

	@Autowired
	private StageService stageService;

	@Autowired
	private GPSService gPSService;

	@Autowired
	private LodgeRepository lodgeRepository;

	// Tests --------------------------------------------------------------

	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositiveListLodgesNotAuthenticate(){
		
		Collection<Lodge> lodges;
		Integer size;
		
		lodges=lodgeService.findAllLodgesWhoseRequestIsAcepted();
		size=lodges.size();
		
		Assert.isTrue(size==3);
		
		
	}
	
	
	// List of lodges, in descending order of number of bookings.

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeListLodgesByDescNumberOfBooking() {

		this.authenticate("administrator1");

		ArrayList<Lodge> collection;

		collection = new ArrayList<>(
				lodgeService.findAllLodgesOrderedByDescendingNumberOfBooking());

		Assert.isTrue(collection.get(0).getId() == 38);

		this.unauthenticate();

	}
	
	//List of pending stages, including the lodges that he or she's booked
		@Test(expected  = java.lang.IllegalArgumentException.class)
		public void testNegativeListLodgesBookedByPilgrim() {

			this.authenticate("pilgrim1");
			
			ArrayList<Lodge> collection;
			
			collection = new ArrayList<>(lodgeService.findAllLodgesByPilgrim());
			
			Assert.isTrue(collection.get(0).getId()==38);
			
			
			this.unauthenticate();
			
		}

	// List of lodges sorted by descending number of bookings.
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositiveListLodgesByDescNumberOfBookingInnkeeper() {

		this.authenticate("innkeeper1");

		ArrayList<Lodge> collection;

		collection = new ArrayList<>(
				lodgeService
						.findAllLodgesSortedByDescendingNumberOfBookingsByInnkeeper());

		Assert.isTrue(collection.get(0).getId() == 38);

		this.unauthenticate();

	}

	// List of lodges sorted by increasing average rating.

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositiveListLodgesOrderByIncreasingRate() {

		this.authenticate("innkeeper1");

		ArrayList<Lodge> collection;

		collection = new ArrayList<>(
				lodgeService
						.findAllLodgesOrderedByIncreaseAverageRatingByInnkeeper());

		Assert.isTrue(collection.get(0).getId() == 38);

		this.unauthenticate();

	}

	// List of lodges sorted by descending price, grouped by stage.
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositiveListLodgesOrderByDescendingPrice() {

		this.authenticate("innkeeper1");

		ArrayList<Lodge> collection;

		collection = new ArrayList<>(
				lodgeService.findAllLodgesOrderedByDescendingPriceByInnkeeper());

		Assert.isTrue(collection.get(0).getId() == 38);

		this.unauthenticate();

	}

	// Crear un alojamiento con número de camas negativo. --------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateLodgeBeds() {
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

		lodge = lodgeService.create();
		name = "name";
		address = "address";
		coordinates = gPSService.findOne(9);
		contactPhone = "900000000";
		lodgeDescription = "lodgeDescription";
		numberOfBeds = -1;
		price = 1.0;
		stage = stageService.findOne(27);

		lodge.setName(name);
		lodge.setAddress(address);
		lodge.setCoordinates(coordinates);
		lodge.setContactPhone(contactPhone);
		lodge.setLodgeDescription(lodgeDescription);
		lodge.setNumberOfBeds(numberOfBeds);
		lodge.setPrice(price);
		lodge.setStage(stage);

		lodgeService.save(lodge);
		lodgeRepository.flush();

		unauthenticate();
	}

	// Crear un alojamiento con precio negativo. --------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateLodgePrice() {
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

		lodge = lodgeService.create();
		name = "name";
		address = "address";
		coordinates = gPSService.findOne(9);
		contactPhone = "900000000";
		lodgeDescription = "lodgeDescription";
		numberOfBeds = 1;
		price = -1.0;
		stage = stageService.findOne(27);

		lodge.setName(name);
		lodge.setAddress(address);
		lodge.setCoordinates(coordinates);
		lodge.setContactPhone(contactPhone);
		lodge.setLodgeDescription(lodgeDescription);
		lodge.setNumberOfBeds(numberOfBeds);
		lodge.setPrice(price);
		lodge.setStage(stage);

		lodgeService.save(lodge);
		lodgeRepository.flush();

		unauthenticate();
	}
	
	//Crear un alojamiento con una webSite incorrecta
	
	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateLodgeWebSite(){
		authenticate("innkeeper1");
		
		Lodge lodge;
		String name;
		String address;
		GPS coordinates;
		String webSite;
		String contactPhone;
		String lodgeDescription;
		Integer numberOfBeds;
		Double price;
		Stage stage;
		
		lodge=lodgeService.create();
		name="name";
		address="address";
		webSite= "www.lodge.com";
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
		lodge.setWebSite(webSite);
		lodge.setLodgeDescription(lodgeDescription);
		lodge.setNumberOfBeds(numberOfBeds);
		lodge.setPrice(price);
		lodge.setStage(stage);
		
		lodgeService.save(lodge);
		lodgeRepository.flush();
		
		unauthenticate();
	}
	//Crear un alojamiento con una contactPhone incorrecto
	
		@Test(expected = javax.validation.ConstraintViolationException.class)
		public void testNegativeCreateLodgeContactPhone(){
			authenticate("innkeeper1");
			
			Lodge lodge;
			String name;
			String address;
			GPS coordinates;
			String webSite;
			String contactPhone;
			String lodgeDescription;
			Integer numberOfBeds;
			Double price;
			Stage stage;
			
			lodge=lodgeService.create();
			name="name";
			address="address";
			webSite= "http://www.lodge.com";
			coordinates=gPSService.findOne(9);
			contactPhone="90000fdzvsdfadfd0000";
			lodgeDescription="lodgeDescription";
			numberOfBeds=1;
			price=1.0;
			stage=stageService.findOne(27);
			
			lodge.setName(name);
			lodge.setAddress(address);
			lodge.setCoordinates(coordinates);
			lodge.setContactPhone(contactPhone);
			lodge.setWebSite(webSite);
			lodge.setLodgeDescription(lodgeDescription);
			lodge.setNumberOfBeds(numberOfBeds);
			lodge.setPrice(price);
			lodge.setStage(stage);
			
			lodgeService.save(lodge);
			lodgeRepository.flush();
			
			unauthenticate();
		}
		


	// Alojamientos de peregrino con tamaño incorrecto. ------------------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeListLodges() {
		authenticate("innkeeper1");

		Collection<Lodge> lodges;
		Integer size;

		lodges = lodgeService.findAllLodgesByInnkeeper();
		size = lodges.size();

		Assert.isTrue(size == 3);

		unauthenticate();

	}

	// Editar un alojamiento sin estar autenticado. -------------------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeEditLodge() {
		authenticate("innkeeper2");

		Lodge lodge;

		lodge = lodgeService.findOne(37);

		lodge.setAddress("newaddress");

		lodgeService.save(lodge);

		unauthenticate();
	}

}
