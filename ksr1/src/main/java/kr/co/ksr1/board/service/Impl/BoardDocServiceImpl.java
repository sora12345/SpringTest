package kr.co.ksr1.board.service.Impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ksr1.board.dao.IBoardCommentDAO;
import kr.co.ksr1.board.dao.IBoardDocDAO;
import kr.co.ksr1.board.dao.IBoardFileDAO;
import kr.co.ksr1.board.dao.IBoardLikeDAO;
import kr.co.ksr1.board.dao.IBoardNoticeDAO;
import kr.co.ksr1.board.dao.Impl.BoardDocDAOImpl;
import kr.co.ksr1.board.dao.Impl.BoardNoticeDAOImpl;
import kr.co.ksr1.board.dto.BoardDocDTO;
import kr.co.ksr1.board.dto.BoardFileDTO;
import kr.co.ksr1.board.dto.BoardSearchDTO;
import kr.co.ksr1.board.service.IBoardDocService;
import kr.co.ksr1.board.service.IBoardFileService;
import kr.co.ksr1.board.service.IBoardLikeService;
import kr.co.ksr1.commom.dao.BaseDaoSupport;
import kr.co.ksr1.commom.file.FileService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardDocServiceImpl implements IBoardDocService {

	@Autowired private IBoardDocDAO boardDocDAOImpl = null;
	@Autowired private IBoardFileDAO boardFileDAOImpl = null;
	@Autowired private IBoardCommentDAO boardCommentDAOImpl = null;
	@Autowired private IBoardFileService boardFileServiceImpl = null;
	@Autowired private IBoardLikeDAO boardLikeDAOImpl = null;
	@Autowired private FileService fileService = null;
	@Autowired private IBoardNoticeDAO boardNoticeDAOImpl = null;

	@Override
	@Transactional
	public void write(BoardDocDTO boardDocDTO, HttpSession session) {

		// 1. 게시물 insert
		boardDocDAOImpl.insertData(boardDocDTO);
		
		// 2. 게시물 파일 insert
		List<MultipartFile> fileList = boardDocDTO.getFiles();
		BoardFileDTO boardFileDTO = null;

		if (fileList != null) {

			for (MultipartFile file : boardDocDTO.getFiles()) {
				log.debug("name1================>" + file.getOriginalFilename());
				log.debug("name2=================>" + file.getSize());

				// 2. 첨부파일 물리적인 디스크에 저장
				boardFileDTO = fileService.uploadSingleFile(file, session);

				// 3. 첨부파일 디비에 insert
				boardFileDTO.setDocId(boardDocDTO.getDocId());
				boardFileServiceImpl.write(boardFileDTO);

			}
		}
		
		// 3. 공지사항 게시글 체크 및 insert
		
		if(boardDocDTO.getMapId()==5) {
			boardNoticeDAOImpl.insertData(boardDocDTO);
		}

	}

	@Override
	@Transactional
	public BoardDocDTO view(BoardDocDTO _boardDocDTO) {
		
		// 1. 조회수 증가
		this.editByCntRead(_boardDocDTO.getDocId());

		// 2. 조회
		BoardDocDTO boardDocDTO = boardDocDAOImpl.selectOne(_boardDocDTO);

		// 3. 첨부파일 가져오기
		List<BoardFileDTO> fileList = boardFileServiceImpl.list(_boardDocDTO.getDocId());
		boardDocDTO.setFileList(fileList);

		return boardDocDTO;
	}

	@Override
	@Transactional
	public void edit(BoardDocDTO boardDocDTO, HttpSession session) {

		// 1. 첨부파일 등록
		BoardFileDTO fileDTO = null;
		log.debug("getDocId()=====================>" + boardDocDTO.getFiles());

		if (boardDocDTO.getFiles() != null) {
			for (MultipartFile file : boardDocDTO.getFiles()) {
				log.debug("name1================>" + file.getOriginalFilename());
				log.debug("name2=================>" + file.getSize());

				// 2. 첨부파일 물리적인 디스크에 저장
				fileDTO = fileService.uploadSingleFile(file, session);

				// 3. 첨부파일 디비에 insert
				fileDTO.setDocId(boardDocDTO.getDocId());
				log.debug("fileDTO=====================>" + fileDTO);
				log.debug("fileDTO=====================>" + fileDTO);
				boardFileServiceImpl.write(fileDTO);
			}
		}
		if (boardDocDTO.getDelFiles() != null) {
			// 2.첨부파일 삭제
			for (Integer sno : boardDocDTO.getDelFiles()) {
				boardFileServiceImpl.remove(sno);
				log.debug("==========>" + sno);
			}
		}
		// 3. 수정
		boardDocDAOImpl.updateData(boardDocDTO);
		
		// 3. 공지사항 게시글 체크 및 edit
		
		if(boardDocDTO.getMapId()==5) {
			boardNoticeDAOImpl.updateData(boardDocDTO.getDocId());
		}
	}

	@Override
	public void remove(Integer docId) {

		boardFileDAOImpl.deleteByDocId(docId);
		boardCommentDAOImpl.deleteByDocId(docId);
		boardLikeDAOImpl.deleteByDocId(docId);
		boardDocDAOImpl.deleteData(docId);
		

	}

	@Override
	public List<BoardDocDTO> list(BoardSearchDTO boardSearchDTO) {

		// 1. 총 게시물 갯수
		boardSearchDTO.setTotal(boardDocDAOImpl.selectCount(boardSearchDTO));

		// 2. 목록
		List<BoardDocDTO> list = boardDocDAOImpl.selectList(boardSearchDTO);

		for (BoardDocDTO boardDocDTO : list) {
			if (boardDocDTO.getCntFile() > 0) {
				List<BoardFileDTO> fileList = boardFileServiceImpl.list(boardDocDTO.getDocId());
				boardDocDTO.setFileList(fileList);
			}
		}
		return list;
	}

	@Override
	public void editByCntRead(Integer docId) {

		boardDocDAOImpl.updateByCntRead(docId);

	}

	@Override
	public List<BoardDocDTO> listByUserId(BoardSearchDTO boardSearchDTO) {

		boardSearchDTO.setTotal(boardDocDAOImpl.selectCountByUserId(boardSearchDTO));

		// 2. 목록
		List<BoardDocDTO> list = boardDocDAOImpl.selectListByUserId(boardSearchDTO);

		for (BoardDocDTO boardDocDTO : list) {
			if (boardDocDTO.getCntFile() > 0) {
				List<BoardFileDTO> fileList = boardFileServiceImpl.list(boardDocDTO.getDocId());
				boardDocDTO.setFileList(fileList);
			}
		}
		return list;

	}

	@Override
	public List<BoardDocDTO> listMyUserId(Integer userId) {

		// 2. 목록
		List<BoardDocDTO> list = boardDocDAOImpl.selectListMyUserId(userId);

		for (BoardDocDTO boardDocDTO : list) {
			if (boardDocDTO.getCntFile() > 0) {
				List<BoardFileDTO> fileList = boardFileServiceImpl.list(boardDocDTO.getDocId());
				boardDocDTO.setFileList(fileList);
			}
		}
		return list;

	}

	@Override
	public List<BoardDocDTO> listTotal(BoardSearchDTO boardSearchDTO) {

		boardSearchDTO.setTotal(boardDocDAOImpl.selectCountTotal(boardSearchDTO));

		// 2. 목록
		List<BoardDocDTO> list = boardDocDAOImpl.selectListTotal(boardSearchDTO);

		for (BoardDocDTO boardDocDTO : list) {
			if (boardDocDTO.getCntFile() > 0) {
				List<BoardFileDTO> fileList = boardFileServiceImpl.list(boardDocDTO.getDocId());
				boardDocDTO.setFileList(fileList);
			}
		}
		return list;

	}

	@Override
	public List<BoardDocDTO> listMyLikeY(BoardSearchDTO boardSearchDTO) {
		

		boardSearchDTO.setLikeYn("Y");
		
		List<BoardDocDTO> list = boardDocDAOImpl.selectListMyLike(boardSearchDTO);
		
		for (BoardDocDTO boardDocDTO : list) {
			if (boardDocDTO.getCntFile() > 0) {
				List<BoardFileDTO> fileList = boardFileServiceImpl.list(boardDocDTO.getDocId());
				boardDocDTO.setFileList(fileList);
			}
		}
		
		return list;
	}
	
	@Override
	public List<BoardDocDTO> listMyLikeN(BoardSearchDTO boardSearchDTO) {
		
		boardSearchDTO.setLikeYn("N");

		List<BoardDocDTO> list = boardDocDAOImpl.selectListMyLike(boardSearchDTO);
		
		for (BoardDocDTO boardDocDTO : list) {
			if (boardDocDTO.getCntFile() > 0) {
				List<BoardFileDTO> fileList = boardFileServiceImpl.list(boardDocDTO.getDocId());
				boardDocDTO.setFileList(fileList);
			}
		}
		
		return list;
	}

}
