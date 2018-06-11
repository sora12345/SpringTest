package kr.co.ksr1.board.service;

import kr.co.ksr1.board.dto.BoardLikeDTO;

public interface IBoardLikeService {
	
	public void write(BoardLikeDTO boardLikeDTO);
	public void remove(Integer likeId);
	public void removeByDocId(Integer docId);

}
