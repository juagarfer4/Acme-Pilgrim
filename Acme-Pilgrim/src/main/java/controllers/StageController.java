package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Route;
import domain.Stage;

import services.RouteService;
import services.StageService;

@Controller
@RequestMapping("/stage")
public class StageController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private RouteService routeService;

	// Constructors ----------------------------------------------------------

	public StageController() {
		super();
	}

	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int routeId) {
		ModelAndView result;
		Collection<Stage> stages;
		Collection<Route> routes;
			
		stages = stageService.findAllStagesByRoute(routeId);
		routes = routeService.findAll();

		result = new ModelAndView("stage/list");
		result.addObject("stages", stages);
		result.addObject("routes",routes);
		result.addObject("requestURI", "stage/list.do");

		return result;
	}
	
	// Creation ---------------------------------------------------------------
	
	// Edition ---------------------------------------------------------------
	
	// Ancillary methods ----------------------------------------------------

}
