
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <script type="text/javascript">
	$(function(){
		loadTreeMap();
	});
	
	// 통합맵 가져오기
	function loadTreeMap() {
		d = new dTree('d');
		
		var url = "${_ctx}/board/map/list.ssol";
		$.get(url, function(data) {
			console.log(data);
			if($.isArray(data)){
				$(data).each(function (index) {
					console.log(index);
					console.log(this);
					if(this.parMapId == 6){
						if(this.parMapId == null){
							d.add(this.mapId, -1, this.mapName);
						}else{
							d.add(this.mapId, this.parMapId, this.mapName, "${_ctx}/board/doc/list.ssol?mapId="+this.mapId);
						}
					}else if(this.parMapId == 7){
						if(this.parMapId == null){
							d.add(this.mapId, -1, this.mapName);
						}else{
							d.add(this.mapId, this.parMapId, this.mapName, "${_ctx}/board/photo/list.ssol?mapId="+this.mapId);
						}
					}else {
						if(this.parMapId == null){
							d.add(this.mapId, -1, this.mapName);
						}else{
							d.add(this.mapId, this.parMapId, this.mapName, "${_ctx}/board/doc/list.ssol?mapId="+this.mapId);
						}
					}
					
				});
						d.add(100000, 1, "팝업관리", "${_ctx}/popup/doc/list.ssol");
			$("#dtree").html(d.toString());
			}
		});
	}
// 	FUNCTION LISTINLEFT(MAPID) {
// 		DOCUMENT.LOCATION.HREF = "${_CTX}/BOARD/DOC./LIST.SSOL?MAPID="+THIS.MAPID);
		
// 	}
</script>

<div id="leftWrap">
    	<div id="infoWrap">       
        	<div class="info_txt">
                <p class="info_name">${_user.name}
               	<a href="${_ctx}/home2"><img src="${_ctx}/res/images/chat.png" style="width:10%; cursor:pointer;"></a></p>
                <p class="info_date">${_user.email}<br/>[${_user.regDt}]
               	<a href="${_ctx}/main/index.ssol"><img src="${_ctx}/res/images/home.png" style="width:10%; cursor:pointer;"></a></p>
                
                <p class="info_pic"><img src="${_ctx}/res/images/thum_img.jpg" alt="thum"></p>
            </div>
            <span><a href="${_ctx}/board/doc/myUpdate.ssol" style="width:45%; display:inline-block;">나의정보수정</a></span>
            <span><a href="${_ctx}/logout.ssol" style="width:45%; display:inline-block;">Logout</a></span>
        </div>
        
        <div id="category">
        
        		<!-- dtree 시작 -->
            <div class="dtree" id="dtree">     
            </div>
            <!-- dtree 끝 -->
        
        </div>
    
    </div>
