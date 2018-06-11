package kr.co.ksr1.board.dao;

import java.util.List;

import kr.co.ksr1.board.dto.BoardFileDTO;

public interface IBoardFileDAO {
	
	public List<BoardFileDTO> selectList(Integer docId);
	public BoardFileDTO selectOne(BoardFileDTO boardFileDTO);

	public void insertData(BoardFileDTO boardFileDTO);
	public void deleteData(Integer fileSno);
	public void deleteByDocId(Integer docId);
	
}
