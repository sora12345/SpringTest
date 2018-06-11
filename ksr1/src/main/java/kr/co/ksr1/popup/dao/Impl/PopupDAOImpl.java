package kr.co.ksr1.popup.dao.Impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.commom.dao.BaseDaoSupport;
import kr.co.ksr1.popup.dao.IPopupDAO;
import kr.co.ksr1.popup.dto.PopupDTO;

@Repository
public class PopupDAOImpl extends BaseDaoSupport implements IPopupDAO {

	@Override
	public void insertData(PopupDTO popupDTO) {
		
		this.getSqlSession().insert("Popup.insertData", popupDTO);
	}

	@Override
	public void updateData(PopupDTO popupDTO) {
		
		this.getSqlSession().update("Popup.updateData", popupDTO);
	}

	@Override
	public void deleteData(Integer popId) {
		
		this.getSqlSession().delete("Popup.deleteData", popId);
	}

	@Override
	public PopupDTO selectOne(Integer popId) {
		
		return this.getSqlSession().selectOne("Popup.selectOne", popId);
	}

	@Override
	public List<PopupDTO> list() {
		
		return this.getSqlSession().selectList("Popup.slelectList");
	}

}
