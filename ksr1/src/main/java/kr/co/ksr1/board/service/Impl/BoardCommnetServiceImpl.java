package kr.co.ksr1.board.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.board.dao.IBoardCommentDAO;
import kr.co.ksr1.board.dto.BoardCommentDTO;
import kr.co.ksr1.board.service.IBoardCommentService;

@Service
public class BoardCommnetServiceImpl implements IBoardCommentService {
	
	@Autowired
	private IBoardCommentDAO boardCommentDAOImpl = null;
	
	@Override
	public void write(BoardCommentDTO boardCommentDTO) {
		
		boardCommentDAOImpl.insertData(boardCommentDTO);
		
	}

	@Override
	public void remove(Integer commentId) {
		
		boardCommentDAOImpl.deleteData(commentId);
	}

	@Override
	public List<BoardCommentDTO> view(Integer docId) {
	
		return boardCommentDAOImpl.selectList(docId);
	}

	@Override
	public List<BoardCommentDTO> viewByUserId(Integer userId) {

		return boardCommentDAOImpl.selectListByUserId(userId);
	}

	@Override
	public void removeByDocId(Integer docId) {
		
		this.boardCommentDAOImpl.deleteByDocId(docId);
	}

}
