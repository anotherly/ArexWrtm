var timeRefresh=null;
var pastSide="";//이전 사이드바 메뉴

/************************************************************************
함수명 : ajaxMethod
설 명 : ajax 처리 함수 
인 자 : url(컨트롤러에 매핑된 주소), data(전송 값), 
	   callback(ajax 통신 후 이동 주소), 
	   message(전송 성공 시 메시지)
사용법 : 화면에서 서버로 ajax통신으로 값 전송시 사용
작성일 : 2020-07-30
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.07.30   정다빈       최초작성
************************************************************************/
function ajaxMethod(url, data, callback, message,thDiv){
	var output = new Array();
	$.ajax({
		url : url,
		type : "post",
		dataType : "json",
		data : data,
		/*ajax는 비동기 식이지만 함수형태로 호출 시 success 이전에 
		*화면이 구성되어 값을 제대로 전송받지 못함
		*함수로 호출할 경우 false로 지정
		*/
		async : false,
		success : function(json) {
//			//console.log("아작스 서버 연동 성공");
//			//console.log(json);
//			//console.log(json.msg);
			if(json.msg=="" || typeof json.msg ==="undefined"){//서버 단에서 에러가 없을 경우
				if(message!="" && typeof message !== "undefined"){//에러는 아니지만 전달메시지가 있을경우
					alert(message);
				}
				//콜백 화면 이동 : div 값 변경일경우 사용
				//주소 자체 이동, body 전체 화면 변환일경우 수정 필요
				if(callback!="" && typeof callback!=="undefined"){
					if(callback=="goback"){
						history.go(-1);
					}else{
						location.href=callback;
					}
				}
			}else{
				//검색 결과가 없는 것이라면
				if(json.msg=="search_not"){
					json="";
				}else{
					alert(json.msg);
				}
			}
			output = json;
		},
		error : function() {
			//console.log("에러가 발생하였습니다.");
		},
		//finally 기능 수행
		complete : function() {

		}
	});
	return output;
}
/************************************************************************
함수명 : reloadOrKill
설 명 : ajax 처리 함수 
인 자 : url(컨트롤러에 매핑된 주소), data(전송 값), 
	   callback(ajax 통신 후 이동 주소), 
	   message(전송 성공 시 메시지)
사용법 : 화면에서 서버로 ajax통신으로 값 전송시 사용
작성일 : 2020-07-30
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.07.30   정다빈       최초작성
************************************************************************/
function reloadOrKill(isClose){
	//console.log("reloadOrKill 커먼 메소드 진입");
	ajaxMethod('/user/reloadOrKill.do',{"tagId":isClose});
}

/************************************************************************
함수명 : chkMenu
설 명 : 메뉴 관련 항목 페이지 이동 시 메뉴 선택 보여주기 
인 자 : 
사용법 : 
작성일 : 2020-12-30
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.12.30   정다빈       최초작성
************************************************************************/
function chkMenu(that){
	//console.log("대메뉴 항목 클릭 : "+$(that).attr('id'));
	
	//클릭한 항목 액티브
	//단일메뉴와 다중메뉴 분기
	if($(this).parent().parent().children('a').text().indexOf('혼잡도 범위 설정')!=-1){
		$(this).parent().parent().parent().parent().children('a').addClass('active');
	}else{
		$(this).parent().parent().children('a').addClass('active');
	}
}


