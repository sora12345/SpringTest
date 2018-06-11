package kr.co.ksr1.user.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.co.ksr1.user.dao.IUserDAO;
import kr.co.ksr1.user.dto.UserDTO;
import kr.co.ksr1.user.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired private IUserDAO userDAO = null;
	
	@Override
	public void write(UserDTO userDTO) {
		
		userDAO.insertData(userDTO);
	}

	@Override
	public UserDTO view(Integer userId) {
		return userDAO.selectOne(userId);
	}

	@Override
	public void edit(UserDTO userDTO) {
		// TODO Auto-generated method stub
		userDAO.updateData(userDTO);
		
	}

	@Override
	public void remove(Integer userId) {
		// TODO Auto-generated method stub
		userDAO.deleteData(userId);
	}

	@Override
	public int checkLoginId(String loginId) {
		// TODO Auto-generated method stub
		int cnt = userDAO.selectCountByLoginId(loginId);
		if(cnt > 0) {
			// 사용할 수 없음
			return 0;
		}else {
			// 사용할 수 있음
			return 1;
		}
		 
	}

	@Override
	public int checkPhone(String phone) {
		// TODO Auto-generated method stub
		int cnt = userDAO.selectCountByPhone(phone);
		if(cnt > 0) {
			return 0;
		}else {
			return 1;
		}
		
	}

	@Override
	public int checkEmail(String email) {
		// TODO Auto-generated method stub
		int cnt = userDAO.selectCountByEmail(email);
		if(cnt > 0) {
			return 0;
		}else {
			return 1;
		}
		
	}

	@Override
	public UserDTO viewByloingId(String loginId) {
		// TODO Auto-generated method stub
		return userDAO.selectOneByLoginId(loginId);
	}

}
