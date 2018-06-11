package kr.co.ksr1.board.service;

import java.util.List;

import kr.co.ksr1.board.dto.BoardDocDTO;

public interface IBoardNoticeService {
	
	public void write(BoardDocDTO boardDocDTO);
	public List<BoardDocDTO> list(BoardDocDTO boardDocDTO);
	public void edit(Integer docId);

}
