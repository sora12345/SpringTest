package kr.co.ksr1.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardFileDTO {
	
	private long fileSize = 0;		// 파일크기
	
	private Integer docId = null;			// 게시물ID
	private Integer fileSno	= null;			// 첨부파일_SNO
	
	private String orgFileName	= null;		// ORG_파일명
	private String newFileName	= null;		// NEW_파일명
	private String fileType	= null;			// 파일타입
	private String filePath	= null;			// 파일경로
	private String fileExt = null;			// 확장자
	
	private Date regDt = null;				// 등록일
}








