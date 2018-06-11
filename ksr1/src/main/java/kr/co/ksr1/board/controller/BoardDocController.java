package kr.co.ksr1.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardMapDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;
import kr.co.ksr1.board.service.IBoardDocService;
import kr.co.ksr1.board.service.IBoardMapService;
import kr.co.ksr1.user.dto.UserDTO;
import kr.co.ksr1.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/doc/")
public class BoardDocController {
	
	@Autowired private IBoardMapService boardMapServiceImpl = null;
	@Autowired private IBoardDocService boardDocServiceImpl = null;
	@Autowired private IUserService userServiceImpl = null;
	
	// 게시판 목록
	@RequestMapping(value="/list.ssol", method=RequestMethod.GET)
	public void list(Model model, @ModelAttribute("search") BoardSearchDTO search, Integer docId) {
	
		
		// 1.통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(search.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		
		// 2.게시판 목록 가져오기
		List<BoardDocDTO> list = boardDocServiceImpl.list(search);
		model.addAttribute("list", list);
		
		log.debug("===========>list나왕?"+list);
	
	}
	
	// 전체글 모아보기
	@RequestMapping(value="/totalDoc.ssol", method=RequestMethod.GET)
	public void totalDoc(Model model, @ModelAttribute("search") BoardSearchDTO boardSearchDTO) {
	
		
		// 1.통합맵 정보 가져오기
		BoardMapDTO boardMapDTO = boardMapServiceImpl.view(boardSearchDTO.getMapId());		
		model.addAttribute("boardMapDTO",boardMapDTO);
		
		// 2.게시판 목록 가져오기
		List<BoardDocDTO> list = boardDocServiceImpl.listTotal(boardSearchDTO);
		model.addAttribute("list", list);
	
	}
	
	// 내가 등록한 글 가져오기
	@RequestMapping(value="/mylist.ssol", method=RequestMethod.GET)
	public void mylist(Model model, HttpSession session, @ModelAttribute("search") BoardSearchDTO search) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		search.setUserId(userDTO.getUserId());
		
		List<BoardDocDTO> list = boardDocServiceImpl.listByUserId(search);
		model.addAttribute("list",list);
		
	}
	
	// 게시판 글쓰기 화면에 mapId 가져오기
	@RequestMapping(value="/write.ssol", method=RequestMethod.GET)
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
	
	// 내가 등록한 댓글 가져오기
	@RequestMapping(value="/myComment.ssol", method=RequestMethod.GET)
	public void myComment(Model model, HttpSession session, @ModelAttribute("search") BoardSearchDTO search) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		search.setUserId(userDTO.getUserId());
		
		List<BoardDocDTO> list = boardDocServiceImpl.listMyUserId(search.getUserId());
		model.addAttribute("list",list);
		
	}
	
	// 게시글 삭제
	@RequestMapping(value="/remove.ssol", method=RequestMethod.GET)
	public String remove(Model model,Integer docId, Integer mapId) {
		
		boardDocServiceImpl.remove(docId);
		
		return "redirect:./list.ssol?mapId="+mapId;
	}
	

	
	// 나의정보수정 페이지로 이동
	@RequestMapping(value="/myUpdate.ssol", method=RequestMethod.GET)
	public void goMyUpdate(Model model,HttpSession session) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		model.addAttribute("userDTO", userDTO);
		
		log.debug("@@@userDTO===============>"+userDTO );
		
	}
	
	// 나의정보수정
	@ResponseBody
	@RequestMapping(value="/myUpdate.ssol", method=RequestMethod.POST)
	public String doMyUpdate(UserDTO userDTO) {
		
		log.debug("userDTO===============>"+userDTO );
		userServiceImpl.edit(userDTO);
		
		return "s";
	
	}
	
	// 내가 좋아요 한 글
	@RequestMapping(value="/myLike.ssol", method=RequestMethod.GET)
	public void myLikeY(Model model, HttpSession session, @ModelAttribute("search") BoardSearchDTO boardSearchDTO) {
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		boardSearchDTO.setUserId(userDTO.getUserId());
		
		// 2.게시판 목록 가져오
		
		List<BoardDocDTO> listY = boardDocServiceImpl.listMyLikeY(boardSearchDTO);
		model.addAttribute("listY", listY);
		
		List<BoardDocDTO> listN = boardDocServiceImpl.listMyLikeN(boardSearchDTO);
		model.addAttribute("listN", listN);
	
	
	}
	
	
	
}