/************************************************************************
함수명 : goMenuSite
설 명 : 메뉴 관련 항목 페이지 이동 
인 자 : 
사용법 : 
작성일 : 2020-07-30
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.07.30   정다빈       최초작성
************************************************************************/
function goMenuSite(goUrl,thDiv){
	$.ajax({
		url: goUrl,
		type: "POST",
		async : false,
		// ajax 통신 성공 시 로직 수행
		success: function(json){
			console.log("성공");
			if(json==""){
				alert("권한이 없습니다");
				return false;
			}else{
				//서버측으로 부터 받은 별도의 에러메시지가 없을 경우 화면 이동
				if(json.msg=="" || typeof json.msg ==="undefined"){
					console.log("url 이동");
					location.href=goUrl;
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

/************************************************************************
함수명 : chkBoxFunc
설 명 : 테이블 내 체크박스 관련 이벤트 처리
인 자 : 테이블(this)를 받음(that으로)
사용법 : 
작성일 : 2020-08-17
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.08.17   정다빈       최초작성
************************************************************************/
function chkBoxFunc(that){
	var chkId=$(that).attr("id");
	var chkVal=$(that).val();
	var temp =[];//지금 체크한 체크박스에 관한 배열
	console.log("tbody의 체크된 체크박스 수 : "+$('input:checkbox[name="chk"]:checked').length);
	//클릭한게 전체선택일 경우
	if(chkId=="chkAll"){
		//console.log("전체선택");
		//체크된 경우
		if ($("#chkAll").is(":checked")){
			//console.log("전체 체크박스 선택됨");
			//console.log("현재 페이지 넘버 : ");
			//하위 체크박스들도 모두 선택
			$("tbody input:checkbox").prop("checked", true);
			
		}else{//선택->취소 : 전체 체크 해지시
			//console.log("전체 체크박스 해지됨");
		    $("tbody input:checkbox").prop("checked", false);
		}
		 
	}else{//단일 선택일 경우
		console.log("단일체크박스 클릭"+$(that).val());
		console.log("@@@체크된 값들 길이: "+$('input:checkbox[name="chk"]:checked').length);
		console.log("@@@tbody 길이: "+$("tbody tr").length);
		//단일선택->전체
		if($('input:checkbox[name="chk"]:checked').length==$("input:checkbox[name='chk']").length){
    		$("#chkAll").prop("checked", true);
    	}else{//단일해지->전체해지
    		$("#chkAll").prop("checked", false);
    	}
		if($(that).is(":checked")){//체크박스가 체크된 경우
			//console.log("단일선택"+$(that).val());
		}else{//체크 해지된 경우
			//console.log("단일해지"+$(that).val());
			
		}
	}
	
}

/************************************************************************
함수명 : chkArrValiF
설 명 : 체크박스 배열내 선택 구별 함수
인 자 : 
사용법 : 
작성일 : 2020-08-19
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.08.19   정다빈       최초작성
************************************************************************/
function chkArrValiF(objArr,chkVal){
	for(var i =0;i<objArr.length;i++){
		if(chkVal==objArr[i]){
			return false;
		}
	}
	return true;
}

/************************************************************************
함수명 : schChkKey
설 명 : 검색 값 유효성 검사
인 자 : 
사용법 : 
작성일 : 2020-08-30
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.08.30   정다빈       최초작성
 ************************************************************************/
function schChkKey(that,schFlag){
	var sndUrl='';
	var dat;
	//키워드가 무엇인지 판별
	var schId= $(that).attr("name").split("_")[1];
	//텍스트에 값을 입력 안했다면
	if(schId!="empCode" && $(that).parent().children().first().val()==""){
		$(that).parent().parent().children().last().css("color","red");
		$(that).parent().parent().children().last().text("값을 입력해주세요");
	}else{
		//검색버튼이 2개이상일때는 어디로 보낼지 값이 무엇인지 분기처리
		if(schId=="USER_ID"){
			sndUrl="/user/findUserId.do";
			dat={"USER_ID":$(that).parent().children().first().val()};
			
			var schData=ajaxMethod(sndUrl, dat);
			//id일 경우는 값이 없을때 사용가능하고 시험코드는 값이 있을때 사용가능함
			if(schData == "" || typeof schData.data === "undefined"){//db에 값 미존재
				$(that).parent().parent().children().last().css("color","blue");
				$(that).parent().parent().children().last().text("사용 가능한 id입니다.");
				schFlag=true;
			}else{//db에 값 존재
				$(that).parent().parent().children().last().css("color","red");
				$(that).parent().parent().children().last().text("이미 사용중인 id입니다.");
				schFlag=false;
			}
		}
	}
	return schFlag;
}
/*파일 업로드 또는 다운로드 시*/
/**
 * 사진 등록 또는 변경 시
 */
function changePicture(obj){
	console.log("사진 등록 또는 변경 시");
	$(obj).parent().children().first().val((obj.val()).replace("C:\\fakepath\\", ""));
}

/*
 * n개의 변수를 받아서 구분문자로 통합할 때
 * str : 구분자 , arr : 통합문자들의 배열
 * 
 * */
function concatenate(str,arr){
	var conStr="";
	for (var i = 0; i < arr.length; i++) {
		conStr+=arr[i];
		if(i!=arr.length-1){
			conStr+=str;
		}
	}
	return conStr;
}
