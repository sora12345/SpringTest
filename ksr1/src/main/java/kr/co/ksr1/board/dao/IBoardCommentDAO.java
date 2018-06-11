package kr.co.ksr1.board.dao;

import java.util.List;

import kr.co.ksr1.board.dto.BoardCommentDTO;

public interface IBoardCommentDAO {
	
	public void insertData(BoardCommentDTO boardCommentDTO);
	public void deleteData(Integer commentId);
	public void deleteByDocId(Integer docId);
	
	public List<BoardCommentDTO> selectList(Integer docId);
	public List<BoardCommentDTO> selectListByUserId(Integer userId);

}
