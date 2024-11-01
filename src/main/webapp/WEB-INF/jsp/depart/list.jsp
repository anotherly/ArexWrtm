<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>단말장치(LTE-R) 관리 WEB 시스템</title>
	<meta charset="UTF-8">
    <jsp:include page="../cmn/top.jsp" flush="false" />
<script>
	var updUrl="/depart/update.do";
	var delUrl="/depart/delete.ajax";
	var delbak="/depart/list.do";
	
	//데이터 테이블 관련
	var iidx;//날짜컬럼 인덱스
	var selectlang;
	$(document).ready(function(){
		//테이블 기본설정 세팅
		dtTbSetting();
		iidx = 3;
		//console.log("사용자 목록 화면 진입");
		var colCnt=0;
		var idxTb =0;
		
		var tb2=$("#tableList").DataTable({
			ajax : {
                "url":"/depart/list.ajax",
                "type":"POST",
                "dataType": "json",
            },  
            columns: [
            	{
            		data:   "departCode",
	            	"render": function (data, type, row, meta) {
	            		////console.log(meta.row+"	/	"+meta.col);
                        return '<input type="checkbox" id="chk" name="chk" value="'+data+'">';
	                },
            	
            		/* ,className: "select-checkbox" */
                },
                {data:"departCode"},
                {data:"cpyName"},
                /* {data:"cpyCode"}, */
                {data:"hqName"},
                /* {data:"hqCode"}, */
                {data:"teamName"},
                /* {data:"teamCode"}, */
                {data:"usedYn"},
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
		$("#tableList").on("mouseenter", "tbody tr", function(){
			$(this).addClass('active');
		});
		//마우스 내릴시
		$("#tableList").on("mouseleave", "tbody tr", function(){
			$(this).removeClass('active');
		});
		
		//체크박스영역 제외 마우스 올릴시 포인터로
		$("#tableList").on("mouseleave", "tbody td:not(':first-child')", function(){
			$(this).css('cursor','pointer');
		});
		
		//페이지 이동이나 열 개수 변경시 전체체크박스 관련 이벤트
		$('#tableList').on('draw.dt', function(){
			////console.log("데이터테이블 값 변경");
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
			location.href="/depart/insert.do";
		});
		
		//상세 화면 조회
		/* $("#tableList").on("click", "tbody td:not(':first-child')", function(){
			//console.log("목록에서 상세요소 클릭");
			var tagId = $(this).parent().children().first().children().first().val();
			$(this).attr('id');
			if(tagId!="chkTd"){
				$("#contents").load("/depart/detail.do",{"departCode":tagId}); 
			}
		}); */

		//데이트타임피커
		 var toDate = new Date();
		 $('#datetimepicker1').datetimepicker({
			 format:"YYYY-MM-DD" ,
			 defaultDate:moment().subtract(1, 'months'),
			 maxDate : moment()
		})
		/* .on('dp.change', function (e) {
			calculDate();
			tb.draw();
		}); */
		 $('#datetimepicker2').datetimepicker({
			 format:"YYYY-MM-DD",
			 defaultDate:moment()
			 ,maxDate : moment()
		})
		
	});
	
	/* 검색 */
	 function search(){
		 //console.log("검색");
		 let frm = $("#searchFrm").serialize();
		 var tagUrl="/depart/list.ajax";
		 tbSearch("tableList",tagUrl,frm);
	 }
    
</script>
</head>
<body class="open">
    <!-- lnb Start ------------------>
    <aside id="lnb" class="lnb">
        <a class="lnb-control" title="메뉴 펼침/닫침"><span class="menu-toggle">메뉴 펼침/닫침</span></a>
        <nav class="navbar navbar-expand-sm navbar-default">
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
					<div class="ttl_ctn">기관 목록</div>
				</div>
                <!-- search_box Start -->
                <div class="search_box">
                	<form id=searchFrm name="searchFrm" class="search_form" method="post" enctype="multipart/form-data">
                		<div style=" width: 900px;display: flex;flex-wrap: nowrap;align-items: center;flex-direction: row;">
	                        <div class="form-group" style="margin-right: 50px;">
	                            <label for="sch_text_01" class="form-control-label">통합코드</label>
	                            <input type="text" id="schAllCode" name="schAllCode" placeholder="" class="form-control">
	                        </div>
	                        <div class="form-group" style="margin-right: 50px;">
	                            <label for="sch_text_02" class="form-control-label">기관명</label>
	                            <input type="text" id="schCpy" name="schCpy" placeholder="" class="form-control">
	                        </div>
	                        <div class="form-group" style="margin-right: 50px;">
	                            <label for="sch_text_02" class="form-control-label">본부/처/실</label>
	                            <input type="text" id="schHq" name="schHq" placeholder="" class="form-control">
	                        </div>
	                        <div class="form-group" style="margin-right: 50px;">
	                            <label for="sch_text_02" class="form-control-label">팀</label>
	                            <input type="text" id="schTeam" name="schTeam" placeholder="" class="form-control">
	                        </div>
                		</div>
                    </form>
                    <div class="search_btn">
                        <button class="btn btn_sch btn_primary" onclick="search()"><i class="ico_sch"></i>조회</button>
                        <!-- <button class="btn btn_reset"><i class="ico_reset"></i>초기화</button> -->
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
										<th>통합코드</th>
										<th>기관명 이름</th>
										<!-- <th>기관(회사) 코드</th> -->
										<th>본부/처/실 이름</th>
										<!-- <th>본부/처/실 코드</th> -->
										<th>팀 이름</th>
										<!-- <th>팀 코드</th> -->
										<th>사용중 여부</th>
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