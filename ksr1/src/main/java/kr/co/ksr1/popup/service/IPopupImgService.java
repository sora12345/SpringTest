package kr.co.ksr1.popup.service;

import java.util.List;

import kr.co.ksr1.popup.dto.PopupImgDTO;

public interface IPopupImgService {
	
	public void write(PopupImgDTO popupImgDTO);
	public List<PopupImgDTO> list(Integer popId);
	public void remove(Integer popImgId);
	
	

}
