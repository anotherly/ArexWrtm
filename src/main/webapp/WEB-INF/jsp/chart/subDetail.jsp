<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<style>
.contents_box{
	background:none;
	box-shadow: none;
	padding:0;
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

.ctn_tbl_row .ctn_tbl_th{
	background:#02221A;
	font-size:calc(5px + 0.4vw + 0.4vh);
	flex: 0 0 25%;
	/* height:calc(10px + 0.4vw + 0.4vh); */
}

.ctn_tbl_td{
	background:none;
	font-size:calc(5px + 0.4vw + 0.4vh);
}

.contents_box *{
	color:#fff;
}

#showOper{
	background-color : #006f6f;
	border-radius : 5px;
	border : none;
	color : yellow;
}

</style>
<script>
	var chartObj;
	var lteRIp='';
	$(document).ready(function(){
		// 상세보기 버튼 클릭 전 css
		$('.operation_time').css('margin-right','-260px');
		
		// 상세 클릭 관련 변수
		var detailChecker = 'true';
		
		console.log("서브 상세");
		//차트 화면일경우 상세정보 테마 변경
		/* $('.contents_box').css('background','none');
		$('.ctn_tbl_th').css('background','#02221A');
		$('.ctn_tbl_td').css('background','none');
		$('.ctn_tbl_th').css('font-size','calc(5px + 0.4vw + 0.4vh)');
		$('.ctn_tbl_td').css('font-size','calc(5px + 0.4vw + 0.4vh)');
		 */
		$('.contents_box *').css('color','#fff');
		 
		lteRIp='${data.lteRIp}';
		var rtVo=ajaxMethod("/realtimeChartFirst.ajax",{"lteRIp":lteRIp}).data;
		//chart start
		var memArr;
		var downArr;
		var upArr;
		
		// 2024-10-10 : 2번째 y축 (운영 시간) 변수 생성
		var operArr;
		
		if (typeof rtVo !== "undefined" && rtVo != null  && rtVo != '') {
			memArr=[
				  rtVo[0].memCritVal
		    	, rtVo[1].memCritVal
		    	, rtVo[2].memCritVal
		    	, rtVo[3].memCritVal
		    	, rtVo[4].memCritVal
		    	, rtVo[5].memCritVal
			];
			downArr=[
            	  rtVo[0].lteRComDnVal
            	, rtVo[1].lteRComDnVal
            	, rtVo[2].lteRComDnVal
            	, rtVo[3].lteRComDnVal
            	, rtVo[4].lteRComDnVal
            	, rtVo[5].lteRComDnVal
			];
			upArr=[
            	  rtVo[0].lteRComUpVal
            	, rtVo[1].lteRComUpVal
            	, rtVo[2].lteRComUpVal
            	, rtVo[3].lteRComUpVal
            	, rtVo[4].lteRComUpVal
            	, rtVo[5].lteRComUpVal
			];
			
			$("#memCritVal").text(rtVo[0].memCritVal);
			$("#lteRComUpVal").text(rtVo[0].lteRComUpVal);
			$("#lteRComDnVal").text(rtVo[0].lteRComDnVal);
			
		} else {
			memArr=[0,0,0,0,0,0];
			downArr=[0,0,0,0,0,0];
			upArr=[0,0,0,0,0,0];
			
			// 2024-10-10 : 2번째 y축 (운영 시간) null 일 때 처리
			operArr=[0,0,0,0,0,0];
			
			
			$("#memCritVal").text(0);
			$("#lteRComUpVal").text(0);
			$("#lteRComDnVal").text(0);
			
		}
    	
		var rtVo=ajaxMethod("/realtimeChartFirst.ajax",{"lteRIp":lteRIp}).data;
		var MEMORY = rtVo.memCritVal; 
		var UP = rtVo.lteRComUpVal; 
		var DOWN = rtVo.lteRComDnVal; 
		
	    chartObj = c3.generate({
	        bindto: '#chartDiv',
		    title: { 
		        text: 'MEMORY, UP / DOWNLOAD 현황'
		    },
	        padding: {
	            left: 100,
	            right: 50,
	            bottom: 40
	        },
	        point: {
	            show: false
	        },
	        data: {
	            type: "line",
	            x: 'x',
	            //xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
	            //gettime - x 일때 x는 밀리세컨드 단위임
	            columns: [
	                ['x', 
		                	(new Date().getTime())
		                  , (new Date().getTime()) - 30*1000
		                  , (new Date().getTime()) - 60*1000
		                  , (new Date().getTime()) - 90*1000
		                  , (new Date().getTime()) - 120*1000
		                  , (new Date().getTime()) - 150*1000
		                  //, (new Date().getTime()) - 60000
	                ],
	                ['MEMORY'
	            		, memArr[0]
	            		, memArr[1]
	            		, memArr[2]
	            		, memArr[3]
	            		, memArr[4]
	            		, memArr[5]
	            		],
	            	['UP'
	            		, upArr[0]
	            		, upArr[1]
	            		, upArr[2]
	            		, upArr[3]
	            		, upArr[4]
	            		, upArr[5]
	            	],
	            	['DOWN'
	            		, downArr[0]
	            		, downArr[1]
	            		, downArr[2]
	            		, downArr[3]
	            		, downArr[4]
	            		, downArr[5]
	            	]/* ,
	                ['OPERATIONTIME'
	                	, operArr[0]
	                	, operArr[1]
	                	, operArr[2]
	                	, operArr[3]
	                	, operArr[4]
	                	, operArr[5]
	                ] */
	            ],
	            connectNull: true,
	            axes: {
	            	UP: 'y2',
	            	DOWN: 'y2'
	            }
		        ,colors: {
		        	'MEMORY': '#003399',
		        	'UP': '#71ABA8',
		        	'DOWN': '#A072CE',
/* 		        	'운영시간' : '#DACA00' */
		        }
	        },
	        axis: {
	            x: {
	                type: 'timeseries',
                   	tick: {
                           //count: 6,
                           format: '%H:%M:%S'
                    },
	                /* label: {
	                    text: '시간',
	                    position: 'outer-bottom',
	                } */
	            },
	            y: {
	                label: {
	                	max: 100,
	                    text: '사용량',
	                    position: 'outer-left',
	                }
	            }
	            /* ,y2: {
	            	show:true,
	            	label: {
	                	max: 100,
	                    text: 'mbps',
	                    position: 'outer-right',
	                }
	            } */
	            /* 2024-10-10 : y축 추가 */
	            , y2: {
	            	show : true,
	            	label : {
	            		max : 100,
	            		text : '운영시간',
	            		position : 'outer-right',
	            	}
	            }
	        }
	    });

	  	//화면 테마에 맞춰 그래프 선 및 폰트 색상 변경
		cssChart();
	    
	    $("#chartDiv").css('min-height','38vh');
		chartObj.resize();
		
	    subChartTimer=setInterval(function(){
	    	console.log("차트 갱신");
			var rtVo=ajaxMethod("/realtimeChart.ajax",{"lteRIp":lteRIp}).data;
			var MEMORY = rtVo.memCritVal; 
			var UP = rtVo.lteRComUpVal; 
			var DOWN = rtVo.lteRComDnVal; 
			chartObj.flow({
	           columns: [
	               ['x', (new Date().getTime())],
	               ['MEMORY', MEMORY],
	               ['UP', UP],
	               ['DOWN', DOWN],
	               // 2024-10-10 : 2번째 y축 생성
/* 	               ['OPERATIONTIME', OPERATIONTIME] */
	           ],
	       });
			cssChart();
			
			//상세항목 갱신
			$("#memCritVal").text(rtVo.memCritVal);
			$("#lteRComUpVal").text(rtVo.lteRComUpVal);
			$("#lteRComDnVal").text(rtVo.lteRComDnVal);
			
		},30*1000);
		
		//chart end

		//접어두기 시 전체차트 소환
		$("#flip").on('click',function(){
			$("#lteTbd tr td").each(function(i,list){
				$(this).removeClass("selected");
			});
			chkTerId='';
			chartTimerReset();
			$("#all_chart").empty();
			var userAuth='${login.userAuth}';
			if(userAuth==0){
				$("#all_chart").load("/chart/mainAdminChart.do");
			}else{
				$("#all_chart").load("/chart/mainUserChart.do");
			}
		});
		
		
		
		$('#showOper').on('click', function(){
			console.log(' 운영시간 차트 버튼 클릭');
			
			// 클릭한 버튼이 상세 보기일 때
			if(detailChecker == 'true') {
				// 버튼 클릭 시 기존 요소 hide 처리
				$('.showSecond').show();
				$('.secondTitle').hide();
				$('#contents_box').hide();
				
				// 버튼 클릭 시 텍스트 위치 조절
				$('.operation_time').css('margin-right','-134px');
				$('#showOper').text("상세접기");
				
				detailChecker = 'false';
			} else { // 클릭한 버튼이 상세 접기일 때
				$('.showSecond').hide();
				$('.secondTitle').show();
				$('#contents_box').show();
				
				$('.operation_time').css('margin-right','-260px');
				$('#showOper').text("상세보기");
				
				detailChecker = 'true';
			}
		});
	});
