package kr.co.ksr1.board.dto;

import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import kr.co.ksr1.user.dto.UserDTO;
import lombok.Data;

@Data
public class BoardDocDTO extends UserDTO {
	
	private Integer cntRead = null;				// 조회수
	private Integer cntFile = null;				// 첨부파일 갯수
	private Integer cntComment = null;			// 댓글 갯수
	private Integer cntComment2 = null;			// 댓글 갯수
	
	private Integer cntLikeY = null;			// 좋아요 갯수
	private Integer cntLikeN = null;			// 싫어요 갯수

	private Integer docId = null;				// 게시물ID
	private Integer mapId = null;				// 맵ID
	private Integer userId	= null;				// 사용자ID
	private Integer likeId	= null;				// 좋아요ID
	private Integer viewerId = null;			// 조회
	private Integer userName = null;			// 사용자이름
	
	private String noticeYn	= null;				// 좋아요ID
	private String likeYn	= null;				// 좋아요ID
	private String mapName = null;				// 맵이름
	private String title = null;				// 제목
	private String boardContents = null;		// 내용
	private String secretWritePw = null;
	private String secretWriteYn = null;
	
	private List<MultipartFile> files = null;	// 첨부파일
	private List<BoardFileDTO> fileList =null;  // 첨부파일 목록
	
	private List<Integer> delFiles =null;  		// 삭제할 첨부파일 SNO	

	private Date regDt = null;					// 등록일
}
