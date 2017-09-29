package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Booking;
import domain.Innkeeper;
import domain.Pilgrim;
import domain.CreditCard;
import domain.Registration;
import forms.PilgrimForm;

import repositories.PilgrimRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class PilgrimService {
	
	// Managed repository --------------------------------------------
	
	@Autowired
	private PilgrimRepository pilgrimRepository;
	
	// Supporting services -------------------------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private InnkeeperService innkeeperService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors ---------------------------------------------------
	
	public PilgrimService(){
		super();
	}
	
	// Simple CRUD methods --------------------------------------------
	
	public Pilgrim create(){
		Pilgrim result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		CreditCard creditCard;
		Collection<Registration> registrations;
		Collection<Booking> bookings;
		
		result=new Pilgrim();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		creditCard=new CreditCard();
		registrations=new ArrayList<Registration>();
		bookings=new ArrayList<Booking>();
		
		
		
		authority.setAuthority("PILGRIM");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setRegistrations(registrations);
		result.setBookings(bookings);
		
		return result;
	}
	
	public Pilgrim findOne(Integer pilgrimId){
		Pilgrim result;
		
		result=pilgrimRepository.findOne(pilgrimId);
		
		return result;
	}
	
	public Collection<Pilgrim> findAll(){
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAll();
		
		return result;
	}
	
	public void save(Pilgrim pilgrim){
		Assert.notNull(pilgrim);
		
		this.checkCreditCard(pilgrim);
		this.encodePassword(pilgrim);
		
		pilgrimRepository.save(pilgrim);
		
		CreditCard creditCard;
		
		creditCard=pilgrim.getCreditCard();
		
		creditCardService.save(creditCard);
	}
	
	// Other business methods ----------------------------------------------
	
	public Pilgrim findByPrincipal() {
		Pilgrim result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = pilgrimRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}
	
	public Collection<Pilgrim> showPrincipal(){
		Collection<Pilgrim> result;
		Pilgrim pilgrim;
		
		result=new ArrayList<Pilgrim>();
		pilgrim=this.findByPrincipal();
		
		result.add(pilgrim);
		
		return result;
	}
	
	public Collection<Pilgrim> findAllPilgrimsByKeyword(String keyword){
		Administrator administrator;
		
		administrator=administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
		
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAllPilgrimsByKeyword(keyword);
		
		return result;
	}
	
	public Collection<Pilgrim> findAllPilgrimsInDescendingOrderOfRegistrations(){
		Administrator administrator;
		
		administrator=administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
		
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAllPilgrimsInDescendingOrderOfRegistrations();
		
		return result;
	}
	
	public void checkCreditCard(Pilgrim pilgrim){
		CreditCard cc = pilgrim.getCreditCard();
		Calendar d = new GregorianCalendar();

		Assert.isTrue(cc.getExpirationYear() >= d.get(Calendar.YEAR));
		if (cc.getExpirationYear() == d.get(Calendar.YEAR)) Assert.isTrue(cc.getExpirationMonth() > d.get(Calendar.MONTH) + 1);		
	}
	
	public void encodePassword(Pilgrim pilgrim){
		UserAccount userAccount;
		String password;
		Md5PasswordEncoder encoder;
		
		encoder= new Md5PasswordEncoder();
		userAccount=pilgrim.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
	}
	
	public Pilgrim reconstruct(PilgrimForm pilgrimForm) {

		Pilgrim pilgrim = this.create();
		
		Assert.isTrue(pilgrimForm.isCondition());
		Assert.isTrue(pilgrimForm.getPassword().equals(pilgrimForm.getPasswordVerificada()));
		
		pilgrim.setName(pilgrimForm.getName());
		pilgrim.setSurname(pilgrimForm.getSurname());
		pilgrim.setEmail(pilgrimForm.getEmail());
		pilgrim.setContactPhone(pilgrimForm.getContactPhone());
		pilgrim.setHomePage(pilgrimForm.getHomePage());
		pilgrim.getUserAccount().setPassword(pilgrimForm.getPassword());
		pilgrim.getUserAccount().setUsername(pilgrimForm.getUsername());
		pilgrim.setCreditCard(pilgrimForm.getCreditCard());
		pilgrim.setNationality(pilgrimForm.getNationality());
		pilgrim.setBirthDate(pilgrimForm.getBirthDate());
		
		this.checkCreditCard(pilgrim);
		
		return pilgrim;
	}
	
	
	public Collection<Pilgrim> findAllPilgrimsBookedInLodgeOfInnkeeper(){
		Innkeeper innkeeper;
		
		innkeeper=innkeeperService.findByPrincipal();
		
		Assert.notNull(innkeeper);
		
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAllPilgrimsBookedInLodgeOfInnkeeper(innkeeper.getUserAccount().getId());
		
		return result;
	}
	
	public Collection<Pilgrim> findAllPilgrimsMoreBookingsInLodgeOfInnkeeper(){
		Innkeeper innkeeper;
		
		innkeeper=innkeeperService.findByPrincipal();
		
		Assert.notNull(innkeeper);
		
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAllPilgrimsMoreBookingsInLodgeOfInnkeeper(innkeeper.getUserAccount().getId());
		
		return result;
	}
	


	public Collection<Pilgrim> findAllPilgrimsBookedInLodgeOfInnkeeperOrderedByDescendigBirthDate(){
		Innkeeper innkeeper;
		
		innkeeper=innkeeperService.findByPrincipal();
		
		Assert.notNull(innkeeper);
		
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAllPilgrimsBookedInLodgeOfInnkeeperOrderedByDescendigBirthDate(innkeeper.getUserAccount().getId());
		
		return result;
	}
	
	public Collection<Pilgrim> findAllPilgrimsOrderedByDescendigBirthDate(){
		Collection<Pilgrim> result;
		
		result=pilgrimRepository.findAllPilgrimsOrderedByDescendigBirthDate();
		
		return result;
	}
	
}
