package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.BookingRepository;

import domain.Booking;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BookingServiceNegativeTest extends AbstractTest{
	
	// Service under test -------------------------------------------------
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private BookingRepository bookingRepository;
	
	// Tests --------------------------------------------------------------
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeFindAllBookingByInnkeeperAndDate(){
		authenticate("innkeeper1");
		
		Collection<Booking> bookings;
		Date date;
		Integer size;
		
		date = new Date(2015 - 1900, 5 - 1, 5);
		bookings=bookingService.findAllBookingByInnkeeperAndDate(date);
		size=bookings.size();
		
		Assert.isTrue(size==6);
		
		unauthenticate();
	}
	
	// Cancelar una factura con menos de un día de antelación. ---------------
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeCancelBooking(){
		authenticate("pilgrim1");
		
		Booking booking;
		
		booking=bookingService.findOne(53);
		
		bookingService.cancel(booking);
		
		booking.setIsCancelled(true);
		
		authenticate(null);
	}
	
	// Crear una reserva con más camas de las que dispone el alojamiento. ---------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeCreateBooking(){
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
		numberOfBedsRequested=600;
		
		booking.setArrivalDate(arrivalDate);
		booking.setNumberOfNights(numberOfNights);
		booking.setNumberOfBedsRequested(numberOfBedsRequested);
		
		bookingService.save(booking);
		
		unauthenticate();
	}
	
	// Ver las reservas de un peregrino y no devuelve el numero correcto de entradas-------------------
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testNegativeBookingHistory1(){
		authenticate("pilgrim1");
		
		Collection<Booking> booking;
		Integer size;
		
		booking=bookingService.findAllBookingsByPilgrim();
		size=booking.size();
		
		Assert.isTrue(size==7);
		
		unauthenticate();
	}
	
	// Editar una reserva distinta a la mía. ---------------------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeEditBedsBooking(){
		authenticate("pilgrim1");
		
		Booking booking;
		Integer numberOfBedsRequested;
		
		booking=bookingService.findOne(51);
		numberOfBedsRequested=1;
		
		booking.setNumberOfBedsRequested(numberOfBedsRequested);
		
		bookingService.saveEdit2(booking);
		
		unauthenticate();
	}
	
	// Poner a una reserva un número negativo de camas. -----------------------
	
	@Test(expected=java.lang.IllegalArgumentException.class)
	public void testNegativeEditNightsBooking(){
		authenticate("pilgrim2");
		
		Booking booking;
		Integer numberOfNights;
		
		booking=bookingService.findOne(48);
		numberOfNights=-1;
		
		booking.setNumberOfNights(numberOfNights);
		
		bookingService.saveEdit3(booking);
		
		unauthenticate();
	}


}
