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
import services.LodgeAssessmentService;

import controllers.AbstractController;
import domain.Booking;
import domain.LodgeAssessment;

@Controller
@RequestMapping("/lodgeassessment/pilgrim")
public class LodgeAssessmentPilgrimController extends AbstractController {

	// Services ----------------------------------------------

	@Autowired
	private LodgeAssessmentService lodgeAssessmentService;
	
	@Autowired
	private BookingService bookingService;

	// Constructors ------------------------------------------

	public LodgeAssessmentPilgrimController() {
		super();
	}

	// Listing -----------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Integer bookingId) {
		ModelAndView result;
		Booking booking;
		LodgeAssessment lodgeAssessment;
		Collection<LodgeAssessment> lodgeAssessments;
		
		booking=bookingService.findOne(bookingId);
		lodgeAssessment=booking.getLodgeAssessment();
		lodgeAssessments=new ArrayList<LodgeAssessment>();
		
		lodgeAssessments.add(lodgeAssessment);
		
		result = new ModelAndView("lodgeassessment/pilgrim/list");
		result.addObject("lodgeAssessments", lodgeAssessments);
		result.addObject("requestURI", "lodgeassessment/pilgrim/list.do");

		return result;
	}

	// Creation ----------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer bookingId) {
		ModelAndView result;
		LodgeAssessment lodgeAssessment;

		lodgeAssessment = lodgeAssessmentService.create(bookingId);

		result = new ModelAndView("lodgeassessment/pilgrim/edit");
		result.addObject("lodgeAssessment", lodgeAssessment);
		result.addObject("actionURI", "lodgeassessment/pilgrim/edit.do");

		return result;
	}

	// Edition ---------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LodgeAssessment lodgeAssessment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(lodgeAssessment);
		} else {
			try {
				Booking booking;
				booking = lodgeAssessment.getBooking();
				booking.setLodgeAssessment(lodgeAssessment);
				lodgeAssessmentService.save(lodgeAssessment);
				result = new ModelAndView(
						"redirect:/booking/pilgrim/listtoassess.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(lodgeAssessment,
						"lodgeassessment.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}

		result.addObject("actionURI", "lodgeassessment/pilgrim/edit.do");
		return result;
	}

	// Ancillary methods -----------------------------------------------

	public ModelAndView createEditModelAndView(LodgeAssessment lodgeAssessment) {
		ModelAndView result;

		result = createEditModelAndView(lodgeAssessment, null);

		return result;
	}

	public ModelAndView createEditModelAndView(LodgeAssessment lodgeAssessment,
			String message) {
		ModelAndView result;

		result = new ModelAndView("lodgeassessment/pilgrim/edit");
		result.addObject("lodgeAssessment", lodgeAssessment);
		result.addObject("message", message);

		return result;
	}

}
