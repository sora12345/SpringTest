<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagetag" uri="/WEB-INF/tlds/pagetag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />
	
<script>
 		$(function () {
 			
 			// 검색 타입 유지
 			$("#searchType > option[value='${search.searchType}']").attr("selected", true);
 			
 			// 글쓰기 버튼 클릭시
 		 	$("#btnWrite").click(function () {
 				document.location.href="${_ctx}/popup/doc/write.ssol";
 			});
 		});
 		// 페이지 이동
 		function goPage(page) {
 
 		$("#page").val(page);
 		$("#frmsearch").submit();
 		}
 		
 		// 조회페이지 이동
 		function goView(popId) {
			$("#frmView input[name='popId']").val(popId);
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
                <h1>팝업관리</h1>
            </div>
            <!-- 검색 시작 -->
            <form id="frmsearch"  name="frmsearch" action="${_ctx}/popup/doc/list.ssol" method="get" class="search_area">
            		<input type="hidden" name="mapId" id="mapId" value="${boardMapDTO.mapId}"/>
            		<input type="hidden" name="page" id="page" value="${search.page}"/>
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
                            <th width="10%">활성화여부</th>
                            <th width="15%">등록일자</th>
                           
                        </tr>
                    </thead>
                    <tbody>
                    	<c:forEach items="${list}" var="item">
                        <tr>
                            <td>${item.popId}</td>
                            <td class="txtCut alignLeft">
                            <a href="javascript:goView('${item.popId}');">${item.popTitle}</a> 
                            </td>
<%--                             <td>${item.popTitle}</td> --%>
                            <td>${item.popupYn}</td>
                            <td><fmt:formatDate value="${item.regDt}" pattern= "yyyy-MM-dd a hh:mm:ss"/></td>
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
	<form id="frmView"  name="frmView" action="${_ctx}/popup/doc/view.ssol" method="get" >
		<input type="hidden" name="popId" />
	</form>
</body>
</html>
