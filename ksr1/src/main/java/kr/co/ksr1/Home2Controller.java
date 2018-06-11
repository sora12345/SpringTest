package kr.co.ksr1;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ksr1.user.dto.UserDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class Home2Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Home2Controller.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home2", method = RequestMethod.GET)
	public String home(HttpSession session, Model model) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		model.addAttribute("userDTO", userDTO);
		
		return "home2";
	}
	
}
