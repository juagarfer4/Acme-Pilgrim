package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Route;

import services.RouteService;

@Controller
@RequestMapping("/route")
public class RouteController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private RouteService routeService;

	// Constructors ----------------------------------------------------------

	public RouteController() {
		super();
	}

	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Route> routes;
			
		routes = routeService.findAllRoutesThatAreNotCancelled();

		result = new ModelAndView("route/list");
		result.addObject("routes", routes);
		result.addObject("requestURI", "route/list.do");

		return result;
	}
	
	// Creation --------------------------------------------------------------
	
	// Edition --------------------------------------------------------------
	
	// Ancillary methods ----------------------------------------------------

}
