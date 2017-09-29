package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import domain.Lodge;

import services.LodgeService;

@Controller
@RequestMapping("/lodge")
public class LodgeController extends AbstractController{
	
	// Services --------------------------------------------------------------
	
	@Autowired
	private LodgeService lodgeService;

	// Constructors ----------------------------------------------------------

	public LodgeController() {
		super();
	}

	// Listing ---------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Lodge> lodges;
			
		lodges = lodgeService.findAllLodgesWhoseRequestIsAcepted();

		result = new ModelAndView("lodge/list");
		result.addObject("lodges", lodges);
		result.addObject("requestURI", "lodge/list.do");

		return result;
	}
	
	// Creation --------------------------------------------------------------
	
	// Edition --------------------------------------------------------------
	
	// Ancillary methods ----------------------------------------------------

}
