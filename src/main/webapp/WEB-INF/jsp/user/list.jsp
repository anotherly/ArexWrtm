<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>단말장치(LTE-R) 관리 WEB 시스템</title>
	<meta charset="UTF-8">
    <jsp:include page="../cmn/top.jsp" flush="false" />
    <style>
    	/* 드래그 방지 막기 */
    	body {
		  -webkit-user-select:none;
		  -moz-user-select:none;
		  -ms-user-select:none;
		  user-select:none
		}
		
		/*24-10-25 : 테이블에서 간혹 커서가 바뀌는 문제 => 테이블의 td 영역에 커서를 포인터로 맞춤(important 처리)*/
		table td{
			cursor : pointer !important;
		}
    </style>
<script>
	var updUrl="/user/update.do";
	var delUrl="/user/delete.ajax";
	var delbak="/user/list.do";
	
	//데이터 테이블 관련
	var iidx;//날짜컬럼 인덱스
	var selectlang;
	$(document).ready(function(){
		//테이블 기본설정 세팅
		dtTbSetting();
		iidx = 3;
		console.log("사용자 목록 화면 진입");
		var colCnt=0;
		var idxTb =0;
		
		$("#datetimepicker1").find("input").prop('disabled', true);
		$("#datetimepicker2").find("input").prop('disabled', true);
		
		var tb2=$("#tableList").DataTable({
			ajax : {
                "url":"/user/list.ajax",
                "type":"POST",
                "dataType": "json",
            },  
            columns: [
            	{
            		data:   "userId",
	            	"render": function (data, type, row, meta) {
	            		//console.log(meta.row+"	/	"+meta.col);
                        return '<input type="checkbox" id="chk" name="chk" value="'+data+'">';
	                },
                },
                {data:"userId"},
                {data:"userName"},
                {data:"cpyName",
                	"render": function (data, type, row, meta) {
                        return data ? data : '해당 없음'; 
                    },
                },
                {data:"hqName",
                	"render": function (data, type, row, meta) {
                        return data ? data : '해당 없음'; 
                    },	
               	},
                {data:"teamName",
               		"render": function (data, type, row, meta) {
                        return data ? data : '해당 없음';
                    },	
                },
                {data:"regYdt"}
            ],
            "lengthMenu": [ [5, 10, 20], [5, 10, 20] ],
            "pageLength": 10,
            pagingType : "full_numbers",
            columnDefs: [ 
            	{ orderable: false, targets: [0] }//특정 열(인덱스번호)에 대한 정렬 비활성화
            	,{className: "dt-center",targets: "_all"} 
            ],
            select: {
                style:    'multi',
                selector: 'td:first-child'
            },
            //order: [[ 9, 'desc' ]]
            responsive: true
           ,language : lang_kor // //or lang_eng
		});
		$("#tableList_filter").attr("hidden", "hidden");
		
		//체크박스 클릭 시 이벤트
		$("#tableList").on("click", 'input:checkbox', function() {
			chkBoxFunc(this);
		});
		//마우스 올릴시 
		$("#tableList").on("mouseenter", "tbody tr", function() {
			$(this).addClass('active');
		});
		//마우스 내릴시
		$("#tableList").on("mouseleave", "tbody tr", function() {
			$(this).removeClass('active');
		});
		
		//체크박스영역 제외 마우스 올릴시 포인터로
		$("#tableList").on("mouseleave", "tbody td:not(':first-child')", function(){
			$(this).css('cursor','pointer');
		});
		
		//페이지 이동이나 열 개수 변경시 전체체크박스 관련 이벤트
		$('#tableList').on('draw.dt', function(){
			//console.log("데이터테이블 값 변경");
			//인덱스 번호 재설정
			$('#tableList input:checkbox[name="chk"]').each(function(i,list) {
				$(this).attr("id","chk"+i)
			});
			
			//행개수에 따라 수정삭제버튼 생성여부
			//행 개수 0개일때
			if($('input:checkbox[name="chk"]').length !=0 && typeof $('input:checkbox[name="chk"]').length !== "undefined"){
				/* if(typeof $("#btnUpdate").val()==="undefined"){
					$("#btnIns").append("<input type='button' id='btnUpdate' class='btn btn_primary' value='수정' onclick='tbUpdate(this,updUrl)'>");
				} */
				if(typeof $("#btnDelete").val()==="undefined"){
					$("#btnIns").append("<input type='button' id='btnDelete' class='btn btn_primary' value='삭제' onclick='tbDelete(this,delUrl,delbak)'>");
				}
			}else{
				//$("#btnIns").empty();	
				if(typeof $("#btnUpdate").val()==="undefined"){
					$("#btnUpdate").remove();
				}
				if(typeof $("#btnDelete").val()==="undefined"){
					$("#btnDelete").remove();
				}
			}
			
			if($('input:checkbox[name="chk"]:checked').length==$("tbody tr").length){
	    		$("#chkAll").prop("checked", true);
	    	}else{
	    		$("#chkAll").prop("checked", false);
	    	}
		});
		
		//등록 화면 조회
		$("#btnInsert").click(function() {
			location.href="/user/insert.do";
		});
		
		//상세 화면 조회
		$("#tableList").on("click", "tbody td:not(':first-child')", function(){
			console.log("목록에서 상세요소 클릭");
			var tagId = $(this).parent().children().first().children().first().val();
			$(this).attr('id');
			if(tagId!="chkTd"){
				/* $("#contents").load("/user/detail.do",{"userId":tagId});  */
				
				//24-10-17 : 뒤로가기 문제 해결
				location.href='/user/detail.do?userId=' + tagId;
			}
		});

		//데이트타임피커
		 var toDate = new Date();
		 $('#datetimepicker1').datetimepicker({
			 format:"YYYY-MM-DD" ,
			 defaultDate:moment().subtract(1, 'months'),
			 maxDate : moment()
		});
		/* .on('dp.change', function (e) {
			calculDate();
			tb.draw();
		}); */
		 $('#datetimepicker2').datetimepicker({
			 format:"YYYY-MM-DD",
			 defaultDate:moment()
			 ,maxDate : moment()
		});
		
		$("#dateChk").on("click",function(){
			console.log("날짜선택여부");
			if($(this).is(':checked')){
				$("#datetimepicker1").find("input").prop('disabled', false);
				$("#datetimepicker2").find("input").prop('disabled', false);
			}else{
				$("#datetimepicker1").find("input").prop('disabled', true);
				$("#datetimepicker2").find("input").prop('disabled', true);
			}
		});
		
	});
	
	/* 검색 */
	 function search(){
		
		/* 24-10-22 : 등록일 선택 시, 좌측 달력보다 우측 달력이 과거인 경우 검색 못하도록 처리하기 */
		console.log("검색");
	
		if($('#dateChk').is(':checked')) { // 등록일 선택한 경우 -> 검사 후 데이터 보내기 
			var startDate = $('#sDate').val();
			var endDate = $('#eDate').val();
			
			var dateChecker = dateCheck(startDate,endDate);
		
			if(!dateChecker) { // 선택한 등록일이 올바르지 않은 경우
				alert('종료일이 시작일 보다 과거일 수 없습니다.');
				$('#eDate').focus();
				return false;
			} else { // 선택한 등록일이 올바른 경우
				let frm = $("#searchFrm").serialize();
				var tagUrl="/user/list.ajax";
				tbSearch("tableList",tagUrl,frm);
			}
		} else { // 등록일 선택이 아닌 경우
			let frm = $("#searchFrm").serialize();
			var tagUrl="/user/list.ajax";
			tbSearch("tableList",tagUrl,frm);
		}
	 }
	
	function dateCheck(startDate,endDate) {
		const ldate = new Date(startDate);
		const rdate = new Date(endDate);
		
		if(ldate.getTime() < rdate.getTime()){ // 정상적인 날짜인 경우 (좌측 달력이 우측 달력보다 과거인 경우)
			return true;
		} else { // 비정상적인 날짜인 경우 (좌측 달력이 우측 달력보다 미래인 경우)
			return false;
		}
	}
    
