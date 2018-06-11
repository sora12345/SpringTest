package kr.co.ksr1.board.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.board.dao.IBoardLikeDAO;
import kr.co.ksr1.board.dto.BoardLikeDTO;
import kr.co.ksr1.board.service.IBoardLikeService;

@Service
public class BoardLikeServiceImpl implements IBoardLikeService {
	
	@Autowired private IBoardLikeDAO boardLikeDAOImpl = null;
	
	
	// 등록(좋아요, 싫어요)
	@Override
	public void write(BoardLikeDTO boardLikeDTO) {
	// likeYn ==> Y, N
		
		// 무관심 처리
		if(boardLikeDTO.getLikeYn() == null || boardLikeDTO.getLikeYn().equals("")) {
			// 삭제
			this.remove(boardLikeDTO.getLikeId());
		}else {
			boardLikeDAOImpl.insertData(boardLikeDTO);
		}
		
	}

	// 무관심(데이터 삭제)
	@Override
	public void remove(Integer likeId) {

		boardLikeDAOImpl.deleteData(likeId);
	}

	@Override
	public void removeByDocId(Integer docId) {

		boardLikeDAOImpl.deleteByDocId(docId);
	}

}
