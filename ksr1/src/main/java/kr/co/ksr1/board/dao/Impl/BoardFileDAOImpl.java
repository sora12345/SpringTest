package kr.co.ksr1.board.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.board.dao.IBoardFileDAO;
import kr.co.ksr1.board.dto.BoardFileDTO;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Repository
public class BoardFileDAOImpl extends BaseDaoSupport implements IBoardFileDAO {

	@Override
	public void insertData(BoardFileDTO boardFileDTO) {
		
		this.getSqlSession().insert("BoardFile.insertData",boardFileDTO);
		
	}

	@Override
	public List<BoardFileDTO> selectList(Integer docId) {

		return this.getSqlSession().selectList("BoardFile.selectList",docId);
	}

	@Override
	public void deleteData(Integer fileSno) {
		
		this.getSqlSession().delete("BoardFile.deleteData", fileSno);
	}

	@Override
	public BoardFileDTO selectOne(BoardFileDTO boardFileDTO) {

		return this.getSqlSession().selectOne("BoardFile.selectOne",boardFileDTO);
	}

	@Override
	public void deleteByDocId(Integer docId) {
		
		this.getSqlSession().delete("BoardFile.deleteByDocId", docId);
	}

}
