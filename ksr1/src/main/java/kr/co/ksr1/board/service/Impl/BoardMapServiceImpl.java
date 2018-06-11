package kr.co.ksr1.board.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.board.dao.IBoardMapDAO;
import kr.co.ksr1.board.dto.BoardMapDTO;
import kr.co.ksr1.board.service.IBoardMapService;
import kr.co.ksr1.commom.dao.BaseDaoSupport;

@Service
public class BoardMapServiceImpl extends BaseDaoSupport implements IBoardMapService {
	
	@Autowired
	private IBoardMapDAO boardMapDAOImpl = null;
	
	@Override
	public List<BoardMapDTO> list() {
		// TODO Auto-generated method stub
		return boardMapDAOImpl.selectList();
	}

	@Override
	public BoardMapDTO view(Integer mapId) {
		// TODO Auto-generated method stub
		return boardMapDAOImpl.selectOne(mapId);
	}

}
