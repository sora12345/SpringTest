package kr.co.ksr1.board.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.board.dao.IBoardNoticeDAO;
import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Repository
public class BoardNoticeDAOImpl extends BaseDaoSupport implements IBoardNoticeDAO {

	@Override
	public void insertData(BoardDocDTO boardDocDTO) {

		this.getSqlSession().insert("BoardNotice.insertData", boardDocDTO);
	}

	@Override
	public List<BoardDocDTO> list(BoardDocDTO boardDocDTO) {

		return this.getSqlSession().selectList("BoardNotice.list", boardDocDTO);
	}

	@Override
	public void updateData(Integer docId) {

		this.getSqlSession().update("BoardNotice.updateData", docId);
	}




}
