<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>단말장치(LTE-R) 관리 WEB 시스템</title>
	<meta charset="UTF-8">
    <jsp:include page="../cmn/top.jsp" flush="false" />
    
    <style>
    	/* 24-10-18 : 단말기 개수 폰트 사이즈 설정 */
		@media(max-width : 3840px) {
			/* 화면이 4K 일 때 */
			
			/* 단말기 카운터 스타일 조정  */
			#countRouter {
				font-size : 20px;
				width: 250px;
				margin-top : 10px;
			}
			
			/* td 스타일 조정 */
			.lte-table td {
				width : 167px; /* tr 전체 넓이 / 7 값 */
				height : 185px;
			} 
			
		}
		
		@media(max-width : 2200px) {
			/* 화면이 FullHD일 때 */
			/* 단말기 카운터 스타일 조정  */
			#countRouter {
				font-size : 15px;
				width: 200px;
				margin-top : 15px;
			}
			
			.lte-table td {
				height : 65px;
			}
		}

    </style>
<script>
	var teamCode='';
	var chkTerId='';
	$(document).ready(function(){
		
		// 24-10-10 : 페이지 옮겨도 30초 후 갱신 시 페이지 변하지 않도록 처리하기 위한 변수
		var nowPage = 0;
		var nextPage = 1;
		
		var alData=ajaxMethod("/terminal/list.ajax");
		var Fcounter = alData.data.length;
		
		countRouter(teamCode,Fcounter); // 단말기 총 개수를 세는 함수
		trainOne(alData.data);
		hideTr(nowPage*6,nextPage*6);
		
		//시간 갱신
		$("#nowDt").text(alData.nowDt);
		
		console.log("chart 진입");
		var userAuth='${login.userAuth}';
		if(userAuth==0){
			$("#all_chart").load("/chart/mainAdminChart.do");
		}else{
			$("#all_chart").load("/chart/mainUserChart.do");
		} 
		
		
		// 단말기 총 개수 함수
		function countRouter(teamCode,counter) {	
			if(counter == 0 || counter == "undefined" || counter == null || counter == '') {
				$('#countRouter').text("단말기 개수 : 0개" );
			} else {
				$('#countRouter').text("단말기 개수 : " + counter + " 개" );
				$('#countRouter').css('color','#fff');

			}
		}
		
		//페이징 처리
		$('#paging span').on('click',function(){
			var btnId=$(this).attr('id');
			if (btnId=='pageStart') {//앞으로 가기
				//starNum이 0보다 작을경우 반응하지 않음
				if(startNum>0){
					startNum=startNum-1;
					endNum=endNum-1;
					
					nowPage = startNum;
					nextPage = endNum;
					hideTr(nowPage*6,nextPage*6);

				}
			} else {//뒤로 가기
				//최대 페이지 수-1 보다 
				if(startNum<maxPage){
					startNum=startNum+1;
					endNum=endNum+1;
					
					nowPage = startNum;
					nextPage = endNum;
					hideTr(nowPage*6,nextPage*6);
				}
			}
			
		});
		//우측상단 탭 클릭시
		//팀별 조회
		$(".arex_tab").on('click',function(){
			
			//색상 활성 비활성
			var parDiv=$(this).parent().children();
			$(parDiv).each(function(i,list){
				$(list).removeClass('selected');
			});
			var tagId = $(this).attr('id');
			$(this).addClass('selected');
			
			if(tagId!='tab_all'){
				teamCode=tagId;
			}else{
				teamCode='';
			}
			var alData=ajaxMethod("/terminal/list.ajax",{"teamCode":teamCode}).data;
			var counter = alData.length;
			
			countRouter(teamCode,counter); // 단말기 총 개수를 세는 함수
			
			if(alData.length!=0){
				trainOne(alData);
				hideTr(nowPage*6,nextPage*6);
			}
		});
		
		//좌측 메인차트 갱신
		/* mainChartTimer=setInterval(function(){
			$("#all_chart").empty();
			var userAuth='${login.userAuth}';
			if(userAuth==0){
				$("#all_chart").load("/chart/mainAdminChart.do");
			}else{
				$("#all_chart").load("/chart/mainUserChart.do");
			}
		},30*1000); // 기본값 30*1000 
		
		//우측 단말기 갱신
		tableTimer=setInterval(function(){
			console.log("우측 단말기 갱신");
			// 선택된 탭이 전체일 때
			if(teamCode == '') {
				var alData=ajaxMethod("/terminal/list.ajax");
				var counter = alData.data.length;
				
				countRouter(teamCode,counter); // 단말기 총 개수를 세는 함수
				trainOne(alData.data);
				hideTr(nowPage*6,nextPage*6);
			} else { // 전체 이외의 탭을 선택했을 때
				var alData=ajaxMethod("/terminal/list.ajax",{"teamCode":teamCode});
				var counter = alData.data.length;
				
				if(alData.data.length!=0){
					countRouter(teamCode,counter);
					trainOne(alData.data);
					hideTr(nowPage*6,nextPage*6);
				}
			}
			
			//시간 갱신 ( 현재 전체 이외의 탭 선택 후 단말기 갱신 시 시간이 변경되지 않는 문제 발견)
			$("#nowDt").text(alData.nowDt);
			
			//상세보기에서 갱신되도 배경선택은 유지하도록
			var tblist= $(".lte-table td");
	    	$(tblist).each(function(i,list){
	    		if(list==chkTerId){
	    			$(list).addClass('selected');
	    		}
			});
			
		},30*1000); */
	});
	
	//동적 테이블(삭제 및 갱신)시 td 클릭 이벤트
	//차트 상세
    $(document).on('click','#trainTb td',function(){
    	var tblist= $(".lte-table td");
    	$(tblist).each(function(i,list){
			$(list).removeClass('selected');
		});
    	$(this).addClass("selected");
    	chartTimerReset();
    	
    	$("#all_chart").empty();
    	
    	chkTerId=$(this).attr('id');
    	$("#all_chart").load("/chart/subDetail.do",{"lteRIp":chkTerId});
    });
	
