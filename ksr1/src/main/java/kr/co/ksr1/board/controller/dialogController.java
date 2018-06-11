package kr.co.ksr1.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;
import kr.co.ksr1.board.service.IBoardDocService;
import kr.co.ksr1.popup.dto.PopupDTO;
import kr.co.ksr1.popup.service.IPopupService;
import kr.co.ksr1.user.dto.UserDTO;
import kr.co.ksr1.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/board/dialog/")
public class dialogController {
	
	@Autowired private IUserService userServiceImpl = null;
	@Autowired private IPopupService popupServiceImpl = null;
	
	@RequestMapping(value="/userInfoView.ssol", method=RequestMethod.GET)
	public void userInfo(Model model,Integer userId) {
		
		log.debug("=================>userDTO : "+userId);
		
		UserDTO userDTO = userServiceImpl.view(userId);
		log.debug("=================>user"+userDTO);
		model.addAttribute("userDTO", userDTO);
	}
	
	@RequestMapping(value="/secretPw.ssol", method=RequestMethod.GET)
	public void secretPw(Integer docId, @ModelAttribute("boardDocDTO") BoardDocDTO boardDocDTO) {
	log.debug("boardDocDTO : : : : : : : " + boardDocDTO);	
	}
	
	@RequestMapping(value="/popupDialog.ssol", method=RequestMethod.GET)
	public void popupDialog(Model model) {
		
		List<PopupDTO> list = popupServiceImpl.list();
		log.debug("=================>list"+list);
		model.addAttribute("list", list);
	}
	
	
}
