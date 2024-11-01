//갱신함수의 관련 타이머 셋팅
var mainChartTimer=null;
var subChartTimer=null;
var tableTimer=null;

function chartTimerReset(){
	clearInterval(mainChartTimer);
	mainChartTimer=null;
	clearInterval(subChartTimer);
	subChartTimer=null;
}

function allTimerReset(){
	clearInterval(mainChartTimer);
	mainChartTimer=null;
	clearInterval(subChartTimer);
	subChartTimer=null;
	clearInterval(tableTimer);
	tableTimer=null;
}

/************************************************************************
함수명 : exportChartToPng
설 명 : 차트 svg를 이미지로 변환
인 자 : chartid
사용법 : 
작성일 : 2020-12-21
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.12.21   정다빈       최초작성
************************************************************************/

function exportChartToPng(chartID){
	//fix weird back fill
	//console.log("exportChartToPng 차트아이디: "+chartID);
	d3.select('#'+chartID).selectAll("path").attr("fill", "none");
	//fix no axes
	d3.select('#'+chartID).selectAll("path.domain").attr("stroke", "black");
	//fix no tick
	d3.select('#'+chartID).selectAll(".tick line").attr("stroke", "black");
	fcnt = $('#'+chartID).find('svg').length;
	var svgElement = $('#'+chartID).find('svg')[downCnt];
	saveSvgAsPng(svgElement, chartID+'.png');
}

function cssChart(){
//	console.log("화면에 맞게 차트요소 색상변경");
	$("g text").css("fill","#fff");
	
	$(".c3-axis c3-axis-x text").css("fill","#fff");
	$("tspan").css("fill","#fff");
	
	$(".c3-axis-x path").css("stroke","#fff");
	$(".c3-axis-x line").css("stroke","#fff");
	
	$(".c3-axis-y path").css("stroke","#fff");
	$(".c3-axis-y line").css("stroke","#fff");
	$(".c3-axis-y2 path").css("stroke","#fff");
	$(".c3-axis-y2 line").css("stroke","#fff");
	
//	$(".c3-target-여유").children().css("fill","black");
//	$(".c3-target-보통").children().css("fill","black");
//	$(".c3-target-주의").children().css("fill","black");
//	$(".c3-target-혼잡").children().css("fill","black");
	
	$(".c3-chart-arc").css("font-weight","bold");
}

function cssNonChart(){
//	console.log("화면에 맞게 차트요소 색상변경");
	$("text").css("fill","");
	
	$(".c3-axis c3-axis-x text").css("fill","");
	$(".c3-axis c3-axis-y text").css("fill","");
	$("tspan").css("fill","");
	
	$(".c3-axis-x path").css("stroke","");
	$(".c3-axis-x line").css("stroke","");
	
	$(".c3-axis-y path").css("stroke","");
	$(".c3-axis-y line").css("stroke","");
	
	//$(".c3-chart-arc").css("font-weight","bold");
}



//월별 프로세스 종료횟수 막대
function pckillCnt(sdate){
	console.log('프로세스 종료 횟수 차트 진입');
	 var stvo1 = ajaxMethod("/stat/pckillCnt.ajax",{"sDate":sdate}).data;
	 const jsonD = JSON.stringify(stvo1);
	 
	 var mBarC = c3.generate({
		 		bindto: '#proKill',
		 		size: {
			        width: 600,
			        height: 250
			    },
			    title: { 
			        text: '주별 프로세스 종료 횟수'
			    },
		        data: {
		            json: JSON.parse(jsonD),
		            keys: {
		                x: 'reg_date', // it's possible to specify 'x' when category axis
		                value: ['count'],
		            },
		            type: 'bar'
		        },
		        bar: {
		        	width: 30
//			        width: {
//			            ratio: 0.5 // this makes bar width 50% of length between ticks
//			        }
			    },
			    legend: {
			        show: false
			    },
			    axis: {
		            x: {
				        type: 'category',
		                label: {
		                    text: '시간',
		                    position: 'outer-top',
		                }
		            },
		            y: {
		                label: {
		                	max: 100,
		                    text: '건수',
		                    position: 'outer-right',
		                }
		            }
		        }
		    });
	 return mBarC;
}

