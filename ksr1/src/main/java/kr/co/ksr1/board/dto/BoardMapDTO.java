package kr.co.ksr1.board.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardMapDTO {
	
	private Integer mapId = null;		// 맵ID
	private Integer mapSort = null;		// 순번
	private Integer parMapId = null;
	
	private String mapName	= null;		// 맵이름
	private String mapType = null;		// 맵타입
	private String delYn = null;		// 삭제여부
	private String commentYn = null;	// 댓글여부
	private String fileYn = null;		// 첨부파일여부
	
	private Date regDt	= null;			//등록일
	
	

	
	
	
	
	
	


}
