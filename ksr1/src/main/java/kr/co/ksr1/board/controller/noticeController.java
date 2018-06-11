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
import kr.co.ksr1.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/notice/")
public class noticeController {
	
	@Autowired private IBoardDocService boardDocServiceImpl = null;
	@Autowired private IBoardMapService boardMapServiceImpl = null;
	
	@RequestMapping(value="/list.ssol", method=RequestMethod.GET)
	public void list(Model model, @ModelAttribute("search") BoardSearchDTO search) {
		
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		
		List<BoardDocDTO> list = boardDocServiceImpl.list(search);
		model.addAttribute("list", list);
	}
	
	@RequestMapping(value="/write.ssol", method=RequestMethod.GET)
	public void goWrite(Model model,@ModelAttribute("search") BoardSearchDTO search) {
		
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
	}
	
	@RequestMapping(value="/write.ssol", method=RequestMethod.POST)
	public String doWrite(Model model, BoardDocDTO boardDocDTO, HttpSession session, @ModelAttribute("search") BoardSearchDTO search) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		boardDocDTO.setUserId(userDTO.getUserId());
		
		boardDocServiceImpl.write(boardDocDTO, session);
		
		return "redirect:./view.ssol?docId="+boardDocDTO.getDocId()+"&"+search.getParams();
		
	}
	
	// 조회
	@RequestMapping(value="/view.ssol", method=RequestMethod.GET)
	public void view(Model model, @ModelAttribute("search") BoardSearchDTO search, BoardDocDTO _boardDocDTO, HttpSession session) {
		
		// 1.통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);

		// 로그인 한 사용자 ID 가져오기(뷰어)
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		_boardDocDTO.setViewerId(userDTO.getUserId());
		model.addAttribute("userDTO", userDTO);
		
		// 2.조회

		BoardDocDTO boardDocDTO = boardDocServiceImpl.view(_boardDocDTO);
		log.debug("========================야호"+boardDocDTO);
		model.addAttribute("boardDocDTO", boardDocDTO);
		
	}
	
	// 게시판 수정 이동
	@RequestMapping(value="/edit.ssol", method=RequestMethod.GET)
	public void goEdit(Model model, HttpSession session, @ModelAttribute("search") BoardSearchDTO search, BoardDocDTO _boardDocDTO) {
			
		// 1.통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		
		// 2. 로그인 한 사용자 ID 구하기(뷰어)
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		_boardDocDTO.setViewerId(userDTO.getUserId());
		
		// 3. 조회
		BoardDocDTO boardDocDTO = boardDocServiceImpl.view(_boardDocDTO);
		model.addAttribute("boardDocDTO", boardDocDTO);
	}
	
	// 게시판 수정
	@RequestMapping(value="/edit.ssol", method=RequestMethod.POST)
	public String doEdit(Model model, HttpSession session, @ModelAttribute("search") BoardSearchDTO search, BoardDocDTO boardDocDTO) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		boardDocDTO.setUserId(userDTO.getUserId());
		
		log.debug("@@@ docID ==> " + boardDocDTO);
		
		boardDocServiceImpl.edit(boardDocDTO, session);
		log.debug("================>"+userDTO);
		log.debug("잉잉??================>"+boardDocDTO.getDocId());
				
		return "redirect:./view.ssol?docId="+boardDocDTO.getDocId()+"&"+search.getParams();
	}
	
	// 게시글 삭제
	@RequestMapping(value="/remove.ssol", method=RequestMethod.GET)
	public String remove(Model model,Integer docId, Integer mapId) {
		
		boardDocServiceImpl.remove(docId);
		
		return "redirect:./list.ssol?mapId="+mapId;
	}
		
}
