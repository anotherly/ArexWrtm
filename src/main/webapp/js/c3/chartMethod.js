/************************************************************************
함수명 : realtimeChart
설 명 : 실시간 선형 차트
인 자 : divId(그려지는 div의 id값),w(가로길이),h(세로길이),tltip(툴팁 관련)
사용법 : 
작성일 : 2022-06-24
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2021.05.06   정다빈       최초작성
 ************************************************************************/
function realtimeChart(divId,w,h,tltip){
	var chart = c3.generate({
		bindto: '#'+divId,
        size: {
	        width: 1200,
	        height: 250
	    },
        padding: {
            left: 50,
            right: 50
        },
        point: {
            show: false
        },

        data: {
            type: "spline",
            x: 'x',
            //xFormat: '%Y%m%d', // 'xFormat' can be used as custom format of 'x'
            columns: [
                ['x', (new Date().getTime()), (new Date().getTime()) - 10000, (new Date().getTime()), (new Date().getTime()) - 20000, (new Date().getTime()), (new Date().getTime()) - 30000, (new Date().getTime()), (new Date().getTime()) - 40000, (new Date().getTime()), (new Date().getTime()) - 50000, (new Date().getTime()), (new Date().getTime()) - 60000],
                //['x', '20130101', '20130102', '20130103', '20130104', '20130105', '20130106'],

                ['전체 CPU 사용량', null, null, null, null, null, null, null],
                ['프로세스 CPU 사용량', null, null, null, null, null, null, null]
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
}



/************************************************************************
함수명 : ryBChart
설 명 : 량별 막대그래프 생성
인 자 : divId(그려지는 div의 id값),w(가로길이),h(세로길이),tltip(툴팁 관련)
사용법 : 
작성일 : 2022-06-24
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2021.05.06   정다빈       최초작성
 ************************************************************************/

function ryBChart(divId,w,h,tltip){
	console.log("막대그래프 차트 생성함수 진입");
	var rstC = c3.generate({
		bindto: '#'+divId,
		size: {
	        height: h,
	        width: w,
	    },
		data: {
			x:'x',
			columns: [
				titleArr,
				rlxArr,
				usArr,	
				careArr,	
				cwdArr	
	        ]
	    	,line: {
	            connectNull: false,
	        }
			,type: 'bar'
			,groups: [
				['여유','보통','주의','혼잡'],
			]
			,labels:{
				format: function (v, id, i, j) {
					if(v!=null){
						//console.log("값이 0이 아닐경우 : "+v,id,i,j,d);
						var format= d3.format(',');
		                return format(v);
					}else{
						//console.log("값이 0일경우 : "+v,id,i,j,d);
						return "";
					}
	            }
			}
			
			,colors: {
				'여유': '#50E94F',
				'보통': '#F5E001',
				'주의': '#F68F00',
				'혼잡': '#FE0000'
	        }
			,order: null
		}//data
		,legend: {//범주(여유,보통,주의,혼잡)
	        position: 'right'
	    }
		,bar: {
	        width: {
	            ratio: 0.3 // this makes bar width 50% of length between ticks
	        }
	        // or
	        //width: 100 // this makes bar width 100px
	    }
		,axis: {
			x: {
	            type: 'category'
	        }
			,y: {
	            //padding: {top: 200,left:50,right:50, bottom: 0},
				 tick: {
	                //format: d3.format("$,")
	                format: function (d) {
	                	if(((d/1000)%1)==0){
	                		return d/1000;			                		
	                	}
	                }
	         	}
	        }
	    }
		,tooltip: {
	        format: {
	        	title: function (d) {
	        		/* //console.log("d : "+d);
	        		//console.log("d.id : "+d.id); */
	        		if(typeof tltip !== "undefined"){
	        			return titleArr[d+1]+tltip; 
	        		}else{
	        			return titleArr[d+1];
	        		}
	        	},
	            value: function (value, ratio, id) {
	            	/* //console.log("value : "+value);
	            	//console.log("ratio : "+ratio);
	            	//console.log("id : "+id); */
	            	var format= d3.format(',');
	                return format(value);
	            }
	        }
	    }
		,onrendered: function () {
	        d3.select(this.config.bindto).selectAll(".c3-chart-text text").style("display", function (d) {
	            if (d && d.value === 0){
	            	return "none"	
	            }else{
	            	d.color
		            return d;
	            }
	        });
	    }
	});
	return rstC;
}
