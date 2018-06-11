package kr.co.ksr1.popup.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import kr.co.ksr1.popup.dto.PopupDTO;

public interface IPopupService {
	
	public void write(PopupDTO popupDTO,HttpSession session);
	public void edit(PopupDTO popupDTO,HttpSession session);
	public void remove(Integer popId);
	
	public PopupDTO view(Integer popId);
	public List<PopupDTO> list();
	

}
