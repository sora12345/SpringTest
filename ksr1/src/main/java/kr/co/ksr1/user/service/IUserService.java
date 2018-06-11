package kr.co.ksr1.user.service;

import kr.co.ksr1.user.dto.UserDTO;

public interface IUserService {
	
	public void write(UserDTO userDTO);
	public UserDTO view(Integer userId);
	public UserDTO viewByloingId(String loginId);	
	public void edit(UserDTO userDTO);
	public void remove(Integer userId);
	public int checkLoginId(String loginId);
	public int checkPhone(String phone);
	public int checkEmail(String email);


}
