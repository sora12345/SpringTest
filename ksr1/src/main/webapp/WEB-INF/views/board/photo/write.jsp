<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<c:import url="/WEB-INF/views/inc/head.jsp" />
	
	<script type="text/javascript" src="${_ctx}/res/editor/js/service/HuskyEZCreator.js" charset="utf-8"></script>
	
	<script>
 		$(function () {
 		 	// 저장
 			$("#btnSave").click(function () {
 		 		if($("#frmWrite").valid()){
 		 			 // 에디터의 내용이 textarea에 적용된다.
 		 		    oEditors.getById["boardContents"].exec("UPDATE_CONTENTS_FIELD", []);
 		 			$("#frmWrite").submit();
 		 		}
 			});
 		 	// 웹에디터
 			var oEditors = [];
 			nhn.husky.EZCreator.createInIFrame({
 			    oAppRef: oEditors,
 			    elPlaceHolder: "boardContents",
 			    sSkinURI: "${_ctx}/res/editor/SmartEditor2Skin.html",
 			    fCreator: "createSEditor2"
 			});
 		});
 		// 첨부파일 추가
 		function addFile() {
			var appendingFileHtml = "<input type='file' name='files' style='width:80%'  onchange='fileCheck(this)' accept='image/gif, image/jpeg, image/png' /><img src='${_ctx}/res/images/button.jpg' style='width:20px; cursor:pointer'onclick='delFile(this)'/>";
			var len = $("td#tdFile>input[type=file]").length;
				if(len < 5){
						$("#tdFile").append(appendingFileHtml);		
				}else{
					alert("더이상 추가할 수 없습니다.");
				}
		}
 		// 첨부파일 input 삭제
 		function delFile(obj) {
 			$(obj).prev().remove(); //input remove
 			$(obj).remove();
			
		}
 		
 		function fileCheck(obj) {
 		    pathpoint = obj.value.lastIndexOf('.');
 		    filepoint = obj.value.substring(pathpoint+1,obj.length);
 		    filetype = filepoint.toLowerCase();
 		    if(filetype=='jpg' || filetype=='gif' || filetype=='png' || filetype=='jpeg' || filetype=='bmp') {

 		        // 정상적인 이미지 확장자 파일일 경우 ...

 		    } else {
 		        alert('이미지 파일만 선택할 수 있습니다.');

 		        parentObj  = obj.parentNode
 		        node = parentObj.replaceChild(obj.cloneNode(true),obj);

 		        return false;
 		    }
 		    if(filetype=='bmp') {
 		        upload = confirm('BMP 파일은 웹상에서 사용하기엔 적절한 이미지 포맷이 아닙니다.\n그래도 계속 하시겠습니까?');
 		        if(!upload) return false;
 		    }
 		}
 		
	</script>
</head>

<body>
<div id="wrap">
		<c:import url="/WEB-INF/views/inc/header.jsp" />
    <c:import url="/WEB-INF/views/inc/left.jsp" />
    
    <div id="rightWrap">
    
    	<div class="rightBlock">
            <div class="page_top">
                <h1>${boardMapDTO.mapName}(사진첩)</h1>
            </div>
            
            <div class="boardWrap">
                
                <form id="frmWrite" name="frmWrite" action="${_ctx}/board/photo/write.ssol" method="post" enctype="multipart/form-data">
                	
									<!-- 가져온 mapId 숨기기 -->
                	<input type= "hidden" name="mapId" id="mapId" value="${search.mapId}"/> 
								  <input type="hidden" name="page" id="page" value="${search.page}"/>
								  <input type="hidden" name="searchType"  value="${search.searchType}"/>
								  <input type="hidden" name="searchText"  value="${search.searchText}"/>
                                
	                <table class="base_tbl tbl_write">
	                	<tr>
	                        <th width="20%" class="t_color">제목입력</th>
	                        <td>
	                        <input type="text" name="title" required="required"/>
	                        </td>
	                    </tr>
	                    <tr>
	                        <th class="t_color">내용입력</th>
	                        <td><textarea name="boardContents" id="boardContents" required="required" style="font-family:sans-serif;"></textarea></td>
	                    </tr>
	                     <tr>
                        <th class="t_color">이미지<a href="javascript:addFile();" style="color:black; text-decoration: none; padding: 5px; border-radius: 10px; border: 1px solid black;">추가</a>
	                       </th>
	                       <td id ="tdFile">
	                       </td>
	                    </tr>
                	</table>
               </form>
	                
	                <div class="btnSet alignCenter">
	                    <a href="javascript:;" id="btnSave" class="disPB btnBase">저장</a>
	                    <a href="${_ctx}/board/photo/list.ssol?mapId=${search.mapId}" id="btnCancel" class="disPB btnBase">취소</a>
	                </div>
               
            </div>
        </div>
    
    </div>
    
	</div>
</body>
</html>
