package kr.co.ksr1.popup.service.Impl;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.co.ksr1.commom.file.FileService;
import kr.co.ksr1.popup.dao.IPopupDAO;
import kr.co.ksr1.popup.dto.PopupDTO;
import kr.co.ksr1.popup.dto.PopupImgDTO;
import kr.co.ksr1.popup.service.IPopupImgService;
import kr.co.ksr1.popup.service.IPopupService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PopupServiceImpl implements IPopupService {

	@Autowired
	private IPopupDAO popupDAOImpl = null;
	@Autowired
	private FileService fileService = null;
	@Autowired
	private IPopupImgService popupImgServiceImpl = null;

	@Override
	public void write(PopupDTO popupDTO, HttpSession session) {

		// 1. 게시물 insert
		popupDAOImpl.insertData(popupDTO);

		PopupImgDTO popupImgDTO = null;

		log.debug("===========>popupDTO.getFiles()" + popupDTO.getFiles());

		if (popupDTO.getFiles() != null) {

			for (MultipartFile file : popupDTO.getFiles()) {

				// 2. 첨부파일 물리적인 디스크에 저장
				popupImgDTO = fileService.uploadSingleImg(file, session);

				// 3. 첨부파일 디비에 insert
				popupImgDTO.setPopId(popupDTO.getPopId());
				popupImgServiceImpl.write(popupImgDTO);

			}
		}
	}

	@Override
	public void edit(PopupDTO popupDTO, HttpSession session) {

		// 1. 게시물 insert

		PopupImgDTO popupImgDTO = null;

		log.debug("===========>popupDTO.getFiles()" + popupDTO.getFiles());

		if (popupDTO.getFiles() != null) {
			for (MultipartFile file : popupDTO.getFiles()) {

				// 2. 첨부파일 물리적인 디스크에 저장
				popupImgDTO = fileService.uploadSingleImg(file, session);

				// 3. 첨부파일 디비에 insert
				popupImgDTO.setPopId(popupDTO.getPopId());
				popupImgServiceImpl.write(popupImgDTO);

			}
		}

		// 2. 첨부파일 삭제
		log.debug("popupDTO의 sno : " + popupDTO);
		if (popupDTO.getDelFiles() != null) {
			for (Integer sno : popupDTO.getDelFiles()) {
				popupImgServiceImpl.remove(sno);
				log.debug("sno 왜 안뜸? 이해가 안됨: " + sno);
			}
		}
		popupDAOImpl.updateData(popupDTO);
	}

	@Override
	public void remove(Integer popId) {

		popupDAOImpl.deleteData(popId);
	}

	@Override
	public PopupDTO view(Integer popId) {

		PopupDTO popupDTO = popupDAOImpl.selectOne(popId);

		List<PopupImgDTO> fileList = popupImgServiceImpl.list(popId);
		popupDTO.setFileList(fileList);

		return popupDTO;
	}

	@Override
	public List<PopupDTO> list() {

		// 2. 게시물 목록 가져오기
		List<PopupDTO> list = popupDAOImpl.list();
		for (PopupDTO popupDTO : list) {
			// 첨부파일 갯수가 0 이상 일 경우만 첨부파일 목록을 가져온다

			List<PopupImgDTO> fileList = popupImgServiceImpl.list(popupDTO.getPopId());
			popupDTO.setFileList(fileList);

		}
		return list;

	}
}
