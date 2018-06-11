package kr.co.ksr1.popup.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ksr1.popup.dto.PopupDTO;
import kr.co.ksr1.popup.service.IPopupService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/popup/doc")
public class PopupController {
	
	@Autowired private IPopupService popupServiceImpl = null;
	
	@RequestMapping(value="/list.ssol", method=RequestMethod.GET)
	public void list(Model model) {
		
		
		List<PopupDTO> list = popupServiceImpl.list();
		model.addAttribute("list", list);
		
		log.debug("=========>list"+list);
	}
	
	@RequestMapping(value="/view.ssol", method=RequestMethod.GET)
	public void goview(Model model, Integer popId) {
		
		PopupDTO popupDTO = popupServiceImpl.view(popId);
		model.addAttribute("popupDTO", popupDTO);
		
		
	}
	
	@RequestMapping(value="/write.ssol", method=RequestMethod.GET)
	public void gowrite() {
		
	}
	
	@RequestMapping(value="/write.ssol", method=RequestMethod.POST)
	public String dowrite(PopupDTO popupDTO, HttpSession session) {
		popupServiceImpl.write(popupDTO, session);
		
		return "redirect:./view.ssol?popId="+popupDTO.getPopId();
	}
	
	@RequestMapping(value="/edit.ssol", method=RequestMethod.GET)
	public void goEdit(Model model, Integer popId) {
		
		PopupDTO popupDTO = popupServiceImpl.view(popId);
		model.addAttribute("popupDTO",popupDTO);
	}
	

	@RequestMapping(value = "/edit.ssol", method = RequestMethod.POST)
	public String doEdit(Model model, PopupDTO popupDTO, HttpSession session) {
		popupServiceImpl.edit(popupDTO, session);
		return "redirect:./view.ssol?popId=" + popupDTO.getPopId(); 
	}
	
	@RequestMapping(value = "/remove.ssol", method=RequestMethod.GET)
	public String remove(Integer popId) {
		log.debug("삭제 popId : " + popId);
		popupServiceImpl.remove(popId);
		return "redirect:./list.ssol";
	}

}
