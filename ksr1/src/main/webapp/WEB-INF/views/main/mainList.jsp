<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:forEach items="${list}" var="item">
 <tr>
     <td>${item.mapName}</td>
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
     <td><fmt:formatNumber value="${item.cntRead}"/></td>
 </tr>
</c:forEach>