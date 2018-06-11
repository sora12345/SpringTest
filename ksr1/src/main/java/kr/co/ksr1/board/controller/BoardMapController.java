package kr.co.ksr1.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ksr1.board.dto.BoardMapDTO;
import kr.co.ksr1.board.service.IBoardMapService;

@Controller
@RequestMapping("/board/map/")
public class BoardMapController {
	
	@Autowired
	private IBoardMapService boardMapServiceImpl = null;
	
	@ResponseBody
	@RequestMapping(value="/list.ssol", method=RequestMethod.GET)
	public List<BoardMapDTO> list() {
		return boardMapServiceImpl.list();
		
	}


}
