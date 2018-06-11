package kr.co.ksr1.board.service;

import java.util.List;

import kr.co.ksr1.board.dto.BoardMapDTO;

public interface IBoardMapService {
	
	public List<BoardMapDTO> list();
	public BoardMapDTO view(Integer mapId);

}