//x축이 가변적이기 때문에 계속 재정의해야함
/*function reloadBar(chart,stvo1){
	//아작스 데이터 연동시에 받을때
	//addobject로 보낸 문자값 앞에 . 붙여서 넘겨주는거 잊지말자...
	//그리고 얘같은 경우는 (막대는) x축 바뀔수도 있으니 load가 아니라 generate로 구상
	const jsonD = JSON.stringify(stvo1);
	chart=c3.generate({
        data: {
            json: JSON.parse(jsonD),
            keys: {
                value: ['cnt']
            }
        }
	});
	return chart;
}*/

//월별 이벤트로그 종류 원형
function evtlogLgd(sdate){
	 var stvo1 = ajaxMethod("/stat/evtlogLgd.ajax",{"sDate":sdate}).data;
	 /* var stvo2 = ajaxMethod("/stat/evtlogLgd.ajax");
	 const jsonD = JSON.stringify(stvo1); */
	//var jsonData = JSON.parse(jsonD);
	console.log("이벤트로그 차트 진입1");
	  
	var data = {};
	var lgnds = []; 
	stvo1.forEach(function(e) {
		lgnds.push(e.source);
	    data[e.source] = e.cnt;
	});   
	 
	 //const jsonD = JSON.stringify(stvo1);
	console.log("이벤트로그 차트 진입");
    var mPieC = c3.generate({
        bindto: '#evtLog',
        size: {
	        width: 600,
	        height: 250
	    },
	    title: { 
	        text: '일일 이벤트로그 유형'
	    },
	    data: {
	        json: [ data ],
	        keys: {
	            value: lgnds,
	        },
	        type:'pie'
	    }
    });
	 return mPieC;
}


//얘도 로드하니 데이터가 안없어지니 그냥 제너레이트를 하는걸로
/*function reloadPie(chart,stvo1){
	console.log('원형 갱신');
	var data = {};
	var lgnds = []; 
	stvo1.forEach(function(e) {
		lgnds.push(e.source);
	    data[e.source] = e.cnt;
	}); 
	chart.load({
        json: [ data ],
        keys: {
            value: lgnds
        }
    });
	
	return chart;
}*/

/************************************************************************
함수명 : realtimeLine
설 명 : 선형 실시간 그래프
인 자 : url(데이터를 ajax로 받아올 url),div(표출할 div),title:제목,wid:가로,ht:세로
ld1,ld2,ld3,ld4:범례항목
사용법 : 
작성일 : 2020-06-28
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2022.06.28   정다빈       최초작성
************************************************************************/
function realtimeLine(url,div,title,wid,ht,timer,ld1,ld2,ld3,ld4){
	
	var chartA = c3.generate({
        bindto: '#'+div,
        size: {
	        width: 1200,
	        height: 250
	    },
	    title: { 
	        text: '전체/프로세스 CPU 사용량'
	    },
        /*padding: {
            left: 50,
            right: 50
        },*/
        point: {
            show: false
        },

        data: {
            type: "line",
            x: 'x',
            //xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
            columns: [
                ['x', (new Date().getTime()), (new Date().getTime()) - 10000, (new Date().getTime()), (new Date().getTime()) - 20000, (new Date().getTime()), (new Date().getTime()) - 30000, (new Date().getTime()), (new Date().getTime()) - 40000, (new Date().getTime()), (new Date().getTime()) - 50000, (new Date().getTime()), (new Date().getTime()) - 60000],
                //['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],

                ['ld1', null, null, null, null, null, null, null],
                ['ld2', null, null, null, null, null, null, null]
            ],
            connectNull: true
        },
        axis: {
            x: {
                type: 'timeseries',
                tick: {
                    format: '%H:%M:%S'
                },
                label: {
                    text: 'Time',
                    position: 'outer-top',
                }
            },
            y: {
                label: {
                	max: 100,
                    text: '%',
                    position: 'outer-right',
                }
            }
        }
    });

	timerMethod(url,timer,1000,chartA,ld1,ld2);
	
}


