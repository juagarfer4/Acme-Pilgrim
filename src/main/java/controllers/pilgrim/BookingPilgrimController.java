package controllers.pilgrim;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BookingService;

import controllers.AbstractController;
import domain.Booking;

@Controller
@RequestMapping("/booking/pilgrim")
public class BookingPilgrimController extends AbstractController {

	// Services ----------------------------------------------

	@Autowired
	private BookingService bookingService;

	// Constructors ------------------------------------------

	public BookingPilgrimController() {
		super();
	}

	// Listing -----------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Booking> bookings;
		Boolean edit;
		Collection<Boolean> sees;

		bookings = bookingService.findAllNotCancelledBookingsByPilgrim();
		edit=true;
		sees=new ArrayList<Boolean>();
		
		for(Booking b:bookings){
			if(b.getLodgeAssessment()!=null){
				sees.add(true);
			}else{
				sees.add(false);
			}
		}
		
		result = new ModelAndView("booking/pilgrim/list");
		result.addObject("bookings", bookings);
		result.addObject("edit", edit);
		result.addObject("sees", sees);
		result.addObject("requestURI", "booking/pilgrim/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listtoassess", method = RequestMethod.GET)
	public ModelAndView listtoassess() {
		ModelAndView result;
		Collection<Booking> bookings;
		Boolean assess;

		bookings = bookingService.findAllBookingsByPilgrimWithoutAssessment();
		assess=true;
		
		result = new ModelAndView("booking/pilgrim/listtoassess");
		result.addObject("bookings", bookings);
		result.addObject("assess", assess);
		result.addObject("requestURI", "booking/pilgrim/listtoassess.do");

		return result;
	}

	// Creation -----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer lodgeId) {
		ModelAndView result;
		Booking booking;

		booking = bookingService.create(lodgeId);

		result = new ModelAndView("booking/pilgrim/edit");
		result.addObject("booking", booking);
		result.addObject("actionURI", "booking/pilgrim/edit.do");

		return result;
	}

	// Edition ---------------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int bookingId){
		ModelAndView result;
		Booking booking;
			
		booking = bookingService.findOne(bookingId);
		result = createEditModelAndView(booking);
			
		return result;
			
	}
	
	@RequestMapping(value = "/editbeds", method = RequestMethod.GET)
	public ModelAndView editBeds(@RequestParam int bookingId){
		ModelAndView result;
		Booking booking;
			
		booking = bookingService.findOne(bookingId);
		result = createEditModelAndView2(booking);
			
		return result;
			
	}
	
	@RequestMapping(value = "/editnights", method = RequestMethod.GET)
	public ModelAndView editNights(@RequestParam int bookingId){
		ModelAndView result;
		Booking booking;
			
		booking = bookingService.findOne(bookingId);
		result = createEditModelAndView3(booking);
			
		return result;
			
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Booking booking, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(booking);
		} else {
			try {
				bookingService.save(booking);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(booking, "booking.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}

		result.addObject("actionURI", "booking/pilgrim/edit.do");
		return result;

	}
	
	@RequestMapping(value = "/editbeds", method = RequestMethod.POST, params = "save")
	public ModelAndView save2(@Valid Booking booking, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView2(booking);
		} else {
			try {
				bookingService.save(booking);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView2(booking, "booking.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}

		result.addObject("actionURI", "booking/pilgrim/editbeds.do");
		return result;

	}
	
	@RequestMapping(value = "/editnights", method = RequestMethod.POST, params = "save")
	public ModelAndView save3(@Valid Booking booking, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView3(booking);
		} else {
			try {
				bookingService.save(booking);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView3(booking, "booking.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}

		result.addObject("actionURI", "booking/pilgrim/editnights.do");
		return result;

	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Booking booking, BindingResult binding){
		ModelAndView result;
		
		try{
			bookingService.cancel(booking);
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(booking, "booking.commit.error");
		}
			
		return result;
	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(Booking booking) {
		ModelAndView result;

		result = createEditModelAndView(booking, null);

		return result;

	}

	public ModelAndView createEditModelAndView(Booking booking, String message) {
		ModelAndView result;

		result = new ModelAndView("booking/pilgrim/edit");
		result.addObject("booking", booking);
		result.addObject("message", message);
		return result;

	}
	
	public ModelAndView createEditModelAndView2(Booking booking) {
		ModelAndView result;

		result = createEditModelAndView2(booking, null);

		return result;

	}
	
	public ModelAndView createEditModelAndView2(Booking booking, String message) {
		ModelAndView result;

		result = new ModelAndView("booking/pilgrim/editbeds");
		result.addObject("booking", booking);
		result.addObject("message", message);
		return result;

	}
	
	public ModelAndView createEditModelAndView3(Booking booking) {
		ModelAndView result;

		result = createEditModelAndView3(booking, null);

		return result;

	}
	
	public ModelAndView createEditModelAndView3(Booking booking, String message) {
		ModelAndView result;

		result = new ModelAndView("booking/pilgrim/editnights");
		result.addObject("booking", booking);
		result.addObject("message", message);
		return result;

	}

}
