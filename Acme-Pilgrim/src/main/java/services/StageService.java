package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Lodge;
import domain.Orderer;
import domain.Pilgrim;
import domain.Registration;
import domain.Stage;
import domain.StageInstance;

import repositories.StageRepository;
import security.UserAccount;

@Service
@Transactional
public class StageService {
	
	// Managed repository -----------------------------------------
	
	@Autowired
	private StageRepository stageRepository;
		
	// Supporting services -----------------------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private PilgrimService pilgrimService;
		
	// Constructors -------------------------------------------------
		
	public StageService(){
		super();
	}
		
	// Simple CRUD methods ------------------------------------------
	
	public Stage create(){
		Stage result;
		Collection<Orderer> orderers;
		Collection<StageInstance> stageInstances;
		Collection<Lodge> lodges;
		
		result=new Stage();
		orderers=new ArrayList<Orderer>();
		stageInstances=new ArrayList<StageInstance>();
		lodges=new ArrayList<Lodge>();
		
		result.setOrderers(orderers);
		result.setStageInstances(stageInstances);
		result.setLodges(lodges);
		
		return result;
	}
	
	public Stage findOne(Integer stageId){
		Stage result;
		
		result=stageRepository.findOne(stageId);
		
		return result;
	}
	
	public Collection<Stage> findAll(){
		Collection<Stage> result;
		
		result=stageRepository.findAll();
		
		return result;
	}
	
	public void save(Stage stage){
		Assert.notNull(stage);
		
		this.checkPrincipal();
		
		stageRepository.save(stage);
	}
	
	public void delete(Stage stage){
		Assert.notNull(stage);
		
		this.checkPrincipal();
		
		stageRepository.delete(stage);
	}
		
	// Other business methods --------------------------------------
	
	public void checkPrincipal() {
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
	}
	
	public Collection<Stage> findAllStagesByRoute(Integer routeId){
		Collection<Stage> result;
		
		result=stageRepository.findAllStagesByRoute(routeId);
		
		return result;
	}
	
	public Collection<Stage> findAllStagesByRegistration(Integer registrationId){
		Registration registration;
		
		registration=registrationService.findOne(registrationId);
		
		Assert.notNull(registration);
		
		registrationService.checkPrincipal(registration);
		
		Collection<Stage> result;
		
		result=stageRepository.findAllStagesByRegistration(registrationId);
		
		return result;
	}
	
	public Collection<Stage> findAllStagesThatCanBeDeleted(){
		Collection<Stage> result;
		
		result=stageRepository.findAllStagesThatCanBeDeleted();
		
		return result;
	}
	
	public Collection<Stage> findAllStagesOrderedByAscendingAverageRating(){
		Collection<Stage> result;
		
		result=stageRepository.findAllStagesOrderedByAscendingAverageRating();
		
		return result;
	}
	public Collection<Stage> findAllStagesHistoryByPilgrim() {
		Pilgrim pilgrim;

		pilgrim = pilgrimService.findByPrincipal();

		Assert.notNull(pilgrim);

		Collection<Stage> result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = pilgrim.getUserAccount();
		userAccountId = userAccount.getId();
		result = stageRepository.findAllStagesHistoryByPilgrim(userAccountId);

		return result;
	}
	
	public Collection<Stage> findAllPendingStageByPilgrim() {
		Pilgrim pilgrim;

		pilgrim = pilgrimService.findByPrincipal();

		Assert.notNull(pilgrim);

		Collection<Stage> result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = pilgrim.getUserAccount();
		userAccountId = userAccount.getId();
		result = stageRepository.findAllPendingStageByPilgrim(userAccountId);

		return result;
	}

}
