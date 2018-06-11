<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />

<style>
  body {
   font-family: Arial, Helvetica, sans-serif;
  }
  
  table {
   font-size: 1em;
  }
  
  .ui-draggable, .ui-droppable {
   background-position: top;
  }
    label, input { display:block; }
    input.text { margin-bottom:12px; width:95%; padding: .4em; }
    fieldset { padding:0; border:0; margin-top:25px; }
    h1 { font-size: 1.2em; margin: .6em 0; }
    div#users-contain { width: 350px; margin: 20px 0; }
    div#users-contain table { margin: 1em 0; border-collapse: collapse; width: 100%; }
    div#users-contain table td, div#users-contain table th { border: 1px solid #eee; padding: .6em 10px; text-align: left; }
    .ui-dialog .ui-state-error { padding: .3em; }
    .validateTips { border: 1px solid transparent; padding: 0.3em; }
    
    .btnSet>span{float: left; margin-top: 30px;}
    .btnSet>span>div{float: left; width: 30px; height: 30px; background-repeat: no-repeat; background-size:cover;}
    .btnSet>span>div>span{width: 100%; height: 100%; display: inline-block; text-align: center; line-height: 220%; color: white;}
</style>

<script>

		$(function () {
				// 좋아요
				if("${boardDocDTO.likeYn}" == "Y"){
					$("#btnLike").attr("src", "${_ctx}/res/images/likeY.png");
					$("#btnLike").attr("title", "좋아요");
					
				// 싫어요
				}else if("${boardDocDTO.likeYn}" == "N"){
					$("#btnLike").attr("src", "${_ctx}/res/images/likeN.png");
					$("#btnLike").attr("title", "싫어요");
				
					// 무관심
				}else if("${boardDocDTO.likeYn}" == ""){
					$("#btnLike").attr("src", "${_ctx}/res/images/like.png");
				}
		
			// 좋아요(Y), 싫어요(N), 무관심
			$("#btnLike").click(function(){
				var likeYn = $(this).attr("data-like-yn");
				var likeId = $(this).attr("data-like-id");
				console.log(likeId);
				console.log(likeYn);
				var url = "${_ctx}/board/like/like.ssol";
				var param = {"docId":"${boardDocDTO.docId}", "likeId":likeId,  "likeYn" : ""};
				
				// 현재 좋아요 ==> 싫어요
				if(likeYn == "Y"){
					param.likeYn = "N";
					
				// 현재 싫어요 ==> 무관심(데이터 삭제)
				}else if(likeYn == "N"){
					param.likeYn = "";
					
				// 현재 무관심 ==> 좋아요
				}else if(likeYn == ""){
					param.likeYn = "Y";
				}
				
				$.post(url, param, function(data){
					if(data.code == 9){
						location.reload();
					}
				});
			});
		});

		// 조회페이지 이동
 		function goList(mapId) {
 			//목록으로 다시 이동
			$("#mapId").val(mapId);
			$("#frmList").submit();
		}
			
		$(function () {
			
			// 댓글등록 클릭시
			$("#commentWrap").on("click","#btnComment", function () {
				var comments = $("#comments").val();

				var url = "${_ctx}/board/comment/write.ssol";
				var params = {
						docId:"${boardDocDTO.docId}"
						,	comments:comments
						};
				$.post(url, params, function(data){
					listComment();	
				});
			});
			
			// 댓글 input에 엔터를 치면 댓글 등록
			$("#commentWrap").on("keydown", "#comments", function (e) {
				if(e.keyCode==13){
					$("#btnComment").click();
				}
			});
			
			
			listComment();
			
		});
		
		// 댓글 목록 가져오기
		function listComment() {
			var url = "${_ctx}/board/comment/list.ssol?docId=${boardDocDTO.docId}";
			$.get(url, function (html) {
				$("#commentWrap").html(html);
			});
		}
		
		function deleteComment(commentId) {
			var url = "${_ctx}/board/comment/list.ssol";
			$.post(url,{commentId:commentId}, function(){
				listComment();
			});
		}
		
		// 사용자 정보 목록 가져오기
		function popupDialog() {
			var url = "${_ctx}/board/dialog/userInfoView.ssol?userId=${boardDocDTO.userId}";
			$.get(url, function (html) {
				$("#dialog").html(html);
			});
		}
</script>
</head>

<body>

<div id="dialog" >
<!-- 팝업띄우기 -->
</div>

