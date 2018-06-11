package kr.co.ksr1.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardLikeDTO {
	
	private Integer likeId = null;		// 좋아요ID
	private Integer docId = null;		// 게시물ID
	private Integer userId = null;		// 사용자ID
	
	private String likeYn = null;		// 좋아요여부
	
	private Date reg_dt = null;			// 등록일

}
