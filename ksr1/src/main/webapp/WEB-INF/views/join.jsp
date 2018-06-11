<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
			$("input").focusin(function(){
		        $(this).css("background-color", "#E0F8F7");
			}).focusout(function(){
		         $(this).css("background-color", "#ffffff");
		  });
			// 페이지 로딩완료 후에 첫번째 input에 포커스 주기
			$("#loginId").focus();
			
			// "이메일 체크" 버튼을 클릭했을 때
			$("#btnCheckEmail").click(function () {
				var email = $("#email").val();
				if(email==""){
					alert("이메일을 입력해주세요.");
				}else{
					var url = "${_ctx}/check/email.ssol?email="+email;
					$.get(url, function (data) {
						if(data.code > 0){
							alert(data.msg);
							
							$("#checkedEmail").val("Y");
							
						}else{
							alert(data.msg);
							
							$("#checkedEmail").val("N");
							
						}
					});
				}
			});
			
			// "핸드폰 체크" 버튼을 클릭했을 때
			$("#btnCheckPhone").click(function () {
				var phone = $("#phone").val();
				if(phone==""){
					alert("핸드폰 번호를 입력해주세요");
				}else{
					var url = "${_ctx}/check/phone.ssol?phone="+phone;
					$.get(url, function (data) {
						if(data.code > 0){
							alert(data.msg);
							
							$("#checkedPhone").val("Y");
							
						}else{
							alert(data.msg);
							
							$("#checkedPhone").val("N");
							
						}
					});
				}
			});
			
			// "아이디 체크" 버튼을 클릭했을 때
			$("#btnCheckId").click(function() {
				var loginId = $("#loginId").val();
				if(loginId==""){
					alert("아이디를 작성해주세요.");
				}else{
					//아이디 중복 검사 실행
					var url = "${_ctx}/check/id.ssol?loginId="+loginId;
					$.get(url, function(data) {
						// 아이디 사용 가능
						if(data.code > 0){
							alert(data.msg);
							
							// 아이디 중복 체크를 했음
							$("#checkedId").val("Y");
							
							//아이디 사용 불가능
						}else{
							alert(data.msg);
							
							// 아이디 중복 체크를 안했음
							$("#checkedId").val("N");
						}
						
					});
				}
			});
			
			// 캡챠 이미지 클릭시 - 방법 1
			$("#imgCaptcha").click(function(){
				$(this).attr("src", "${_ctx}/captcha/index");
			});
			
			// 중복체크 후 로그인아이디에 다시 포커스를 줬을때
			$("#loginId").focus(function(){
				$("#checkedId").val("N");
			});
			
			// 중복체크 후 핸드폰에 다시 포커스를 줬을때
			$("#phone").focus(function(){
				$("#checkedPhone").val("N");
			});
			
			// 중복체크 후 이메일에 다시 포커스를 줬을때
			$("#email").focus(function(){
				$("#checkedEmail").val("N");
			});
			
			// "저장" 버튼을 클릭 했을때
			$("#btnSave").click(function() {
			// javascript로 form 전송
			// frmJoin.submit();
			
			// 캡챠 새로고침
			$("#imgCaptcha").attr("src", "${_ctx}/captcha/index");
			
			// 1. 아이디 중복체크 검사
			var checkedId = $("#checkedId").val();
			if(checkedId == "N"){
				alert("아이디 중복체크를 먼저 해주세요.");
				return;
			}
			
			// 2. 핸드폰 중복체크 검사
			var checkedPhone = $("#checkedPhone").val();
			if(checkedPhone=="N"){
				alert("핸드폰 중복체크를 먼저 해주세요.");
				return;
			}
			
			// 3. 이메일 중복체크 검사
			var checkedEmail = $("#checkedEmail").val();
			if(checkedEmail=="N"){
				alert("이메일 중복체크를 먼저 해주세요.");
				return;
			}
			
			

			// 검증에 통과하면	
			if($("#frmJoin").valid()) {
				
				$.get("${_ctx}/captcha/isRight", {captcha : $("#captcha").val()}, function(data){
					//캡챠 성공시	
					
						if(data==1){
								//서버에 데이터 전송	
								var url = "${_ctx}/join.ssol";
								$.post(url, $("#frmJoin").serialize(), function(data) {
	
									if (data == "s") {
										alert("회원가입 성공");
										document.location.href="${_ctx}/login.ssol";
										
									} else {
										alert("회원가입 실패!!");
										document.location.reload();
									}
								});
						}else{
								alert("캡챠 문자가 다릅니다.");
					}	
				});
			}
		});
	});

</script>
</head>

<body>

	<div id="loginWrap">


		<div id="join">
			<h1>회원가입</h1>
				<form id="frmJoin" name="frmJoin">
					<input type="hidden" id = "checkedId" value="N"/>
					<input type="hidden" id = "checkedPhone" value="N"/>
					<input type="hidden" id = "checkedEmail" value="N"/> 
					<dl>
						<dd>
							<input type="text" name="loginId" id="loginId" placeholder="아이디" maxlength="15" minlength="6" style="width:70%;" required />
							<a href="javascript:;" class="checkId" id="btnCheckId">ID 중복확인</a>  
						</dd>
						<dd>
							<input type="password" id="loginPw" name="loginPw" placeholder="비밀번호" maxlength="15" minlength="8" required />
						</dd>
						<dd>
							<input type="password" name="reLoginPw" placeholder="비밀번호확인" maxlength="15" minlength="8" equalTo="#loginPw" required />
						</dd>
						<dd>
							<input type="text" name="name" placeholder="이름" maxlength="30" required />
						</dd>
						<dd>
							<input type="text" id="phone" name="phone" placeholder="핸드폰번호" alt="mobile" maxlength="15" minlength="6" style="width:70%;" required />
							<a href="javascript:;" class= "checkId" id="btnCheckPhone">핸드폰중복확인</a>
						</dd>
						<dd>
							<input type="email" id="email" name="email" placeholder="이메일" maxlength="20" maxlength="15" minlength="6" style="width:70%;" required />
							<a href="javascript:;" class = "checkId" id="btnCheckEmail">이메일중복확인</a>
						</dd>
						<dd>
							<img src="${_ctx}/captcha/index" id="imgCaptcha" style="cursor: pointer"/>
							<input type="text" name="captcha" id="captcha" placeholder="이미지 문자 작성" style="width:237px;" required />
						</dd>
					</dl>
	
					<a href="javascript:;" class="loginBtn" id="btnSave">저장</a> 
					<a href="${_ctx}/index.ssol" class="joinBtn">취소</a>
			</form>
		</div>
	</div>
</body>
</html>
