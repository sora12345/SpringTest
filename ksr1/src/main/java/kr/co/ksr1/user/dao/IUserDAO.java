package kr.co.ksr1.user.dao;

import kr.co.ksr1.user.dto.UserDTO;

public interface IUserDAO {

	public void insertData(UserDTO userDTO);
	public UserDTO selectOne(Integer userId);
	public UserDTO selectOneByLoginId(String loginId);
	public void updateData(UserDTO userDTO);
	public void deleteData(Integer userId);
	public int selectCountByLoginId(String loginId);
	public int selectCountByPhone(String phone);
	public int selectCountByEmail(String email);
}
