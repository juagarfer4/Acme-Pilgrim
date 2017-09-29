package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.Innkeeper;
import domain.Lodge;
import domain.Pilgrim;

import repositories.BookingRepository;
import security.UserAccount;

@Service
@Transactional
public class BookingService {
	
	// Managed repository -------------------------------------
	
	@Autowired
	private BookingRepository bookingRepository;
	
	// Supporting services ----------------------------------
	
	@Autowired
	private LodgeService lodgeService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private LodgeCalendarService lodgeCalendarService;
	
	@Autowired
	private InnkeeperService innkeeperService;
	
	// Constructors ----------------------------------------
	
	public BookingService(){
		super();
	}
	
	// Simple CRUD methods --------------------------------
	
	public Booking create(Integer lodgeId){
		Booking result;
		Lodge lodge;
		Pilgrim pilgrim;
		Date creationMoment;
		Boolean isCancelled;
		
		result=new Booking();
		lodge=lodgeService.findOne(lodgeId);
		pilgrim=pilgrimService.findByPrincipal();
		creationMoment=new Date(System.currentTimeMillis()-1);
		isCancelled=false;
		
		result.setPilgrim(pilgrim);
		result.setLodge(lodge);
		result.setCreationMoment(creationMoment);
		result.setIsCancelled(isCancelled);
		
		return result;
	}
	
	public Booking findOne(Integer bookingId){
		Booking result;
		
		result=bookingRepository.findOne(bookingId);
		
		this.checkPrincipal(result);
		
		return result;
	}
	
	public void save(Booking booking){
		Date arrivalDate;
		
		Assert.notNull(booking);
		this.checkPrincipal(booking);
		
		Assert.isTrue(booking.getArrivalDate().after(booking.getCreationMoment()));
		
		Assert.isTrue( booking.getLodge().getNumberOfBeds()>= booking.getNumberOfBedsRequested());
		
		
		arrivalDate= booking.getArrivalDate();
		
		//COMPROBAMOS DISPONIBILIDAD
		for(int i = 0 ;i < booking.getNumberOfNights();i++){
			
			//compruebo la arrivalDate + i
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(arrivalDate); // Configuramos la fecha que se recibe
		      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
		      Assert.isTrue(lodgeCalendarService.checkAvailability(calendar.getTime(),booking.getNumberOfBedsRequested(),booking.getLodge().getId()));
			
		}
		
		arrivalDate= booking.getArrivalDate();
		
		for(int i = 0 ;i < booking.getNumberOfNights();i++){
			
			//compruebo la arrivalDate + i
		      Calendar calendar = Calendar.getInstance();
		      calendar.setTime(arrivalDate); // Configuramos la fecha que se recibe
		      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
		      lodgeCalendarService.updateLodgeCalendar(calendar.getTime(),booking.getNumberOfBedsRequested(),booking.getLodge().getId());
			
		}
		
		Double price;
		
		price=booking.getLodge().getPrice()*booking.getNumberOfNights()*booking.getNumberOfBedsRequested();
		
		booking.setPrice(price);
		
		Boolean isCancelled;
		
		isCancelled=booking.getIsCancelled();
		
		if(isCancelled==true){
			this.cancel(booking);
		}
		
		bookingRepository.save(booking);
	}
	
	// Other business methods -----------------------------------------
	
