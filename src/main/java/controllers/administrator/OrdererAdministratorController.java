package controllers.administrator;


import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.OrdererService;
import services.RouteService;

import controllers.AbstractController;
import domain.Orderer;
import domain.Route;

@Controller
@RequestMapping("/orderer/administrator")
public class OrdererAdministratorController extends AbstractController{
	
	// Services --------------------------------------------
	
	@Autowired
	private OrdererService ordererService;
	
	@Autowired
	private RouteService routeService;
		
	// Constructors ----------------------------------------
		
	public OrdererAdministratorController(){
			super();
	}
		
	// Listing ---------------------------------------------
		
	// Creation ---------------------------------------------
		
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int stageId){
		ModelAndView result;
		Orderer orderer;
		Collection<Route> routes;
				
		orderer = ordererService.create(stageId);
		routes = routeService.findAll();
		
				
		result = new ModelAndView("orderer/administrator/edit");
		result.addObject("orderer", orderer);
		result.addObject("routes", routes);
		result.addObject("actionURI", "orderer/administrator/edit.do");
				
		return result;
	}
		
	// Edition ------------------------------------------------4
		
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int ordererId){
		ModelAndView result;
		Orderer orderer;
			
		orderer=ordererService.findOne(ordererId);
		result = createEditModelAndView(orderer);
				
		return result;
				
	}
		
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Orderer orderer, BindingResult binding){
		ModelAndView result;
				
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(orderer);
		}else{
			try{
				ordererService.save(orderer);
				result=new ModelAndView("redirect:/stage/administrator/list.do");
			}catch(Throwable oops){
				result=createEditModelAndView(orderer, "orderer.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
						
		result.addObject("actionURI", "orderer/administrator/edit.do");
		return result;
				
	}
	
	
		
	// Ancillary methods --------------------------------------------------
		
	public ModelAndView createEditModelAndView(Orderer orderer){
		ModelAndView result;
			
		result=createEditModelAndView(orderer, null);
					
		return result;
				
	}
					
	public ModelAndView createEditModelAndView(Orderer orderer, String message){
		ModelAndView result;
		Collection<Route> routes;
		
		routes= routeService.findAll();
	
			
		result=new ModelAndView("orderer/administrator/edit");
		result.addObject("orderer", orderer);
		result.addObject("routes", routes);
		result.addObject("message", message);
		return result;
					
	}

}
