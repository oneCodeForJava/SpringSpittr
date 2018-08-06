package spittr.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import spittr.Spitter;
import spittr.data.SpittleRepository;

@Controller
@RequestMapping("/spitter")
public class SpitterController {
	
	private SpittleRepository spittleRepository;
	
	@Autowired
	public SpitterController(SpittleRepository mockRepository) {
		this.spittleRepository = mockRepository;
	}
	
	@RequestMapping(value="/register", method=RequestMethod.GET)
	public String showSpittle(){
		return "registerForm";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String processRegisttation(Spitter spitter){
		spittleRepository.save(spitter);
		return "redirect:/spitter/"+spitter.getUsername();
	}
	
	@RequestMapping(value="{username}", method=RequestMethod.GET)
	public String showSpitterProfile(@PathVariable String username, Model model){
		Spitter spitter = spittleRepository.findByUsername(username);
		model.addAttribute(spitter);
		return "profile";
	}

}