</script>
</head>
<body class="open" style="background: url(../images/bg/yy_bg.png);background-size: cover;">
    <!-- lnb Start ------------------>
    <aside id="lnb" class="lnb">
        <a class="lnb-control" title="메뉴 펼침/닫침"><span class="menu-toggle">메뉴 펼침/닫침</span></a>
        <nav class="navbar">
			<ul class="menu-inner"></ul>
        </nav>
    </aside>
    <!-- lnb End ------------------>

    <!-- container Start ------------------>
    <div id="container" class="container-wrap" style="margin-top: 60px;background: none;" >
		<!-- header Start ------------------>
		<div id="header" class="header-wrap"></div>
		<!-- header End ------------------>

		<!-- contents Start ------------------>
		<div id="containerAll" class="containerAll">
			<!-- 내용 부분 -->
			<!-- <div id="container_chart" class="container_b"></div> -->
			<div id="all_chart" class="container_b"></div>
			<!-- 우측 단말기 테이블 전체-->
			<div id="container_b" class="container_b" style="padding: 20px 0 0 37px;">
				<!-- 팀별 선택 현황 -->
				<div style="width:100%;display:flex;margin-bottom:10px;">
					<div id="teamSlt" class="tab_container" style="display:flex;">
						<c:if test="${login.userAuth==0}">
							<div id="tab_all" class="arex_tab selected"><span>전체</span></div>
							<div id="sht" class="arex_tab"><span>신호</span></div>
							<div id="sst" class="arex_tab"><span>시설</span></div>
							<div id="jgt" class="arex_tab"><span>전기</span></div>
							
							<input type="hidden" id="returnTeam">
						</c:if>
					</div>
					<span id="countRouter"></span>
					<div id="paging" style="width: 100px;">
						<span id="pageStart" class="pg-btn">◀</span>
						<span id="pageEnd"  class="pg-btn">▶</span>
					</div>
				</div>
				
				<!-- 단말기 테이블 -->
				<div id="trainDiv"style="width: 100%;height: 100%;" >
					<div class="lte-div">
						<div id="trainNum">
							<table id="trainTb" class="lte-table">
								<tbody id="lteTbd"></tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="tilte" style="width: 100%;display: flex;align-items: center;">
					<span style="font-size: 18px;font-weight: bold;color: #fff;margin-right:10px;">단말기 상태정보 수신시각 : </span>
					<span id="nowDt" style="font-size: 18px;font-weight: bold;color: #fff;"> 1234</span>
				</div>
			</div>
		</div>
	</div>	
</body>

</html>