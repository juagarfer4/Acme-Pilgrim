/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Innkeeper;
import domain.Lodge;
import domain.Pilgrim;
import domain.Route;
import domain.Stage;

import services.InnkeeperService;
import services.LodgeService;
import services.PilgrimService;
import services.RouteService;
import services.StageService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {
	
	// Services -----------------------------------------------------
	
	@Autowired
	private RouteService routeService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private InnkeeperService innkeeperService;
	
	@Autowired
	private LodgeService lodgeService;
	
	@Autowired
	private StageService stageService;
	
	
	

	// Constructors -----------------------------------------------------------
	
	public AdministratorController() {
		super();
	}
		
	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		Collection<Route> routes1;
		Collection<Pilgrim> pilgrims1;
		Collection<Route> routes2;
		Collection<Innkeeper> innkeepers1;
		Collection<Lodge> lodges1;
		Collection<Stage> stages1;
		Collection<Pilgrim> pilgrims4;
			
		routes1 = routeService.findAllNotDeletedRoutesInDescendingOrderOfRegistrations();
		pilgrims1 = pilgrimService.findAllPilgrimsInDescendingOrderOfRegistrations();
		routes2 = routeService.findAllRoutesDeleted();
		innkeepers1 = innkeeperService.findAllInnkeeperOrderedDescendingNumberOfLodges();
		lodges1 = lodgeService.findAllLodgesOrderedByDescendingNumberOfBooking();
		stages1 = stageService.findAllStagesOrderedByAscendingAverageRating();
		pilgrims4 = pilgrimService.findAllPilgrimsOrderedByDescendigBirthDate();
			
		result=new ModelAndView("administrator/dashboard");
		result.addObject("routes1", routes1);
		result.addObject("pilgrims1", pilgrims1);
		result.addObject("routes2", routes2);
		result.addObject("innkeepers1", innkeepers1);
		result.addObject("lodges1", lodges1);
		result.addObject("stages1", stages1);
		result.addObject("pilgrims4", pilgrims4);
		result.addObject("requestURI", "administrator/dashboard.do");
		
		return result;
	}
		
	// Creation --------------------------------------------
		
	// Edition -------------------------------------------
			
	// Ancillary methods -----------------------------------
	
}