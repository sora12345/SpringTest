<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script>
	$(document).ready(function() {
		$("body").find("a").click(function(e) {
			e.preventDefault;
			return false;
		});
	});
</script>

<div id="header">
    		<ul>
            <li><a href="${_ctx}/board/doc/totalDoc.ssol">전체글 모아보기</a></li>
            <li><a href="${_ctx}/board/doc/mylist.ssol">내가 등록 한 글</a></li>
            <li><a href="${_ctx}/board/doc/myComment.ssol">내가 댓글 단 글</a></li>
            <li><a href="${_ctx}/board/doc/myLike.ssol">내가 좋아요 한 글</a></li>
<%--             <li><a href="${_ctx}/home2">채팅</a></li> --%>
<!--             <li><a href="#">Menu6</a></li> -->
<!--             <li><a href="#">Menu7</a></li> -->
        </ul>
    </div>
