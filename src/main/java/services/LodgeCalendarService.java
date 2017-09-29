package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Innkeeper;
import domain.Lodge;
import domain.LodgeCalendar;


import repositories.LodgeCalendarRepository;
import repositories.LodgeRepository;

@Service
@Transactional
public class LodgeCalendarService {
	
	// Managed repository -----------------------------------------
	
	@Autowired
	private LodgeCalendarRepository lodgeCalendarRepository;
		
	// Supporting services -----------------------------------------
	
	@Autowired
	private LodgeService lodgeService;
	
	
	@Autowired
	private InnkeeperService innkeeperService;
		
	// Constructors -------------------------------------------------
		
	public LodgeCalendarService(){
		super();
	}
		
	// Simple CRUD methods ------------------------------------------
	
	public LodgeCalendar create(Lodge lodgeParam ,Date date){
		LodgeCalendar result;
		Lodge lodge;
		Date datee;
		int numberOfBeds;
		
		result=new LodgeCalendar();
		lodge = lodgeParam; 
		datee= date;
		numberOfBeds = lodge.getNumberOfBeds();
		
		result.setLodge(lodge);
		result.setDate(datee);
		result.setNumberOfBeds(numberOfBeds);
		
		return result;
	}
	
	public LodgeCalendar findOne(Integer lodgeCalendarId){
		LodgeCalendar result;
		
		result=lodgeCalendarRepository.findOne(lodgeCalendarId);
		
		return result;
	}
	
	public Collection<LodgeCalendar> findAll(){
		Collection<LodgeCalendar> result;
		
		result=lodgeCalendarRepository.findAll();
		
		return result;
	}
	
	public void save(LodgeCalendar lodgeCalendar){
		Assert.notNull(lodgeCalendar);		
		
		lodgeCalendarRepository.save(lodgeCalendar);
	}
	
	
	// Other business methods --------------------------------------
	
	
	public boolean checkAvailability(Date date , int numberOfBeds,int lodgeId){
		boolean result;
		result=false;
		Integer numberFreeBeds;
		Lodge lodge;
		
		lodge=lodgeService.findOne(lodgeId);
		numberFreeBeds=lodgeCalendarRepository.numberOfFreeBeds(date,lodgeId);
		
		if(lodgeCalendarRepository.existLodgeCalendar(date, lodgeId) == 0){
			LodgeCalendar lodgeCalendar;
			result=true; //no hay creado ese LodgeCalendar 
			
			
			lodgeCalendar = this.create(lodge, date);
			this.save(lodgeCalendar);
			
		}else{
			
			if(numberFreeBeds<=0){//nunca se debe dar el caso que sea menor a 0
				result=false;//no hay camas libres
			}else{
				if(numberFreeBeds<numberOfBeds){
					result=false;//no hay SUFICIENTES camas libres
				}else{
					result=true;//hay camas suficientes
				}
			}
		}
		
		return result;
	}

	public void updateLodgeCalendar(Date time, Integer numberOfBedsRequested,int lodgeId) {
		LodgeCalendar lodgeCalendar;
		int numberOfBeds;
		
		lodgeCalendar= lodgeCalendarRepository.findLodgeCalendarByDateLodge(time, lodgeId);
		numberOfBeds = lodgeCalendar.getNumberOfBeds();
		
		lodgeCalendar.setNumberOfBeds(numberOfBeds-numberOfBedsRequested);
		
		this.save(lodgeCalendar);
		
		
	}

	public void cancelBooking(Date time, Integer numberOfBedsRequested, int lodgeId) {
		LodgeCalendar lodgeCalendar;
		//int numberOfBeds;
		
		lodgeCalendar= lodgeCalendarRepository.findLodgeCalendarByDateLodge(time, lodgeId);
		//numberOfBeds = lodgeCalendar.getNumberOfBeds();
		
		lodgeCalendar.setNumberOfBeds(lodgeCalendar.getNumberOfBeds()+(numberOfBedsRequested*2));
		
		this.save(lodgeCalendar);
		
	}
	
	public Double monthRate() {
		Double result;
		Integer totalBeds;
		Integer totalBendsInAMonth;
		Innkeeper innkeeper;
		Date date;
		int innkeeperId;
		
		innkeeper=innkeeperService.findByPrincipal();
		innkeeperId=innkeeper.getUserAccount().getId();
		date=new Date( System.currentTimeMillis());
		totalBeds= lodgeCalendarRepository.findBedsOfAnInnkeeper(innkeeperId);
		totalBendsInAMonth = lodgeCalendarRepository.findBedsOfAnInnkeeperInAMonth(innkeeperId, date.getMonth()+2);
		
		result=(double) ((totalBeds*31)/(totalBendsInAMonth));
//		result= 100-result;
	
		return result;
	}
	
	


}
