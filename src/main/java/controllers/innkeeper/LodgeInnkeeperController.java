package controllers.innkeeper;

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
import services.LodgeService;
import services.StageService;

import controllers.AbstractController;
import domain.Lodge;
import domain.GPS;
import domain.Stage;

@Controller
@RequestMapping("/lodge/innkeeper")
public class LodgeInnkeeperController extends AbstractController{
	
	// Services --------------------------------------------
	
	@Autowired
	private LodgeService lodgeService;
	
	@Autowired
	private StageService stageService;
	
	@Autowired
	private GPSService gPSService;
	
	// Constructors ----------------------------------------
	
	public LodgeInnkeeperController(){
			super();
	}
	
	// Listing ---------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(){
		ModelAndView result;
		Collection<Lodge> lodges;
		Boolean myLodges;
						
		lodges=lodgeService.findAllLodgesByInnkeeper();
		myLodges=true;
						
		result=new ModelAndView("lodge/innkeeper/list");
		result.addObject("lodges", lodges);
		result.addObject("myLodges", myLodges);
		result.addObject("requestURI", "lodge/innkeeper/list.do");
						
		return result;
	}
	
	@RequestMapping(value = "/listtodelete", method = RequestMethod.GET)
	public ModelAndView listtodelete(){
		ModelAndView result;
		Collection<Lodge> lodges;
		Boolean myLodges;
						
		lodges=lodgeService.findAllLodgesToDelete();
		myLodges=true;
						
		result=new ModelAndView("lodge/innkeeper/listtodelete");
		result.addObject("lodges", lodges);
		result.addObject("myLodges", myLodges);
		result.addObject("requestURI", "lodge/innkeeper/listtodelete.do");
						
		return result;
	}
	
	// Creation ---------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(){
		ModelAndView result;
		Lodge lodge;
		Collection<GPS> gPSs;
		Collection<Stage> stages;
			
		lodge = lodgeService.create();
		gPSs=gPSService.findAll();
		stages=stageService.findAll();
			
		result = new ModelAndView("lodge/innkeeper/edit");
		result.addObject("lodge", lodge);
		result.addObject("gPSs", gPSs);
		result.addObject("stages",stages);
		result.addObject("actionURI", "lodge/innkeeper/edit.do");
			
		return result;
	}
	
	// Edition ------------------------------------------------4
	
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int lodgeId){
		ModelAndView result;
		Lodge lodge;
		//
//		GPS coordinates;
//		Stage stage;
//		
		lodge=lodgeService.findOne(lodgeId);
//		coordinates=lodge.getCoordinates();
//		stage=lodge.getStage();
//		
//		lodge.setCoordinates(coordinates);
//		lodge.setStage(stage);
		
		result = createEditModelAndView(lodge);
			
		return result;
			
	}
	
	@RequestMapping(value="/edit", method=RequestMethod.POST, params="save")
	public ModelAndView save(@Valid Lodge lodge, BindingResult binding){
		ModelAndView result;
			
		if(binding.hasErrors()){
			System.out.println(binding.getAllErrors().get(0));
			result=createEditModelAndView(lodge);
		}else{
			try{
				lodgeService.save(lodge);
				result=new ModelAndView("redirect:list.do");
			}catch(Throwable oops){
				result=createEditModelAndView(lodge, "lodge.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}
					
		result.addObject("actionURI", "lodge/innkeeper/edit.do");
		return result;
				
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(Lodge lodge, BindingResult binding){
		ModelAndView result;
		
		try{
			lodgeService.delete(lodge);//---------------------
			result = new ModelAndView("redirect:list.do");
		}catch(Throwable oops){
			result = createEditModelAndView(lodge, "lodge.commit.error");
		}
			
		return result;
	}
	
	// Ancillary methods --------------------------------------------------
	
	public ModelAndView createEditModelAndView(Lodge lodge){
		ModelAndView result;
			
		result=createEditModelAndView(lodge, null);
					
		return result;
			
	}
				
	public ModelAndView createEditModelAndView(Lodge lodge, String message){
		ModelAndView result;
		Collection<GPS> gPSs;
		Collection<Stage> stages;
		GPS coordinates;
		Stage stage;
		
		gPSs=gPSService.findAll();
		stages=stageService.findAll();
		
		if(lodge.getCoordinates()==null){
			coordinates=null;
		}else{
			coordinates=lodge.getCoordinates();
		}
		
		if(lodge.getStage()==null){
			stage=null;
		}else{
			stage=lodge.getStage();
		}
					
		result=new ModelAndView("lodge/innkeeper/edit");
		result.addObject("lodge", lodge);
		result.addObject("gPSs", gPSs);
		result.addObject("stages", stages);
		result.addObject("coordinates", coordinates);
		result.addObject("stage", stage);
		result.addObject("message", message);
		return result;
					
	}

}
