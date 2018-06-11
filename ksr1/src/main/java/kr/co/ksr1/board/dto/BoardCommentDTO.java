package kr.co.ksr1.board.dto;

import java.util.Date;

import kr.co.ksr1.user.dto.UserDTO;
import lombok.Data;

@Data
public class BoardCommentDTO extends UserDTO{
	
	private Integer commentId =null;	// 댓글ID
	private Integer docId = null;		// 게시물ID
	private Integer userId = null;		// 사용자ID
	
	private String comments =null;		// 댓글내용
	
	private Date regDt = null;			// 등록일

}
