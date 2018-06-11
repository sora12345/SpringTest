<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />


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
            
            <div class="boardWrap">
                
                <table class="base_tbl">
                    <thead>
                        <tr>
                            <th colspan="8" class="t_color">${popupDTO.popTitle}</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        		<td width="10%">활성화여부</td>
                            <td width="20%" class="t_color" >${popupDTO.popupYn}</td>
                            <td width="10%">작성일</td>
                            <td width="20%" class="t_color"><fmt:formatDate value="${popupDTO.regDt}" pattern= "yyyy-MM-dd a hh:mm:ss"/></td>
                        </tr>
                        <tr>
                          	<td width="8%">이미지</td>
                            <td colspan="5" class="txtCut alignLeft">
                            <c:forEach items="${popupDTO.fileList}" var="item">
														<img src="${_ctx}/${item.imgPath}/${item.newImgName}.${item.imgExt}" />
														</c:forEach>
														</td>
                        </tr>
                    </tbody>
                </table>
                <div class="btnSet">
                    <a href="${_ctx}/popup/doc/list.ssol" class="disPB btnBase">목록</a>
                    	<a href="${_ctx}/popup/doc/edit.ssol?popId=${popupDTO.popId}" id="btnUpdate" class="disPB btnBase">수정</a>
                    	<a href="${_ctx}/popup/doc/remove.ssol?popId=${popupDTO.popId}" class="disPB btnBase">삭제</a>
                </div>
                
            </div>
        </div>
    </div>
</div>
	
</body>
</html>

