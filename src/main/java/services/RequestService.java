package services;

import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Innkeeper;
import domain.Request;

import repositories.RequestRepository;
import security.UserAccount;

@Service
@Transactional
public class RequestService {
	
	// Managed repository ----------------------------------------------------------
	
	@Autowired
	private RequestRepository requestRepository;
	
	// Supporting services ----------------------------------------------------------
	
	@Autowired
	private InnkeeperService innkeeperService;
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructors -----------------------------------------------------------------
	
	public RequestService(){
		super();
	}
	
	// Simple CRUD methods -----------------------------------------------------------
	
	public Request create(){
		Request result;
		Innkeeper innkeeper;
		String code;
		Date creationMoment;
		String status;
		
		result=new Request();
		innkeeper=innkeeperService.findByPrincipal();
		code=RequestService.generateCode();
		creationMoment=new Date(System.currentTimeMillis()-1);
		status="PENDING";
		
		result.setInnkeeper(innkeeper);
		result.setCode(code);
		result.setCreationMoment(creationMoment);
		result.setStatus(status);
		
		return result;
	}
	
	public Request findOne(Integer requestId){
		Request result;
		
		result=requestRepository.findOne(requestId);
		
		return result;
	}
	
	public void saveInnkeeper(Request request){
		Assert.notNull(request);
		
		this.checkPrincipalInnkeeper(request);
		Assert.isTrue(request.getStatus().equals("PENDING") || request.getStatus().equals("ACCEPTED") ||request.getStatus().equals("REJECTED"));
		
		Integer aux;
		aux=0;
		
		for(Request r:request.getInnkeeper().getRequests()){
			if(r.getStatus().equals("ACCEPTED") || r.getStatus().equals("PENDING")){
				aux=null;
			}
		}
		
		Assert.notNull(aux);
		
		requestRepository.save(request);
	}
	
	public void saveAdministrator(Request request){
		Assert.notNull(request);
		
		this.checkPrincipalAdministrator(request);
		
		String status;
		String comments;
		
		status=request.getStatus();
		comments=request.getComments();
		
		Assert.isTrue(request.getStatus().equals("PENDING") || request.getStatus().equals("ACCEPTED") ||request.getStatus().equals("REJECTED"));
		
		if(status.equals("REJECTED")){
				Assert.isTrue(!(comments.isEmpty()));
				Assert.isTrue(comments.length()!= 0);
				Assert.isTrue(!(comments.trim().equals("")));
		}
	
		requestRepository.save(request);
	}
	
	// Other business methods --------------------------------------------------
	
	public void checkPrincipalInnkeeper(Request request) {
		Innkeeper innkeeper;
		Innkeeper principal;

		innkeeper=request.getInnkeeper();
		principal = innkeeperService.findByPrincipal();
		
		Assert.isTrue(innkeeper==principal);
	}
	
	public void checkPrincipalAdministrator(Request request) {
		Administrator administrator;
		Administrator principal;

		administrator=request.getAdministrator();
		principal = administratorService.findByPrincipal();
		
		Assert.isTrue(administrator==principal);
	}
	
	public static String generateCode(){
		 String result;
		 String parte2;
		 int ramdom;
		 
		 UUID uuid = UUID.randomUUID();
		 result = uuid.toString().replace("-", "_");
		 
		 ramdom = (int)(Math.random()*10);
		 
		 if (ramdom%2==0){
		 UUID uuid2 = UUID.randomUUID();
		 parte2 = uuid2.toString().replace("-", "_");
		 result += "-" +parte2;
		 }
		 
		 return result;
	}
	
	public Collection<Request> conditionToPublishRequest(){
		Collection<Request> result;
		Innkeeper innkeeper;
		UserAccount userAccount;
		Integer userAccountId;
		
		innkeeper=innkeeperService.findByPrincipal();
		userAccount=innkeeper.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=requestRepository.conditionToPublishRequest(userAccountId);
		
		return result;
	}
	
	public Collection<Request> findAllRequestsByInnkeeper(){
		Collection<Request> result;
		Innkeeper innkeeper;
		UserAccount userAccount;
		Integer userAccountId;
		
		innkeeper=innkeeperService.findByPrincipal();
		userAccount=innkeeper.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=requestRepository.findAllRequestsByInnkeeper(userAccountId);
		
		return result;
	}
	
	public Collection<Request> findAllRequestsByAdministratorToManage(){
		Collection<Request> result;
		Administrator administrator;
		UserAccount userAccount;
		Integer userAccountId;
		
		administrator=administratorService.findByPrincipal();
		userAccount=administrator.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=requestRepository.findAllRequestsByAdministratorToManage(userAccountId);
		
		return result;
	}
	
	public Collection<Request> findAllRequestsPendingbyAdministrator(){
		Collection<Request> result;
		Administrator principal;
		
		principal=administratorService.findByPrincipal();
		
		result=requestRepository.findAllRequestsPendingbyAdministrator(principal.getUserAccount().getId());
		
		
		return result;
	}

}
