package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Innkeeper;

import repositories.InnkeeperRepository;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class InnkeeperServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private InnkeeperService innkeeperService;
	
	@Autowired
	private InnkeeperRepository innkeeperRepository;

	// Tests --------------------------------------------------------------

	// Invalid moment for the credit card. ------------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCreateInnkeeper() {

		Innkeeper innkeeper;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		CreditCard creditCard;
		String username;
		String password;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		innkeeper = innkeeperService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "900000000";
		homePage = "http://www.homepage.com";
		userAccount = innkeeper.getUserAccount();
		creditCard = innkeeper.getCreditCard();
		username = "username";
		password = "username";
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 1;
		expirationYear = 2015;
		cVVCode = 712;

		innkeeper.setName(name);
		innkeeper.setSurname(surname);
		innkeeper.setEmail(email);
		innkeeper.setContactPhone(contactPhone);
		innkeeper.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		innkeeperService.save(innkeeper);
	}

	// Wrong email. ------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegaiveCreateInnkeeperEmail() {

		Innkeeper innkeeper;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		CreditCard creditCard;
		String username;
		String password;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		innkeeper = innkeeperService.create();
		name = "name";
		surname = "surname";
		email = "email";
		contactPhone = "900000000";
		homePage = "http://www.homepage.com";
		userAccount = innkeeper.getUserAccount();
		creditCard = innkeeper.getCreditCard();
		username = "username";
		password = "username";
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 4;
		expirationYear = 2019;
		cVVCode = 712;

		innkeeper.setName(name);
		innkeeper.setSurname(surname);
		innkeeper.setEmail(email);
		innkeeper.setContactPhone(contactPhone);
		innkeeper.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		innkeeperService.save(innkeeper);
		innkeeperRepository.flush();
	}

	// Wrong home page. ------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegaiveCreateInnkeeperHomepage() {

		Innkeeper innkeeper;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		CreditCard creditCard;
		String username;
		String password;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		innkeeper = innkeeperService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "900000000";
		homePage = "www.homepage.com";
		userAccount = innkeeper.getUserAccount();
		creditCard = innkeeper.getCreditCard();
		username = "username";
		password = "username";
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 4;
		expirationYear = 2019;
		cVVCode = 712;

		innkeeper.setName(name);
		innkeeper.setSurname(surname);
		innkeeper.setEmail(email);
		innkeeper.setContactPhone(contactPhone);
		innkeeper.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		innkeeperService.save(innkeeper);
		innkeeperRepository.flush();
	}
	
	// Wrong contact phone. ------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegaiveCreateInnkeepercontactPhone() {

		Innkeeper innkeeper;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		CreditCard creditCard;
		String username;
		String password;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		innkeeper = innkeeperService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "9000000sdadsa00";
		homePage = "http://www.homepage.com";
		userAccount = innkeeper.getUserAccount();
		creditCard = innkeeper.getCreditCard();
		username = "username";
		password = "username";
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 4;
		expirationYear = 2019;
		cVVCode = 712;

		innkeeper.setName(name);
		innkeeper.setSurname(surname);
		innkeeper.setEmail(email);
		innkeeper.setContactPhone(contactPhone);
		innkeeper.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		innkeeperService.save(innkeeper);
		innkeeperRepository.flush();
	}

	// Repeated username. ----------------------

	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
	public void testNegaiveCreateInnkeeperUsername() {

		Innkeeper innkeeper;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		CreditCard creditCard;
		String username;
		String password;
		String holderName;
		String brandName;
		String cardNumber;
		Integer expirationMonth;
		Integer expirationYear;
		Integer cVVCode;

		innkeeper = innkeeperService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "900000000";
		homePage = "http://www.homepage.com";
		userAccount = innkeeper.getUserAccount();
		creditCard = innkeeper.getCreditCard();
		username = "innkeeper1";
		password = "innkeeper1";
		holderName = "holderName";
		brandName = "Visa";
		cardNumber = "4485312019524250";
		expirationMonth = 4;
		expirationYear = 2019;
		cVVCode = 712;

		innkeeper.setName(name);
		innkeeper.setSurname(surname);
		innkeeper.setEmail(email);
		innkeeper.setContactPhone(contactPhone);
		innkeeper.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);
		creditCard.setHolderName(holderName);
		creditCard.setBrandName(brandName);
		creditCard.setCardNumber(cardNumber);
		creditCard.setExpirationMonth(expirationMonth);
		creditCard.setExpirationYear(expirationYear);
		creditCard.setcVVCode(cVVCode);

		innkeeperService.save(innkeeper);
		innkeeperRepository.flush();
	}

	// List of innkeepers, in descending order of number of lodges managed.
	// ---------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeListInnkeeperByDescNumberOfLodges() {

		this.authenticate("administrator1");

		ArrayList<Innkeeper> collection;

		collection = new ArrayList<>(
				innkeeperService
						.findAllInnkeeperOrderedDescendingNumberOfLodges());

		Assert.isTrue(collection.get(0).getId() == 20);

		this.unauthenticate();

	}

	// Occupancy rate of their lodges regarding the next month.
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testPositiveOccupancyRateofLodgesNextMonth() {

		this.authenticate("innkeeper1");

		Double rate;

		rate = innkeeperService.findOccupancyRateOfNextMonth();

		Assert.isTrue(rate == 19);// DUDA

		this.unauthenticate();

	}

	// El primer posadero es otro. --------------------------

	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testListInkeepersNegative() {
		authenticate("administrator1");

		Collection<Innkeeper> innkeepers;
		List<Innkeeper> listInnkeepers;
		Innkeeper innkeeper;
		String name;

		innkeepers = innkeeperService
				.findAllInnkeeperOrderedDescendingNumberOfLodges();
		listInnkeepers = new ArrayList<Innkeeper>(innkeepers);
		innkeeper = listInnkeepers.get(0);
		name = innkeeper.getName();

		Assert.isTrue(name.equals("nameInnkeeper2"));

		unauthenticate();
	}

}
