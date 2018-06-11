package kr.co.ksr1.popup.dao;import java.util.List;

import kr.co.ksr1.popup.dto.PopupImgDTO;

public interface IPopupImgDAO {
	
	public void insertData(PopupImgDTO popupImgDTO);
	public List<PopupImgDTO> selectList(Integer popId);
	public void deleteData(Integer popImgId);

}
