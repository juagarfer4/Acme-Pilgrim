package controllers.pilgrim;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PilgrimService;

import controllers.AbstractController;
import domain.Pilgrim;

@Controller
@RequestMapping("/pilgrim/pilgrim")
public class PilgrimPilgrimController extends AbstractController{
	
	// Services -------------------------------------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	
	// Constructors ---------------------------------------------
	
	public PilgrimPilgrimController(){
		super();
	}
	
	// Listing -------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Pilgrim> pilgrims;
		Boolean myProfile;
				
		pilgrims=pilgrimService.showPrincipal();
		myProfile=true;
				
		result=new ModelAndView("pilgrim/pilgrim/list");
		result.addObject("pilgrims", pilgrims);
		result.addObject("myProfile", myProfile);
		result.addObject("requestURI", "pilgrim/pilgrim/list.do");
				
		return result;
	}
	
	// Creation ---------------------------------------------------
	
	// Edition ---------------------------------------------------
	
	// Ancillary methods ----------------------------------------

}
