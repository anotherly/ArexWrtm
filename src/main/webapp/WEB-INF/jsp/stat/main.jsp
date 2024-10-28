<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>단말장치(LTE-R) 관리 WEB 시스템</title>
	<meta charset="UTF-8">
    <jsp:include page="../cmn/top.jsp" flush="false" />
<script>
	//차트 다운로드에 필요한 것들
	var downCnt=0;//변환그림의 현재 인덱스
	var fcnt=0;//변환할 그림의 최종 인덱스
	var c3Title="";//제목(일,월,년 구분)
	var c3TagId="";//전송할 날짜값
	
	var typeId="dayStat";//일월연 구분
	$(document).ready(function(){
		//우측상단 탭 클릭시
		//팀별 조회
		$(".arex_tab").on('click',function(){
			//색상 활성 비활성
			var parDiv=$(this).parent().children();
			$(parDiv).each(function(i,list){
				$(list).removeClass('selected');
			});
			var tagId = $(this).attr('id');
			typeId=tagId;
			$(this).addClass('selected');
			
			if(tagId=="monStat"){
				$("#statContainer").load("/stat/monStat.do");
			}else if(tagId=="yearStat"){
				$("#statContainer").load("/stat/yearStat.do");
			}else{
				$("#statContainer").load("/stat/dayStat.do");
			}
		});
		$("#statContainer").load("/stat/dayStat.do");
	});
	
	// 셀렉트 박스 변경 시 값에 따라 스크롤바 생성
	
	$('select[name="tableList_length"]').on('change', function(){
		var selectVal = $(this).val();
		console.log('현재 선택값 :' + selectVal);
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
		<!-- 이미지 캡쳐 폼 -->
		<form method="POST" id="captureForm" name="captureForm">
			<input type="hidden" name="img_val0" id="img_val0" value="" />
			<input type="hidden" name="title_val0" id="title_val0" value="" />
			<input type="hidden" name="img_val1" id="img_val1" value="" />
			<input type="hidden" name="title_val1" id="title_val1" value="" />
			<input type="hidden" name="img_val2" id="img_val2" value="" />
			<input type="hidden" name="title_val2" id="title_val2" value="" />
		</form>
		<!-- contents Start ------------------>
		<div id="containerAll" class="containerAll" style="flex-direction: column;">
			<!-- 내용 부분 -->
			<!-- <div id="container_chart" class="container_b"></div> -->
			<div id="teamSlt" class="tab_container" style="display:flex;">
				<div id="dayStat" class="arex_tab selected"><span>일일 통계</span></div>
				<div id="monStat" class="arex_tab"><span>월별 통계</span></div>
				<div id="yearStat" class="arex_tab"><span>연도별 통계</span></div>
			</div>
			<!--  -->
			<div id="statContainer" class="stat-container"></div>
		</div>
	</div>	
</body>

</html>