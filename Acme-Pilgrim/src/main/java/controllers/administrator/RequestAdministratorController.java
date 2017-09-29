package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.RequestService;

import controllers.AbstractController;
import domain.Administrator;
import domain.Request;

@Controller
@RequestMapping("/request/administrator")
public class RequestAdministratorController extends AbstractController{
	
	// Services -----------------------------------------------------
	
	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AdministratorService administratorService;
	
	// Constructors -------------------------------------------------
	
	public RequestAdministratorController(){
		super();
	}
	
	// Listing ------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Request> requests;
					
		requests=requestService.findAllRequestsByAdministratorToManage();
					
		result=new ModelAndView("request/administrator/list");
		result.addObject("requests", requests);
		result.addObject("requestURI", "request/administrator/list.do");
					
		return result;
	}
	
	// Creation ---------------------------------------------------------
	
	// Edition -----------------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requestId){
		ModelAndView result;
		Request request;				

		request = requestService.findOne(requestId);
		request.setAdministrator(administratorService.findByPrincipal());
		administratorService.findByPrincipal().getRequests().add(request);
		
		Assert.notNull(request);

		result = createEditModelAndView(request);
		

		return result;
	}
				
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Request request, BindingResult binding){
		ModelAndView result;
	
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(request);
		}else{
			try{
				requestService.saveAdministrator(request);
				result=new ModelAndView("redirect:list.do");
			}catch (Throwable oops){
				result=createEditModelAndView(request, "request.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
					
		result.addObject("actionURI", "request/administrator/edit.do");
					
		return result;
	}
	
	// Ancillary methods --------------------------------------------------
	
	protected ModelAndView createEditModelAndView(Request request){
		ModelAndView result;
					
		result=createEditModelAndView(request, null);
					
		return result;
	}
				
	protected ModelAndView createEditModelAndView(Request request, String message){
		ModelAndView result;
		Administrator administrator;
		
		if(request.getAdministrator()==null){
			administrator=null;
		}else{
			administrator=request.getAdministrator();
		}
					
		result=new ModelAndView("request/administrator/edit");
		result.addObject("request", request);
		result.addObject("administrator",administrator);
		result.addObject("message", message);
					
		return result;
	}

}
