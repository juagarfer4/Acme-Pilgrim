package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Administrator;
import domain.Assessment;
import domain.Orderer;
import domain.Pilgrim;
import domain.Route;
import domain.Registration;

import repositories.RouteRepository;
import security.UserAccount;

@Service
@Transactional
public class RouteService {
	
	// Managed repository -----------------------------------------
	
	@Autowired
	private RouteRepository routeRepository;
	
	// Supporting services -----------------------------------------
	
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	// Constructors -------------------------------------------------
	
	public RouteService(){
		super();
	}
	
	// Simple CRUD methods ------------------------------------------
	
	public Route create(){
		Route result;
		Boolean isDeleted;
		Collection<Registration> registrations;
		Collection<Orderer> orderers;
		
		result=new Route();
		isDeleted=false;
		registrations=new ArrayList<Registration>();
		orderers=new ArrayList<Orderer>();
		
		result.setIsDeleted(isDeleted);
		result.setRegistrations(registrations);
		result.setOrderers(orderers);
		result.setLengthRating(1.0);
		result.setDifficultyRating(1.0);
		
		return result;
	}
	
	public Route findOne(Integer routeId){
		Route result;
		
		result=routeRepository.findOne(routeId);
		
		return result;
	}
	
	public Collection<Route> findAll(){
		Collection<Route> result;
		
		result=routeRepository.findAll();
		
		return result;
	}
	
	public void save(Route route){
		Assert.notNull(route);
		
		this.checkPrincipal();
		
		if(route.getId()!=0){
			this.calculateLengthRating(route);
			this.calculateDifficultyRating(route);
		}
		
		routeRepository.save(route);
	}
	
	// Other business methods --------------------------------------
	
	public void checkPrincipal() {
		Administrator administrator;

		administrator = administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
	}
	
	public void deleteRoute(Route route){
		Assert.notNull(route);
		
		this.checkPrincipal();
		
		Boolean isDeleted;
		Collection<Registration> registrations;
		Collection<Orderer> orderers;
		Integer registrationsSize;
		Integer orderersSize;
		
		isDeleted=route.getIsDeleted();
		registrations=route.getRegistrations();
		orderers=route.getOrderers();
		registrationsSize=registrations.size();
		orderersSize=orderers.size();
		
		Assert.isTrue(isDeleted==false);
		Assert.isTrue(registrationsSize==0);
		Assert.isTrue(orderersSize==0);
		
		route.setIsDeleted(true);
		
		this.save(route);
	}
	
	public Collection<Route> findAllRoutesThatAreNotCancelled(){
		Collection<Route> result;
		
		result=routeRepository.findAllRoutesThatAreNotCancelled();
		
		return result;
	}
	
	public Collection<Route> findAllNotDeletedRoutesByPilgrim(Integer userAccountId){
		Collection<Route> result;
		
		result=routeRepository.findAllNotDeletedRoutesByPilgrim(userAccountId);
		
		return result;
	}
	
	public Collection<Route> findAllNotDeletedRoutesInDescendingOrderOfRegistrations(){
		Administrator administrator;
		
		administrator=administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
		
		Collection<Route> result;
		
		result=routeRepository.findAllNotDeletedRoutesInDescendingOrderOfRegistrations();
		
		return result;
	}
	
	public Collection<Route> findAllRoutesDeleted(){
		Administrator administrator;
		
		administrator=administratorService.findByPrincipal();
		
		Assert.notNull(administrator);
		
		Collection<Route> result;
		
		result=routeRepository.findAllRoutesDeleted();
		
		return result;
	}
	
	
	
	public Collection<Route> findAllRoutesToRegister(){
		Collection<Route> result;
		Pilgrim pilgrim;
		UserAccount userAccount;
		Integer userAccountId;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=routeRepository.findAllRoutesToRegister(userAccountId);
		
		return result;
	}
	
	public Collection<Route> findAllRoutesToDelete(){
		Collection<Route> result;
		
		result=routeRepository.findAllRoutesToDelete();

		return result;
	}
	
	public void calculateLengthRating(Route route){
		Double lengthRating;
		Integer routeId;
		
		routeId=route.getId();
		lengthRating=routeRepository.calculateLengthRating(routeId);
		
		route.setLengthRating(lengthRating);
	}
	
	public void calculateDifficultyRating(Route route){
		Double difficultyRating;
		Integer routeId;
		
		routeId=route.getId();
		difficultyRating=routeRepository.calculateDifficultyRating(routeId);
		
		route.setDifficultyRating(difficultyRating);
	}
	
	public void calculateAssessmentRating(Assessment assessment){
		Assert.notNull(assessment);
		
		Route route;
		Integer routeId;
		Double lengthRating;
		Double difficultyRating;
		
		route=assessment.getStageInstance().getRegistration().getRoute();
		routeId=route.getId();
		lengthRating=routeRepository.calculateLengthRating(routeId);
		difficultyRating=routeRepository.calculateDifficultyRating(routeId);
		
		route.setLengthRating(lengthRating);
		route.setDifficultyRating(difficultyRating);
		
		routeRepository.save(route);
	}

}
