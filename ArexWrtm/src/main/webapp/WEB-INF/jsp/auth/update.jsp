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
			console.log("수정");
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
				
				if(validChk){
					let queryString = $("#updateForm").serialize();
					ajaxMethod('/auth/update.ajax',queryString,'/auth/list.do','저장되었습니다');
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
				location.href='/auth/list.do';
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
						<div class="ttl_ctn">권한 수정</div>
						<!-- 설명글 -->
					</div>
					<!-- 컨텐츠 테이블 헤더 End -->
					<!-- 컨텐츠 테이블 영역 Start -->
					<form id="updateForm" name="updateForm" method="post" enctype="multipart/form-data">
						
						<!-- 컨텐츠 테이블 영역 Start -->
						<div class="ctn_tbl_area">
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">권한명</div>
								<div class="ctn_tbl_td">
									<input type="text" id="authDefine" name ="authDefine" placeholder="" value="${data.authDefine}" class="form-control input_base_require" required>
								</div>
								<div class="ctn_tbl_th ">권한 값</div>
								<div class="ctn_tbl_td">
									${data.authLevel}
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">관리 SW 제어 여부</div>
								<div class="ctn_tbl_td">
									<select name ="funcCtrl">
										<option value='1' <c:if test="${data.funcCtrl eq 1}">selected</c:if>>허용</option>
										<option value='0' <c:if test="${data.funcCtrl eq 0}">selected</c:if>>불가</option>
									</select>

								</div>
								<div class="ctn_tbl_th ">운영/사용률 화면 접근</div>
								<div class="ctn_tbl_td">
									<select name ="funcMotr">
										<option value='1' <c:if test="${data.funcMotr eq 1}">selected</c:if>>허용</option>
										<option value='0' <c:if test="${data.funcMotr eq 0}">selected</c:if>>불가</option>
									</select>
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">기관 관리 화면 접근</div>
								<div class="ctn_tbl_td">
									<select name ="funcDepart">
										<option value='1' <c:if test="${data.funcDepart eq 1}">selected</c:if>>허용</option>
										<option value='0' <c:if test="${data.funcDepart eq 0}">selected</c:if>>불가</option>
									</select>
								</div>
								<div class="ctn_tbl_th ">권한 관리 화면 접근</div>
								<div class="ctn_tbl_td">
									<select name ="funcAuth">
										<option value='1' <c:if test="${data.funcAuth eq 1}">selected</c:if>>허용</option>
										<option value='0' <c:if test="${data.funcAuth eq 0}">selected</c:if>>불가</option>
									</select>
								</div>
							</div>
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">사용자 관리 화면 접근</div>
								<div class="ctn_tbl_td">
									<select name ="funcUser">
										<option value='1' <c:if test="${data.funcUser eq 1}">selected</c:if>>허용</option>
										<option value='0' <c:if test="${data.funcUser eq 0}">selected</c:if>>불가</option>
									</select>
								</div>
								<div class="ctn_tbl_th ">단말기 관리 화면 접근</div>
								<div class="ctn_tbl_td">
									<select name ="funcLter">
										<option value='1' <c:if test="${data.funcLter eq 1}">selected</c:if>>허용</option>
										<option value='0' <c:if test="${data.funcLter eq 0}">selected</c:if>>불가</option>
									</select>
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