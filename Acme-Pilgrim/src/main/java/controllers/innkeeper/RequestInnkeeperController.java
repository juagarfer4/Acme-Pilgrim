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

import services.AdministratorService;
import services.RequestService;

import controllers.AbstractController;
import domain.Administrator;
import domain.Request;

@Controller
@RequestMapping("/request/innkeeper")
public class RequestInnkeeperController extends AbstractController {

	// Services -------------------------------------------------

	@Autowired
	private RequestService requestService;
	
	@Autowired
	private AdministratorService administratorService;

	// Constructors ---------------------------------------------

	public RequestInnkeeperController() {
		super();
	}

	// Listing -------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Request> requests;
		Boolean myRequests;
			
		requests=requestService.findAllRequestsByInnkeeper();
		myRequests=true;

		result = new ModelAndView("request/innkeeper/list");
		result.addObject("requests", requests);
		result.addObject("myRequests", myRequests);
		result.addObject("requestURI", "request/innkeeper/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Request request;
		Collection<Administrator> administrators;

		request = requestService.create();
		administrators=administratorService.findAll();

		result = new ModelAndView("request/innkeeper/edit");
		result.addObject("request", request);
		result.addObject("administrators", administrators);
		result.addObject("actionURI", "request/innkeeper/edit.do");

		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int requestId) {
		ModelAndView result;
		Request request;

		request = requestService.findOne(requestId);
		result = createEditModelAndView(request);

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Request request, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(request);
		} else {
			try {
				requestService.saveInnkeeper(request);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(request, "request.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		result.addObject("actionURI", "request/innkeeper/edit.do");
		return result;
	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(Request request) {
		ModelAndView result;

		result = createEditModelAndView(request, null);

		return result;
	}

	public ModelAndView createEditModelAndView(Request request, String message) {
		ModelAndView result;
		Collection<Administrator> administrators;
		
		administrators=administratorService.findAll();

		result = new ModelAndView("request/innkeeper/edit");
		result.addObject("request", request);
		result.addObject("administrators", administrators);
		result.addObject("message", message);

		return result;
	}

}
