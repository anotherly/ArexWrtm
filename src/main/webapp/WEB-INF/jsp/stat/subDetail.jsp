<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<script>
	var chartObj;
	var lteRIp='';
	$(document).ready(function(){
		//console.log("서브 상세");
		//차트 화면일경우 상세정보 테마 변경
		$('.contents_box').css('background','none');
		$('.ctn_tbl_th').css('background','#02221A');
		$('.ctn_tbl_td').css('background','none');
		$('.ctn_tbl_th').css('font-size','calc(1vw - 0px)');
		$('.ctn_tbl_td').css('font-size','calc(1vw - 0px)');
		$('.contents_box *').css('color','#fff');
		
		lteRIp='${data.lteRMacAdd}';
		var rtVo=ajaxMethod("/realtimeChartFirst.ajax",{"lteRIp":lteRIp}).data;
		//chart start
		var memArr;
		var downArr;
		var upArr;
		
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
		} else {
			memArr=[0,0,0,0,0,0];
			downArr=[0,0,0,0,0,0];
			upArr=[0,0,0,0,0,0];
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
	            	]
	            ],
	            connectNull: true,
	            axes: {
	            	UP: 'y2',
	            	DOWN: 'y2'
	            }
		        ,colors: {
		        	'MEMORY': '#DACA00',
		        	'UP': '#D08B22',
		        	'DOWN': '#A072CE'
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
	                    text: 'kbps',
	                    position: 'outer-left',
	                }
	            }
	            ,y2: {
	            	show:true,
	            	label: {
	                	max: 100,
	                    text: 'mbps',
	                    position: 'outer-right',
	                }
	            }
	        }
		    ,legend: {
		        padding: 15,
		        item: {
		          tile: {
		            width: 20,
		            height: 20
		          }
		        }
		      }
	    });

	  	//화면 테마에 맞춰 그래프 선 및 폰트 색상 변경
		cssChart();
	    
	    $("#chartDiv").css('min-height','42vh');
		chartObj.resize();
	    
		//$("#chartDetail").load("/chart/subDetail.do",{"lteRIp":lteRIp});
		
	    subChartTimer=setInterval(function(){
	    	//console.log("nws timeinterval");
	    	
			var rtVo=ajaxMethod("/realtimeChart.ajax",{"lteRIp":lteRIp}).data;
			var MEMORY = rtVo.memCritVal; 
			var UP = rtVo.lteRComUpVal; 
			var DOWN = rtVo.lteRComDnVal; 
			/* if(typeof rtVo !=="undefined" && rtVo!=null){
				RSRP = rtVo.RSRP;
				RSRQ = rtVo.RSRQ;
			} */
			chartObj.flow({
				/* padding: {
		            left: 50,
		            right: 50
		        }, */
	           columns: [
	               ['x', (new Date().getTime())],
	               ['MEMORY', MEMORY],
	               ['UP', UP],
	               ['DOWN', DOWN]
	           ],
	       });
			cssChart();
		},30*1000);
		
		//chart end

		//접어두기 시 전체차트 소환
		$("#flip").on('click',function(){
			timerReset();
		    /* $("#all_chart").show();
			$("#container_chart").hide(); */
			$("#all_chart").empty();
			var userAuth='${login.userAuth}';
			if(userAuth==0){
				$("#all_chart").load("/chart/mainAdminChart.do");
			}else{
				$("#all_chart").load("/chart/mainUserChart.do");
			}
		});
	});
</script>

<div class="tilte" style="width: 100%;display: flex;align-items: center;justify-content: space-between;margin-bottom: 33px;">
	<span style="font-size: 25px;font-weight: bold;border-bottom: 2px double yellow;color: #fff;">${data.lteRUsed}</span>
	<span id="flip" style="font-size: 20px;font-weight: bold;color:yellow;    cursor: pointer;">◀◀ 접어두기</span>
</div>
<div id="content" style="width: 100%;">
	<div id="chartDiv" class="chartDiv"></div>
</div>
<div id="chartDetail" style="width: 100%;">
 <div id="contents" class="contents-wrap" style="height:40vh;margin-top:0px;">
	<!-- work Start -->
	<div id="work" class="work-wrap" style="min-width: unset;padding:0px;">
		<!-- contents_box Start -->
		<div id="contents_box" class="contents_box">
			<!-- 컨텐츠 테이블 헤더 Start -->
			<!-- 컨텐츠 테이블 헤더 End -->
			<!-- 컨텐츠 테이블 영역 Start -->
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">단말기 고유 IP</div>
						<div class="ctn_tbl_td">
							${data.lteRIp}
						</div>
						<div class="ctn_tbl_th">MAC Add</div>
						<div class="ctn_tbl_td">
							${data.lteRMacAdd}
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">사용 용도</div>
						<div class="ctn_tbl_td">
							${data.lteRUsed}
						</div>
						<div class="ctn_tbl_th">설치 위치</div>
						<div class="ctn_tbl_td">
							${data.insLocTxt}
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th">RSRP 값</div>
						<div class="ctn_tbl_td">
							${data.RSRP}
						</div>
						<div class="ctn_tbl_th">RSRQ 값</div>
						<div class="ctn_tbl_td">
							${data.RSRQ}
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
						<div class="ctn_tbl_th">MCC</div>
						<div class="ctn_tbl_td">
							${data.lteRMCC}
						</div>
						<div class="ctn_tbl_th">MNC</div>
						<div class="ctn_tbl_td">
							${data.lteRMNC}
						</div>
					</div>
				</div>
				<!-- btn_box Start -->
			</div>
		<!-- contents_box End -->
		</div>
	</div>	
	
</html>