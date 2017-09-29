package controllers.innkeeper;

import java.util.Collection;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;

import controllers.AbstractController;
import domain.Booking;

@Controller
@RequestMapping("/booking/innkeeper")
public class BookingInnkeeperController extends AbstractController{
	
	// Services -------------------------------------------------
	
	@Autowired
	private BookingService bookingService;
	
	// Constructors ---------------------------------------------
	
	public BookingInnkeeperController(){
		super();
	}
	
	// Listing ------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
					
		result=new ModelAndView("booking/innkeeper/list");
		result.addObject("requestURI", "booking/innkeeper/list.do");
					
		return result;
	}
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam Date date){
		ModelAndView result;
		Collection<Booking> bookings;
					
		bookings=bookingService.findAllBookingByInnkeeperAndDate(date);
					
		result=new ModelAndView("booking/innkeeper/search");
		//result.addObject("bookings", bookings);
		result.addObject("requestURI", "booking/innkeeper/search.do");
					
		return result;
	}
	
	// Creation ----------------------------------------------
	
	// Edition ---------------------------------------------
	
	// Ancillary methods -----------------------------------

}
