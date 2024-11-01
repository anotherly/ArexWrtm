<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>단말장치(LTE-R) 관리 WEB 시스템</title>
	<meta charset="UTF-8">
    <jsp:include page="../cmn/top.jsp" flush="false" />
<script>
	var teamCode='';
	$(document).ready(function(){
		var alData=ajaxMethod("/terminal/list.ajax").data;
		trainOne(alData);
		//console.log("chart 진입");
		//$("#container_chart").hide();
		var userAuth='${login.userAuth}';
		if(userAuth==0){
			$("#all_chart").load("/chart/mainAdminChart.do");
		}else{
			$("#all_chart").load("/chart/mainUserChart.do");
		}
		
		//페이징 처리
		$('#paging span').on('click',function(){
			//console.log("paging : "+preStatNum);
			var btnId=$(this).attr('id');
			if (btnId=='pageStart') {//앞으로 가기
				alData=ajaxMethod("/terminal/list.ajax",{"startNum":preStatNum,"endNum":endNum,"teamCode":teamCode}).data;
				if(alData.length!=0){
					$("#lteTbd").empty();
					trainOne(alData);	
					startNum=Number(startNum)+alData.length;
				}
			} else {//뒤로 가기
				alData=ajaxMethod("/terminal/list.ajax",{"startNum":endNum,"endNum":endNum,"teamCode":teamCode}).data;
				if(alData.length!=0){
					$("#lteTbd").empty();
					trainOne(alData);
				}
			}
		});
		//우측상단 탭 클릭시
		//팀별 조회
		$(".arex_tab").on('click',function(){
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
			alData=ajaxMethod("/terminal/list.ajax",{"teamCode":teamCode}).data;
			if(alData.length!=0){
				$("#lteTbd").empty();
				trainOne(alData);
			}
		});
	});
	
	//동적 테이블(삭제 및 갱신)시 td 클릭 이벤트
	//차트 상세
    $(document).on('click','#trainTb td',function(){
    	
    	timerReset();
    	$("#all_chart").empty();
		/* $("#all_chart").hide();
		$("#container_chart").show(); */
    	//console.log($(this));
    	lineVal=$(this).attr('id');
    	$("#all_chart").load("/chart/subDetail.do",{"lteRIp":lineVal});
    	//$("#container_chart").load("/chart/subDetail.do",{"lteRMacAdd":lineVal});
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
		<div id="header" class="header-wrap">
		</div>
		<!-- header End ------------------>

		<!-- contents Start ------------------>
		<div id="containerAll" class="containerAll">
			<!-- 내용 부분 -->
			<!-- <div id="container_chart" class="container_b"></div> -->
			<div id="all_chart" class="container_b"></div>
			<!-- 우측 단말기 테이블 전체-->
			<div id="container_b" class="container_b" style="padding: 20px 0 0 37px;">
				<div style="width:100%;display:flex;margin-bottom:10px;">
					<div id="teamSlt" class="tab_container" style="display:flex;">
						<c:if test="${login.userAuth==0}">
							<div id="tab_all" class="arex_tab selected"><span>전체</span></div>
							<div id="sht" class="arex_tab"><span>신호</span></div>
							<div id="sst" class="arex_tab"><span>시설</span></div>
							<div id="jgt" class="arex_tab"><span>전기</span></div>
						</c:if>
					</div>
					<div id="paging" style="width: 100px;">
						<span id="pageStart" class="pg-btn">◀</span>
						<span id="pageEnd"  class="pg-btn">▶</span>
					</div>
				</div>
				<!-- 팀별 선택 현황 -->
				
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

			</div>
		</div>
	</div>	
</body>

</html>