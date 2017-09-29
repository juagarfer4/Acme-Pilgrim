package controllers.pilgrim;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.PilgrimService;
import services.RouteService;

import controllers.AbstractController;
import domain.Pilgrim;
import domain.Route;

@Controller
@RequestMapping("/route/pilgrim")
public class RoutePilgrimController extends AbstractController{
	
	// Services -------------------------------------------------
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	// Constructors ---------------------------------------------
	
	public RoutePilgrimController(){
		super();
	}
	
	// Listing -------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Pilgrim pilgrim;
		Integer userAccountId;
		Collection<Route> routes;
		Boolean myRoutes;
				
		pilgrim=pilgrimService.findByPrincipal();
		userAccountId=pilgrim.getUserAccount().getId();
		routes=routeService.findAllNotDeletedRoutesByPilgrim(userAccountId);
		myRoutes=true;
				
		result=new ModelAndView("route/pilgrim/list");
		result.addObject("routes", routes);
		result.addObject("myRoutes", myRoutes);
		result.addObject("requestURI", "route/pilgrim/list.do");
				
		return result;
	}
	
	// Creation ----------------------------------------------
	
	// Edition ------------------------------------------------
	
	// Ancillary methods ----------------------------------------

}
