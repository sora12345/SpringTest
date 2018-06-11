package kr.co.ksr1.board.dao;

import kr.co.ksr1.board.dto.BoardLikeDTO;

public interface IBoardLikeDAO {
	
	public void insertData(BoardLikeDTO boardLikeDTO);
	public void deleteData(Integer likeId);
	public void deleteByDocId(Integer docId);

}