</script>
</head>
<body class="open">
    <!-- lnb Start ------------------>
    <aside id="lnb" class="lnb">
        <a class="lnb-control" title="메뉴 펼침/닫침">
        <span class="menu-toggle">메뉴 펼침/닫침</span></a>
        <nav class="navbar" id="menuNav">
           <ul class="menu-inner"></ul>
        </nav>
    </aside>
    <!-- lnb End ------------------>
    <!-- container Start ------------------>
    <div id="container" class="container-wrap" style="margin-top: 0px;">
		<!-- header Start ------------------>
		<div id="header" class="header-wrap">
		</div>
		<!-- header End ------------------>
		<!-- contents Start ------------------>
		<div id="contents" class="contents-wrap">
			<!-- work Start -->
			<div id="work" class="work-wrap list_page">
				<div class="ctn_tbl_header">
					<div class="ttl_ctn">사용자 목록</div>
				</div>
                <!-- search_box Start -->
                <div class="search_box">
                	<form id=searchFrm name="searchFrm" class="search_form" method="post" enctype="multipart/form-data">
                        <div class="form-group" >
                            <label for="sch_text_01" class="form-control-label">사용자 ID</label>
                            <input type="text" id="schUserId" name="schUserId" placeholder="" class="form-control">
                        </div>
                        <div class="form-group" >
                            <label for="sch_text_01" class="form-control-label">사용자명</label>
                            <input type="text" id="schUserName" name="schUserName" placeholder="" class="form-control">
                        </div>
                        <div class="form-group" >
                            <label for="sch_text_01" class="form-control-label">기관명</label>
                            <select class="table_sel"  style="width: 164px; height:100%;"  name="schUserCpy">
                            	<option value="">전체</option>
							    <c:forEach var="departVo" items="${departList1}">
							        <option value="${departVo.cpyCode}">${departVo.cpyName}</option>
							    </c:forEach>
							</select>
                        </div>
                        <div class="form-group" >
                            <label for="sch_text_01" class="form-control-label">본부/처/실</label>
                          	<select class="table_sel"  style="width: 164px; height:100%;"  name="schUserHq">
                          		<option value="">전체</option>
							    <c:forEach var="departVo" items="${departList2}">
							        <option value="${departVo.hqCode}">${departVo.hqName}</option>
							    </c:forEach>
							</select>
                        </div>
                        <div class="form-group" >
                            <label for="sch_text_01" class="form-control-label">팀명</label>
                            <select class="table_sel"  style="width: 164px; height:100%;" name="schUserTeam">
                            	<option value="">전체</option>
							    <c:forEach var="departVo" items="${departList3}">
							        <option value="${departVo.teamCode}">${departVo.teamName}</option>
							    </c:forEach>
							</select>
                        </div>
                        <div class="form-group" >
                        
                            <label for="sch_text_01" class="form-control-label">
                            	<input type="checkbox" id="dateChk" name="dateChk" value="">
                            	등록일
                            </label>
                            	<div class='input-group date' id='datetimepicker1'>
									<input type='text' class="form-cont" name="sDate" id="sDate" required/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
								 ~ 
								<div class='input-group date' id='datetimepicker2'>
									<input type="text" class="form-cont" id="eDate" name="eDate"  required/>
									<span class="input-group-addon">
										<span class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
                        </div>
                    </form>
                    <div class="search_btn" style="position: absolute;right: 98px;top: 19px;">
                        <button class="btn btn_sch btn_primary" onclick="search()"><i class="ico_sch"></i>조회</button>
                    </div>
                </div>
                <!-- search_box End -->

	            <!-- grid_box Start -->
				<div class="datatable-list-01">
					<div class="page-description">
						<div class="rows">
							<table id="tableList" class="table table-bordered" style="width: 100%;">
								<thead>
									<tr>
										<th><input type="checkbox" id="chkAll" class="chk"></th>
										<th>ID</th>
										<th>이름</th>
										<th>기관명</th>
										<th>본부/처/실</th>
										<th>팀명</th>
										<th>등록일</th>
									</tr>
								</thead>
							</table>
						</div>
					</div>
					
					<div id ="btnDiv" class="btn_box" style="display: flex;flex-direction: row-reverse;float:right;">
						<div id="btnIns" style="display: flex;justify-content: space-around;width: 230px;">
							<input type='button' class="btn btn_primary" id='btnInsert' value='등록'>
						</div>
					</div>
				</div>
	               <!-- grid_box End -->
			</div>	
			<!-- work End -->
		</div>
		<!-- contents End ------------------>
	</div>
	<!-- container End ------------------>
</body>
</html>