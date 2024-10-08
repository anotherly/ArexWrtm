<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
	<!-- 드롭다운 메뉴 -->
	<!-- css -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/top.css">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/import.css" media="all">
	<!-- JS -->
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.migrate.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.form.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/validation.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/dtb.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/menu.js"></script>
	<script src="<%=request.getContextPath()%>/js/arex.js"></script>
	<script src="<%=request.getContextPath()%>/js/loginSC/login.js"></script>
	
	<!-- Custom style -->
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/button.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/svc.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/dtb.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/user.css">
	<!-- DataTable -->
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/DataTables/datatables.min.css" />
	<script type="text/javascript" src="<%=request.getContextPath()%>/DataTables/datatables.min.js"></script>
	
	<!-- DateTimePicker -->
	<script src="<%=request.getContextPath()%>/calender/moment.js"></script>
	<script src="<%=request.getContextPath()%>/calender/mo_ko.js"></script>
	<script src="<%=request.getContextPath()%>/calender/bootstrap-datetimepicker.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/no-boot-calendar-custom.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/calender/datetimepickerstyle.css" />
	
	<!-- 로그인 시큐어코딩 관련 -->
	<script src="<%=request.getContextPath()%>/js/loginSC/login.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/validation.js"></script>
	
	<!-- c3.js chart -->
	<link href="<%=request.getContextPath()%>/css/c3/normalize-e465cb86.css" media="screen" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/c3/tomorrow-d7cf0921.css" media="screen" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/c3/c3-eb4b9be8.css" media="screen" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/c3/style-99fb8989.css" media="screen" rel="stylesheet" type="text/css" />
	<link href="<%=request.getContextPath()%>/css/c3/samples/chart_combination-da39a3ee.css" media="screen" rel="stylesheet" type="text/css" />
	
	<script src="<%=request.getContextPath()%>/js/c3/vendor/modernizr-2.6.1.min-68fdcc99.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/c3/foundation.min-1dfe8110.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/c3/highlight.pack-4af5004d.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/c3/d3-5.8.2.min-c5268e33.js" type="text/javascript"></script>
	<script src="<%=request.getContextPath()%>/js/c3/c3.min-d2a1f852.js" type="text/javascript"></script>
	
	<!-- 자체제작 메소드 -->
	<script src="<%=request.getContextPath()%>/js/chartMethod/chart.js" type="text/javascript"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/arex-chart.css">
	<link rel="stylesheet" href="<%=request.getContextPath()%>/css/arex.css">
	<!-- 차트 다운로드 -->	
	<script src="<%=request.getContextPath()%>/js/chartMethod/saveSvgAsPng.js" type="text/javascript"></script>

<title>탑</title>
</head>
<script>
	$(document).ready(function() {
		console.log("탑 화면");
		
		setInterval(function(){
			var nowhh = new Date().getHours();
			//새벽 3시~4시 사이에 화면 갱신
			if(nowhh=='3'){
				location.reload();
			}
		},60*60*1000);
		
		var sessionVo = '${login.userId}';
		//로그인(세션) 여부를 판별하여 화면전환 (로그인/메인화면)
		if(sessionVo==''){
			console.log("로그인 세션X");//로그인 안되있음
			alert("로그인이 필요한 서비스이므로 로그인 창으로 이동합니다");
			logBfurl=location.href;
			location.href="/login/login.do";
			
			/* if(location.href.indexOf("/chart/") == -1
			&&location.href.indexOf("/stat/") == -1){
				console.log("로그인 페이지로 이동");
				alert("로그인이 필요한 서비스이므로 로그인 창으로 이동합니다");
				logBfurl=location.href;
				location.href="/login/login.do";
			} */
		}
		
		$('.menu-inner').load("/cmn/menu.do");
		$('#header').load("/cmn/header.do");
		//$('.menu-inner').hide();
		// 메뉴 항목 클릭 시
		$(".goUrlMenu").on("click", function() {
			allTimerReset();
			goMenuSite($(this).attr('id'));
		});
		
		window.onload = function() {
			console.log("윈도우 온로드");
			document.querySelector(".lnb-control").addEventListener(
			"click", function() {
				if ($('body').attr('class') == 'open') {
					$('.menu-inner div').show();
				} else {
					$('.menu-inner div').hide();
				}
				document.body.classList.toggle('open');
			});
		} 
		
		// 윈도우 resize이벤트를 통한 전체화면 여부 체크
		$(window).on('resize', function() {
			if(screen.width === window.innerWidth && screen.height === window.innerHeight){
				console.log("full screen");
			} else {
				console.log("nomal screen");
			}      
		});
	});
</script>

</body>
</html>