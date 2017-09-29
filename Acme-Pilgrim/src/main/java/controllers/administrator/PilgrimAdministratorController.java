package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.PilgrimService;

import controllers.AbstractController;
import domain.Pilgrim;

@Controller
@RequestMapping("/pilgrim/administrator")
public class PilgrimAdministratorController extends AbstractController{
	
	// Services --------------------------------------------
	
	@Autowired
	private PilgrimService pilgrimService;
	
	// Constructors ----------------------------------------
	
	public PilgrimAdministratorController(){
		super();
	}
	
	// Listing ---------------------------------------------
	
	@RequestMapping(value = "/search", method = RequestMethod.GET)
	public ModelAndView search(@RequestParam String keyword){
		ModelAndView result;
		Collection<Pilgrim> pilgrims;
					
		pilgrims=pilgrimService.findAllPilgrimsByKeyword(keyword);
					
		result=new ModelAndView("pilgrim/administrator/search");
		result.addObject("pilgrims", pilgrims);
		result.addObject("requestURI", "pilgrim/administrator/search.do");
					
		return result;
	}
	
	@RequestMapping(value = "/listpilgrims", method = RequestMethod.GET)
	public ModelAndView listPilgrims(){
		ModelAndView result;
		Collection<Pilgrim> pilgrims;
		Boolean showRoutes;
				
		pilgrims=pilgrimService.findAll();
		showRoutes=true;
				
		result=new ModelAndView("pilgrim/administrator/listpilgrims");
		result.addObject("pilgrims", pilgrims);
		result.addObject("showRoutes", showRoutes);
		result.addObject("requestURI", "pilgrim/administrator/listpilgrims.do");
				
		return result;
	}
	
	// Creation --------------------------------------------
	
	// Edition ---------------------------------------------
	
	// Ancillary methods -------------------------------------

}
