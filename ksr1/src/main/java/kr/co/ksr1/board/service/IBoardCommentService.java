package kr.co.ksr1.board.service;

import java.util.List;

import kr.co.ksr1.board.dto.BoardCommentDTO;

public interface IBoardCommentService {
	
	public void write(BoardCommentDTO boardCommentDTO);
	public void remove(Integer commentId);
	public void removeByDocId(Integer docId);
	
	public List<BoardCommentDTO> view(Integer docId);
	public List<BoardCommentDTO> viewByUserId(Integer userId);
	

}
