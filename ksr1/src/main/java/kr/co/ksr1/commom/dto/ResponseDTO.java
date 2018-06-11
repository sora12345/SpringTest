package kr.co.ksr1.commom.dto;

import lombok.Data;

@Data
public class ResponseDTO {
	public int code = 9;		// 9:성공
	public String msg = "정상적으로 처리되었습니다.";

}
