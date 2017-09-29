package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Booking;
import domain.Innkeeper;
import domain.Lodge;
import domain.LodgeAssessment;
import domain.Pilgrim;
import domain.Route;
import domain.Stage;
import forms.PilgrimForm;

import services.BookingService;
import services.LodgeAssessmentService;
import services.LodgeService;
import services.PilgrimService;
import services.RegistrationService;
import services.RouteService;
import services.StageService;

@Controller
@RequestMapping("/pilgrim")
public class PilgrimController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private LodgeAssessmentService lodgeAssessmentService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private LodgeService lodgeService;
	
	@Autowired
	private PilgrimService pilgrimService;

	// Constructors -----------------------------------------------------------

	public PilgrimController() {
		super();
	}
		
	// Listing ----------------------------------------------------------
	
	// Creation -----------------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		PilgrimForm pilgrimForm;
			
		pilgrimForm = new PilgrimForm();
			
		result = new ModelAndView("pilgrim/edit");
		result.addObject("pilgrimForm", pilgrimForm);
		result.addObject("actionURI", "pilgrim/edit.do");
			
		return result;
	}
	
	// Edition ---------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid PilgrimForm pilgrimForm, BindingResult binding){
		ModelAndView result;
		Pilgrim pilgrim;
		
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(pilgrimForm);
		}else{
			try{
				pilgrim=pilgrimService.reconstruct(pilgrimForm);
				pilgrimService.save(pilgrim);
				result=new ModelAndView("redirect:/security/login.do");
			}catch(Throwable oops){
				result=createEditModelAndView(pilgrimForm, "pilgrim.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}
				
		return result;
					
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		Collection<Booking> bookings;
		Collection<LodgeAssessment> lodgeAssessment;
		Collection<Route> routes;
		Collection<Stage> stages1;
		Collection<Stage> stages2;
		Collection<Lodge> lodges;
		Collection<Pilgrim> pilgrims;
		
		bookings = bookingService.findAllBookingsByPilgrim();
		lodgeAssessment = lodgeAssessmentService.findAllLodgeAssessmenrByPilgrim();
		routes = registrationService.findAllRoutesByPilgrim();
		stages1 = stageService.findAllStagesHistoryByPilgrim();
		stages2 = stageService.findAllPendingStageByPilgrim();
		lodges = lodgeService.findAllLodgesByPilgrim() ;
		pilgrims = pilgrimService.findAllPilgrimsOrderedByDescendigBirthDate();
		
			
		result=new ModelAndView("pilgrim/dashboard");
		result.addObject("bookings", bookings);
		result.addObject("lodgeAssessment", lodgeAssessment);
		result.addObject("routes", routes);
		result.addObject("stages1", stages1);
		result.addObject("stages2", stages2);
		result.addObject("lodges", lodges);
		result.addObject("pilgrims", pilgrims);
		result.addObject("requestURI", "pilgrim/dashboard.do");
		
		return result;
	}
	
	// Ancillary methods ---------------------------------------------------------------
	
	public ModelAndView createEditModelAndView(PilgrimForm pilgrimForm){
		ModelAndView result;
			
		result=createEditModelAndView(pilgrimForm, null);
				
		return result;
				
	}
			
	public ModelAndView createEditModelAndView(PilgrimForm pilgrimForm, String message){
		ModelAndView result;
		
				
		result=new ModelAndView("pilgrim/edit");
		result.addObject("pilgrimForm", pilgrimForm);
		result.addObject("message", message);
				
		return result;
				
	}

}