	//method to edit number of beds of a booking
	public void saveEdit2(Booking booking){
		Date arrivalDate;
		
		Booking oldBooking;
		
		Assert.notNull(booking);
		this.checkPrincipal(booking);
		
		
		oldBooking=bookingRepository.findOne(booking.getId());
		
		if(oldBooking.getNumberOfBedsRequested()< booking.getNumberOfBedsRequested()){//se aumenta el número de camas
			arrivalDate= booking.getArrivalDate();
			//COMPROBAMOS DISPONIBILIDAD
			for(int i = 0 ;i < booking.getNumberOfNights();i++){
				
				//compruebo la arrivalDate + i
			      Calendar calendar = Calendar.getInstance();
			      calendar.setTime(arrivalDate); // Configuramos la fecha que se recibe
			      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
			      Assert.isTrue(lodgeCalendarService.checkAvailability(calendar.getTime(),booking.getNumberOfBedsRequested()-oldBooking.getNumberOfBedsRequested(),booking.getLodge().getId()));
				
			}
			
			arrivalDate= booking.getArrivalDate();
			
			for(int i = 0 ;i < booking.getNumberOfNights();i++){
				
				//compruebo la arrivalDate + i
			      Calendar calendar = Calendar.getInstance();
			      calendar.setTime(arrivalDate); // Configuramos la fecha que se recibe
			      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
			      lodgeCalendarService.updateLodgeCalendar(calendar.getTime(),booking.getNumberOfBedsRequested()-oldBooking.getNumberOfBedsRequested(),booking.getLodge().getId());
				
			}
				
			
		}
		else if(oldBooking.getNumberOfBedsRequested()> booking.getNumberOfBedsRequested()){//se disminuye el número de camas
			
			arrivalDate= booking.getArrivalDate();
			
			for(int i = 0 ;i < booking.getNumberOfNights();i++){
				
				//compruebo la arrivalDate + i
			      Calendar calendar = Calendar.getInstance();
			      calendar.setTime(arrivalDate); // Configuramos la fecha que se recibe
			      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
			      lodgeCalendarService.updateLodgeCalendar(calendar.getTime(),booking.getNumberOfBedsRequested()-oldBooking.getNumberOfBedsRequested(),booking.getLodge().getId());
			}
			
		}else{//si se queda igual 
			//se podria poner un assert para que salte la operacion , pero hemos decidido que la booking se guarde de nuevo recalculando el precio.
		}
		
		
		
		Double price;
		
		price=booking.getLodge().getPrice()*booking.getNumberOfNights()*booking.getNumberOfBedsRequested();
		
		booking.setPrice(price);
		
		bookingRepository.save(booking);
	}
	
	//method to edit number of nights of a booking
	public void saveEdit3(Booking booking){
		Date arrivalDate;
		
		Booking oldBooking;
		
		Assert.notNull(booking);
		this.checkPrincipal(booking);
		
		
		oldBooking=bookingRepository.findOne(booking.getId());
		
		if(oldBooking.getNumberOfNights()< booking.getNumberOfNights()){//se aumenta el número de noches
			
			arrivalDate= booking.getArrivalDate();
			//Calculamos la fecha a partir de los nuevos dias que se aumenta
			
			  Calendar calendarN = Calendar.getInstance();
		      calendarN.setTime(arrivalDate); // Configuramos la fecha que se recibe
		      calendarN.add(Calendar.DAY_OF_YEAR,oldBooking.getNumberOfNights()); 
			
			//COMPROBAMOS DISPONIBILIDAD
			for(int i = 0 ;i < booking.getNumberOfNights()-oldBooking.getNumberOfNights();i++){
				
				//compruebo la arrivalDate + i
			      Calendar calendar = Calendar.getInstance();
			      calendar.setTime(calendarN.getTime()); // Configuramos la fecha que se recibe
			      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
			      Assert.isTrue(lodgeCalendarService.checkAvailability(calendar.getTime(),booking.getNumberOfBedsRequested(),booking.getLodge().getId()));
				
			}
			
			
			
			for(int i = 0 ;i <booking.getNumberOfNights()-oldBooking.getNumberOfNights();i++){
				
				//compruebo la arrivalDate + i
			      Calendar calendar = Calendar.getInstance();
			      calendar.setTime(calendarN.getTime()); // Configuramos la fecha que se recibe
			      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
			      lodgeCalendarService.updateLodgeCalendar(calendar.getTime(),booking.getNumberOfBedsRequested(),booking.getLodge().getId());
				
			}
				
			
		}
		else if(oldBooking.getNumberOfBedsRequested()> booking.getNumberOfBedsRequested()){//se disminuye el número de camas
			
			arrivalDate= booking.getArrivalDate();
			//Calculamos la fecha a partir de los nuevos dias que se aumenta
			
			  Calendar calendarN = Calendar.getInstance();
		      calendarN.setTime(arrivalDate); // Configuramos la fecha que se recibe
		      calendarN.add(Calendar.DAY_OF_YEAR,booking.getNumberOfNights()); 
			
			for(int i = 0 ;i < oldBooking.getNumberOfNights()- booking.getNumberOfNights();i++){
				
				//compruebo la arrivalDate + i
			      Calendar calendar = Calendar.getInstance();
			      calendar.setTime(calendarN.getTime()); // Configuramos la fecha que se recibe
			      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
			      lodgeCalendarService.cancelBooking(calendar.getTime(), booking.getNumberOfBedsRequested(), booking.getLodge().getId());
			}
			
		}else{//si se queda igual 
			//se podria poner un assert para que salte la operacion , pero hemos decidido que la booking se guarde de nuevo recalculando el precio.
		}
		
		
		
		Double price;
		
		price=booking.getLodge().getPrice()*booking.getNumberOfNights()*booking.getNumberOfBedsRequested();
		
		booking.setPrice(price);
		
		bookingRepository.save(booking);
	}
	
