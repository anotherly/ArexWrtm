var lang_kor;
var lang_eng;
/************************************************************************
함수명 : tbSearch
설 명 : 검색결과에 따른 테이블 갱신
인 자 : tbId(테이블 id),url(이동할 주소), frm(검색 폼 데이터)
사용법 :  
작성일 : 2023-08-24
작성자 : 기술연구소 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2023.08.24   정다빈       최초작성
************************************************************************/
function tbSearch(tbId,tagUrl,frm){
	 $.ajax({
			url : tagUrl,
			type : "post",
			dataType : "json",
			data : frm,
			async : false,
			success : function(json) {
				console.log("데이터테이블 검색 성공");
				
				if(json.data.length != 0){
					//성공시 테이블 갱신하고 신규 데이터 생성
					$("#"+tbId).dataTable().fnClearTable();//갱신
					$('#'+tbId).dataTable().fnAddData(json.data);//데이터 적용
				}else{
					$("#"+tbId).dataTable().fnClearTable();//갱신
				}
			},
			error : function(e) {
				alert("검색 시 오류가 발생했습니다 : "+e);
			},
			complete : function() {

			}
	 });
}


/************************************************************************
함수명 : tbUpdate
설 명 : 수정 기능 함수(동적 버튼)
인 자 : that(테이블 this), paramUrl(이동할 주소)
사용법 : 
작성일 : 2020-08-24
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.08.24   정다빈       최초작성
************************************************************************/
function tbUpdate(that,paramUrl){
	var tagId;
	var cnt = $('input:checkbox[name="chk"]:checked').length;
	if($('input:checkbox[name="chk"]:checked').length==0){
		alert("수정할 항목을 선택해 주세요");
	}else if($('input:checkbox[name="chk"]:checked').length!=1){
		alert("수정할 항목을 하나만 선택해 주세요");
	}else{
		for(i=0;i<$("tbody tr").length;i++){
			if($("#chk"+i).is(":checked")){
				tagId = $("#chk"+i).val();
			}
		}
		if(paramUrl.indexOf("user")!=-1){
			$("#content").load(paramUrl,{"USER_ID":tagId});
		}
		if(paramUrl.indexOf("company")!=-1){
			$("#content").load(paramUrl,{"COMPANY_ID":tagId});
		}
		
		 
	}
}


/************************************************************************
함수명 : tbDelete
설 명 : 삭제 기능 함수(동적 버튼)
인 자 : that(테이블 this), paramUrl(이동할 주소)
사용법 : 
작성일 : 2020-08-24
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.08.24   정다빈       최초작성
************************************************************************/
function tbDelete(that,paramUrl,callback,tb){
	console.log('dddd');
	var idArr=[]; 

	for(i=0;i<$("tbody tr").length;i++){
		if($("#chk"+i).is(":checked")){
			idArr.push($("#chk"+i).val());//배열에 아이디 값 삽입
		}
	}
	if(typeof idArr.length==="undefined" || idArr.length==0){
		alert("삭제할 항목을 선택해 주세요");
	}else{
		if(confirm("선택하신 항목을 삭제하시겠습니까?")==true){
			var url=paramUrl;
			var data = {"idArr":idArr};
			ajaxMethod(url, data, callback);
			chkArr=[];
		}
	}
}


/************************************************************************
함수명 : calculDate
설 명 : 삭제 기능 함수(동적 버튼)
인 자 : idx(날짜가 존재하는 칼럼)
사용법 : 
작성일 : 2020-08-24
작성자 : 솔루션사업팀 정다빈
수정일        수정자       수정내용
----------- ------ -------------------
2020.08.26   정다빈       최초작성
************************************************************************/
function calculDate(){
	console.log("계산함수 들어옴 idx : "+iidx);
	$.fn.dataTable.ext.search.push(
        function(settings, data, dataIndex){
		////console.log("계산함수 들어옴 idx4 : "+iidx);
            var min = Date.parse($('#fromDate').val()+" 00:00:00");
            var max = Date.parse(moment($('#toDate').val()).add(1,'days').format('YYYY-MM-DD'))
            var targetDate = Date.parse(data[iidx]);

            if( (isNaN(min) && isNaN(max) ) || 
                (isNaN(min) && targetDate <= max )|| 
                ( min <= targetDate && isNaN(max) ) ||
                ( targetDate >= min && targetDate <= max) ){ 
                    return true;
            }
            return false;
        }
    )
}

function dtTbSetting(){
/*    var col_eng = [
        { title: "Name" },
        { title: "Position" },
        { title: "Office" },
        { title: "Extn." },
        { title: "Start date" },
        { title: "Salary" }
    ];
 
    var col_kor = [
        { title: "이름" },
        { title: "직위" },
        { title: "오피스" },
        { title: "내선" },
        { title: "입사일" },
        { title: "연봉" }
    ];*/
 
    // DataTables Default
    lang_eng = {
        "decimal" : "",
        "emptyTable" : "No data available in table",
        "info" : "Showing _START_ to _END_ of _TOTAL_ entries",
        "infoEmpty" : "Showing 0 to 0 of 0 entries",
        "infoFiltered" : "(filtered from _MAX_ total entries)",
        "infoPostFix" : "",
        "thousands" : ",",
        "lengthMenu" : "Show _MENU_ entries",
        "loadingRecords" : "Loading...",
        "processing" : "Processing...",
        "search" : "Search : ",
        "zeroRecords" : "No matching records found",
        "paginate" : {
        	 "first" : "<<",
             "last" : ">>",
             "next" : ">",
             "previous" : "<"
        },
        "aria" : {
            "sortAscending" : " :  activate to sort column ascending",
            "sortDescending" : " :  activate to sort column descending"
        }
    };
 
    // Korean
    lang_kor = {
        "decimal" : "",
        "emptyTable" : "데이터가 없습니다.",
        "info" : "_START_ - _END_ (총 _TOTAL_ 행)",
        "infoEmpty" : "0행",
        "infoFiltered" : "(전체 _MAX_ 행 중 검색결과)",
        "infoPostFix" : "",
        "thousands" : ",",
        "lengthMenu" : "_MENU_ 개씩 보기",
        "loadingRecords" : "로딩중...",
        "processing" : "처리중...",
        "search" : "검색 : ",
        "zeroRecords" : "검색된 데이터가 없습니다.",
        "paginate" : {
            "first" : "<<",
            "last" : ">>",
            "next" : ">",
            "previous" : "<"
        },
        "aria" : {
            "sortAscending" : " :  오름차순 정렬",
            "sortDescending" : " :  내림차순 정렬"
        }
    };
}


