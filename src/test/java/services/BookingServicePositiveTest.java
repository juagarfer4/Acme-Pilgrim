package services;

import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Booking;
import domain.Lodge;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class BookingServicePositiveTest extends AbstractTest{
	
	// Service under test -------------------------------------------------
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private LodgeService lodgeService;
	
	// Tests --------------------------------------------------------------
	
	@Test
	public void testPositiveFindAllBookingByInnkeeperAndDate(){
		authenticate("innkeeper1");
		
		Collection<Booking> bookings;
		Date date;
		
		date = new Date(2015 - 1900, 5 - 1, 5);
		bookings=bookingService.findAllBookingByInnkeeperAndDate(date);
		
		System.out.println(bookings);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveCancelBooking(){
		authenticate("pilgrim1");
		
		Booking booking;
		
		booking=bookingService.findOne(48);
		
		bookingService.cancel(booking);
		
		booking.setIsCancelled(true);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveCreateBooking(){
		authenticate("pilgrim2");
		
		Booking booking;
		Integer lodgeId;
		Date arrivalDate;
		Integer numberOfNights;
		Integer numberOfBedsRequested;
		
		lodgeId=38;
		booking=bookingService.create(lodgeId);
		arrivalDate = new Date(2016 - 1900, 5 - 1, 5);
		numberOfNights=3;
		numberOfBedsRequested=4;
		
		booking.setArrivalDate(arrivalDate);
		booking.setNumberOfNights(numberOfNights);
		booking.setNumberOfBedsRequested(numberOfBedsRequested);
		
		bookingService.save(booking);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveBookingHistory(){
		authenticate("pilgrim1");
		
		Collection<Booking> booking;
		Integer size;
		
		booking=bookingService.findAllBookingsByPilgrim();
		size=booking.size();
		
		Assert.isTrue(size==3);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveEditBedsBooking(){
		authenticate("pilgrim1");
		
		Booking booking;
		Integer numberOfBedsRequested;
		
		booking=bookingService.findOne(48);
		numberOfBedsRequested=1;
		
		booking.setNumberOfBedsRequested(numberOfBedsRequested);
		
		bookingService.saveEdit2(booking);
		
		unauthenticate();
	}
	
	@Test
	public void testPositiveEditNightsBooking(){
		authenticate("pilgrim1");
		
		Booking booking;
		Integer numberOfNights;
		
		booking=bookingService.findOne(48);
		numberOfNights=1;
		
		booking.setNumberOfNights(numberOfNights);
		
		bookingService.saveEdit3(booking);
		
		unauthenticate();
	}

}