</script>

<div class="tilte" style="width: 100%;display: flex;align-items: center;justify-content: space-between;margin-bottom: 33px;">
	<span style="font-size: 25px;font-weight: bold;border-bottom: 2px double yellow;color: #fff;">${data.lteRUsed}</span>
	<span id="flip" style="font-size: 20px;font-weight: bold;color:yellow;cursor: pointer;">◀◀ 접어두기</span>
</div>
<div id="content" style="width: 100%;">
	<div id="chartDiv" class="chartDiv"></div>
</div>
<div id="chartDetail" style="width: 100%;">
 <div id="contents" class="contents-wrap" style="height:40vh;margin-top:0px;">
	<!-- work Start -->
	<div id="work" class="work-wrap" style="min-width: unset;padding:0px;">
		<!-- contents_box Start -->
		
		<div class="tilte" style="width: 100%;display: flex;align-items: center;justify-content: space-between;">
		<!-- 운영시간 차트 버튼 클릭 시 show 처리 -->
			<span class="showSecond" style="font-size: 25px;font-weight: bold;border-bottom: 2px double yellow;color: #fff; display:none;">${data.lteRUsed} 운영 시간 정보</span>
			<span class="secondTitle" style="font-size: 25px;font-weight: bold;border-bottom: 2px double yellow;color: #fff;">단말기 상세 정보</span>
			<!-- 24-10-10 : 데이터 수신 시각 => 단말기 운영 누적 시간으로 변경 / 상세 보기, 상세보기 접기 버튼 위치 이동 -->
			
			<!--  <span style="font-size: 18px;font-weight: bold;color: #fff;">데이터 수신시각 : ${data.receiveTime}</span> -->
			<span class="operation_time" style="font-size: 18px;font-weight: bold;color: #fff;">단말기 운영 누적시간 : ${data.receiveTime}</span> <!-- 값 아직 변경 안됨 / 변경 필요 -->
			<!--  2024-10-10 : 운영시간 차트 띄우는 버튼  -->
			<button id="showOper">상세보기</button>
		</div>
		
		
		<!--  운영 시간 차트 띄울 div  -->
		<div id="opration_box" class="contents_box" style="display:none;">
		</div>
		
		<div id="contents_box" class="contents_box">
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">사용 용도</div>
						<div class="ctn_tbl_td">
							<span id="lteRUsed">${data.lteRUsed}</span>
						</div>
						<div class="ctn_tbl_th">설치 위치</div>
						<div class="ctn_tbl_td">
							<span id="insLocTxt">${data.insLocTxt}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">단말기 고유 IP</div>
						<div class="ctn_tbl_td">
							<span id="lteRIp">${data.lteRIp}</span>
						</div>
						<div class="ctn_tbl_th">MAC Add</div>
						<div class="ctn_tbl_td">
							<span  id="lteRMacAdd">${data.lteRMacAdd}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">SW 버전</div>
						<div class="ctn_tbl_td">
							<span  id="swVerCode">${data.swVerCode}</span>
						</div>
						<div class="ctn_tbl_th">MEMORY(KB)</div>
						<div class="ctn_tbl_td">
							<span id="memCritVal">${data.memCritVal}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">UPLOAD(MB)</div>
						<div class="ctn_tbl_td" >
							<span id="lteRComUpVal">${data.lteRComUpVal}</span>
						</div>
						<div class="ctn_tbl_th">DOWNLOAD(MB)</div>
						<div class="ctn_tbl_td" >
							<span id="lteRComDnVal">${data.lteRComDnVal}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">RSRP 값</div>
						<div class="ctn_tbl_td" id="RSRP">
							${data.RSRP}
						</div>
						<div class="ctn_tbl_th">RSRQ 값</div>
						<div class="ctn_tbl_td">
							<span  id="RSRQ">${data.RSRQ}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Client #1</div>
						<div class="ctn_tbl_td">
							<span id="conSystem01Ip">${data.conSystem01Ip}</span>
						</div>
						<div class="ctn_tbl_th">Client #2</div>
						<div class="ctn_tbl_td">
							<span id="conSystem02Ip">${data.conSystem02Ip}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">IMEI 정보</div>
						<div class="ctn_tbl_td">
							<span id="lteRImei">${data.lteRImei}</span>
						</div>
						<div class="ctn_tbl_th">IMSI 정보</div>
						<div class="ctn_tbl_td">
							<span id="lteRImsi">${data.lteRImsi}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">MCC</div>
						<div class="ctn_tbl_td">
							<span id="lteRMCC">${data.lteRMCC}</span>
						</div>
						<div class="ctn_tbl_th">MNC</div>
						<div class="ctn_tbl_td">
							<span id="lteRMNC">${data.lteRMNC}</span>
						</div>
					</div>
					
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">Band</div>
						<div class="ctn_tbl_td">
							<span id="lteRBand">${data.lteRBand}</span>
						</div>
						<div class="ctn_tbl_th">Channel </div>
						<div class="ctn_tbl_td">
							<span id="lteRChannel">${data.lteRChannel}</span>
						</div>
					</div>
				</div>
				<!-- btn_box Start -->
			</div>
		<!-- contents_box End -->
		</div>
	</div>	
	
</html>