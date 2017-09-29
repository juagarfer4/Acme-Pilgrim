package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.Innkeeper;
import domain.Lodge;
import domain.LodgeAssessment;
import domain.LodgeCalendar;
import domain.Pilgrim;
import domain.Stage;

import repositories.LodgeRepository;
import security.UserAccount;

@Service
@Transactional
public class LodgeService {

	// Managed repository --------------------------------------

	@Autowired
	private LodgeRepository lodgeRepository;

	// Supporting services -------------------------------------------

	@Autowired
	private InnkeeperService innkeeperService;
	
	@Autowired
	private PilgrimService pilgrimService;

	// Constructors --------------------------------------------------

	public LodgeService() {
		super();
	}

	// Simple CRUD methods --------------------------------------------
	public Lodge create() {
		Lodge result;

		Collection<Booking> bookings;
		Innkeeper innkeeper;
		Stage stage;
		Collection<LodgeCalendar> lodgeCalendars;

		result = new Lodge();
		bookings = new ArrayList<Booking>();
		stage = new Stage();
		innkeeper = innkeeperService.findByPrincipal();
		lodgeCalendars=new ArrayList<LodgeCalendar>();

		result.setBookings(bookings);
		result.setInnkeeper(innkeeper);
		result.setStage(stage);
		result.setLodgeCalendars(lodgeCalendars);

		return result;
	}

	public Lodge findOne(Integer LodgeId) {
		Lodge result;

		result = lodgeRepository.findOne(LodgeId);

		return result;
	}

	public Collection<Lodge> findAll() {
		Collection<Lodge> result;

		result = lodgeRepository.findAll();

		return result;
	}

	public void delete(Lodge lodge) {
		Assert.notNull(lodge);

		this.checkPrincipal(lodge);

		lodgeRepository.delete(lodge);
	}

	public void save(Lodge lodge) {
		Assert.notNull(lodge);
		
		this.checkPrincipal(lodge);
		
		if(lodge.getId()!=0){
			this.calculateLocationRating(lodge);
			this.calculateRoomsRating(lodge);
			this.calculateServiceRating(lodge);
			this.calculatePriceRating(lodge);
		}

		lodgeRepository.save(lodge);
	}

	// Other business methods -----------------------------------------

	public void checkPrincipal(Lodge lodge) {
		Innkeeper innkeeper;
		Innkeeper principal;

		innkeeper=lodge.getInnkeeper();
		principal = innkeeperService.findByPrincipal();
	
		Assert.isTrue(innkeeper==principal);
	}

	public Collection<Lodge> findAllLodgesWhoseRequestIsAcepted() {
		Collection<Lodge> result;

		result = lodgeRepository.findAllLodgesWhoseRequestIsAcepted();

		return result;
	}

	public Collection<Lodge> findAllLodgesByInnkeeper() {
		Innkeeper innkeeper;

		innkeeper = innkeeperService.findByPrincipal();

		Assert.notNull(innkeeper);

		Collection<Lodge> result;
		UserAccount userAccount;
		Integer userAccountId;

		userAccount = innkeeper.getUserAccount();
		userAccountId = userAccount.getId();
		result = lodgeRepository.findAllLodgesByInnkeeper(userAccountId);

		return result;
	}

	public Collection<Lodge> findAllLodgesSortedByDescendingNumberOfBookingsByInnkeeper() {
		Collection<Lodge> result;
		Innkeeper innkeeper;
		UserAccount userAccount;
		Integer userAccountId;

		innkeeper = innkeeperService.findByPrincipal();

		Assert.notNull(innkeeper);

		userAccount = innkeeper.getUserAccount();
		userAccountId = userAccount.getId();

		result = lodgeRepository
				.findAllLodgesSortedByDescendingNumberOfBookingsByInnkeeper(userAccountId);

		return result;
	}


