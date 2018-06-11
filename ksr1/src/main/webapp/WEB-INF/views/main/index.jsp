<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<c:import url="/WEB-INF/views/inc/head.jsp" />
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.css"/>
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://cdn.jsdelivr.net/bxslider/4.2.12/jquery.bxslider.min.js"></script>
	
	<style>
		.slider{width: 100%; height: 300px; max-height: 300px;}
		.slider{width: 100%; height: 250px; max-height: 250px;}
		.bx-wrapper img {width: 100%; height: 250px; max-height: 250px;}
		.bx-controls-direction a{color: transparent;}
		
		#mainListUl li{float: left; margin-left: 2px; cursor: pointer;}
		#mainListUl li.active{color: blue;}
	</style>
	
	<script>
	$(document).ready(function(){
		
		var url = "${_ctx}/board/dialog/popupDialog.ssol";
		$.get(url, function (html) {
			$("#popupdialog").html(html);
		});
		
		$("#close").click(function(){
	      $("#frmsearch").toggle();
	  	});
	    	$('.slider').bxSlider();
		});
	
	$.get("", {searchType : "R"}, function(data) {
// 		$("#mainTbody").html(data);
	});
	
	function changeMenu(ee) {
		var _this = $(ee);
		var type = _this.attr("data-type");
		
		console.log(type);
		
		// 지금 눌린거 색깔 바꾸기
		_this.siblings().removeClass("active");
		_this.addClass("active");
		
		// 게시글 정보 불러오기! 누나 화이팅!
		$.get("", {searchType : type}, function(data) {
			$("#mainTbody").html(data);
		});
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
				<h1>메인 페이지</h1>
			</div>
							<div style="text-align: right;">
               <a href="javascript:;"  id="close" style="color: black;">▲대문접기</a>
							</div>
            <form id="frmsearch"  name="frmsearch" class="search_area" >
								<div class="slider" >
								    <div><img src="${_ctx}/res/images/love.jpg" /></div>
								    <div><img src="https://www.desicomments.com/wp-content/uploads/2017/02/Welcome-Pic-600x355.jpg" /></div>
								    <div><img src="https://image.freepik.com/free-vector/welcome-lettering_23-2147511605.jpg" /></div>
							  </div>
            </form>
          
            <div class="boardWrap">
  						<div class="boardWrap" style="width: 45%; min-width:0px; display: inline-block; margin-right:22px;">
  							<table class="base_tbl">
                    <thead>
                        <tr style="padding: 0px;">
                            <th style="text-align:left; padding-top: 18px;">전체글 모아보기 </th>
                            <th style="text-align:right; font-size:9px; padding: 0px; padding-top: 28px;"><a href="${_ctx}/board/doc/totalDoc.ssol" style="color: black;">더보기▶</a></th>
                        </tr>
                    </thead>
                    <tbody>
                    		<tr style="padding: 0px;">
                            <th colspan="2" style="border: 0px;">
                            	<ul id="mainListUl" style="float: right; font-size: 13px;">
                            		<li class="active" onclick="changeMenu(this);" data-type="R">등록일</li>
                            		<li onclick="changeMenu(this);" data-type="T">제목</li>
                            		<li onclick="changeMenu(this);" data-type="H">조회수</li>
                            	</ul>
                            </th>
                        </tr>
                    </tbody>
                    <tbody id="mainTbody">
                    	
                    </tbody>
                </table>
                
  						</div>
  						
  						<div class="boardWrap" style="width: 45%; min-width:0px; display: inline-block;">
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">1111111111111</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">2222222222222</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">3333333333333</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">4444444444444</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">5555555555555</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">6666666666666</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">7777777777777</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">8888888888888</div>
  							<div style="border: 1px solid blue; margin: 1px; width: 32%; height: 20%; float: left;">9999999999999</div>
  						
  						</div>
            </div>
        </div>
    </div>	
	</div>


</body>
</html>

