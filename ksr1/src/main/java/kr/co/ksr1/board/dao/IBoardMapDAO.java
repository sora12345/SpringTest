package kr.co.ksr1.board.dao;

import java.util.List;

import kr.co.ksr1.board.dto.BoardMapDTO;

public interface IBoardMapDAO {
	
	public List<BoardMapDTO> selectList();
	public BoardMapDTO selectOne(Integer mapId);

}
