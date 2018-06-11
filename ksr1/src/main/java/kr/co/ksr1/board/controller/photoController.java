package kr.co.ksr1.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardMapDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;
import kr.co.ksr1.board.service.IBoardDocService;
import kr.co.ksr1.board.service.IBoardMapService;
import kr.co.ksr1.popup.dto.PopupDTO;
import kr.co.ksr1.popup.service.IPopupService;
import kr.co.ksr1.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/photo/")
public class photoController {
	
	@Autowired private IBoardDocService boardDocServiceImpl = null;
	@Autowired private IBoardMapService boardMapServiceImpl = null;
	@Autowired private IPopupService popupServiceImpl = null;
	
	@RequestMapping(value="/list.ssol", method=RequestMethod.GET)
	public void list(Model model,@ModelAttribute("search") BoardSearchDTO search) {
		// 1.통합맵 정보 가져오기
		search.setRows(20);

		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		List<BoardDocDTO> list = boardDocServiceImpl.list(search);
		
		model.addAttribute("list", list);
	}
		
		// 게시판 글쓰기 화면에 mapId 가져오기
	@RequestMapping(value="write.ssol", method=RequestMethod.GET)
	public void goWrite(Model model,@ModelAttribute("search") BoardSearchDTO search) {
		
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
	}
	
	// 게시판 저장
	@RequestMapping(value="/write.ssol", method=RequestMethod.POST)
	public String doWrite(Model model, HttpSession session, BoardDocDTO boardDocDTO, @ModelAttribute("search") BoardSearchDTO search) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		boardDocDTO.setUserId(userDTO.getUserId());
		
		log.debug("================>"+userDTO);

		boardDocServiceImpl.write(boardDocDTO, session);
		
		return "redirect:./view.ssol?docId="+boardDocDTO.getDocId()+"&"+search.getParams();
			
		
	}
	// 게시판 수정 이동
	@RequestMapping(value="/edit.ssol", method=RequestMethod.GET)
	public void goEdit(Model model, @ModelAttribute("search") BoardSearchDTO search, @ModelAttribute("docId") BoardDocDTO _boardDocDTO) {
		
		// 1.통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		
		// 2.게시판 목록 가져오기
		BoardDocDTO boardDocDTO = boardDocServiceImpl.view(_boardDocDTO);
		model.addAttribute("boardDocDTO", boardDocDTO);
	}
	
	// 게시판 수정
	@RequestMapping(value="/edit.ssol", method=RequestMethod.POST)
	public String doEdit(Model model, HttpSession session, @ModelAttribute("search") BoardSearchDTO search, BoardDocDTO boardDocDTO) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		boardDocDTO.setUserId(userDTO.getUserId());
		
		boardDocServiceImpl.edit(boardDocDTO, session);
		log.debug("================>"+userDTO);
				
		return "redirect:./view.ssol?docId="+boardDocDTO.getDocId()+"&"+search.getParams();
	}
	
	// 조회
	@RequestMapping(value="/view.ssol", method=RequestMethod.GET)
	public void view(Model model, @ModelAttribute("search") BoardSearchDTO search, BoardDocDTO _boardDocDTO, HttpSession session) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		model.addAttribute("userDTO", userDTO);
		
		// 1.통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		
		// 2.조회
		BoardDocDTO boardDocDTO = boardDocServiceImpl.view(_boardDocDTO);
		model.addAttribute("boardDocDTO", boardDocDTO);
	
		
	}
	// 게시글 삭제
	@RequestMapping(value="/remove.ssol", method=RequestMethod.GET)
	public String remove(Model model,Integer docId, Integer mapId) {
		
		boardDocServiceImpl.remove(docId);
		
		return "redirect:./list.ssol?mapId="+mapId;
	}

}
