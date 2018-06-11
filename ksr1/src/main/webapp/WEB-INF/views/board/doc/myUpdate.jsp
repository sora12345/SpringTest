<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<c:import url="/WEB-INF/views/inc/head.jsp" />

<script>
	$(document).ready(function() {

		// 핸드폰에 Mask 추가
		$("#phone").setMask();

		//focus in/out 시에 input 박스 배경색 변경
		$("input").focusin(function() {
			$(this).css("background-color", "#E0F8F7");
		}).focusout(function() {
			$(this).css("background-color", "#ffffff");
		});

		// "저장" 버튼을 클릭 했을때
		$("#btnSave").click(function() {
			// javascript로 form 전송
			// frmJoin.submit();
			var orgLoginPw = $("#orgLoginPw").val();
			var writeLoginPw = "${userDTO.loginPw}";
			console.log(orgLoginPw + ", " + writeLoginPw);
			if (orgLoginPw == writeLoginPw) {
				// 검증에 통과하면	
				if ($("#frmJoin").valid()) {
					//서버에 데이터 전송	
					var url = "${_ctx}/board/doc/myUpdate.ssol";
					$.post(url, $("#frmJoin").serialize(), function(data) {

						if (data == "s") {
							console.log($("#frmJoin").serialize());
							alert("회원정보 변경이 완료되었습니다. 다시 로그인하세요");
							document.location.href = "${_ctx}/login.ssol";

						} else {
							alert("회원정보 변경이 실패되었습니다. 관리자에게 문의하세요");
							document.location.reload();
						}
					});
				}
			} else {
				alert("현재 비밀번호가 다릅니다. 다시 확인하시고 입력해주세요");
			}
		});
	});
</script>
</head>

<body>

	<div id="loginWrap">
		<c:import url="/WEB-INF/views/inc/header.jsp" />
		<c:import url="/WEB-INF/views/inc/left.jsp" />

		<div id="rightWrap">

			<div class="rightBlock">
				<div class="page_top">
					<h1>나의 정보 수정</h1>
				</div>
			</div>
		</div>


		<div id="join" style="padding: 150px 554px 200px 554px; left: 31.6%; top: 18%; border: 1px solid #a0a0a0; box-shadow: 0 5px 0px rgba(0, 0, 0, .1);">
			<form id="frmJoin" name="frmJoin">
				<input type="hidden" name="userId" id="userId" value="${userDTO.userId}" />
				<dl>
					<dd>
						<input type="text" id="loginId" name="loginId" placeholder="${userDTO.loginId}" value="${userDTO.loginId}" readonly />
					</dd>
					<dd>
						<input type="password" id="orgLoginPw" name="orgLoginPw" placeholder="현재 비밀번호" maxlength="15" minlength="8" required />
					</dd>
					<dd>
						<input autocomplete="off" type="password" id="loginPw" name="loginPw" placeholder="새 비밀번호" maxlength="15" minlength="8" required />
					</dd>
					<dd>
						<input type="password" name="reLoginPw" placeholder="새 비밀번호 확인" maxlength="15" minlength="8" equalTo="#loginPw" required />
					</dd>
					<dd>
						<input type="text" name="name" placeholder="${userDTO.name}" maxlength="30" required />
					</dd>
					<dd>
						<input type="text" id="phone" name="phone" placeholder="${userDTO.phone}" alt="mobile" maxlength="15" minlength="6" required />
					</dd>
					<dd>
						<input type="email" id="email" name="email" placeholder="${userDTO.email}" maxlength="20" maxlength="15" minlength="6" required />
					</dd>
				</dl>

				<a href="javascript:;" class="loginBtn" id="btnSave">저장</a> <a href="${_ctx}/main/index.ssol" class="joinBtn">취소</a>
			</form>
		</div>
	</div>
</body>
</html>
