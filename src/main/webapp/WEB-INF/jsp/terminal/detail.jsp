<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
 	<title>단말장치(LTE-R) 관리 WEB 시스템</title>
	<meta charset="UTF-8">
    <jsp:include page="../cmn/top.jsp" flush="false" />
	<style>
	/* .ctn_tbl_row .ctn_tbl_th {
	    flex: 0 0 150px;
	} */
	
	/* 단말기 관리 > 상세 페이지의 경우 세로로 길기 때문에 x축만 overflow hidden 처리 */
	html, body {
		overflow-x : hidden;
		overflow-y : auto;
	}
	
	.ctn_tbl_row .ctn_tbl_th,.ctn_tbl_row .ctn_tbl_td{
		min-height:unset;
		height:50px;
	}
	
	@media screen and (max-width: 1920px){
		.ctn_tbl_row .ctn_tbl_th,.ctn_tbl_row .ctn_tbl_td{
			height:40px;
		}
	}
	
	</style>
	<script>
	
		$(document).ready(function(){
			console.log("상세");
			var tagId="${data.lteRIp}";
			
			$("#btnSave").on('click', function(){
				console.log('클릭');
				/* $("#work").load('/terminal/update.do',{"lteRIp":tagId}); */
				location.href='/terminal/update.do?lteRIp='+tagId;
			});
			
			$("#btnCancel").on('click', function(){
				location.href='/terminal/list.do';
			});
			//$(".ctn_tbl_row .ctn_tbl_th").css("flex","0 0 150px");
		});
	
	</script>
</head>
<body class="open">
	<!-- lnb Start ------------------>
	    <aside id="lnb" class="lnb">
	        <a class="lnb-control" title="메뉴 펼침/닫침"><span class="menu-toggle">메뉴 펼침/닫침</span></a>
	        <nav class="navbar navbar-expand-sm navbar-default">
	            <ul class="menu-inner"></ul>
	        </nav>
	    </aside>
    <!-- lnb End ------------------>
    
    <!-- container Start ------------------>
	<div id="container" class="container-wrap"  style="margin-top: 0px;">
		<!-- header Start ------------------>
		<div id="header" class="header-wrap">
		</div>
		<!-- header End ------------------>
		<!-- contents Start ------------------>
		<div id="contents" class="contents-wrap">
			<!-- work Start -->
			<div id="work" class="work-wrap" style="padding-top: 0px;">
				<!-- contents_box Start -->
				<div id="contents_box" class="contents_box">
					<!-- 컨텐츠 테이블 헤더 Start -->
					<div class="ctn_tbl_header">
						<div class="ttl_ctn">LTE-R 단말 상세정보</div>
					</div>
					<!-- 컨텐츠 테이블 헤더 End -->
					<!-- 컨텐츠 테이블 영역 Start -->
						<div class="ctn_tbl_area">
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">팀명</div>
								<div class="ctn_tbl_td">
									${data.teamName}
								</div>
								<div class="ctn_tbl_th ">정보 수신일자</div>
								<div class="ctn_tbl_td">
									${data.getDeviceInfoYdt}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">장비명(사용 용도)</div>
								<div class="ctn_tbl_td">
									${data.lteRUsed}
								</div>
								<div class="ctn_tbl_th">설치 위치</div>
								<div class="ctn_tbl_td">
									${data.insLocTxt}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">IMEI 정보</div>
								<div class="ctn_tbl_td">
									${data.lteRImei}
								</div>
								<div class="ctn_tbl_th">IMSI 정보</div>
								<div class="ctn_tbl_td">
									${data.lteRImsi}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">Serial No</div>
								<div class="ctn_tbl_td">
									${data.lteRserial}
								</div>
								<div class="ctn_tbl_th">MAC Add</div>
								<div class="ctn_tbl_td">
									${data.lteRMacAdd}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">WAN IP</div>
								<div class="ctn_tbl_td">
									${data.lteRIp}
								</div>
								<div class="ctn_tbl_th">게이트웨이 IP</div>
								<div class="ctn_tbl_td">
									${data.conGw}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">Client#1 IP</div>
								<div class="ctn_tbl_td">
									${data.conSystem01Ip}
								</div>
								<div class="ctn_tbl_th">Client#1 이름</div>
								<div class="ctn_tbl_td">
									${data.conSystem01}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">Client#2 IP</div>
								<div class="ctn_tbl_td">
									${data.conSystem02Ip}
								</div>
								<div class="ctn_tbl_th">Client#2 이름</div>
								<div class="ctn_tbl_td">
									${data.conSystem02}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">USIM 번호</div>
								<div class="ctn_tbl_td">
									${data.lteRSimNo}
								</div>
								<div class="ctn_tbl_th">사용자 ID</div>
								<div class="ctn_tbl_td">
									${data.lteRUseRId}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">무선장치 모델</div>
								<div class="ctn_tbl_td">
									${data.lteRDeviceType}
								</div>
								<div class="ctn_tbl_th">펌웨어 버전</div>
								<div class="ctn_tbl_td">
									${data.swVerCode}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">팀별 단말 인덱스</div>
								<div class="ctn_tbl_td">
									${data.teamCnt}
								</div>
								<div class="ctn_tbl_th">전체 단말 인덱스</div>
								<div class="ctn_tbl_td">
									${data.deviceCnt}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">Cell ID</div>
								<div class="ctn_tbl_td">
									${data.lterCellId}
								</div>
								<div class="ctn_tbl_th">사용 유무</div>
								<div class="ctn_tbl_td">
									<c:if test="${data.useYn eq 1}">사용</c:if>
									<c:if test="${data.useYn eq 0}">미사용</c:if> 
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">Band</div>
								<div class="ctn_tbl_td">
									${data.lteRBand}
								</div>
								<div class="ctn_tbl_th">Channel </div>
								<div class="ctn_tbl_td">
									${data.lteRChannel}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">MCC</div>
								<div class="ctn_tbl_td">
									${data.lteRMCC}
								</div>
								<div class="ctn_tbl_th">MNC</div>
								<div class="ctn_tbl_td">
									${data.lteRMNC}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">원격리셋 날짜</div>
								<div class="ctn_tbl_td">
									${data.rmtResetYdt}
								</div>
								<div class="ctn_tbl_th">원격리셋 결과</div>
								<div class="ctn_tbl_td">
									${data.rmtResetRlt}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">원격 업데이트 날짜</div>
								<div class="ctn_tbl_td">
									${data.rmtUptYdt}
								</div>
								<div class="ctn_tbl_th">원격 업데이트 결과</div>
								<div class="ctn_tbl_td">
									${data.rmtUptRlt}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th">동록일자</div>
								<div class="ctn_tbl_td">
									${data.regYdt}
								</div>
								<div class="ctn_tbl_th">정보전송 주기</div>
								<div class="ctn_tbl_td">
									${data.intervalTime}
								</div>
							</div>
						</div>
						
						<!-- btn_box Start -->
						<div class="btn_box">
							<div class="right">
								<input type="button" class="btn btn_primary" id="btnSave" alt="저장" value="수정" />
								<input type="button" class="btn" id="btnCancel" alt="취소" value="취소" />
							</div>
						</div>
						<!-- btn_box End -->
				</div>
				<!-- contents_box End -->

				<!-- footer Start ------------------>
				<!-- footer End ------------------>

			</div>
			<!-- work End -->
		</div>
		<!-- contents End ------------------>
	</div>
	<!-- container End ------------------>
</body>
</html>