package kr.co.ksr1.commom.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



import kr.co.ksr1.user.dto.UserDTO;
import kr.co.ksr1.user.service.IUserService;


public class UserDetailService implements UserDetailsService {
	private static final Logger logger = LoggerFactory.getLogger( UserDetailService.class );

	@Autowired private IUserService userServiceImpl = null;
	
	@Override
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		UserDTO userDTO = null;
		try{
			logger.debug("id=================>"+loginId);
				 userDTO = userServiceImpl.viewByloingId(loginId);
			
			if( userDTO == null ) {
				logger.debug("member is null =================>");
//				익셉션을 강제로 발생시키는 것
				throw new BadCredentialsException("ID나 비밀번호가 잘못되었습니다.");
			}
		}catch(DataAccessException dae){
			dae.printStackTrace();
			logger.error("[ERROR]==>", dae);
			throw new DataAccessException("ERROR") {
				private static final long serialVersionUID = 1L;
			};
		}
		
		return new UserDetail(userDTO);
	}
	
}
