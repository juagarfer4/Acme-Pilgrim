package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import domain.GPS;

import repositories.GPSRepository;

@Service
@Transactional
public class GPSService {
	
	// Managed repository -----------------------------------------
	
	@Autowired
	private GPSRepository gPSRepository;
	
	// Supporting services -----------------------------------------
	
	// Simple CRUD methods -----------------------------------------
	
	public GPS findOne(Integer gPSId){
		GPS result;
		
		result=gPSRepository.findOne(gPSId);
		
		return result;
	}
	
	public Collection<GPS> findAll(){
		Collection<GPS> result;
		
		result=gPSRepository.findAll();
		
		return result;
	}
	
	// Other business methods --------------------------------------

}
