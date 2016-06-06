package fr.pizzeria.spring.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import fr.pizzeria.dao.IPizzaRepository;
import fr.pizzeria.model.performance.Performance;

@Controller
@RequestMapping("/performance")
public class PerformanceController {
	@Autowired
	private IPizzaRepository performanceRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String viewList(Model model) {
		List<Performance> perfs = performanceRepository.findAll();
		model.addAttribute("performanceList", perfs);
		return "performance/performanceList";
	}

	@RequestMapping(path = "/delete", method = RequestMethod.POST, consumes = MediaType.
			
			
			APPLICATION_FORM_URLENCODED_VALUE)
	public String delete(@RequestParam("id") Integer performanceId, Model model) {
		performanceRepository.delete(performanceId);
		model.addAttribute("msg", "Performance supprimée");
		return "redirect:/mvc/performance";
	}

	@RequestMapping(path = "/deleteall", method = RequestMethod.POST, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String delete(Model model) throws UnsupportedEncodingException {
		performanceRepository.deleteAll();
		model.addAttribute("msg", "Toutes les données sont supprimées");
		return "redirect:/mvc/performance";
	}
}
