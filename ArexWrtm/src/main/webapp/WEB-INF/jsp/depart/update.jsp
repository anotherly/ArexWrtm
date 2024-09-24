<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>단말장치(LTE-R) 관리 WEB 시스템</title>
<meta charset="UTF-8">
<jsp:include page="/cmn/top.do" flush="false" />
	<script>
		$(document).ready(function() {
			console.log("고객 등록 화면");
			$('select').each(function(i,list){
				var selVal=$(this).val();
				//한글명
				var val1=$(this).parent().parent().children().eq(2).children().eq(0);
				//코드
				var val2=$(this).parent().parent().children().eq(2).children().eq(1);
				if(selVal!=0){
					var selId = $(this).attr("id");
					var selText = $("#"+selId+" option:selected").text();//선택한 텍스트
					val1.val(selText);
					val2.val(selVal);
					val1.prop("readonly",true);
					val2.prop("readonly",true);
				}else{
					val1.val("");
					val2.val("");
					val1.prop("readonly",false);
					val2.prop("readonly",false);
				}
			});
			
			//셀렉트박스 변경 시 
			$('select').on('change',function(){
				var selVal=$(this).val();
				//한글명
				var val1=$(this).parent().parent().children().eq(2).children().eq(0);
				//코드
				var val2=$(this).parent().parent().children().eq(2).children().eq(1);
				if(selVal!=0){
					var selId = $(this).attr("id");
					var selText = $("#"+selId+" option:selected").text();//선택한 텍스트
					val1.val(selText);
					val2.val(selVal);
					val1.prop("readonly",true);
					val2.prop("readonly",true);
				}else{
					val1.val("");
					val2.val("");
					val1.prop("readonly",false);
					val2.prop("readonly",false);
				}
			});		
			
			$("#btnSave").on('click',function(){
				console.log("정보 저장");
				var validChk = true;
				$(".input_base_require").each(function(i,list){
					console.log("필수값체크");
					if($(this).val()==null||$(this).val()==''){
						alert("필수 항목을 기재해 주세요");
						$(this).focus();
						validChk=false;
						return false;
					}
				});
				
				var arr1 = [ $("#cpyCode").val() ,$("#hqCode").val() ,$("#teamCode").val() ];
				var arr2 = [ $("#cpyName").val() ,$("#hqName").val() ,$("#teamName").val() ];
				$("#departCode").val(concatenate("-",arr1));
				$("#departName").val(concatenate(" ",arr2));
				
				//비교
				var newDeptCode =$("#departCode").val();
				var newDeptName =$("#departName").val();
				
				var oldDeptCode = $("#oldDeptCode").val();
				var oldDeptName = $("#oldDeptName").val();
				
				if (newDeptCode == oldDeptCode && newDeptName == oldDeptName){
					$("#updateChk").val("N");
				}else{
					$("#updateChk").val("Y");
				}
				
				if(validChk){
					let queryString = $("#acDetailFrm").serialize();
					ajaxMethod('/depart/update.ajax',queryString,'/depart/list.do','저장되었습니다');
				}
			}); 
			
			//y면 체크 아니면 비체크인데 비체크값을 n으로 변경
			$('input[type="checkbox"]').each(function(i,list){
				console.log("하단체크박스 : "+i+"	/	"+$(this).attr("id"));
				if($(this).is(':checked')){
					$(this).val('Y');
				}else{
					$(this).val('N');
				}
			});
			
			//input 하위 모든 체크박스 클릭 시
			$('input[type="checkbox"]').on('click',function(){
				console.log("하단체크박스클릭");
				if($(this).is(':checked')){
					$(this).val('Y');
				}else{
					$(this).val('N');
				}
			});
			
			$("#btnCancel").on('click',function(){
				location.href='/depart/list.do';
			});
		});
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
	<div id="container" class="container-wrap"  style="margin-top: 0px;">
		<!-- header Start ------------------>
		<div id="header" class="header-wrap">
		</div>
		<!-- header End ------------------>
		<!-- contents Start ------------------>
		<div id="contents" class="contents-wrap">
			<!-- work Start -->
			<div id="work" class="work-wrap">
				<!-- contents_box Start -->
				<div id="contents_box" class="contents_box">
					<!-- 컨텐츠 테이블 헤더 Start -->
					<div class="ctn_tbl_header">
						<div class="ttl_ctn">소속기관 정보 수정</div>
						<!-- 설명글 -->
					</div>
					<!-- 컨텐츠 테이블 헤더 End -->
					<!-- 컨텐츠 테이블 영역 Start -->
					<form id="acDetailFrm" name="acDetailFrm" method="post" enctype="multipart/form-data">
					
						<input type="hidden" id="departCode" name ="departCode" placeholder="" class="form-control" required>
						<input type="hidden" id="departName" name ="departName" placeholder="" class="form-control" required>
						
						<input type="hidden" id="oldDeptCode" name ="oldDeptCode" placeholder="" class="form-control" value="${data.departCode}" readonly>
						<input type="hidden" id="oldDeptName" name ="oldDeptName" placeholder="" class="form-control" value="${data.departName}" readonly>
						
						<input type="hidden" id="updateChk" name ="updateChk" placeholder="" class="form-control" readonly>
						
						<!-- 컨텐츠 테이블 영역 Start -->
						<div class="ctn_tbl_area">
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th"></div>
								<div class="ctn_tbl_th"></div><div class="ctn_tbl_th" style="flex: 1;">
									<div class="ctn_tbl_th" style="flex: 1;">기관정보 (한◦영/16자)</div>
									<div class="ctn_tbl_th" style="flex: 1;">코드정보 (영문/3자)</div>
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">기관명</div>
								<div class="ctn_tbl_td" style="flex: 0;">
									<select class="table_sel"  style="width: 164px; height:100%;" id="cpy_sel">
									    <c:forEach var="departVo" items="${departList1}">
									        <option value="${departVo.cpyCode}" <c:if test="${data.cpyCode eq departVo.cpyCode}">selected</c:if>>${departVo.cpyName}</option>
									    </c:forEach>
									    <option value="0">직접입력</option>
									</select>
								</div>
								<div class="ctn_tbl_td">
									<input type="text" id="cpyName" name ="cpyName" placeholder="" class="form-control input_base_require" required>
									<input type="text" id="cpyCode" name ="cpyCode" placeholder="" class="form-control" required>
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">본부/처/실</div>
								<div class="ctn_tbl_td" style="flex: 0;">
									<select class="table_sel"  style="width: 164px; height:100%;" id="hq_sel">
									    <c:forEach var="departVo" items="${departList2}">
									        <option value="${departVo.hqCode}"<c:if test="${data.hqCode eq departVo.hqCode}">selected</c:if>>${departVo.hqName}</option>
									    </c:forEach>
									    <option value="0">직접입력</option>
									</select>
								</div>
								<div class="ctn_tbl_td">
									<input type="text" id="hqName" name ="hqName" placeholder="" class="form-control" required>
									<input type="text" id="hqCode" name ="hqCode" placeholder="" class="form-control" required>
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">팀명</div>
								<div class="ctn_tbl_td" style="flex: 0;">
									<select class="table_sel"  style="width: 164px; height:100%;" id="team_sel">
									    <option value="0">직접입력</option>
									    <c:forEach var="departVo" items="${departList3}">
									        <option value="${departVo.teamCode}"<c:if test="${data.teamCode eq departVo.teamCode}">selected</c:if>>${departVo.teamName}</option>
									    </c:forEach>
									</select>
								</div>
								<div class="ctn_tbl_td">
									<input type="text" id="teamName" name ="teamName" placeholder="" class="form-control input_base_require" required>
									<input type="text" id="teamCode" name ="teamCode" placeholder="" class="form-control input_base_require" required>
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">사용여부</div>
								<div class="ctn_tbl_td">
									<select name ="usedYn">
										<option value='1' <c:if test="${data.usedYn eq 1}">selected</c:if>>사용</option>
										<option value='0' <c:if test="${data.usedYn eq 0}">selected</c:if>>미사용</option>
									</select>
								</div>
							</div>
						</div>
						<!-- btn_box Start -->
						<div class="btn_box">
							<div class="right">
								<input type="button" class="btn btn_primary" id="btnSave" alt="수정" value="수정" />
								<input type="button" class="btn" id="btnCancel" alt="취소" value="취소" />
							</div>
						</div>
						<!-- btn_box End -->
					</form>
				</div>
				<!-- contents_box End -->
			</div>
			<!-- work End -->
		</div>
		<!-- contents End ------------------>
	</div>
	<!-- container End ------------------>
</body>

</html>