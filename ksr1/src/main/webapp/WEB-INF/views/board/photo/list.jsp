<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="pagetag" uri="/WEB-INF/tlds/pagetag.tld"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<c:import url="/WEB-INF/views/inc/head.jsp" />

<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script> 

<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 

<style>
	.photo-grid-container { margin:50px auto 30px auto; max-width:960px;}
</style>

<script>

		
 		
 		$(function () {
 			$('.photo-grid-container').sortablePhotos({
 			    selector: '> .photo-grid-item',
 				  padding: 8
 			  });
 			 // 검색 타입 유지
 			$("#searchType > option[value='${search.searchType}']").attr("selected", true);
 			
 			// 글쓰기 버튼 클릭시
 		 	$("#btnWrite").click(function () {
 				document.location.href="${_ctx}/board/photo/write.ssol?mapId=${search.mapId}";
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
                <h1>${boardMapDTO.mapName}(사진첩)</h1>
            </div>
     

						<!-- 검색 시작 -->
            <form id="frmsearch"  name="frmsearch" action="${_ctx}/board/photo/list.ssol" method="get" class="search_area">
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
						
						<div class="photo-grid-container">
						
						  <c:forEach items="${list}" var="file">
						 
		 
						   <div class="photo-grid-item"> <a href="javascript:goView('${file.docId}');"><img title="${file.title}" src="${_ctx}/${file.fileList.get(0).filePath}/${file.fileList.get(0).newFileName}.${file.fileList.get(0).fileExt}"/>  </a> </div>
						  
						
						
						  </c:forEach>
						</div>
            
<!--             <div class="boardWrap"> -->
                
<!--                 <table class="base_tbl"> -->
<!--                     <tbody> -->
<%--                     	<c:forEach items="${list}" var="item"> --%>
<!--                         <tr> -->
<%--                             <td>${item.docId}</td> --%>
<!--                             <td class="txtCut alignLeft"> -->
<%--                             <a href="javascript:goView('${item.docId}');"> --%>
<%--                             	<c:out value="${item.title}" escapeXml="true">${item.title}</c:out>  --%>
<!--                             </a>  -->
<%--                             <c:if test="${item.cntComment>0}">(${item.cntComment})</c:if> --%>
                           
<!--                             </td> -->
<%--                             <td>${item.name}</td> --%>
<%--                             <td><fmt:formatDate value="${item.regDt}" pattern= "yyyy-MM-dd a hh:mm:ss"/></td> --%>
                            
<!--                             </tr> -->
<!--                             <tr> -->
<!-- 		                            <td colspan="5" class="txtCut alignLeft"> -->
<%-- 		                            <c:forEach items="${item.fileList}" var="file"> --%>
<%-- 																<img src="${_ctx}/${file.filePath}/${file.newFileName}.${file.fileExt}"  style="width:200px; height: 200px; margin-bottom: 10px;"/> --%>
<%-- 																</c:forEach> --%>
<!-- 																</td> -->
<!--                         		</tr> -->
                            
<%--                             <td><fmt:formatNumber value="${item.cntRead}"/></td> --%>
<!--                         </tr> -->
<%--                        </c:forEach> --%>
<!--                     </tbody> -->
<!--                 </table> -->
                
                <div class="btnSet">
                	<a href="javascript:;" id="btnWrite" class="disPB btnBase">글쓰기</a>
                </div>
                
                <pagetag:paging page="${search}" />
                
<!--             </div> -->
<!--         </div> -->
    </div>	
	</div>
	<form id="frmView"  name="frmView" action="${_ctx}/board/photo/view.ssol" method="get" >
		<input type="hidden" name="docId" />
		<input type="hidden" name="mapId" id="mapId" value="${boardMapDTO.mapId}"/>
	  <input type="hidden" name="page" id="page" value="${search.page}"/>
	  <input type="hidden" name="searchType"  value="${search.searchType}"/>
	  <input type="hidden" name="searchText"  value="${search.searchText}"/>
	</form>
	<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script> 
<script src="http://code.jquery.com/ui/1.11.4/jquery-ui.min.js"></script> 
<script src="${_ctx}/res/js/gallery/jquery-sortable-photos.js"></script> 

</body>
</html>
