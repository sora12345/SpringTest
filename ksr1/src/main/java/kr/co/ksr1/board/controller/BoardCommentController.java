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

import kr.co.ksr1.board.dto.BoardCommentDTO;
import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;
import kr.co.ksr1.board.service.IBoardCommentService;
import kr.co.ksr1.commom.dto.ResponseDTO;
import kr.co.ksr1.user.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/comment/")
public class BoardCommentController {
	
	@Autowired
	private IBoardCommentService boardCommentServiceImpl = null;
	
	// 게시판 저장
	@ResponseBody
	@RequestMapping(value="/write.ssol", method=RequestMethod.POST)
	public ResponseDTO doWrite(Model model, HttpSession session, BoardCommentDTO boardCommentDTO) {
		
		ResponseDTO responseDTO = new ResponseDTO();
			
			try {
				// 1. 사용자 ID 구하기
				UserDTO userDTO = (UserDTO)session.getAttribute("_user");
				boardCommentDTO.setUserId(userDTO.getUserId());
				log.debug("======="+userDTO);
				
				// 2. 댓글 저장
				boardCommentServiceImpl.write(boardCommentDTO);
				
			}catch(Exception e) {
				responseDTO.setCode(-1);
				responseDTO.setMsg("에러가 발생했습니다. 관리자에게 문의하세요.");
			}
			
			return responseDTO;

	}
	// 댓글 목록
	@RequestMapping(value="/list.ssol", method=RequestMethod.GET)
	public void list(Model model, Integer docId, HttpSession session) {
		
		UserDTO userDTO = (UserDTO)session.getAttribute("_user");
		model.addAttribute("userDTO", userDTO);
		
		List<BoardCommentDTO> list = boardCommentServiceImpl.view(docId);
		model.addAttribute("list", list);
	
	}
	@RequestMapping(value="/list.ssol", method=RequestMethod.POST)
	public void delete(Integer commentId) {
		
		boardCommentServiceImpl.remove(commentId);
	
	}
	

	
	
	
}
