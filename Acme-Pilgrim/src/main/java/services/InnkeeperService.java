package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Innkeeper;
import domain.Lodge;
import domain.Request;
import forms.InnkeeperForm;

import repositories.InnkeeperRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class InnkeeperService {
	
	// Managed repository -------------------------------------------------------
	
	@Autowired
	private InnkeeperRepository innkeeperRepository;
	
	// Supporting services -------------------------------------------
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Constructors --------------------------------------------------
	
	public InnkeeperService(){
		super();
	}
	
	// Simple CRUD methods --------------------------------------------
	
	public Innkeeper create(){
		Innkeeper result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		CreditCard creditCard;
		Collection<Request> requests;
		Collection<Lodge> lodges;
		
		result=new Innkeeper();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		creditCard=new CreditCard();
		requests=new ArrayList<Request>();
		lodges=new ArrayList<Lodge>();
		
		authority.setAuthority("INNKEEPER");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setCreditCard(creditCard);
		result.setRequests(requests);
		result.setLodges(lodges);
		
		return result;
	}
	
	public Innkeeper findOne(Integer innkeeperId){
		Innkeeper result;
		
		result=innkeeperRepository.findOne(innkeeperId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public void save(Innkeeper innkeeper){
		Assert.notNull(innkeeper);
		
		this.checkCreditCard(innkeeper);
		this.encodePassword(innkeeper);
		
		innkeeperRepository.save(innkeeper);
		
		CreditCard creditCard;
		
		creditCard=innkeeper.getCreditCard();
		
		creditCardService.save(creditCard);
	}
	
	// Other business methods ----------------------------------------------------
	
	public Innkeeper findByPrincipal() {
		Innkeeper result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = innkeeperRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}
	
	public void checkCreditCard(Innkeeper innkeeper){
		CreditCard cc = innkeeper.getCreditCard();
		Calendar d = new GregorianCalendar();

		Assert.isTrue(cc.getExpirationYear() >= d.get(Calendar.YEAR));
		if (cc.getExpirationYear() == d.get(Calendar.YEAR)) Assert.isTrue(cc.getExpirationMonth() > d.get(Calendar.MONTH) + 1);		
	}
	
	public void encodePassword(Innkeeper innkeeper){
		UserAccount userAccount;
		String password;
		Md5PasswordEncoder encoder;
		
		encoder= new Md5PasswordEncoder();
		userAccount=innkeeper.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
	}
	
	public Innkeeper reconstruct(InnkeeperForm innkeeperForm) {

		Innkeeper innkeeper = this.create();

		Assert.isTrue(innkeeperForm.isCondition());
		Assert.isTrue(innkeeperForm.getPassword().equals(innkeeperForm.getPasswordVerificada()));
		
		innkeeper.setName(innkeeperForm.getName());
		innkeeper.setSurname(innkeeperForm.getSurname());
		innkeeper.setEmail(innkeeperForm.getEmail());
		innkeeper.setContactPhone(innkeeperForm.getContactPhone());
		innkeeper.setHomePage(innkeeperForm.getHomePage());
		innkeeper.getUserAccount().setPassword(innkeeperForm.getPassword());
		innkeeper.getUserAccount().setUsername(innkeeperForm.getUsername());
		innkeeper.setCreditCard(innkeeperForm.getCreditCard());
		
		this.checkCreditCard(innkeeper);
		
		return innkeeper;
	}
	
	public Double findOccupancyRateOfNextMonth() {
		Innkeeper innkeeper;
		Double numberOfMyLodges;
		Double numberOfBookingInMyLodgesNextMonth;
		Double result;
		
		innkeeper = this.findByPrincipal();
		
		Assert.notNull(innkeeper);
		
		UserAccount userAccount;
		Integer userAccountId;
		Date currentDate;
		Integer nextMonth;
		Integer year;
		
		userAccount=innkeeper.getUserAccount();
		userAccountId=userAccount.getId();
		
		currentDate=new Date(System.currentTimeMillis());
		if(currentDate.getMonth()==11){
			nextMonth=1;
			year=currentDate.getYear()+1;
		}else{
			nextMonth=currentDate.getMonth()+2;
			year=currentDate.getYear();
		}
		
		
		
		numberOfBookingInMyLodgesNextMonth = innkeeperRepository.findNumberOfBookingInMyLodgesNextMonth(userAccountId, nextMonth , year);
		numberOfMyLodges = innkeeperRepository.findNumberOfMyLodges(userAccountId);
		
		if (numberOfMyLodges != 0.){
		result=numberOfBookingInMyLodgesNextMonth/numberOfMyLodges;
		}else{
			result=0.;
		}
		
		return result;
	}
	
	public Collection<Innkeeper> findAllInnkeeperOrderedDescendingNumberOfLodges(){
		Collection<Innkeeper> result;

		result = innkeeperRepository.findAllInnkeeperOrderedDescendingNumberOfLodges();

		return result;
	}
	
}
