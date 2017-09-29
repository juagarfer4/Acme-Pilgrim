package controllers.pilgrim;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RegistrationService;

import controllers.AbstractController;
import domain.Registration;

@Controller
@RequestMapping("/registration/pilgrim")
public class RegistrationPilgrimController extends AbstractController{
	
	// Services --------------------------------------------------
	
	@Autowired
	private RegistrationService registrationService;
	
	// Constructors ----------------------------------------------
	
	public RegistrationPilgrimController(){
		super();
	}
	
	// Listing ----------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Registration> registration;
				
		registration=registrationService.findAllRegistrationsByPilgrim();
				
		result=new ModelAndView("registration/pilgrim/list");
		result.addObject("registrations", registration);
		result.addObject("requestURI", "registration/pilgrim/list.do");
				
		return result;
	}
	
	// Creation ----------------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer routeId){
		ModelAndView result;
		Registration registration;
		
		registration = registrationService.create(routeId);
				
		result = new ModelAndView("registration/pilgrim/edit");
		result.addObject("registration", registration);
		result.addObject("actionURI", "registration/pilgrim/edit.do");
				
		return result;
	}
	
	// Edition ---------------------------------------------------------------
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Registration registration, BindingResult binding){
		ModelAndView result;
			
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(registration);
		}else{
			try{
				registrationService.save(registration);
				result=new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result=createEditModelAndView(registration, "registration.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
					
		result.addObject("actionURI", "registration/pilgrim/edit.do");
		return result;
				
	}
	
	// Ancillary methods --------------------------------------------------
	
	public ModelAndView createEditModelAndView(Registration registration){
		ModelAndView result;
		
		result=createEditModelAndView(registration, null);
				
		return result;
		
	}
			
	public ModelAndView createEditModelAndView(Registration registration, String message){
		ModelAndView result;
				
		result=new ModelAndView("registration/pilgrim/edit");
		result.addObject("registration", registration);
		result.addObject("message", message);
		return result;
				
	}

}