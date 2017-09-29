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

import services.GPSService;
import services.StageService;

import controllers.AbstractController;
import domain.Stage;
import domain.GPS;

@Controller
@RequestMapping("/stage/administrator")
public class StageAdministratorController extends AbstractController{
	
	// Services --------------------------------------------
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private GPSService gPSService;
	
	// Constructors ----------------------------------------
	
	public StageAdministratorController(){
			super();
	}
	
	// Listing ---------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Stage> stages;
						
		stages=stageService.findAll();
						
		result=new ModelAndView("stage/administrator/list");
		result.addObject("stages", stages);
		result.addObject("requestURI", "stage/administrator/list.do");
						
		return result;
	}
	
	@RequestMapping(value = "/listtodelete", method = RequestMethod.GET)
	public ModelAndView listtodelete(){
		ModelAndView result;
		Collection<Stage> stages;
						
		stages=stageService.findAllStagesThatCanBeDeleted();
						
		result=new ModelAndView("stage/administrator/listtodelete");
		result.addObject("stages", stages);
		result.addObject("requestURI", "stage/administrator/listtodelete.do");
						
		return result;
	}
	
	// Creation ---------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Stage stage;
		Collection<GPS> gPSs;
			
		stage = stageService.create();
		gPSs=gPSService.findAll();
			
		result = new ModelAndView("stage/administrator/edit");
		result.addObject("stage", stage);
		result.addObject("gPSs", gPSs);
		result.addObject("actionURI", "stage/administrator/edit.do");
			
		return result;
	}
	
	// Edition ------------------------------------------------4
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int stageId){
		ModelAndView result;
		Stage stage;
		GPS sourceLocation;
		GPS destinationLocation;
		
		stage=stageService.findOne(stageId);
		sourceLocation=stage.getSourceLocation();
		destinationLocation=stage.getDestinationLocation();
		
		stage.setSourceLocation(sourceLocation);
		stage.setDestinationLocation(destinationLocation);
		
		result = createEditModelAndView(stage);
			
		return result;
			
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Stage stage, BindingResult binding){
		ModelAndView result;
			
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(stage);
		}else{
			try{
				stageService.save(stage);
				result=new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result=createEditModelAndView(stage, "stage.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
					
		result.addObject("actionURI", "stage/administrator/edit.do");
		return result;
				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Stage stage, BindingResult binding){
		ModelAndView result;
		
		try{
			stageService.delete(stage);
			result = new ModelAndView("redirect:listtodelete.do");
		}catch(Throwable oops){
			result = createEditModelAndView(stage, "stage.commit.error");
		}
			
		return result;
	}
	
	// Ancillary methods --------------------------------------------------
	
	public ModelAndView createEditModelAndView(Stage stage){
		ModelAndView result;
			
		result=createEditModelAndView(stage, null);
					
		return result;
			
	}
				
	public ModelAndView createEditModelAndView(Stage stage, String message){
		ModelAndView result;
		Collection<GPS> gPSs;
		GPS sourceLocation;
		GPS destinationLocation;
		
		gPSs=gPSService.findAll();
		
		if(stage.getSourceLocation()==null){
			sourceLocation=null;
		}else{
			sourceLocation=stage.getSourceLocation();
		}
		
		if(stage.getDestinationLocation()==null){
			destinationLocation=null;
		}else{
			destinationLocation=stage.getDestinationLocation();
		}
					
		result=new ModelAndView("stage/administrator/edit");
		result.addObject("stage", stage);
		result.addObject("gPSs", gPSs);
		result.addObject("sourceLocation", sourceLocation);
		result.addObject("destinationLocation", destinationLocation);
		result.addObject("message", message);
		return result;
					
	}

}
