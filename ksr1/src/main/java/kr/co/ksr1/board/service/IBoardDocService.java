package kr.co.ksr1.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;

public interface IBoardDocService {
	
	public BoardDocDTO view(BoardDocDTO _boardDocDTO);

	public void write(BoardDocDTO boardDocDTO, HttpSession session);
	public void edit(BoardDocDTO boardDocDTO, HttpSession session);
	public void remove(Integer docId);
	public void editByCntRead(Integer docId);
	
	public List<BoardDocDTO> list(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> listByUserId(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> listMyUserId(Integer userId);
	public List<BoardDocDTO> listTotal(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> listMyLikeY(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> listMyLikeN(BoardSearchDTO boardSearchDTO);

}
