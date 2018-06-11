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
    
body {background:#999}
#tabs {
  overflow: hidden;
  width:100%;
  margin: 0;
  padding: 0;
  list-style: none;
}

#tabs li {
  float: left;
  margin: 0 .5em 0 0;
}

#tabs a {
  position: relative;
  background: #ddd;
  background-image: linear-gradient(to bottom, #fff, #ddd);  
  padding: .7em 3.5em;
  float: left;
  text-decoration: none;
  color: #444;
  text-shadow: 0 1px 0 rgba(255,255,255,.8);
  border-radius: 5px 0 0 0;
  box-shadow: 0 2px 2px rgba(0,0,0,.4);
}

#tabs a:hover,
#tabs a:hover::after,
#tabs a:focus,
#tabs a:focus::after {
  background: #fff;
}

#tabs a:focus {
  outline: 0;
}

#tabs a::after {
  content:'';
  position:absolute;
  z-index: 1;
  top: 0;
  right: -.5em;  
  bottom: 0;
  width: 1em;
  background: #ddd;
  background-image: linear-gradient(to bottom, #fff, #ddd);  
  box-shadow: 2px 2px 2px rgba(0,0,0,.4);
  transform: skew(10deg);
  border-radius: 0 5px 0 0;  
}

#tabs #current a,
#tabs #current a::after {
  background: #fff;
  z-index: 3;
}

#content {
  background: #fff;
  padding: 2em;
  height: 700px;
  position: relative;
  z-index: 2; 
  border-radius: 0 5px 5px 5px;
  box-shadow: 0 0px 3px 0px rgba(0, 0, 0, .5);
}
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script> 
	
<script>
 		$(function () {
 			
 			// 검색 타입 유지
 			$("#searchType > option[value='${search.searchType}']").attr("selected", true);
 			
 			// 글쓰기 버튼 클릭시
 		 	$("#btnWrite").click(function () {
 				document.location.href="${_ctx}/board/doc/write.ssol?mapId=${boardMapDTO.mapId}";
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
 		
 		// 비밀번호 팝업창 띄우기
		function secretPw(docId, sec) {
			var url = "${_ctx}/board/dialog/secretPw.ssol?docId="+docId + "&secretWritePw=" + sec;
			$.get(url, function (html) {
				$("#dialog").html(html);
			});
		}
		

		$(document).ready(function() {
		    $("#content").find("[id^='tab']").hide(); // Hide all content
		    $("#tabs li:first").attr("id","current"); // Activate the first tab
		    $("#content #tab1").fadeIn(); // Show first tab's content
		    
		    $('#tabs a').click(function(e) {
		        e.preventDefault();
		        if ($(this).closest("li").attr("id") == "current"){ //detection for current tab
		         return;       
		        }
		        else{             
		          $("#content").find("[id^='tab']").hide(); // Hide all content
		          $("#tabs li").attr("id",""); //Reset id's
		          $(this).parent().attr("id","current"); // Activate this
		          $('#' + $(this).attr('name')).fadeIn(); // Show content for the current tab
		        }
		    });
		});

 		
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
                <h1>내가 좋아요 한 글</h1>
            </div>
 	<ul id="tabs">
	   <li><a href="#" name="tab1" ><b>좋아요</b></a></li>
	   <li><a href="#" name="tab2"><b>싫어요</b></a></li>
	</ul>

<div id="content"> 
    <div id="tab1">
		<!-- 검색 시작 -->
        <form id="frmsearch"  name="frmsearch" action="${_ctx}/board/doc/myLike.ssol" method="get" class="search_area">
                <input type="hidden" name="mapId"  value="${boardMapDTO.mapId}"/>
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listY}" var="item">
                    <tr>
                        <td>${item.docId}</td>
                        <td class="txtCut alignLeft">
                        <c:choose>
                        <c:when test="${item.secretWriteYn=='Y'}">
                        <a href="javascript:secretPw('${item.docId}', '${item.secretWritePw}');" id="title">
                            <c:out value="비밀글입니다." escapeXml="true"></c:out>
                            <img src='${_ctx}/res/images/rock.jpg' style='width:15px; cursor:pointer;' title="${item.title}"/>
                        </a>
                        </c:when>
                         <c:otherwise>
                        <a href="javascript:goView('${item.docId}');">
                            <c:out value="${item.title}" escapeXml="true"></c:out>  
                        </a>
                        </c:otherwise>
                        </c:choose>
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
    	<div id="tab2">
    		<!-- 검색 시작 -->
            <form id="frmsearch"  name="frmsearch" action="${_ctx}/board/doc/myLike.ssol" method="get" class="search_area">
                    <input type="hidden" name="mapId"  value="${boardMapDTO.mapId}"/>
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
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${listN}" var="item">
                    <tr>
                        <td>${item.docId}</td>
                        <td class="txtCut alignLeft">
                        <c:choose>
                        <c:when test="${item.secretWriteYn=='Y'}">
                        <a href="javascript:secretPw('${item.docId}', '${item.secretWritePw}');" id="title">
                            <c:out value="비밀글입니다." escapeXml="true"></c:out>
                            <img src='${_ctx}/res/images/rock.jpg' style='width:15px; cursor:pointer;' title="${item.title}"/>
                        </a>
                        </c:when>
                         <c:otherwise>
                        <a href="javascript:goView('${item.docId}');">
                            <c:out value="${item.title}" escapeXml="true"></c:out>  
                        </a>
                        </c:otherwise>
                        </c:choose>
                        <c:if test="${item.cntComment>0}">(${item.cntComment})</c:if>
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
    </div>	
	</div>
	<form id="frmView"  name="frmView" action="${_ctx}/board/doc/view.ssol" method="get" >
		<input type="hidden" name="docId" />
		<input type="hidden" name="mapId"  value="${boardMapDTO.mapId}"/>
	  <input type="hidden" name="page" value="${search.page}"/>
	  <input type="hidden" name="searchType"  value="${search.searchType}"/>
	  <input type="hidden" name="searchText"  value="${search.searchText}"/>
	</form>
</body>
</html>
