package kr.co.ksr1;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.co.ksr1.user.dto.UserDTO;
import kr.co.ksr1.user.service.IUserService;



@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestUser {
	
	@Autowired
	private IUserService userService = null;
	
	@Test
	public void write() {
		
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(73);
		userDTO.setLoginId("dfdfdf11");
		userDTO.setLoginPw("5686");
		userDTO.setName("김아무개");
		userDTO.setPhone("010-5987-6874");
		userDTO.setEmail("ioioioio@nate.com");
		
		userService.write(userDTO);
	}
	
	@Test
	public void view() {
		
		UserDTO userDTO = userService.view(5);
		System.out.println(userDTO.toString());
	}
	
	@Test
	public void edit() {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(10);
		userDTO.setLoginPw("7777");
		userDTO.setName("신소라");
		userDTO.setPhone("010-7964-1111");
		userDTO.setEmail("flkdsfjl@hanmail.net");
		
		userService.edit(userDTO);
	}
	
	@Test
	public void remove() {
		userService.remove(95);
	}
	
	

}
