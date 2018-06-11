package kr.co.ksr1.board.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.board.dao.IBoardNoticeDAO;
import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.service.IBoardNoticeService;

@Service
public class BoardNoticeServiceImpl implements IBoardNoticeService {
	
	@Autowired IBoardNoticeDAO boardNoticeDAOImpl = null;

	@Override
	public void write(BoardDocDTO boardDocDTO) {

		boardNoticeDAOImpl.insertData(boardDocDTO);
	}

	@Override
	public List<BoardDocDTO> list(BoardDocDTO boardDocDTO) {

		return boardNoticeDAOImpl.list(boardDocDTO);
	}

	@Override
	public void edit(Integer docId) {

		boardNoticeDAOImpl.updateData(docId);
	}

}
