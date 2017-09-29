package services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Assessment;
import domain.Pilgrim;
import domain.Registration;
import domain.StageInstance;

import repositories.AssessmentRepository;

@Service
@Transactional
public class AssessmentService {
	
	// Managed repository --------------------------------------------
	
	@Autowired
	private AssessmentRepository assessmentRepository;
	
	// Supporting services -------------------------------------------
	
	@Autowired
	private StageInstanceService stageInstanceService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private RouteService routeService;
	
	// Constructors --------------------------------------------------
	
	public AssessmentService(){
		super();
	}
	
	// Simple CRUD methods -----------------------------------------
	
	public Assessment create(Integer stageInstanceId){
		Assessment result;
		StageInstance stageInstance;
		Date creationMoment;
		
		result=new Assessment();
		stageInstance=stageInstanceService.findOne(stageInstanceId);
		creationMoment=new Date(System.currentTimeMillis()-1);
		
		result.setStageInstance(stageInstance);
		result.setCreationMoment(creationMoment);
		
		return result;
	}
	
	public void save(Assessment assessment){
		Assert.notNull(assessment);
		
		this.checkPrincipal(assessment);
		
		Date creationMoment;
		
		creationMoment=new Date(System.currentTimeMillis()-1);
		
		assessment.setCreationMoment(creationMoment);
		
		assessmentRepository.save(assessment);
		
		routeService.calculateAssessmentRating(assessment);
		
	}
	
	// Other business methods ------------------------------------
	
	public void checkPrincipal(Assessment assessment) {
		StageInstance stageInstance;
		Registration registration;
		Pilgrim pilgrim;
		Pilgrim principal;

		stageInstance=assessment.getStageInstance();
		registration=stageInstance.getRegistration();
		pilgrim=registration.getPilgrim();
		principal = pilgrimService.findByPrincipal();
		
		Assert.isTrue(pilgrim==principal);
	}
	
}
