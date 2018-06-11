package kr.co.ksr1.board.service;

import java.util.List;

import kr.co.ksr1.board.dto.BoardFileDTO;

public interface IBoardFileService {
	
	public List<BoardFileDTO> list(Integer docId);
	public BoardFileDTO view(BoardFileDTO boardFileDTO);
	
	public void write(BoardFileDTO boardFileDTO);
	public void remove(Integer fileSno);
	public void removeByDocId(Integer docId);
	
	

}
