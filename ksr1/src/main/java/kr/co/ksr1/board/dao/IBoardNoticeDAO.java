package kr.co.ksr1.board.dao;

import java.util.List;

import kr.co.ksr1.board.dto.BoardDocDTO;

public interface IBoardNoticeDAO {
	
	public void insertData(BoardDocDTO boardDocDTO);
	public List<BoardDocDTO> list(BoardDocDTO boardDocDTO);
	public void updateData(Integer docId);

}
