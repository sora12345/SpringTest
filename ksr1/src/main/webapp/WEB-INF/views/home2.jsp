<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8" />
<script src="${_ctx}/res/sockjs.min.js"></script>
<c:import url="/WEB-INF/views/inc/head.jsp" />
</head>
<body>
	<div id="wrap">
		<c:import url="/WEB-INF/views/inc/header.jsp" />
		<c:import url="/WEB-INF/views/inc/left.jsp" />

		<div id="rightWrap">

			<div class="rightBlock">
				<div class="page_top">
					<h1>실시간 채팅</h1>
				</div>

				<form id="chatForm">
					<input type="text" id="message" />
					<button>send</button>
				</form>
				<div id="chat"></div>

				<script>
					$(document).ready(function() {
						$("#chatForm").submit(function(event) {
							event.preventDefault();
							
							var msg = "";
							var name = "${userDTO.name}";
							msg += name;
							msg += " : ";
							msg += $("#message").val();
							
							sock.send(msg);
							$("#message").val('').focus();
						});
					});

					var sock = new SockJS("/ksr1/echo");
					sock.onmessage = function(e) {
						$("#chat").append(e.data + "<br/>");
					}

					sock.onclose = function() {
						$("#chat").append("연결 종료");
					}
				</script>

			</div>
		</div>
	</div>
</body>
</html>
