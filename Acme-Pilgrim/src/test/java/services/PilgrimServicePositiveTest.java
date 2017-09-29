package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Pilgrim;

import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class PilgrimServicePositiveTest extends AbstractTest {

	// Service under test -------------------------------------------------

	@Autowired
	private PilgrimService pilgrimService;

	// Tests --------------------------------------------------------------

	// List of pilgrims, in descending order of birth date. -------------------

	@Test
	public void testPositiveListPilgrimsByDescOrderOfBirthDate() {

		this.authenticate("administrator1");

		ArrayList<Pilgrim> collection;

		collection = new ArrayList<>(
				pilgrimService.findAllPilgrimsOrderedByDescendigBirthDate());

		Assert.isTrue(collection.get(0).getId() == 18);

		this.unauthenticate();

	}

	// List of pilgrims who have booked one of his or her lodges, grouped by
	// nationality.

	@Test
	public void testPositiveListPilgrimsBookedInLodgesGroupByNacionality() {

		this.authenticate("innkeeper1");

		ArrayList<Pilgrim> collection;

		collection = new ArrayList<>(
				pilgrimService.findAllPilgrimsBookedInLodgeOfInnkeeper());

		Assert.isTrue(collection.size() == 2);

		this.unauthenticate();

	}

	// List of pilgrim/s who has/have made more bookings, grouped by nationality
	// if there are more than one.

	@Test
	public void testPositiveListPilgrimsMoreBookingsGroupByNacionality() {

		this.authenticate("innkeeper1");

		ArrayList<Pilgrim> collection;

		collection = new ArrayList<>(
				pilgrimService.findAllPilgrimsMoreBookingsInLodgeOfInnkeeper());

		Assert.isTrue(collection.size() == 1);

		this.unauthenticate();

	}

	// List of pilgrims sorted by descending birth date.

	@Test
	public void testPositiveListPilgrimsSortedDescBirthDate() {

		this.authenticate("innkeeper1");

		ArrayList<Pilgrim> collection;

		collection = new ArrayList<>(
				pilgrimService
						.findAllPilgrimsBookedInLodgeOfInnkeeperOrderedByDescendigBirthDate());

		Assert.isTrue(collection.get(0).getId() == 18);

		this.unauthenticate();

	}

	// Register to the system as an pilgrim. ------------------------

	@Test()
	public void testPositiveCreatePilgrim() {

		Pilgrim pilgrim;
		String name;
		String surname;
		String email;
		String contactPhone;
		Date birthDate;
		String nationality;
		UserAccount userAccount;
		String username;
		String password;
		CreditCard creditCard;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		pilgrim = pilgrimService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "900000000";
		birthDate = new Date(1980 - 1900, 12 - 1, 12);
		nationality = "Spanish";
		userAccount=pilgrim.getUserAccount();
		username="username";
		password="username";
		creditCard = pilgrim.getCreditCard();
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 4;
		expirationYear = 2019;
		cVVCode = 712;

		pilgrim.setName(name);
		pilgrim.setSurname(surname);
		pilgrim.setEmail(email);
		pilgrim.setContactPhone(contactPhone);
		pilgrim.setBirthDate(birthDate);
		pilgrim.setNationality(nationality);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		pilgrimService.save(pilgrim);
	}
	
	@Test
	public void testPositiveListPilgrims(){
		Collection<Pilgrim> pilgrims;
		Integer size;
		
		pilgrims=pilgrimService.findAll();
		size=pilgrims.size();
		
		Assert.isTrue(size==2);
	}
	
	@Test
	public void testPositiveListPilgrimsRegistrations(){
		authenticate("administrator1");
		
		Collection<Pilgrim> pilgrims;
		List<Pilgrim> listPilgrims;
		Pilgrim pilgrim;
		String name;
		
		pilgrims=pilgrimService.findAllPilgrimsInDescendingOrderOfRegistrations();
		listPilgrims=new ArrayList<Pilgrim>(pilgrims);
		pilgrim=listPilgrims.get(0);
		name=pilgrim.getName();
		
		Assert.isTrue(name.equals("namePilgrim1"));
		
		unauthenticate();
	}
	
	//Search for the pilgrims of the system using a single keyword that must be contained 
	//in their names, surnames, or email addresses
	@Test
	public void testPositiveSearchPilgrimsByKeyword(){
		authenticate("administrator1");
		
		Collection<Pilgrim> pilgrims;
		List<Pilgrim> listPilgrims;
		Pilgrim pilgrim;
		String keyword;
		
		keyword="namePilgrim1";
		pilgrims=pilgrimService.findAllPilgrimsByKeyword(keyword);
		listPilgrims=new ArrayList<Pilgrim>(pilgrims);
		pilgrim=listPilgrims.get(0);
		
		
		Assert.isTrue(pilgrim.getName().equals("namePilgrim1"));
		
		unauthenticate();
	}
	
	
	// List of pilgrims sorted by descending birth date. Pilgrim.

	@Test
	public void testPositiveListPilgrimsSortedDescBirthDatePilgrim() {

		this.authenticate("pilgrim1");

		ArrayList<Pilgrim> collection;

		collection = new ArrayList<>(
				pilgrimService
						.findAllPilgrimsOrderedByDescendigBirthDate());

		Assert.isTrue(collection.get(0).getId() == 18);

		this.unauthenticate();

	}
	
	//List the pilgrims of the system and navigate to their profiles.
	@Test
	public void testPositiveListPilgrimsAndNavigate(){
		authenticate("administrator1");
		
		Collection<Pilgrim> pilgrims;
		List<Pilgrim> listPilgrims;
		Pilgrim pilgrim;
		
		pilgrims=pilgrimService.findAll();
		listPilgrims=new ArrayList<Pilgrim>(pilgrims);
		pilgrim=listPilgrims.get(0);
		
		
		Assert.isTrue(pilgrim.getUserAccount().getId()== 3);
		
		
		
		unauthenticate();
	}

}
