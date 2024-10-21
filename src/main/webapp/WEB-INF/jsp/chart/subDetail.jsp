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

.contents_box2 {
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
	margin-left:15px;
}

/* 24-10-18 : 차트 크기 조절 */
@media(max-width : 3840px) {
	#opration_box > svg{
		margin-top : 70px;
	}
	
	#chartDetail {
		margin-top : 40px;
	}

}

@media(max-width : 1920px) {
	#opration_box > svg{
			margin-top : 0px;
	}
	
	#chartDetail {
		margin-top : 0px;
	}
}

</style>
<script>
	var chartObj;
	var lteRIp='';
	
	// 누적 운영 시간 차트 배열 (전역)
	var detailArr = [];
	
	
	$(document).ready(function(){
		
		// 상세보기 버튼 클릭 전 css
		$('#opration_box').hide();
		
		// 상세 클릭 관련 변수
		var detailChecker = 'true';
		
		var operArr = [];
		var dateArr = [];
		
		
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

		
		// 누적 운영 시간 함수 실행
		var secondF = $('#lteROperVal').val();
		operationText(secondF,'true');
		
		
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
	            	]
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
	        }
	    });

	  	//화면 테마에 맞춰 그래프 선 및 폰트 색상 변경
		cssChart();
	  	
		$('#chartDiv svg:first').addClass('svgFirst');
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
	               ['DOWN', DOWN]

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
		
		// 24-10-11 : 배열에 데이터 넣는 작업
		// getMonth 함수 : 운영 누적 시간에서 x축에 넣을 현재 달을 기준으로 -5개월 까지의 값을 배열에 넣어 저장하는 함수
		function getMonths() {
			const cDate = new Date(); // 현재 날짜 가져오기
			const cMonth = cDate.getMonth(); // 현재 월 가져오기 : 0(1월)~11(12월)
			const cArr = []; //반환할 배열
			
			// 월 배열에 넣기
			for(var i = 0; i < 6; i++) {
				var month = (cMonth - i + 12)%12; //12로 나눈 나머지 사용
				cArr.unshift(month+1); // 1월 부터 시작하는 월을 위해 +1
			}
			
			return cArr; // 월을 넣은 배열 리턴
		}
		
		function getDates() {
		    const cDate = new Date(); // 현재 날짜 가져오기
		    const cYear = cDate.getFullYear(); // 현재 년도 가져오기
		    const cMonth = cDate.getMonth(); // 현재 월 가져오기 : 0(1월)~11(12월)
		    const cArr = []; // 반환할 배열

		    // 월 배열에 넣기
		    for (var i = 0; i < 6; i++) {
		        // 12로 나눈 나머지를 사용하여 월 계산
		        var month = (cMonth - i + 12) % 12; 
		        var yearOffset = Math.floor((cMonth - i) / 12); 
		        var year = cYear - yearOffset; 
		        var date = year + "-" + String(month + 1).padStart(2, '0'); 
		        cArr.unshift(date); 
		    }

		    return cArr; // 날짜를 넣은 배열 리턴
		}
		
		
		operArr = getMonths(); // 전역 변수 배열에 값 저장
		dateArr = getDates();
		
		
		// 24-10-16 : 누적 운영 시간 (초)단위에서 -> (년)(개월)(일)(시) 단위로 변경하기
		// 1시간 보다 적은 숫자일 경우에는 (분)(초)로 나오도록 하기
		
		function operationText(second, situation) {
		    var returnOfer = '';
		    var nowTitle = '현재 운영 누적 시간 : ';
		    var allTitle = '총 운영 누적 시간 : ';
		
		    if (situation === 'true') {
		        if (second < 3600) {
		            var returnMin = Math.floor(second / 60);
		            var returnSec = second % 60;
		
		            if (returnMin === 0 && returnSec !== 0) {
		                returnOfer = nowTitle + returnSec + '초';
		            } else if (returnMin !== 0 && returnSec === 0) {
		                returnOfer = nowTitle + returnMin + '분';
		            } else if (returnMin !== 0 && returnSec !== 0) {
		                returnOfer = nowTitle + returnMin + '분  ' + returnSec + '초';
		            } else {
		                returnOfer = nowTitle + '0분 0초';
		            }
		        } else {
		        	var returnY = Math.floor(second / 31622400);
		            var minusY = second - (returnY * 31622400);
		            var returnM = Math.floor(minusY / 2592000);
		            var minusM = minusY - (returnM * 2592000);
		            var returnD = Math.floor(minusM / 86400);
		    		var minusD = minusM - (returnD * 86400);
		    		var returnH = Math.floor(minusD / 3600);
		            returnOfer = nowTitle;
		
		            if (returnY !== 0) {
		                returnOfer += returnY + '년 ';
		            }
		            if (returnM !== 0) {
		                returnOfer += returnM + '개월 ';
		            }
		            if (returnD !== 0) {
		                returnOfer += returnD + '일 ';
		            }
		    		
		            if(returnH !== 0) {
		            	returnOfer += returnH + '시간'	;
		            }
		        }
		
		        $('#oper_text').text(returnOfer);
		        
		    } else {
		    	returnOfer = allTitle;
		    	
		    	if(second == 0) {
		    		returnOfer += '없음';
		    	} else {
		    		
		    		var returnY = Math.floor(second / 31622400);
			        var minusY = second - (returnY * 31622400);
			        var returnM = Math.floor(minusY / 2592000);
			        var minusM = minusY - (returnM * 2592000);
			        var returnD = Math.floor(minusM / 86400);
					var minusD = minusM - (returnD * 86400);
					var returnH = Math.floor(minusD / 3600);
			
			        if (returnY !== 0) {
			            returnOfer += returnY + '년 ';
			        }
			        if (returnM !== 0) {
			            returnOfer += returnM + '개월 ';
			        }
			        if (returnD !== 0) {
			            returnOfer += returnD + '일 ';
			        }
			        if(returnH !== 0) {
			        	returnOfer += returnH + '시간'	;
			        }
		    	}
		    	
		        $('#oper_text').text(returnOfer);
		    }
		}

		$('#showOper').on('click', function(){
			console.log(' 운영시간 차트 버튼 클릭');
			
			// 차트에 넣을 데이터 불러오기
			var detailChart = ajaxMethod("/detailChart.ajax",{"lteRIp":lteRIp}).data;

			//var detailArr = [];

			for (var j = 0; j < dateArr.length; j++) {
			    var found = false; // detailChart에서 찾았는지 여부를 표시하는 변수

			    // detailChart의 모든 값을 순회합니다.
			    for (var i = 0; i < detailChart.length; i++) {
			        // receiveTime이 같으면 lteROper 값을 detailArr에 넣고 found를 true로 설정
			        if (dateArr[j] === detailChart[i].receiveTime.substring(0, 7)) {
						/* var dayVal = Math.floor(detailChart[i].lteROper / 86400);
			            detailArr[j] = dayVal;  */
			            
			            detailArr[j] = detailChart[i].lteROper;
		            found = true;
			            break; // 일치하는 값이 발견되면 inner loop를 종료
			        }
			    }

			    // 찾지 못한 경우 detailArr에 0을 넣음.
			    if (!found) {
			        detailArr[j] = 0;
			    }
			}
			
			// 클릭한 버튼이 상세 보기일 때
			if(detailChecker == 'true') {
				// 버튼 클릭 시 기존 요소 hide 처리
				$('.showSecond').show();
				$('.secondTitle').hide();
				$('#contents_box').hide();
					
				$('#title2').css('margin-bottom','33px');
				
				let beforeVal = null;
				let Ycounter = 0;
				var firstVal = 0;
				var secondVal = 0;
				
				var chartD = c3.generate({
				    bindto: '#opration_box',
					size :{
				    	auto : true
				    }, 
				    data: {
				    	type: "area",
				        x: 'x',
				        columns: [
				            ['x', operArr[0] + '월', operArr[1] + '월', operArr[2] + '월', operArr[3] + '월', operArr[4] + '월', operArr[5] + '월'],
				            ['누적운영시간',detailArr[0],detailArr[1],detailArr[2],detailArr[3],detailArr[4],detailArr[5]] 
				        ],
				        axes: {
				            '누적운영시간': 'y' 
				        }
				    },
				    legend: {
				    	show : false //범례 빼기
				    },
				    axis: {
				        x: {
				            type: 'category',
				            label: {
				                position: 'outer-bottom'
				            }
				        },
				        y: {
				        	
				        	padding: { bottom: 10 }, // 패딩으로 음수값 제거
				        	tick: {
				        		//count : 7,
				                format: function(value) {
									console.log("value	/	Ycounter	/	beforeVal	/	firstVal"+value+"/"+Ycounter+"/"+beforeVal+"/"+firstVal);
									console.log(beforeVal);
									var days = Math.floor(value / 86400);
									/* 	
				                    console.log("format :" + days);
				                    return days >= 0 ? days+'일' : ''; // days가 음수일 경우 빈 문자열 반환 */
				                    //
				                    if(value >= 0){
				                    	
				                    	//초
				                    	if(value<60){
				                    		return value + '초';
				                    	}
				                    	//분
				                    	else if(value<60*60){
				                    		return Math.floor(value / 60) + '분';
				                    	}
				                    	//시
				                    	else if(value<60*60*24){
				                    		return Math.floor(value / (60*60)) + '시간';
				                    	}
				                    	//일
				                    	else{
				                    		return Math.floor(value / (60*60*24)) + '일' + Math.floor( (value % (60*60*24)) / (60*60) ) +'시간';
				                    	}
				                    	
				                    	/* if(Ycounter == 0) {
					                    	Ycounter += 1;
					                    	beforeVal = days;
					                    	firstVal = days;
					                    	return days + '일';
					                    } else {
					                    	if(days == beforeVal) {
					                    		if(beforeVal == firstVal && secondVal == 0) {
					                    			Ycounter += 1;
					                    			 secondVal +=1;
					                    			console.log(beforeVal);
						                    		beforeVal = days;
					                    			return days + '일';
					                    		} else {
						                    		beforeVal = days;
					                    			return '';
					                    		}
					                    	} else {
					                    		beforeVal = days;
					                    		return days + '일';
					                    	}
					                    }  */
				                    	return days + '일';
				                    }
				                }
				            }, 
				            label: {
				                text: '누적 시간',
				                position: 'outer-left'
				            }
				        }
				    },
				    tooltip: {
				    	  show: true
				    	  ,format : {
				    		  value: function (value) {
				                  var returnOfer = '';
				                  var returnY = Math.floor(value / 31622400);
				                  var minusY = value - (returnY * 31622400);
				                  var returnM = Math.floor(minusY / 2592000);
				                  var minusM = minusY - (returnM * 2592000);
				                  var returnD = Math.floor(minusM / 86400);
				                  var minusD = minusM - (returnD * 86400);
				                  var returnH = Math.floor(minusD / 3600);

				                  if (returnY !== 0) {
				                      returnOfer += returnY + '년 ';
				                  }
				                  if (returnM !== 0) {
				                      returnOfer += returnM + '개월 ';
				                  }
				                  if (returnD !== 0) {
				                      returnOfer += returnD + '일 ';
				                  }
				                  if (returnH !== 0) {
				                      returnOfer += returnH + '시간';
				                  }

				                  if (returnOfer === '') {
				                      returnOfer = '0시간'; // 기본값 설정
				                  }

				                  return returnOfer;
				              }
				    	  }
				    }
				});

				cssChart();
				chartD.resize();

/* 				$('#opration_box svg').css('margin-left','32px'); */
				$('#opration_box').show();

				$('#showOper').text("상세접기");
				
				//24-10-16 : 총 운영 누적 시간 / 현재 운영 누적 시간
				var second = detailArr[5];
				operationText(second,'false'); // 상세 접기 X이므로 situation 값에 false 들어감
				
				
				detailChecker = 'false';
			} else { // 클릭한 버튼이 상세 접기일 때
				$('#title2').css('margin-bottom','0px');
				$('#opration_box').hide();
				
				$('.showSecond').hide();
				$('.secondTitle').show();
				$('#contents_box').show();
				
				$('#showOper').text("상세보기");
				detailChecker = 'true';
				
				//24-10-16 : 총 운영 누적 시간 / 현재 운영 누적 시간
				var second = $('#lteROperVal').val();	
				operationText(second,'true'); // 상세 접기 이므로 situation 값에 true 들어감
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
		
		<div id="title2" class="tilte" style="width: 100%;display: flex;align-items: center;justify-content: space-between;">
		<!-- 운영시간 차트 버튼 클릭 시 show 처리 -->
			<span class="showSecond" style="font-size: 25px;font-weight: bold;border-bottom: 2px double yellow;color: #fff; display:none;">${data.lteRUsed} 운영 시간 정보</span>
			<span class="secondTitle" style="font-size: 25px;font-weight: bold;border-bottom: 2px double yellow;color: #fff;">단말기 상세 정보</span>
			<!-- 24-10-10 : 데이터 수신 시각 => 단말기 운영 누적 시간으로 변경 / 상세 보기, 상세보기 접기 버튼 위치 이동 -->
			
			<div class="content_dummy">
				<!--  <span style="font-size: 18px;font-weight: bold;color: #fff;">데이터 수신시각 : ${data.receiveTime}</span> -->
				<span id="oper_text" class="operation_time" style="font-size: 18px;font-weight: bold;color: #fff; margin-left:18px;">현재 운영 누적시간 : ${data.lteROper}초</span> <!-- 값 아직 변경 안됨 / 변경 필요 -->
				<input type='hidden' value='${data.lteROper}' id='lteROperVal'>
				<!--  2024-10-10 : 운영시간 차트 띄우는 버튼  -->
				<button id="showOper">상세보기</button>
			</div>
		</div>
		
		
		<!--  운영 시간 차트 띄울 div  -->
		<div id="opration_box" class="contents_box2" style="display:none; max-height: 300px;position: relative;display: flex;align-content: center;align-items: center;">
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