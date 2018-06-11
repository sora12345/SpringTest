package kr.co.ksr1.commom.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.co.ksr1.board.dto.BoardFileDTO;
import kr.co.ksr1.board.service.IBoardFileService;
import kr.co.ksr1.commom.file.FileService;

@Controller
@RequestMapping("/file")
public class fileController {
	
			@Autowired private FileService fileService = null;
			@Autowired private IBoardFileService boardFileServiceImpl = null;
	
	@RequestMapping(value="/downloadFile.ssol", method=RequestMethod.GET)
	public void downloadFile(HttpServletResponse response, HttpSession httpSession, BoardFileDTO _fileDTO) {
				
		BoardFileDTO fileDTO = boardFileServiceImpl.view(_fileDTO);
		
		fileService.downloadFile(response, httpSession, fileDTO);
	}
}
