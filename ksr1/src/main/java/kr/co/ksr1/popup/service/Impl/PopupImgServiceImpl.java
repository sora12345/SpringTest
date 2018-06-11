package kr.co.ksr1.popup.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.commom.dao.BaseDaoSupport;
import kr.co.ksr1.popup.dao.IPopupImgDAO;

import kr.co.ksr1.popup.dto.PopupImgDTO;
import kr.co.ksr1.popup.service.IPopupImgService;

@Service
public class PopupImgServiceImpl extends BaseDaoSupport implements IPopupImgService {

	@Autowired IPopupImgDAO popupImgDAOImpl = null;
	
	@Override
	public void write(PopupImgDTO popupImgDTO) {
		
		popupImgDAOImpl.insertData(popupImgDTO);
	}

	@Override
	public List<PopupImgDTO> list(Integer popId) {

		return popupImgDAOImpl.selectList(popId);
	}

	@Override
	public void remove(Integer popImgId) {

		popupImgDAOImpl.deleteData(popImgId);
	}

}
