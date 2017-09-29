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

import services.RouteService;

import controllers.AbstractController;
import domain.Route;

@Controller
@RequestMapping("/route/administrator")
public class RouteAdministratorController extends AbstractController{
    
    // Services --------------------------------------------
    
    @Autowired
    private RouteService routeService;
    
    // Constructors ----------------------------------------
        
    public RouteAdministratorController(){
            super();
    }
        
    // Listing ---------------------------------------------
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ModelAndView list(@RequestParam int pilgrimId) {
        ModelAndView result;
        Collection<Route> routes;
        
        routes = routeService.findAllNotDeletedRoutesByPilgrim(pilgrimId);

        result = new ModelAndView("route/administrator/list");
        result.addObject("routes", routes);
        result.addObject("requestURI", "route/administrator/list.do");

        return result;
    }
    
    @RequestMapping(value = "/listtodelete", method = RequestMethod.GET)
    public ModelAndView listtodelete(){
        ModelAndView result;
        Collection<Route> routes;
                        
        routes=routeService.findAllRoutesToDelete();
                        
        result=new ModelAndView("route/administrator/listtodelete");
        result.addObject("routes", routes);
        result.addObject("requestURI", "route/administrator/listtodelete.do");
                        
        return result;
    }
        
    // Creation ---------------------------------------------
        
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(){
        ModelAndView result;
        Route route;
                
        route = routeService.create();
                
        result = new ModelAndView("route/administrator/edit");
        result.addObject("route", route);
        result.addObject("actionURI", "route/administrator/edit.do");
                
        return result;
    }
        
    // Edition ------------------------------------------------4
        
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public ModelAndView edit(@RequestParam int routeId){
        ModelAndView result;
        Route route;
            
        route=routeService.findOne(routeId);
        result = createEditModelAndView(route);
                
        return result;
                
    }
        
    @RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
    public ModelAndView save(@Valid Route route, BindingResult binding){
        ModelAndView result;
                
        if(binding.hasErrors()){
            System.out.println(binding.getAllErrors().get(0));
            result=createEditModelAndView(route);
        }else{
            try{
                routeService.save(route);
                result=new ModelAndView("redirect:/");
            }catch(Throwable oops){
                result=createEditModelAndView(route, "route.commit.error");
                System.out.println(oops.getLocalizedMessage());
            }
        }
                        
        result.addObject("actionURI", "route/administrator/edit.do");
        return result;
                
    }
    
    @RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Route route, BindingResult binding){
		ModelAndView result;
		
		try{
			routeService.deleteRoute(route);
			result = new ModelAndView("redirect:listtodelete.do");
		}catch(Throwable oops){
			result = createEditModelAndView(route, "route.commit.error");
		}
			
		return result;
	}
    
    // Ancillary methods --------------------------------------------------
        
    public ModelAndView createEditModelAndView(Route route){
        ModelAndView result;
            
        result=createEditModelAndView(route, null);
                    
        return result;
                
    }
                    
    public ModelAndView createEditModelAndView(Route route, String message){
        ModelAndView result;
            
        result=new ModelAndView("route/administrator/edit");
        result.addObject("route", route);
        result.addObject("message", message);
        return result;
                    
    }

}