package kr.co.ksr1.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.co.ksr1.commom.dto.ResponseDTO;
import kr.co.ksr1.user.dto.UserDTO;
import kr.co.ksr1.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class joinController {
	
	@Autowired
	private IUserService userServiceImpl = null;
	
	// 회원가입 페이지로 이동(GET과 POST로 구분)
	@RequestMapping(value="/join.ssol", method=RequestMethod.GET)
	public void gojoin() {
	}
	
	// 회원가입
	@ResponseBody
	@RequestMapping(value="/join.ssol", method=RequestMethod.POST)
	public String dojoin(UserDTO userDTO) {
		log.debug("userDTO =========>"+userDTO);
		
		userServiceImpl.write(userDTO);
		
		return "s";
	}
	
	//아이디 중복 체크
	@ResponseBody
	@RequestMapping(value="/check/id.ssol", method=RequestMethod.GET)
	public ResponseDTO checkId(String loginId) {
		log.debug("loginId==========>"+loginId);
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		try {
			int result = userServiceImpl.checkLoginId(loginId);
			responseDTO.setCode(result);
			
			// 아이디 사용 가능
			if(result==1) {
				responseDTO.setMsg("사용가능한 아이디입니다.");
			// 아이디 사용 불가능
			}else {
				responseDTO.setMsg("중복된 아이디 입니다.");
			}
		}catch(Exception e) {
			responseDTO.setCode(-1);
			responseDTO.setMsg("에러가 발생했습니다. 관리자에게 문의하세요.");
		}
		
		
		return responseDTO;
	}
	//핸드폰 중복 체크
	@ResponseBody
	@RequestMapping(value="/check/phone.ssol", method=RequestMethod.GET)
	public ResponseDTO checkPhone(String phone) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		try {
			int result = userServiceImpl.checkPhone(phone);
			responseDTO.setCode(result);
			
			if(result==1) {
				responseDTO.setMsg("가입가능한 핸드폰입니다.");
			}else {
				responseDTO.setMsg("중복된 핸드폰입니다.");
			}
		}catch(Exception e) {
			responseDTO.setCode(-1);
			responseDTO.setMsg("에러가 발생했습니다. 관리자에게 문의하세요");
		}
		
		return responseDTO;
	
	}
	// 이메일 중복 체크
	@ResponseBody
	@RequestMapping(value="/check/email.ssol", method=RequestMethod.GET)
	public ResponseDTO checkEmail(String email) {
		
		ResponseDTO responseDTO = new ResponseDTO();
		
		try {
			int result = userServiceImpl.checkEmail(email);
			responseDTO.setCode(result);
			
			if(result==1) {
				responseDTO.setMsg("가입가능한 이메일입니다.");
			}else {
				responseDTO.setMsg("중복된 이메일입니다.");
			}
		}catch (Exception e) {
			// TODO: handle exception
			responseDTO.setCode(-1);
			responseDTO.setMsg("에러가 발생했습니다. 관리자에게 문의하세요");
		}
		
		return responseDTO;
	}
}
