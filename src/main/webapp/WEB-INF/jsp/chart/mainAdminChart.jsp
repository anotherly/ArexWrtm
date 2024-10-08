<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>단말장치(LTE-R) 관리 WEB 시스템</title>
<meta charset="UTF-8">

<script>

	var pie1;
	var pie2;
	var bar;

	$(document).ready(function(){
		console.log("메인차트 관리자");
		var alData=ajaxMethod("/chart/mainAdminChart.ajax");
		//데이터 집계부분
		var pie1Data = alData.data1;
		var pie2Data = alData.data2;
		var barData  = alData.data3;
		
		var data1 = {};
		var lgnds1 = []; 
		var data2 = {};
		var lgnds2 = []; 
		pie1Data.forEach(function(e) {
			lgnds1.push(e.teamName);
		    data1[e.teamName] = e.cnt;
		});
		pie2Data.forEach(function(e) {
			lgnds2.push(e.keyVal);
		    data2[e.keyVal] = e.cnt;
		}); 
		
		//범례값이 없을경우..
		//사실 초기 디비설계만 잘짰어도 이런 뻘짓을 안해도 되는데..
		//RSSI_STREGTH를 범례랑 값으로만 컬럼을 구성했어야만함
		if(lgnds2.indexOf('EXCELLENT')==-1){
			lgnds2.push('EXCELLENT');
		    data2['EXCELLENT'] = 0;
		}
		if(lgnds2.indexOf('GOOD')==-1){
			lgnds2.push('GOOD');
		    data2['GOOD'] = 0;
		}
		if(lgnds2.indexOf('MIDDLE')==-1){
			lgnds2.push('MIDDLE');
		    data2['MIDDLE'] = 0;
		}
		if(lgnds2.indexOf('WEAK')==-1){
			lgnds2.push('WEAK');
		    data2['WEAK'] = 0;
		}
		////////////////////////
		
		var colorArr2 = {
			'EXCELLENT': '#0072BE',
            'GOOD': '#93BDC7',
            'MIDDLE': '#A072CE',
            'WEAK':'#bd0088'
		};
		
		//원형 start
		pie1 = c3.generate({
			bindto: '#pie_chart1',
		    data: {
		        json: [ data1 ],
		        keys: {
		            value: lgnds1,
		        },
		        type:'pie',
		        colors: {
		            '신호팀': '#578BC9',
		            '시설팀': '#85B400',
		            '전기팀': '#D08B22'
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
		
		pie2 = c3.generate({
			bindto: '#pie_chart2',
		    data: {
		        json: [ data2 ],
		        keys: {
		            value: lgnds2,
		        },
		        type:'pie'
	        	,colors: colorArr2
		    }
		    ,legend: {
		        //padding: 15,
		        item: {
		          tile: {
		            width: 20,
		            height: 20
		          }
		        }
		      }
		});
		
		var bar = c3.generate({
			bindto: '#bar_chart',
		    data: {
		    	columns: [
		            ['DOWN', barData[0].lteRComDnVal, barData[1].lteRComDnVal, barData[2].lteRComDnVal],
		            ['UP'  , barData[0].lteRComUpVal, barData[1].lteRComUpVal, barData[2].lteRComUpVal]
		        ],
		        type: 'bar'
		    }
			,color: {
	            pattern: ['#A072CE','#71ABA8']
	        },
		    axis: {
			    x: {
			        type: 'category',
			        categories: ['신호','시설','전기']
			    },
	            y: {
	                label: {
	                    text: '사용량(MB)',
	                    position: 'outer-left',
	                }
	            }
		   }
	        ,bar: {
		        width: {
		            ratio: 0.5 // this makes bar width 50% of length between ticks
		        }
		        // or
		        //width: 100 // this makes bar width 100px
		    }
			,legend: {
				//padding: 30,
				item: {
					tile: {
						width: 20,
						height: 20
					}
				}
			}
		});
		
		//원형 end

		//최초 사이즈 지정
		$("#pie_chart1").css('min-width','25vw');
		$("#pie_chart1").css('min-height','35vh');
		
		$("#pie_chart2").css('min-width','25vw');
		$("#pie_chart2").css('min-height','35vh');
		
		$("#bar_chart").css('min-width','40vw');
		$("#bar_chart").css('min-height','38vh');
		
		//거지같은 범례 내리기 트랜스레이트
		$("g.c3-legend-item.c3-legend-item-UP").css("transform","translateY(10px)");
		$("g.c3-legend-item.c3-legend-item-DOWN").css("transform","translateY(10px)");
		
		//반응형 화면 사이즈에 맞춰 사이즈 조정
		pie1.resize();
		pie2.resize();
		bar.resize();

		//화면 테마에 맞춰 그래프 선 및 폰트 색상 변경
		cssChart();
		
	});
</script>
</head>
	<div id="pie_container" class="pie_container" style="width: 100%;display: flex;flex-direction: row;align-items: center;justify-content: space-around;">
		<div class="pie-contents">
			<h1 class="chart-title">무선장치 운용 현황</h1>
			<div id="pie_chart1" class=""></div>
		</div>
		<div class="pie-contents">
			<h1 class="chart-title">RSSI 수신률</h1>
			<div id="pie_chart2" class=""></div>
		</div>
	</div>
	<div id="bar_container">
		<h1 class="chart-title" style="text-align: center;">무선장치 통신 UP/Down Load 현황</h1>
		<div id="bar_chart" class=""></div>
	</div>
</html>