<div id="wrap">
		<c:import url="/WEB-INF/views/inc/header.jsp" />
		<c:import url="/WEB-INF/views/inc/left.jsp" />
    <div id="rightWrap">
    	<div class="rightBlock">
            <div class="page_top">
                <h1>${boardMapDTO.mapName}</h1>
            </div>
            
            <div class="boardWrap">
                
                <table class="base_tbl">
                    <thead>
                        <tr>
                            <th colspan="8" class="t_color">
                            	<c:out value="${item.title}" escapeXml="true">${boardDocDTO.title}</c:out>
                            </th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        	<td width="10%">작성자</td>
                            <td width="20%" class="t_color" id="viewUser" style="cursor: pointer;" onclick="popupDialog();">${boardDocDTO.name}</td>
                            <td width="10%">작성일</td>
                            <td width="20%" class="t_color"><fmt:formatDate value="${boardDocDTO.regDt}" pattern= "yyyy-MM-dd a hh:mm:ss"/></td>
                            <td width="10%">조회수</td>
                            <td width="20%" class="t_color">${boardDocDTO.cntRead}</td>
                            <c:choose>
	                            <c:when test="${search.mapId eq 5 and _user.role ne 'USER'}">
		                            <td width="10%">공지글여부</td>
		                            <td width="20%" class="t_color">${boardDocDTO.noticeYn}</td>
	                            </c:when>
	                            <c:otherwise>
	                            	<td width="10%">비밀글여부</td>
	                            	<td width="20%" class="t_color">${boardDocDTO.secretWriteYn}</td>
	                            </c:otherwise>
                            </c:choose>
                        </tr>
                         	<c:forEach items="${boardDocDTO.fileList}" var="file">
		                        <tr>
		                          	<td width="8%">첨부파일</td>
		                            <td colspan="5" class="txtCut alignLeft"><a href="${_ctx}/file/downloadFile.ssol?docId=${file.docId}&fileSno=${file.fileSno}">${file.orgFileName}</a></td>
		                        </tr>
                         	</c:forEach>
                        <tr>
                            <td colspan="8" class="alignLeft">${boardDocDTO.boardContents}</td>
                        </tr>
                    </tbody>
                </table>
                <div class="btnSet">
                		<span>
                			<div style="background-image: url('${_ctx}/res/images/likeY.png');" title="좋아요">
                				<span>${boardDocDTO.cntLikeY}</span>
                			</div>
                			<div>
                				<img src= "${_ctx}/res/images/like.png" id ="btnLike" data-like-yn="${boardDocDTO.likeYn}" data-like-id="${boardDocDTO.likeId}" style="width:30px; cursor: pointer;"/>
                			</div>
                			<div style="background-image: url('${_ctx}/res/images/likeN.png');" title="싫어요">
                				<span>${boardDocDTO.cntLikeN}</span>
                			</div>
                		</span>
                    <a href="javascript:goList('${boardDocDTO.mapId}');" class="disPB btnBase">목록</a>
                    <a href="${_ctx}/board/doc/write.ssol?mapId=${boardDocDTO.mapId}" class="disPB btnBase">글쓰기</a>
                    <c:if test="${userDTO.userId == boardDocDTO.userId}">
<%--                     <input type="hidden"  id="userId" value="${userDTO.userId}"/> --%>
<%--                     <input type="hidden"  id="userId2" value="${boardDocDTO.userId}"/> --%>
<%--                     <input type="hidden"  id="b" value="${userDTO.userId == boardDocDTO.userId}"/> --%>
                    	<a href="${_ctx}/board/doc/edit.ssol?mapId=${boardDocDTO.mapId}&docId=${boardDocDTO.docId}&noticeYn=${boardDocDTO.noticeYn}" class="disPB btnBase">수정</a>
                    	<a href="${_ctx}/board/doc/remove.ssol?mapId=${boardDocDTO.mapId}&docId=${boardDocDTO.docId}" class="disPB btnBase">삭제</a>
                		</c:if>
                </div>
                
                <div class="replyWrap" id="commentWrap">
								<!-- 댓글보이기 -->
                </div>          
            </div>
        </div>
    </div>
</div>
	<form id="frmList"  name="frmList" action="${_ctx}/board/doc/list.ssol" method="get" >
		<input type="hidden" name="mapId" id="mapId" />
	  <input type="hidden" name="page" id="page" value="${search.page}"/>
	  <input type="hidden" name="searchType"  value="${search.searchType}"/>
	  <input type="hidden" name="searchText"  value="${search.searchText}"/>
	</form>
</body>
</html>

