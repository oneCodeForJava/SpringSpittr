package spittr.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import spittr.dao.SpitterRepository;
import spittr.entity.Spittle;

@Controller
@RequestMapping("/spittles")
public class SpittleController {

	private SpitterRepository spittleRepository;
	
	private static final String MAX_LONG_AS_STRING = Long.MAX_VALUE+"";
	
	@Autowired
	public SpittleController(SpitterRepository mockRepository) {
		this.spittleRepository = mockRepository;
	}
	
/*	@RequestMapping(method=RequestMethod.GET)
	public String spittles(Model model){
		model.addAttribute(spittleRepository.findSpittles(Long.MAX_VALUE, 20));
		return "spittles";
	}*/
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Spittle> spittles(
			@RequestParam(value="max", defaultValue=MAX_LONG_AS_STRING) long max,
			@RequestParam(value="count", defaultValue="20") int count
			){
		return spittleRepository.findSpittles(max, count);
	}
	
	@RequestMapping(value="{spittleId}", method=RequestMethod.GET)
	public String showSpittle(
			//@PathVariable("spittleId") long spittleId,
			@PathVariable long spittleId,
			Model model
			){
		model.addAttribute(spittleRepository.findOne(spittleId));
		return "spittle";
	}

}
