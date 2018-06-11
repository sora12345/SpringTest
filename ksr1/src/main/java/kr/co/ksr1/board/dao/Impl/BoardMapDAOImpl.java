package kr.co.ksr1.board.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.board.dao.IBoardMapDAO;
import kr.co.ksr1.board.dto.BoardMapDTO;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Repository
public class BoardMapDAOImpl extends BaseDaoSupport implements IBoardMapDAO {

	@Override
	public List<BoardMapDTO> selectList() {

		return this.getSqlSession().selectList("BoardMap.selectList");

	}

	@Override
	public BoardMapDTO selectOne(Integer mapId) {
		
		return this.getSqlSession().selectOne("BoardMap.selectOne", mapId);
	}

}
