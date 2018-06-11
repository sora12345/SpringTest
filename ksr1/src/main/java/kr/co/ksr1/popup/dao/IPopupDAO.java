package kr.co.ksr1.popup.dao;

import java.util.List;

import kr.co.ksr1.popup.dto.PopupDTO;

public interface IPopupDAO {
	
	public void insertData(PopupDTO popupDTO);
	public void updateData(PopupDTO popupDTO);
	public void deleteData(Integer popId);
	
	public PopupDTO selectOne(Integer popId);
	public List<PopupDTO> list();
	

}
