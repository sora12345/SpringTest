package kr.co.ksr1.board.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ksr1.board.dto.BoardLikeDTO;
import kr.co.ksr1.board.service.IBoardLikeService;
import kr.co.ksr1.commom.dto.ResponseDTO;
import kr.co.ksr1.user.dto.UserDTO;

@Controller
@RequestMapping("/board/like/")
public class BoardLikeController {
	
	@Autowired private IBoardLikeService boardLikeServiceImpl = null;
	
	@ResponseBody
	@RequestMapping(value="/like.ssol", method=RequestMethod.POST)
	public ResponseDTO doWrite(Model model, HttpSession session, BoardLikeDTO boardLikeDTO) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		try {
			
			// 1. 사용자 ID 구하기
			UserDTO userDTO = (UserDTO)session.getAttribute("_user");
			boardLikeDTO.setUserId(userDTO.getUserId());
			
			// 좋아요 실행
			boardLikeServiceImpl.write(boardLikeDTO);
		
		}catch(Exception e) {
			responseDTO.setCode(-1);
			responseDTO.setMsg("에러가 발생했습니다. 관리자에게 문의하세요.");
		}
		
		return responseDTO;
			
	}
	

}
