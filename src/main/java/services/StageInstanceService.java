package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.StageInstance;
import domain.Registration;
import domain.Pilgrim;

import repositories.StageInstanceRepository;
import security.UserAccount;

@Service
@Transactional
public class StageInstanceService {
	
	// Managed repository --------------------------------------
	
	@Autowired
	private StageInstanceRepository stageInstanceRepository;
	
	// Supporting services -------------------------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private RegistrationService registrationService;
	
	// Constructors ---------------------------------------------
	
	public StageInstanceService(){
		super();
	}
	
	// Simple CRUD methods ---------------------------------------
	
	public StageInstance create(Integer registrationId){
		StageInstance result;
		Registration registration;
		
		result=new StageInstance();
		registration=registrationService.findOne(registrationId);
		
		
		result.setRegistration(registration);
		
		return result;
	}
	
	public StageInstance findOne(Integer stageInstanceId){
		StageInstance result;
		
		result=stageInstanceRepository.findOne(stageInstanceId);
		
		return result;
	}
	
	public void save(StageInstance stageInstance){
		Assert.notNull(stageInstance);
		
		this.checkPrincipal(stageInstance);
		
		Date startingMoment;
		Date endingMoment;
		
		startingMoment=stageInstance.getStartingMoment();
		endingMoment=stageInstance.getEndingMoment();
		
		Assert.isTrue(startingMoment.before(endingMoment));
		
		stageInstanceRepository.save(stageInstance);
	}
	
	// Other business methods -------------------------------------------
	
	public void checkPrincipal(StageInstance stageInstance) {
		Registration registration;
		Pilgrim pilgrim;
		Pilgrim principal;

		registration=stageInstance.getRegistration();
		pilgrim=registration.getPilgrim();
		principal = pilgrimService.findByPrincipal();
		
		Assert.isTrue(pilgrim==principal);
	}
	
	public Collection<StageInstance> findAllStageInstancesWithoutAssessmentByPilgrim(){
		Pilgrim pilgrim;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);
		
		Collection<StageInstance> result;
		UserAccount userAccount;
		Integer userAccountId;
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=stageInstanceRepository.findAllStageInstancesWithoutAssessmentByPilgrim(userAccountId);
		
		return result;
	}

}
