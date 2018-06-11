package kr.co.ksr1.board.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.board.dao.IBoardDocDAO;
import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Repository
public class BoardDocDAOImpl extends BaseDaoSupport implements IBoardDocDAO {

	@Override
	public void insertData(BoardDocDTO boardDocDTO) {
		
		this.getSqlSession().insert("BoardDoc.insertData", boardDocDTO);
	}

	@Override
	public BoardDocDTO selectOne(BoardDocDTO _boardDocDTO) {
		
		return this.getSqlSession().selectOne("BoardDoc.selectOne", _boardDocDTO);
	}

	@Override
	public void updateData(BoardDocDTO boardDocDTO) {
		
		this.getSqlSession().update("BoardDoc.updateData", boardDocDTO);
		
	}

	@Override
	public void deleteData(Integer docId) {
		
		this.getSqlSession().delete("BoardDoc.deleteData", docId);
	}

	@Override
	public List<BoardDocDTO> selectList(BoardSearchDTO boardSearchDTO) {
		
		return this.getSqlSession().selectList("BoardDoc.selectList", boardSearchDTO);
	}

	@Override
	public int selectCount(BoardSearchDTO search) {
		
		return this.getSqlSession().selectOne("BoardDoc.selectCount", search);
	}

	@Override
	public void updateByCntRead(Integer docId) {
		
		this.getSqlSession().update("BoardDoc.updateByCntRead", docId);
	}

	@Override
	public List<BoardDocDTO> selectListByUserId(BoardSearchDTO boardSearchDTO) {

		return getSqlSession().selectList("BoardDoc.selectListByUserId",boardSearchDTO);
	}

	@Override
	public int selectCountByUserId(BoardSearchDTO search) {
		
		return this.getSqlSession().selectOne("BoardDoc.selectCountByUserId", search);
	}

	@Override
	public List<BoardDocDTO> selectListMyUserId(Integer userId) {

		return this.getSqlSession().selectList("BoardDoc.selectListMyUserId", userId);
	}

	@Override
	public List<BoardDocDTO> selectListTotal(BoardSearchDTO boardSearchDTO) {

		return this.getSqlSession().selectList("BoardDoc.selectListTotal", boardSearchDTO);
	}

	@Override
	public int selectCountTotal(BoardSearchDTO search) {
		
		return this.getSqlSession().selectOne("BoardDoc.selectCountTotal", search);
	}

	@Override
	public List<BoardDocDTO> selectListMyLike(BoardSearchDTO boardSearchDTO) {

		return this.getSqlSession().selectList("BoardDoc.selectListMyLike", boardSearchDTO);
	}


}