/************************************************************************
함수명 : timerMethod
설 명 : 실시간 그래프에 대한 타이머 제어 함수 (타이머 변수에 따른) 
인 자 : url(데이터를 ajax로 받아올 url),timer(타이머 변수),timeRange:시간설정범위,chartP(제어할 차트),dv(데이터 항목에 따른 구분자)
ld1,ld2,ld3,ld4:범례항목
사용법 : 
작성일 : 2020-06-28
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2022.06.28   정다빈       최초작성
************************************************************************/
function timerMethod(url,timer,timeRange,chartP,dv,ld1,ld2,ld3,ld4){
    cputime= setInterval(function(){
		var rtVo=ajaxMethod(url).data;
        var sys_cpu = rtVo.SystemCPUUsage;
        var pro_cpu = rtVo.ProcessCPUUsage;
        
        //ld 개수에 따라 colums 개수를 바꿔야 함
        chartP.flow({
            columns: [
                ['x', (new Date().getTime())],
                ['전체 CPU 사용량', sys_cpu],
                ['프로세스 CPU 사용량', pro_cpu]
            ],
        });
	},timeRange);
}


function mainAdminChart(){
	alData=ajaxMethod("/chart/mainAdminChart.ajax");
	
	if(
		typeof alData !== "undefined" && alData !=null && alData !=''		
	){
	
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
	}
}

function mainUserChart(){
	var alData=ajaxMethod("/chart/mainUserChart.ajax");
	
	if(
		typeof alData !== "undefined" && alData !=null && alData !=''		
	){
	
	var pie1Data=alData.data1;
	var pie2Data=alData.data2;
	var barData=alData.data3;
	
	var data1 = {};
	var lgnds1 = []; 
	var data2 = {};
	var lgnds2 = []; 
	pie1Data.forEach(function(e) {
		lgnds1.push(e.keyVal);
	    data1[e.keyVal] = e.cnt;
	}); 
	pie1Data.forEach(function(e) {
		lgnds2.push(e.keyVal);
	    data2[e.keyVal] = e.cnt;
	}); 
	
	//범례값이 없을경우..
	//사실 초기 디비설계만 잘짰어도 이런 뻘짓을 안해도 되는데..
	//RSSI_STREGTH를 범례랑 값으로만 컬럼을 구성했어야만함
	if(lgnds1.indexOf('EXCELLENT')==-1){ 
		lgnds1.push('EXCELLENT');
	    data1['EXCELLENT'] = 0;
	}
	if(lgnds1.indexOf('GOOD')==-1){ 
		lgnds1.push('GOOD');
	    data1['GOOD'] = 0;
	}
	if(lgnds1.indexOf('MIDDLE')==-1){ 
		lgnds1.push('MIDDLE');
	    data1['MIDDLE'] = 0;
	}
	if(lgnds1.indexOf('WEAK')==-1){ 
		lgnds1.push('WEAK');
	    data1['WEAK'] = 0;
	}
	////////////////////////
	
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
	
	//원형 start
	pie1 = c3.generate({
		bindto: '#pie_chart1',
	    data: {
	        json: [ data1 ],
	        keys: {
	            value: lgnds1,
	        },
	        type:'pie'
        	,colors: {
	            'EXCELLENT': '#0072BE',
	            'GOOD': '#93BDC7',
	            'MIDDLE': '#A072CE',
	            'WEAK':'#bd0088'
	        }
	    }
	    /* ,color: {
            pattern: ['#578BC9','#A072CE','#85B400','#D08B22']
        } */
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
        	,colors: {
	            'EXCELLENT': '#0072BE',
	            'GOOD': '#93BDC7',
	            'MIDDLE': '#A072CE',
	            'WEAK':'#bd0088'
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
	
	var bar = c3.generate({
		bindto: '#bar_chart',
	    data: {
	    	columns: [
	            ['DOWN', barData[0].lteRComDnVal],
	            ['UP', barData[0].lteRComUpVal]
	        ],
	        type: 'bar'
	    },
	    color: {
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
	   },
	    bar: {
	        width: {
	            ratio: 0.3 // this makes bar width 50% of length between ticks
	        }
	        // or
	        //width: 100 // this makes bar width 100px
	    }
	});
	
	//원형 end

	//최초 사이즈 지정
	$("#pie_chart1").css('min-width','38vh');
	$("#pie_chart1").css('min-height','38vh');
	$("#pie_chart2").css('min-width','38vh');
	$("#pie_chart2").css('min-height','38vh');
	$("#pie_chart2").css('min-width','38vh');
	$("#bar_chart").css('min-width','50vh');
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
	}
}
