<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagetag" uri="/WEB-INF/tlds/pagetag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />
<style>
		.divLike{text-align: center; display: flex; justify-content: center;}
    .divLike>div{width: 30px; height: 30px; background-repeat: no-repeat; background-size:cover;}
    .divLike>div>span{width: 100%; height: 100%; display: inline-block; text-align: center; line-height: 190%; color: white;}
</style>
	
<script>
 		$(function () {
 			
 			// 검색 타입 유지
 			$("#searchType > option[value='${search.searchType}']").attr("selected", true);
 			
 			// 글쓰기 버튼 클릭시
 		 	$("#btnWrite").click(function () {
 				document.location.href="${_ctx}/board/notice/write.ssol?mapId=${boardMapDTO.mapId}";
 			});
 		});
 		// 페이지 이동
 		function goPage(page) {
 		$("#page").val(page);
 		$("#frmsearch").submit();
 		}
 		
 		// 조회페이지 이동
 		function goView(docId) {
			$("#frmView input[name='docId']").val(docId);
			$("#frmView").submit();
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
                <h1>${boardMapDTO.mapName}</h1>
            </div>
            <!-- 검색 시작 -->
            <form id="frmsearch"  name="frmsearch" action="${_ctx}/board/notice/list.ssol" method="get" class="search_area">
            		<input type="hidden" name="page"  id="page" value="${search.page}"/>
                <dl>
                    <dd>
                        <select name="searchType" id="searchType" style="height: 23px;">
                            <option value="">:: 검색조건 ::</option>
                            <option value="T">제목</option>
                            <option value="C">내용</option>
                            <option value="TC">제목+내용</option>
                            <option value="U">작성자</option>
                        </select>
                    </dd>
                    <dd>
                    	<input type="text" name="searchText" placeholder="검색어" style="height: 23px;" value="${search.searchText}"/>
                    </dd>
                    <dd>
                    	<div class="btnSet">
                				<a href="javascript:goPage('1');" class="disPB btnBase" style="padding: 3px;">검색</a>
                			</div>
                		</dd>	
                </dl>            
            </form>
            <!-- 검색 끝 -->
            
            <div class="boardWrap">
                
                <table class="base_tbl">
                    <thead>
                        <tr>
                            <th width="8%">번호</th>
                            <th>제목</th>
                            <th width="10%">좋아요/싫어요</th>
                            <th width="10%">작성자</th>
                            <th width="15%">등록일자</th>
                            <th width="8%">첨부파일</th>
                            <th width="10%">조회수</th>
                            <th width="10%">공지글 여부</th>
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${list}" var="item">
                        <tr>
                            <td>${item.docId}</td>
                            <td class="txtCut alignLeft">
	                            <a href="javascript:goView('${item.docId}');">
	                            	<c:out value="${item.title}" escapeXml="true"></c:out>  
	                            </a>
                            <c:if test="${item.cntComment2 > 0}">(${item.cntComment2})</c:if>
                          
                            </td>
                            <td class="divLike">
                            	<div style="background-image: url('${_ctx}/res/images/likeY.png');" title="좋아요">
                            		<span>${item.cntLikeY}</span>
                            	</div>
                            	<div style="background-image: url('${_ctx}/res/images/likeN.png');" title="싫어요">
                            		<span>${item.cntLikeN}</span>
                            	</div>
                            </td>
                            <td>${item.name}</td>
                            <td><fmt:formatDate value="${item.regDt}" pattern= "yyyy-MM-dd a hh:mm:ss"/></td>
                            
                            <td>
                            <c:forEach items="${item.fileList}" var="file">
	                            <a href="${_ctx}/file/downloadFile.ssol?docId=${file.docId}&fileSno=${file.fileSno}">
	                            	<img src='${_ctx}/res/images/icon.jpg' style='width:20px; cursor:pointer;' title="${file.orgFileName}"/>
	                            </a>
                            </c:forEach>
                            </td>
                            
                            <td><fmt:formatNumber value="${item.cntRead}"/></td>
                            <td>${item.noticeYn}</td>
                        </tr>
                       </c:forEach>
                    </tbody>
                </table>
                
                <div class="btnSet">
                	<a href="javascript:;" id="btnWrite" class="disPB btnBase">글쓰기</a>
                </div>
                
                <pagetag:paging page="${search}" />
                
            </div>
        </div>
    </div>	
	</div>
	<form id="frmView"  name="frmView" action="${_ctx}/board/notice/view.ssol" method="get" >
		<input type="hidden" name="docId" />
		<input type="hidden" name="mapId" id="mapId" value="${boardMapDTO.mapId}"/>
	  <input type="hidden" name="page" value="${search.page}"/>
	  <input type="hidden" name="searchType"  value="${search.searchType}"/>
	  <input type="hidden" name="searchText"  value="${search.searchText}"/>
	</form>
</body>
</html>