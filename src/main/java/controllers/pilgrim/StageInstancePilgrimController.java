package controllers.pilgrim;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.StageInstanceService;
import services.StageService;

import controllers.AbstractController;
import domain.StageInstance;
import domain.Registration;
import domain.Stage;

@Controller
@RequestMapping("/stageinstance/pilgrim")
public class StageInstancePilgrimController extends AbstractController {

	// Services ----------------------------------

	@Autowired
	private StageInstanceService stageInstanceService;

	@Autowired
	private StageService stageService;

	// Constructors --------------------------------

	public StageInstancePilgrimController() {
		super();
	}

	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<StageInstance> stageInstances;

		stageInstances = stageInstanceService.findAllStageInstancesWithoutAssessmentByPilgrim();
		
		result = new ModelAndView("stageinstance/pilgrim/list");
		result.addObject("stageInstances", stageInstances);
		result.addObject("requestURI", "stageinstance/pilgrim/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam Integer registrationId) {
		ModelAndView result;
		StageInstance stageInstance;
		Collection<Stage> stages;

		stageInstance = stageInstanceService.create(registrationId);
		stages = stageService.findAllStagesByRegistration(registrationId);

		result = new ModelAndView("stageinstance/pilgrim/edit");
		result.addObject("stageInstance", stageInstance);
		result.addObject("stages", stages);
		result.addObject("actionURI", "stageinstance/pilgrim/edit.do");

		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid StageInstance stageInstance, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(stageInstance);
		} else {
			try {
				stageInstanceService.save(stageInstance);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(stageInstance,
						"stageinstance.commit.error");
				System.out.println(oops.getLocalizedMessage());
			}
		}

		result.addObject("actionURI", "stageinstance/pilgrim/edit.do");
		return result;

	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(StageInstance stageInstance) {
		ModelAndView result;

		result = createEditModelAndView(stageInstance, null);

		return result;

	}

	public ModelAndView createEditModelAndView(StageInstance stageInstance, String message) {
		ModelAndView result;
		Registration registration;
		Integer registrationId;
		Collection<Stage> stages;

		registration=stageInstance.getRegistration();
		registrationId=registration.getId();
		stages = stageService.findAllStagesByRegistration(registrationId);

		result = new ModelAndView("stageinstance/pilgrim/edit");
		result.addObject("stageInstance", stageInstance);
		result.addObject("stages", stages);
		result.addObject("message", message);
		return result;

	}

}
