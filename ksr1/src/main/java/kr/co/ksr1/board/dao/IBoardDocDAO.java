package kr.co.ksr1.board.dao;

import java.util.List;

import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;

public interface IBoardDocDAO {
	
	public void insertData(BoardDocDTO boardDocDTO);
	public BoardDocDTO selectOne(BoardDocDTO _boardDocDTO);
	public void updateData(BoardDocDTO boardDocDTO);
	public void deleteData(Integer docId);
	public void updateByCntRead(Integer docId);
	
	public List<BoardDocDTO> selectList(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> selectListByUserId(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> selectListMyUserId(Integer userId);
	public List<BoardDocDTO> selectListTotal(BoardSearchDTO boardSearchDTO);
	public List<BoardDocDTO> selectListMyLike(BoardSearchDTO boardSearchDTO);
	
	public int selectCount(BoardSearchDTO search);
	public int selectCountTotal(BoardSearchDTO search);
	public int selectCountByUserId(BoardSearchDTO search);


}
