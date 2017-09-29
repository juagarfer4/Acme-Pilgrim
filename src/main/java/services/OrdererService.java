package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Orderer;
import domain.Stage;

import repositories.OrdererRepository;

@Service
@Transactional
public class OrdererService {
	
	// Managed repository -----------------------------------------
	
	@Autowired
	private OrdererRepository ordererRepository;
	
	// Supporting services -----------------------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private StageService stageService;
	
	
	// Constructors ---------------------------------------------
	
	public OrdererService(){
		super();
	}
	
	// Simple CRUD methods ----------------------------------------
	
	public Orderer create(int stageId){
		Orderer result;
		Stage stage;
		Integer identifier;
		
		result=new Orderer();
		stage= stageService.findOne(stageId);
		identifier = 0;
		
		result.setStage(stage);
		result.setIdentifier(identifier);
		
		return result;
	}
	
	public void save(Orderer orderer){
		Orderer ord;
		ord = orderer;
		
		ord.setIdentifier(ordererRepository.numbrerOrdererByRouteId(ord.getRoute().getId())+1);

		
		//this.checkPrincipal();
		
		ordererRepository.save(ord);
	}
	
	public Orderer findOne(Integer ordererId){
		Orderer result;
		
		result=ordererRepository.findOne(ordererId);
		
		return result;
	}
	
	// Simple business methods --------------------------------------
	
	public void checkPrincipal() {
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
	}

}
