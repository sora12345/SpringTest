package kr.co.ksr1.board.dao.Impl;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.board.dao.IBoardLikeDAO;
import kr.co.ksr1.board.dto.BoardLikeDTO;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Repository
public class BoardLikeDAOImpl extends BaseDaoSupport implements IBoardLikeDAO {

	@Override
	public void insertData(BoardLikeDTO boardLikeDTO) {
		
		this.getSqlSession().insert("BoardLike.insertData", boardLikeDTO);
	}

	@Override
	public void deleteData(Integer likeId) {
		
		this.getSqlSession().delete("BoardLike.deleteData", likeId);
	}

	@Override
	public void deleteByDocId(Integer docId) {

		this.getSqlSession().delete("BoardLike.deleteByDocId", docId);
	}

}
