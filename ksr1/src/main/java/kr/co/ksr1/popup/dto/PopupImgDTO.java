package kr.co.ksr1.popup.dto;

import java.util.Date;

import lombok.Data;

@Data
public class PopupImgDTO {
	
	private Integer popImgId	= null;
	private Integer popId	= null;
	
	private long imgSize;
	
	private String orgImgName = null;
	private String newImgName = null;
	private String imgType = null;
	private String imgPath = null;
	private String imgExt  = null;
	
	private Date regDt = null;
	
}
