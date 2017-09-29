package services;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.LodgeAssessment;
import domain.Pilgrim;

import repositories.LodgeAssessmentRepository;
import security.UserAccount;

@Service
@Transactional
public class LodgeAssessmentService {

	// Managed repository --------------------------------------------

	@Autowired
	private LodgeAssessmentRepository lodgeAssessmentRepository;

	// Supporting services -------------------------------------------

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private LodgeService lodgeService;

	// Constructors --------------------------------------------------

	public LodgeAssessmentService() {
		super();
	}

	// Simple CRUD methods -----------------------------------------
	
	public LodgeAssessment create(Integer bookingId){
		Booking booking;
		
		booking=bookingService.findOne(bookingId);
		
		Assert.notNull(booking);
		
		LodgeAssessment result;
		Date creationMoment;
		
		result=new LodgeAssessment();
		creationMoment=new Date(System.currentTimeMillis()-1);
		
		result.setBooking(booking);
		result.setCreationMoment(creationMoment);
		
		return result;
	}
	
	public void save(LodgeAssessment lodgeAssessment){
		Assert.notNull(lodgeAssessment);
		
		this.checkPrincipal(lodgeAssessment);
		
		lodgeAssessmentRepository.save(lodgeAssessment);
		
		lodgeService.calculateLodgeAssessmentRating(lodgeAssessment);
	}
	
	// Other business methods ----------------------------------------------
	
	public void checkPrincipal(LodgeAssessment lodgeAssessment){
		Assert.notNull(lodgeAssessment);
		
		Booking booking;
		Pilgrim pilgrim;
		Pilgrim principal;
		
		booking=lodgeAssessment.getBooking();
		pilgrim=booking.getPilgrim();
		
		principal=pilgrimService.findByPrincipal();
		
		Assert.isTrue(pilgrim==principal);
	}
	
	public Collection<LodgeAssessment> findAllLodgeAssessmenrByPilgrim() {
		Pilgrim pilgrim;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);

		UserAccount userAccount;
		Integer userAccountId;
		Collection<LodgeAssessment> result;
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		result=lodgeAssessmentRepository.findAllLodgeAssessmentByPilgrim(userAccountId);
		
		return result;
	}

}
