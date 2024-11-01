var startNum=0;
var endNum=1;
var tbM;//tr 내부에 들어가는 td들
var tbCnt=0;//전체 들어가는 데이터 수 (42개 이하인데 변경예정)
var trCnt=1;//행 카운트

/////////////////////////////

var mok=0;//전체 데이터를 td 수로(7) 나눴을때 몫 (이건 최대 페이지 개수를 구할때 쓰임)
var namage=0;//나머지(마지막 행에서 빈칸 채울때 씀)
var maxPage=0;// 최대페이지 수 : 몫/6(한화면 행수)
var plusPage=0;// 몫/6의 나머지 -> 남은 행수를 계산해서 채울때 씀

/////////////////////////////

//차트화면 우측 단말기테이블
function trainOne(alData){
	//console.log("차트화면 우측 단말기테이블");
	$("#lteTbd").empty();
	//전역변수 초기화
	tbM="";
	trCnt=1;
	tbCnt=0;
	
	startNum=0;
	endNum=1;
	
	mok=Math.floor(alData.length/7,0);
	namage=alData.length%7;
	maxPage=Math.floor(mok/6,0);
	
	var oldName="";//팀이 변경되기 전 이전 팀
	var enterLine=1;//현재 td number
	var blankCnt=0;//페이지 안깨지기 위한 공백 카운트
	
	for (var i = 0; i < alData.length; i++) {
		var backName="";
		if(i==0){
			oldName=alData[i].teamName;
		}
		/*공백처리안함*/
		//배경 선택
		backName=selectBackgra(alData[i].teamName,alData[i].obsCount);
		
		
		/* 2024-10-08 : 단말기 구분을 위해 단말기 사용용도 + ip D 클래스로 처리 */
		// ip에서 D클래스 슬라이스
		var dClass = alData[i].lteRIp.substr(9);
		
		// 이후 사용용도 + D 클래스 붙이기
		var spanVal = alData[i].lteRUsed + "_" + dClass;
		
		//td 내부 만듦
		tbM+= tdCreate(alData[i].lteRIp,backName,spanVal); 
		tbCnt++;
		//7열 초과시 줄바꿈
		if(parseInt((enterLine)%7)==0){
			var tbCont = "<tr id='tr"+trCnt+"'>"+tbM+"</tr>";
			$("#lteTbd").append(tbCont);	
			tbM="";
			trCnt++;
		}else{
			//데이터의 마지막
			if(i==alData.length-1){
				//행을 생성
				trCreate();
			}
		}	
		enterLine++;
		
	}
	//테이블 생성 완료후 해야할 것들
	plusPage=6-($("#lteTbd tr").length%6);
	
	/*hideTr(startNum*6,endNum*6);*/

}

//tr을 생성하고 행번호를 생성
function trCreate(){
	var trValue = "<tr id='tr"+trCnt+"'>"+tbM+"</tr>";
	$("#lteTbd").append(trValue);	
	tbM="";
	trCnt++;
}

//중간에 들어가는 td값을 채움
//tdid,tdCont,spanVal
function tdCreate(tdid,backName,spanVal){
	var funcTbCont="";
	
	/* 2024-10-08 : _IP 붙일 시 텍스트 위치 깨짐 문제 해결 방안 -> 첫번 째 언더바에서 개행하기 */
	
	// findUnder : sapnVal에서 첫번째 언더바가 있는 자리의 인덱스를 찾음
	var findUnder = spanVal.indexOf('_');
	
	// useFront : spanVal의 처음부터 첫 번째 언더바 바로 앞까지 문자열 자름
	var useFront = spanVal.substr(0,findUnder);
	
	// useBack : spanVal의 첫 번째 언더바를 포함하여 맨 뒤까지 문자열 자름
	var useBack = spanVal.substr(findUnder);
	
	// 확인용 로그
	/*//console.log('언더바 앞 :' + useFront);
	//console.log('언더바 뒤:' + useBack);*/
	
	//갱신전에 선택된 단말기였다면 선택배경 유지되도록
	if(chkTerId==tdid){
		funcTbCont=
			"<td id='"+tdid+"' class='selected'>"
			+"<div class='td-div'>"
				+"<div class='img-container'>"
					+"<div style=" 
						+"'background:url(../images/arex/"+backName+".png) no-repeat;" 
						+"background-size: contain;'>" 
					+"</div>"
				+"</div>"
				+"<span>"+spanVal+"</span>"
			+"</div>"
		+"</td>";
	}else{
		funcTbCont=
			"<td id='"+tdid+"'>"
			+"<div class='td-div'>"
				+"<div class='img-container'>"
					+"<div style=" 
						+"'background:url(../images/arex/"+backName+".png) no-repeat;" 
						+"background-size: contain;'>" 
					+"</div>"
				+"</div>"
				// 첫번 째 언더바 바로 다음에 br 태그로 개행 처리
				+"<span>"+useFront+"<br>"+useBack+"</span>"
			+"</div>"
		+"</td>";
	}
	return funcTbCont;
}

//배경 선택
function selectBackgra(teamName,obsCount){
	var backgra='';
	//배경 선택가능
	if(teamName.indexOf('전기') != -1){
		backgra="electric";
	}else if(teamName.indexOf('시설') != -1){
		backgra="facility";
	}else if(teamName.indexOf('신호') != -1){
		backgra="signal";
	}else{
		backgra="lte_non";
	}
	
	//상태표기
	if(obsCount=='reset'){
		backgra=backgra+"_r";
	}else if(obsCount=='update'){
		backgra=backgra+"_u";
	}else if (obsCount=='f') {//데이터 없음
		backgra=backgra+"_f";
	}else if (obsCount=='obs') {//장애
		backgra=backgra+"_obs";
	}else if (obsCount=='weak'){
		backgra=backgra+"_weak"
	}else{
		backgra=backgra;
	}
	return backgra;
}

//페이징 처리를 위한 행 감춤
function hideTr(startPage,endPage){
		$("#lteTbd tr").each(function(i,list){
			
			if(i >= startPage && i < endPage){
				$(list).show();
			}else{
				$(list).hide();
			}
		});
}


