package kr.co.ksr1.popup.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.ksr1.board.dto.BoardFileDTO;
import lombok.Data;

@Data
public class PopupDTO {
	
	private Integer popId = null;

	private String popTitle = null;
	private String popupYn = null;
	
	private Date regDt = null;
	
	private List<MultipartFile> files = null;	// 첨부파일
	private List<PopupImgDTO> fileList =null;  // 첨부파일 목록
	private List<Integer> delFiles = null;		// 삭제할 첨부파일 sno
	


}
