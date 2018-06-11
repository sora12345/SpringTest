package kr.co.ksr1.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class IndexController {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexController.class);
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index1() {
		return "redirect:/login.ssol";
	}
	
	@RequestMapping(value="/index.ssol", method=RequestMethod.GET)
	public String index() {
		return "redirect:/login.ssol";
		
	}
	// 인덱스로 들어오면 로그인으로 넘김
	@RequestMapping(value="/login.ssol", method=RequestMethod.GET)
	public void login() {
		
	}

}
