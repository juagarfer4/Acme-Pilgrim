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
import domain.CreditCard;
import domain.Pilgrim;
import domain.Request;
import forms.AdministratorForm;

import repositories.AdministratorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;

@Service
@Transactional
public class AdministratorService {
	
	// Managed repository --------------------------------------
	
	@Autowired
	private AdministratorRepository administratorRepository;
	
	// Supporting services -----------------------------------------
	
	// Constructors ------------------------------------------------
	
	public AdministratorService(){
		super();
	}
	
	// Simple CRUD methods -------------------------------------
	
	public Administrator create(){
		Administrator result;
		UserAccount userAccount;
		Collection<Authority> authorities;
		Authority authority;
		Collection<Request> requests;
		
		result=new Administrator();
		userAccount=new UserAccount();
		authorities=new ArrayList<Authority>();
		authority=new Authority();
		requests=new ArrayList<Request>();
		
		
		
		authority.setAuthority("ADMINISTRATOR");
		
		authorities.add(authority);
		
		userAccount.setAuthorities(authorities);
		result.setUserAccount(userAccount);
		result.setRequests(requests);
		
		return result;
	}
	
	public Administrator findOne(Integer administratorId){
		Administrator result;
		
		result=administratorRepository.findOne(administratorId);
		
		Assert.notNull(result);
		
		return result;
	}
	
	public Collection<Administrator> findAll(){
		Collection<Administrator> result;
		
		result=administratorRepository.findAll();
		
		return result;
	}
	
	public void save(Administrator administrator){
		Assert.notNull(administrator);
		
		this.encodePassword(administrator);
		
		administratorRepository.save(administrator);
	}
	
	// Other business methods -------------------------------------
	
	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = LoginService.getPrincipal();

		Assert.notNull(userAccount);

		userAccountId = userAccount.getId();

		result = administratorRepository.findByUserAccountId(userAccountId);

		Assert.notNull(result);

		return result;
	}
	
	public Collection<Administrator> showPrincipal(){
		Collection<Administrator> result;
		Administrator administrator;
		
		result=new ArrayList<Administrator>();
		administrator=this.findByPrincipal();
		
		result.add(administrator);
		
		return result;
	}
	
	public void encodePassword(Administrator administrator){
		UserAccount userAccount;
		String password;
		Md5PasswordEncoder encoder;
		
		encoder= new Md5PasswordEncoder();
		userAccount=administrator.getUserAccount();
		password=userAccount.getPassword();
		password=encoder.encodePassword(password, null);
		userAccount.setPassword(password);
	}
	
	public Administrator reconstruct(AdministratorForm administratorForm) {

		Administrator administrator = this.create();
		
		Assert.isTrue(administratorForm.isCondition());
		Assert.isTrue(administratorForm.getPassword().equals(administratorForm.getPasswordVerificada()));
		
		administrator.setName(administratorForm.getName());
		administrator.setSurname(administratorForm.getSurname());
		administrator.setEmail(administratorForm.getEmail());
		administrator.setContactPhone(administratorForm.getContactPhone());
		administrator.setHomePage(administratorForm.getHomePage());
		administrator.getUserAccount().setPassword(administratorForm.getPassword());
		administrator.getUserAccount().setUsername(administratorForm.getUsername());

		return administrator;
	}

}
