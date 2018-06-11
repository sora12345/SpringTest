package kr.co.ksr1.board.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.board.dao.IBoardCommentDAO;
import kr.co.ksr1.board.dto.BoardCommentDTO;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Repository
public class BoardCommentDAOImpl extends BaseDaoSupport implements IBoardCommentDAO {

	@Override
	public void insertData(BoardCommentDTO boardCommentDTO) {
		
		this.getSqlSession().insert("BoardComment.insertData", boardCommentDTO);
		
	}

	@Override
	public void deleteData(Integer commentId) {
		
		this.getSqlSession().delete("BoardComment.deleteData", commentId);
		
	}

	@Override
	public List<BoardCommentDTO> selectList(Integer docId) {
		
		return this.getSqlSession().selectList("BoardComment.selectList", docId);
	}

	@Override
	public List<BoardCommentDTO> selectListByUserId(Integer userId) {
		
		return this.getSqlSession().selectList("BoardComment.selectListByUserId", userId);
	}

	@Override
	public void deleteByDocId(Integer docId) {
		
		this.getSqlSession().delete("BoardComment.deleteByDocId", docId);
	}

}
