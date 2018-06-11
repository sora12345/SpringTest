package kr.co.ksr1.board.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.board.dao.IBoardFileDAO;
import kr.co.ksr1.board.dto.BoardFileDTO;
import kr.co.ksr1.board.service.IBoardFileService;

@Service
public class BoardFileServiceImpl implements IBoardFileService {

	@Autowired
	private IBoardFileDAO boardFileDAOImpl = null;
	
	@Override
	public void write(BoardFileDTO boardFileDTO) {
		boardFileDAOImpl.insertData(boardFileDTO);
		
	}

	@Override
	public List<BoardFileDTO> list(Integer docId) {

		return boardFileDAOImpl.selectList(docId);
	}

	@Override
	public void remove(Integer fileSno) {
		
		boardFileDAOImpl.deleteData(fileSno);
	}

	@Override
	public BoardFileDTO view(BoardFileDTO boardFileDTO) {

		return boardFileDAOImpl.selectOne(boardFileDTO);
	}

	@Override
	public void removeByDocId(Integer docId) {
		
		this.boardFileDAOImpl.deleteByDocId(docId);
	}

}
;