	public Collection<Lodge> findAllLodgesOrderedByIncreaseAverageRatingByInnkeeper() {
		Collection<Lodge> result;
		Innkeeper innkeeper;
		UserAccount userAccount;
		Integer userAccountId;

		innkeeper = innkeeperService.findByPrincipal();

		Assert.notNull(innkeeper);

		userAccount = innkeeper.getUserAccount();
		userAccountId = userAccount.getId();

		result = lodgeRepository
				.findAllLodgesOrderedByIncreaseAverageRatingByInnkeeper(userAccountId);

		return result;
	}

	public Collection<Lodge> findAllLodgesOrderedByDescendingPriceByInnkeeper() {
		Collection<Lodge> result;
		Innkeeper innkeeper;
		UserAccount userAccount;
		Integer userAccountId;

		innkeeper = innkeeperService.findByPrincipal();

		Assert.notNull(innkeeper);

		userAccount = innkeeper.getUserAccount();
		userAccountId = userAccount.getId();

		result = lodgeRepository
				.findAllLodgesOrderedByDescendingPriceByInnkeeper(userAccountId);

		return result;
	}

	public Collection<Lodge> findAllLodgesOrderedByDescendingNumberOfBooking() {
		Collection<Lodge> result;

		result = lodgeRepository
				.findAllLodgesOrderedByDescendingNumberOfBooking();

		return result;
	}
	
	public void calculateLodgeAssessmentRating(LodgeAssessment lodgeAssessment){
		Assert.notNull(lodgeAssessment);
		
		Lodge lodge;
		Integer lodgeId;
		Double locationRating;
		Double roomsRating;
		Double serviceRating;
		Double priceRating;
		
		lodge=lodgeAssessment.getBooking().getLodge();
		lodgeId=lodge.getId();
		locationRating=lodgeRepository.calculateLocationRating(lodgeId);
		roomsRating=lodgeRepository.calculateRoomsRating(lodgeId);
		serviceRating=lodgeRepository.calculateServiceRating(lodgeId);
		priceRating=lodgeRepository.calculatePriceRating(lodgeId);
		
		lodge.setLocationRating(locationRating);
		lodge.setRoomsRating(roomsRating);
		lodge.setServiceRating(serviceRating);
		lodge.setPriceRating(priceRating);
		
		lodgeRepository.save(lodge);
	}
	
	public void calculateLocationRating(Lodge lodge){
		Double locationRating;
		Integer lodgeId;
		
		lodgeId=lodge.getId();
		locationRating=lodgeRepository.calculateLocationRating(lodgeId);
		
		lodge.setLocationRating(locationRating);
	}
	
	public void calculateRoomsRating(Lodge lodge){
		Double roomsRating;
		Integer lodgeId;
		
		lodgeId=lodge.getId();
		roomsRating=lodgeRepository.calculateRoomsRating(lodgeId);
		
		lodge.setRoomsRating(roomsRating);
	}
	
	public void calculateServiceRating(Lodge lodge){
		Double serviceRating;
		Integer lodgeId;
		
		lodgeId=lodge.getId();
		serviceRating=lodgeRepository.calculateServiceRating(lodgeId);
		
		lodge.setServiceRating(serviceRating);
	}
	
	public void calculatePriceRating(Lodge lodge){
		Double priceRating;
		Integer lodgeId;
		
		lodgeId=lodge.getId();
		priceRating=lodgeRepository.calculatePriceRating(lodgeId);
		
		lodge.setPriceRating(priceRating);
	}
	
	public Collection<Lodge> findAllLodgesByPilgrim(){
		Pilgrim pilgrim;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);
		
		Collection<Lodge> result;
		UserAccount userAccount;
		Integer userAccountId;
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=lodgeRepository.findAllLodgesByPilgrim(userAccountId);
		
		return result;
	}
	
	public Collection<Lodge> findAllLodgesToDelete(){
		Innkeeper innkeeper;
		
		innkeeper=innkeeperService.findByPrincipal();
		
		Assert.notNull(innkeeper);
		
		Collection<Lodge> result;
		UserAccount userAccount;
		Integer userAccountId;
		
		userAccount=innkeeper.getUserAccount();
		userAccountId=userAccount.getId();
		
		result=lodgeRepository.findAllLodgesToDelete(userAccountId);
		
		return result;
	}

}