	public void checkPrincipal(Booking booking) {
		Pilgrim pilgrim;
		Pilgrim principal;

		pilgrim=booking.getPilgrim();
		principal = pilgrimService.findByPrincipal();
	
		Assert.isTrue(pilgrim==principal);
	}
	
	public Collection<Booking> findAllBookingsByPilgrim() {
		Pilgrim pilgrim;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);

		UserAccount userAccount;
		Integer userAccountId;
		Collection<Booking> result;
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		result=bookingRepository.findAllBookingsByPilgrim(userAccountId);
		
		return result;
	}
	
	public Collection<Booking> findAllNotCancelledBookingsByPilgrim() {
		Pilgrim pilgrim;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);

		UserAccount userAccount;
		Integer userAccountId;
		Collection<Booking> result;
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		result=bookingRepository.findAllNotCancelledBookingsByPilgrim(userAccountId);
		
		return result;
	}
	
	public Collection<Booking> findAllBookingsByPilgrimWithoutAssessment() {
		Pilgrim pilgrim;
		
		pilgrim=pilgrimService.findByPrincipal();
		
		Assert.notNull(pilgrim);

		UserAccount userAccount;
		Integer userAccountId;
		Collection<Booking> result;
		
		userAccount=pilgrim.getUserAccount();
		userAccountId=userAccount.getId();
		result=bookingRepository.findAllBookingsByPilgrimWithoutAssessment(userAccountId);
		
		return result;
	}
	public Boolean checkCancel(Booking booking){
		Boolean result;
		Date date;
		Date currentDate;
		
		Assert.notNull(booking);
		this.checkPrincipal(booking);
		
		result=false;
		date= booking.getArrivalDate();
		currentDate= new Date(System.currentTimeMillis());
	
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date); // Configuramos la fecha que se recibe
		calendar.add(Calendar.DAY_OF_YEAR, -1);  // numero de días a añadir, o restar en caso de días<0
		date = calendar.getTime();
			
		if(currentDate.before(date) || currentDate.equals(date)){
			result=true;
		}

		return result;
	}

	public void cancel(Booking booking){
	Boolean result;
	Date arrivalDate;
	
	Assert.notNull(booking);
	this.checkPrincipal(booking);
	
	Assert.isTrue(this.checkCancel(booking));//Comprobamos que se puede cancelar la reserva
	
	arrivalDate= booking.getArrivalDate();
		
	for(int i = 0 ;i < booking.getNumberOfNights();i++){
		
		//compruebo la arrivalDate + i
	      Calendar calendar = Calendar.getInstance();
	      calendar.setTime(arrivalDate); // Configuramos la fecha que se recibe
	      calendar.add(Calendar.DAY_OF_YEAR, i);  // numero de días a añadir, o restar en caso de días<0
	      lodgeCalendarService.cancelBooking(calendar.getTime(),booking.getNumberOfBedsRequested(),booking.getLodge().getId());
		
	}
	
}
	
	public Collection<Booking> findAllBookingByInnkeeperAndDate(Date date){
		Innkeeper innkeeper;
		
		innkeeper=innkeeperService.findByPrincipal();
		
		Assert.notNull(innkeeper);
		
		Collection<Booking> result;
		UserAccount userAccount;
		Integer userAccountId;
		
		userAccount=innkeeper.getUserAccount();
		userAccountId=userAccount.getId();
		result=bookingRepository.findAllBookingByInnkeeperAndDate(userAccountId, date);
		
		return result;
	}

}
