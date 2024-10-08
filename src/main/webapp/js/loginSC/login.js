/*
1 * 최초 index 페이지 진입 시
 * 세션 및 로그인 여부에 따라 로그인화면으로 전송할지
 * 메인화면으로 전송할 지 판별 
 * */

var logBfurl="";

function stMainIdx(sessionVo,url){
	console.log("세션 체크");
	window.onunload = function() {
	    console.log('unload');
	    //reloadOrKill(false);
	}
	if(sessionVo==''){
		//console.log("로그인 세션X");//로그인 안되있음
		console.log("로그인 페이지로 이동");
		location.href="/login/login.do";
	}else{
		location.href="/chart/main.do";
	}
	//탭이나 창 닫기시 로그아웃 처리
	$(window).bind("beforeunload", function (e){
		console.log("언로드됨 : "+rkFlag);
	});
}

/*
 * 로그인 처리 및 불완전 접속 종료 시 
 * 기존 세션을 끊고 신규 세션 생성
 * */
function inputLogin(inputVal,loginurl){
	console.log("입력값에 따른 로그인 처리");
	$.ajax({
		url: loginurl,
		type: "POST",
		dataType: "json",
		data: inputVal,
		// ajax 통신 성공 시 로직 수행
		success: function(json){
			//서버측으로 부터 받은 별도의 에러메시지가 없을 경우 로그인 처리
			if(json.msg=="" || typeof json.msg ==="undefined"){
				location.href=json.url;
				//location.href=logBfurl;
			}else{
				if(json.msg=="중복로그인"){
					ajaxMethod("/login/loginPost.do?relgn=1",inputVal);
					location.href=json.url;
					//location.href=logBfurl;
				}else{
					alert(json.msg);
				}
			}
		},
		error : function(json) {
			console.log("에러가 발생하였습니다."+json.msg);
		},
		//finally 기능 수행
		complete : function(json) {
			console.log("파이널리.");
		}
	});
}
