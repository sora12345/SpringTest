<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="replyWrap">
	<dl>
		<dd>
			<input type="text" id="comments" placeholder="댓글등록" title="replyName" style="width: 87%; display: inline-block;"/> 
			<a href="javascript:;" id="btnComment" class="disPB btnBase">댓글등록</a>
		</dd>
	</dl>


	<table class="replyList">
		<c:forEach items="${list}" var="item">
			<tr>
				<th class="name">${item.name}</th>
				<td class="cont">${item.comments}
					<c:if test="${userDTO.userId == item.userId}">
						<a href="javascript:deleteComment(${item.commentId});" id="btnDelete" class="disPB btnS" >삭제</a>
	        		</c:if>
				</td>
				<td class="date"><fmt:formatDate value="${item.regDt}" pattern="yyyy-MM-dd a hh:mm:ss" /></td>
			</tr>
		</c:forEach>
	</table>
</div>