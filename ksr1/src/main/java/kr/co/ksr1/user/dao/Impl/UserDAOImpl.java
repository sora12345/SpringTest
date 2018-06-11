package kr.co.ksr1.user.dao.Impl;

import org.springframework.stereotype.Repository;

import kr.co.ksr1.commom.dao.BaseDaoSupport;
import kr.co.ksr1.user.dao.IUserDAO;
import kr.co.ksr1.user.dto.UserDTO;

@Repository
public class UserDAOImpl extends BaseDaoSupport implements IUserDAO {

	@Override
	public void insertData(UserDTO userDTO) {
		
		this.getSqlSession().insert("User.insertData", userDTO);
	}

	@Override
	public UserDTO selectOne(Integer userId) {
		return this.getSqlSession().selectOne("User.selectOne", userId);
	}

	@Override
	public void updateData(UserDTO userDTO) {
		// TODO Auto-generated method stub
		this.getSqlSession().update("User.updateData", userDTO);
		
	}

	@Override
	public void deleteData(Integer userId) {
		// TODO Auto-generated method stub
		this.getSqlSession().delete("User.deleteData", userId);
	}

	@Override
	public int selectCountByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("User.selectCountByLoginId", loginId);
	}

	@Override
	public int selectCountByPhone(String phone) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("User.selectCountByPhone", phone);
	}

	@Override
	public int selectCountByEmail(String email) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("User.selectCountByEmail", email);
	}

	@Override
	public UserDTO selectOneByLoginId(String loginId) {
		// TODO Auto-generated method stub
		return this.getSqlSession().selectOne("User.selectOneByLoginId", loginId);
	}
	

	
	

}
