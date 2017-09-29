package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Pilgrim;
import domain.Registration;
import domain.Route;
import domain.StageInstance;

import repositories.RegistrationRepository;
import security.UserAccount;

@Service
@Transactional
public class RegistrationService {

	// Managed repository ------------------------------------
	
	@Autowired
	private RegistrationRepository registrationRepository;
	
	// Supporting services -----------------------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private RouteService routeService;
	
	// Constructors --------------------------------------------
	
	public RegistrationService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------
	
	public Registration create(Integer routeId){
		Route route;
		
		route=routeService.findOne(routeId);
		
		Assert.notNull(route);
		
		Boolean isDeleted;
		
		isDeleted=route.getIsDeleted();
		
		Assert.isTrue(isDeleted==false);
		
		Registration result;
		Date moment;
		Pilgrim pilgrim;
		Collection<StageInstance> stageInstances;
		
		result=new Registration();
		moment=new Date(System.currentTimeMillis()-1);
		pilgrim=pilgrimService.findByPrincipal();
		stageInstances=new ArrayList<StageInstance>();
		
		result.setPilgrim(pilgrim);
		result.setMoment(moment);
		result.setRoute(route);
		result.setStageInstances(stageInstances);
		
		return result;
	}
	
	public Registration findOne(Integer registrationId){
		Registration result;
		
		result=registrationRepository.findOne(registrationId);
		
		Assert.notNull(result);
		
		this.checkPrincipal(result);
		
		return result;
	}
	
	public void save(Registration registration){
		Assert.notNull(registration);
		
		this.checkPrincipal(registration);
		
		Date moment;
		
		moment=new Date(System.currentTimeMillis()-1);
		
		registration.setMoment(moment);
		
		registrationRepository.save(registration);
		
	}
	
	// Other business methods ------------------------------------
	
	public void checkPrincipal(Registration registration) {
		Pilgrim pilgrim;
		Pilgrim principal;

		pilgrim = registration.getPilgrim();
		principal = pilgrimService.findByPrincipal();
		
		Assert.isTrue(pilgrim==principal);
	}
	
	public Collection<Registration> findAllRegistrationsByPilgrim(){
		Collection<Registration> result;
		Pilgrim pilgrim;
		UserAccount userAccount;
		Integer userAccountId;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=registrationRepository.findAllRegistrationsByPilgrim(userAccountId);
		
		return result;
	}
	
	public Collection<Route> findAllRoutesByPilgrim(){
		Collection<Route> result;
		Pilgrim pilgrim;
		UserAccount userAccount;
		Integer userAccountId;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=registrationRepository.findAllRoutesByPilgrim(userAccountId);
		
		return result;
	}
	
	
	
}