package kr.co.ksr1.user.dto;

import java.util.Date;

import lombok.Data; //getter setter 를 자동으로 만들어줌.

@Data
public class UserDTO {
	
	private Integer userId = null;	// 사용자ID
	private Integer status = null;	//	STATUS(상태)
									//	0 : 대기
									//	3 : IP차단
									//	4 : 탈퇴
									//	5 : 정지
									//	9 : 정상
	
	private String loginId = null;	// 로그인ID
	private String loginPw = null;	// 로그인PW
	private String name = null;		// 이름
	private String phone = null;	// 핸드폰
	private String email = null;	// 이메일
	private String role = "USER";	// ROLE(롤)
									// USER : 사용자
									// MANAGER : 매니저
									// ADMIN : 관리자
									// SUPER_ADMIN : 최고 관리자
			
	private Date regDt = null;		// 등록일

}
