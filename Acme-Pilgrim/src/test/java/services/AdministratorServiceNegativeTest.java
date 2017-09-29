package services;

import java.util.ArrayList;
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

import domain.Administrator;
import domain.CreditCard;
import domain.Innkeeper;
import domain.Lodge;
import domain.Pilgrim;
import domain.Request;
import domain.Route;
import domain.Stage;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdministratorServiceNegativeTest extends AbstractTest {

	// Service under test ------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	@Autowired
	private AdministratorRepository administratorRepository;

	// Tests --------------------------------------------------------------

	// Test of View his or her profile, which consists of the personal
	// information that Acme Pilgrim,
	// Inc. stores about him or her
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeViewAdministratorProfile() {

		this.authenticate("administrator1");

		ArrayList<Administrator> collection;

		collection = new ArrayList<>(administratorService.showPrincipal());

		Assert.isTrue(collection.get(0).getUserAccount().getId() == 2);

		this.unauthenticate();

	}

	// Register a new administrator of the system
	// Wrong email ------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateAdministratorEmail() {
		this.authenticate("administrator1");

		Administrator administrator;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		String username;
		String password;

		administrator = administratorService.create();
		name = "name";
		surname = "surname";
		email = "email";
		contactPhone = "900000000";
		homePage = "http://www.homepage.com";
		userAccount=administrator.getUserAccount();
		username="username";
		password="username";

		administrator.setName(name);
		administrator.setSurname(surname);
		administrator.setEmail(email);
		administrator.setContactPhone(contactPhone);
		administrator.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);

		administratorService.save(administrator);
		administratorRepository.flush();

		this.unauthenticate();

	}

	// Wrong home page. -----------------------------

	@Test(expected = javax.validation.ConstraintViolationException.class)
	public void testNegativeCreateAdministratorHomepage() {
		this.authenticate("administrator1");

		Administrator administrator;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		String username;
		String password;

		administrator = administratorService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "900000000";
		homePage = "www.homepage.com";
		userAccount=administrator.getUserAccount();
		username="username";
		password="username";

		administrator.setName(name);
		administrator.setSurname(surname);
		administrator.setEmail(email);
		administrator.setContactPhone(contactPhone);
		administrator.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);

		administratorService.save(administrator);
		administratorRepository.flush();

		this.unauthenticate();

	}

	// Repeated username. -----------------------------

	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
	public void testNegativeCreateAdministratorUsername() {
		this.authenticate("administrator1");

		Administrator administrator;
		String name;
		String surname;
		String email;
		String contactPhone;
		String homePage;
		UserAccount userAccount;
		String username;
		String password;

		administrator = administratorService.create();
		name = "name";
		surname = "surname";
		email = "email@us.es";
		contactPhone = "900000000";
		homePage = "www.homepage.com";
		userAccount=administrator.getUserAccount();
		username="administrator2";
		password="administrator2";

		administrator.setName(name);
		administrator.setSurname(surname);
		administrator.setEmail(email);
		administrator.setContactPhone(contactPhone);
		administrator.setHomePage(homePage);
		userAccount.setUsername(username);
		userAccount.setPassword(password);

		administratorService.save(administrator);
		administratorRepository.flush();

		this.unauthenticate();

	}

}
