package controllers.pilgrim;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;

import controllers.AbstractController;
import domain.Assessment;
import domain.StageInstance;

@Controller
@RequestMapping("/assessment/pilgrim")
public class AssessmentPilgrimController extends AbstractController {

	// Services ----------------------------------------------

	@Autowired
	private AssessmentService assessmentService;
	
	// Constructors ------------------------------------------

	public AssessmentPilgrimController() {
		super();
	}

	// Listing -----------------------------------------------
	
	// Creation ----------------------------------------------
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer stageInstanceId) {
		ModelAndView result;
		Assessment assessment;

		assessment = assessmentService.create(stageInstanceId);

		result = new ModelAndView("assessment/pilgrim/edit");
		result.addObject("assessment", assessment);
		result.addObject("actionURI", "assessment/pilgrim/edit.do");

		return result;
	}
	
	// Edition ---------------------------------------------------
	
	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Assessment assessment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(assessment);
		} else {
			try {
				StageInstance stageInstance;
				stageInstance=assessment.getStageInstance();
				stageInstance.setAssessment(assessment);
				assessmentService.save(assessment);
				result = new ModelAndView("redirect:/stageinstance/pilgrim/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(assessment,
						"assessment.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}

		result.addObject("actionURI", "assessment/pilgrim/edit.do");
		return result;
	}
	
	// Ancillary methods -----------------------------------------------
	
	public ModelAndView createEditModelAndView(Assessment assessment) {
		ModelAndView result;

		result = createEditModelAndView(assessment, null);

		return result;
	}
	
	public ModelAndView createEditModelAndView(Assessment assessment, String message){
		ModelAndView result;
			
		result=new ModelAndView("assessment/pilgrim/edit");
		result.addObject("assessment", assessment);
		result.addObject("message", message);
		
		return result;
	}

}
