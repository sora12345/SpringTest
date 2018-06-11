package kr.co.ksr1.popup.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.commom.dao.BaseDaoSupport;
import kr.co.ksr1.popup.dao.IPopupImgDAO;
import kr.co.ksr1.popup.dto.PopupImgDTO;

@Repository
public class PopupImgDAOImpl extends BaseDaoSupport implements IPopupImgDAO {

	@Override
	public void insertData(PopupImgDTO popupImgDTO) {
		
		this.getSqlSession().insert("PopupImg.insertData", popupImgDTO);
	}

	@Override
	public List<PopupImgDTO> selectList(Integer popId) {
		
		return this.getSqlSession().selectList("PopupImg.selectList", popId);
	}

	@Override
	public void deleteData(Integer popImgId) {

		this.getSqlSession().delete("PopupImg.deleteData", popImgId);
	}

}
