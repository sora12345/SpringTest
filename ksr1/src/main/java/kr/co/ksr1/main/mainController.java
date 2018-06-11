package kr.co.ksr1.main;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class mainController {
	
	@RequestMapping(value="/main/index.ssol", method=RequestMethod.GET)
	public void index() {
		
	}

}
