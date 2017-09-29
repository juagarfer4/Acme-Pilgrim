package controllers;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.InnkeeperService;
import services.LodgeCalendarService;
import services.LodgeService;
import services.PilgrimService;
import domain.Innkeeper;
import domain.Lodge;
import domain.Pilgrim;
import forms.InnkeeperForm;

@Controller
@RequestMapping("/innkeeper")
public class InnkeeperController extends AbstractController {

	// Services --------------------------------------------------------------

	@Autowired
	private InnkeeperService innkeeperService;
	
	@Autowired
	private PilgrimService pilgrimService;
	
	@Autowired
	private LodgeService lodgeService;
	
	@Autowired
	private LodgeCalendarService lodgeCalendarService;
	

	// Constructors -----------------------------------------------------------

	public InnkeeperController() {
		super();
	}

	// Listing ----------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam Integer innkeeperId) {
		ModelAndView result;
		Innkeeper innkeeper;
		Collection<Innkeeper> innkeepers;
		
		innkeeper=innkeeperService.findOne(innkeeperId);
		innkeepers=new ArrayList<Innkeeper>();
		
		innkeepers.add(innkeeper);
		
		result = new ModelAndView("innkeeper/list");
		result.addObject("innkeepers", innkeepers);
		result.addObject("requestURI", "innkeeper/list.do");

		return result;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard(){
		ModelAndView result;
		Collection<Pilgrim> pilgrims1;
		Collection<Pilgrim> pilgrims2;
		Collection<Pilgrim> pilgrims3;
		Collection<Lodge> lodges1;
		Collection<Lodge> lodges2;
		Collection<Lodge> lodges3;
		Double rates;
		Collection<Lodge> lodges;
			
		pilgrims1 = pilgrimService.findAllPilgrimsBookedInLodgeOfInnkeeper();
		pilgrims2 = pilgrimService.findAllPilgrimsMoreBookingsInLodgeOfInnkeeper();
		pilgrims3 = pilgrimService.findAllPilgrimsBookedInLodgeOfInnkeeperOrderedByDescendigBirthDate();
		lodges1 = lodgeService.findAllLodgesSortedByDescendingNumberOfBookingsByInnkeeper();
		lodges2 = lodgeService.findAllLodgesOrderedByIncreaseAverageRatingByInnkeeper();
		lodges3 = lodgeService.findAllLodgesOrderedByDescendingPriceByInnkeeper();
//		occupancyRate = innkeeperService.findOccupancyRateOfNextMonth();
		lodges=lodgeService.findAllLodgesByInnkeeper();
//		Map<String, Double> rates = lodgeService.ratingOccupancy(lodges);
//		if (rates.isEmpty()) {
//			rates = null;
//		}
		rates=lodgeCalendarService.monthRate();
			
		result=new ModelAndView("innkeeper/dashboard");
		result.addObject("pilgrims1", pilgrims1);
		result.addObject("pilgrims2", pilgrims2);
		result.addObject("pilgrims3", pilgrims3);
		result.addObject("lodges1", lodges1);
		result.addObject("lodges2", lodges2);
		result.addObject("lodges3", lodges3);
//		result.addObject("occupancyRate", occupancyRate);
		result.addObject("lodges", lodges);
		result.addObject("rates", rates);
		result.addObject("requestURI", "innkeeper/dashboard.do");
		
		return result;
	}

	// Creation -----------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		InnkeeperForm innkeeperForm;

		innkeeperForm = new InnkeeperForm();

		result = new ModelAndView("innkeeper/edit");
		result.addObject("innkeeperForm", innkeeperForm);
		result.addObject("actionURI", "innkeeper/edit.do");

		return result;
	}

	// Edition ---------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid InnkeeperForm innkeeperForm,
			BindingResult binding) {
		ModelAndView result;
		Innkeeper innkeeper;

		if (binding.hasErrors()) {
			System.out.println(binding.getAllErrors().get(0));
			result = createEditModelAndView(innkeeperForm);
		} else {
			try {
				innkeeper = innkeeperService.reconstruct(innkeeperForm);
				innkeeperService.save(innkeeper);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(innkeeperForm,
						"innkeeper.commit.error");
				System.out.println(oops.getStackTrace());
			}
		}

		return result;

	}

	// Ancillary methods ---------------------------------------------------------------

	public ModelAndView createEditModelAndView(InnkeeperForm innkeeperForm) {
		ModelAndView result;

		result = createEditModelAndView(innkeeperForm, null);

		return result;

	}

	public ModelAndView createEditModelAndView(InnkeeperForm innkeeperForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("innkeeper/edit");
		result.addObject("innkeeperForm", innkeeperForm);
		result.addObject("message", message);

		return result;

	}

}
