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
				
				if(validChk){
					var dept = $("#cpyCode option:selected").val()+"-"+$("#hqCode option:selected").val()+"-"+$("#teamCode option:selected").val();
					//240112 수정 - 메인 관리자 권한일땐 공란으로
					if($("select[name='userAuth']").val()==0){
						$("#departCode").val("");
					}else{
						$("#departCode").val(dept);
					}
					
					let queryString = $("#insertForm").serialize();
					ajaxMethod('/user/insert.ajax',queryString,'/user/list.do','저장되었습니다');
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
				location.href='/user/list.do';
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
						<div class="ttl_ctn">사용자 등록</div>
						<!-- 설명글 -->
					</div>
					<!-- 컨텐츠 테이블 헤더 End -->
					<!-- 컨텐츠 테이블 영역 Start -->
					<form id="insertForm" name="insertForm" method="post" enctype="multipart/form-data">
						<!-- 컨텐츠 테이블 영역 Start -->
						<div class="ctn_tbl_area">
							<div class="ctn_tbl_row">
								<input type="hidden" id="departCode" name ="departCode" class="form-control">
								<div class="ctn_tbl_th fm_rep">사용자 ID</div>
								<div class="ctn_tbl_td">
									<input type="text" id="userId" name ="userId" placeholder="" class="form-control input_base_require">
								</div>
								<div class="ctn_tbl_th fm_rep">사용자 이름</div>
								<div class="ctn_tbl_td">
									<input type="text" id="userName" name ="userName" placeholder="" class="form-control input_base_require">
								</div>
							</div>
							
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th fm_rep">비밀번호</div>
								<div class="ctn_tbl_td">
									<input type="password" id="userPw" name ="userPw" placeholder="" class="form-control input_base_require">
								</div>
								<div class="ctn_tbl_th fm_rep">비밀번호 확인</div>
								<div class="ctn_tbl_td">
									<input type="password" id="userPw1" name ="userPw1" placeholder="" class="form-control input_base_require">
								</div>
							</div>
							
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th fm_rep">기관</div>
								<div class="ctn_tbl_td">
									<select class="table_sel"  style="width: 164px; height:100%;"  id="cpyCode">
									    <c:forEach var="departVo" items="${departList1}">
									        <option value="${departVo.cpyCode}">${departVo.cpyName}</option>
									    </c:forEach>
									</select>
								</div>
								<div class="ctn_tbl_th fm_rep">본부/처/실</div>
								<div class="ctn_tbl_td">
									<select class="table_sel"  style="width: 164px; height:100%;" id="hqCode">
									    <c:forEach var="departVo" items="${departList2}">
									        <option value="${departVo.hqCode}">${departVo.hqName}</option>
									    </c:forEach>
									</select>
								</div>
							</div>
							
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th fm_rep">팀명</div>
								<div class="ctn_tbl_td">
		                            <select class="table_sel"  style="width: 164px; height:100%;" id="teamCode">
									    <c:forEach var="departVo" items="${departList3}">
									        <option value="${departVo.teamCode}">${departVo.teamName}</option>
									    </c:forEach>
									</select>
								</div>
								<div class="ctn_tbl_th ">직급</div>
								<div class="ctn_tbl_td">
									<input type="text" id="userRank" name ="userRank" placeholder="" class="form-control">
								</div>
							</div>
							
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">휴대 전화번호 ( - 생략)</div>
								<div class="ctn_tbl_td">
									<input type="text" id="userPhone" name ="userPhone" placeholder="" onkeydown='onlyNumber(event)' onkeyup='onlyNumber(event)' class="form-control">
								</div>
								<div class="ctn_tbl_th ">유선 전화번호( - 생략)</div>
								<div class="ctn_tbl_td">
									<input type="text" id="userTel" name ="userTel" placeholder="" onkeydown='onlyNumber(event)' onkeyup='onlyNumber(event)'  class="form-control">
								</div>
							</div>
							
							<div class="ctn_tbl_row">
								<div class="ctn_tbl_th ">이메일</div>
								<div class="ctn_tbl_td">
									<input type="text" id="userEmail" name ="userEmail" placeholder="" class="form-control">
								</div>
								<div class="ctn_tbl_th  fm_rep">사용자 권한</div>
								<div class="ctn_tbl_td">
									<select class="table_sel"  style="width: 164px; height:100%;" name="userAuth">
									    <c:forEach var="authVo" items="${authList}">
									        <option value="${authVo.authLevel}">${authVo.authDefine}</option>
									    </c:forEach>
									</select>
								</div>
							</div>
						
							<div class="ctn_tbl_row  fm_rep">
								<div class="ctn_tbl_th ">사용 여부</div>
								<div class="ctn_tbl_td">
									<select name ="usedYn">
										<option value='1'>사용</option>
										<option value='0'>미사용</option>
									</select>
								</div>
							</div>
						</div>
						<!-- btn_box Start -->
						<div class="btn_box">
							<div class="right">
								<input type="button" class="btn btn_primary" id="btnSave" alt="저장" value="저장" />
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