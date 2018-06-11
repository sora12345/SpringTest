<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<script type="text/javascript" src="${_ctx}/res/js/jqueryui/jquery-ui.min.js"></script>
<link href="${_ctx}/res/js/jqueryui/jquery-ui.min.css" rel="stylesheet" />
<script>
	var dialog

	$(function() {
		dialog = $("#dialog-form").dialog({
			autoOpen : false,
			height : 130,
			width : 350,
			modal : true,
			close : function() {
			}
		});

		dialog.dialog("open");
		
		$("#password").keypress(function(e) {
			if(e.keyCode == 13) {
				return false;
			}
		});

		$("#btnCheckId").click(function () {
		var myWritePw = $("#password").val();
		var secretWritePw = ${boardDocDTO.secretWritePw};
		console.log(myWritePw + ", " + secretWritePw);

			if( myWritePw == secretWritePw ){
				
				document.location.href="${_ctx}/board/doc/view.ssol?docId=${boardDocDTO.docId}&mapId=${boardDocDTO.mapId}";
				
				}else{
					alert("비밀번호가 다릅니다.");
			}
		});
	});
		

	
</script>

<div id="dialog-form" title="비밀번호를 입력하세요">
	
		<form>
			<fieldset>
				<br/>
				<b>비밀번호</b> <input type="password" id="password" class="text ui-widget-content ui-corner-all" />
				<a href="javascript:;" class="checkId" id="btnCheckId" style="width: 20%; height: 2%;">확인</a>

			</fieldset>
		</form>
</div>
