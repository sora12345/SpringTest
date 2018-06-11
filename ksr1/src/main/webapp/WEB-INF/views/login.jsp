<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />

<script>
	var dialog;

	// "로그인" 버튼 클릭 시
	$(document).ready(function() {
		var url = "${_ctx}/board/dialog/popupDialog.ssol";
		$.get(url, function (html) {
			$("#dialog").html(html);
		});
		
		
		//focus in/out 시에 input 박스 배경색 변경
		$("#loginId").focusin(function() {
			$(this).css("background-color", "#E0F8F7");
		}).focusout(function() {
			$(this).css("background-color", "#ffffff");
		});
		// 페이지 로딩완료 후에 첫번째 input에 포커스 주기
		$("#loginId").focus();

		// valodation이 통과하면
		$("#btnLogin").click(function() {
			if ($("#frmLogin").valid()) {
				$("#frmLogin").submit();
			}
		});
		$("#loginPw").keydown(function (e) {
			console.log(e.keyCode);
			if(e.keyCode == 13){
				$("#btnLogin").click();
			}
		});
		
	});
</script>
</head>

<body>
	<div id="dialog" >
		<!-- 팝업띄우기 -->
	</div>
	<div id="loginWrap">
		<div id="login">
			<c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
				<font color="red"> Your login attempt was not successful due to <br />
				<br /> <c:out value="${SPRING_SECURITY_LAST_EXCEPTION.message}" />
				</font>
				<!-- 새로고침 시 제거 -->
				<c:remove var="SPRING_SECURITY_LAST_EXCEPTION" scope="session" />
				<script>
					// query string 제거
					history.replaceState({}, null, location.pathname);
				</script>
			</c:if>

			<h1>로그인 페이지</h1>
			<form id="frmLogin" name="frmLogin" action="${_ctx}/security/login" method="post" />
			<dl>
				<dt>id</dt>
				<dd>
					<!-- data-min-length-required="야 자리수{0}다. minlength="3" -->
					<input type="text" name="loginId" id="loginId" placeholder="UserID" maxlength="15" minlength="6" required />
				</dd>
				<dt>pw</dt>
				<dd>
					<input type="password" name="loginPw" id="loginPw" placeholder="Password" maxlength="15" minlength="8" required />
				</dd>
			</dl>

			<a href="javascript:;" class="loginBtn" id="btnLogin">로그인</a> 
			<a href="${_ctx}/join.ssol" class="joinBtn">회원가입</a> 
			<a href="${_ctx}/find.ssol" id="btnCheckId">아이디 찾기</a> 
			<a href="${_ctx}/find.ssol" id="btnCheckPw">비밀번호 찾기</a>

			</form>

			</div>



	</div>
</body>
</html>
