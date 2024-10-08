<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<script>

// 커밋 확인용 주석
	$(document).ready(function(){
		console.log("상세");
		var tagId='${data.authLevel}';
		$("#btnSave").on('click', function(){
			$("#contents").load('/auth/update.do',{"authLevel":tagId});
		});
		$("#btnCancel").on('click', function(){
			location.href='/auth/list.do';
		});
	});l

</script>
	<!-- work Start -->
	<div id="work" class="work-wrap">
		<!-- contents_box Start -->
		<div id="contents_box" class="contents_box">
			<!-- 컨텐츠 테이블 헤더 Start -->
			<div class="ctn_tbl_header">
				<div class="ttl_ctn">권한 상세정보</div>
			</div>
			<!-- 컨텐츠 테이블 헤더 End -->
			<!-- 컨텐츠 테이블 영역 Start -->
				<div class="ctn_tbl_area">
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">권한명</div>
						<div class="ctn_tbl_td">
							${data.authDefine}
						</div>
						<div class="ctn_tbl_th ">권한 값</div>
						<div class="ctn_tbl_td">
							${data.authLevel}
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">관리 SW 제어 여부</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.funcCtrl eq 1}">허용</c:if>
							<c:if test="${data.funcCtrl eq 0}">불가</c:if>
						</div>
						<div class="ctn_tbl_th ">운영/사용률 화면 접근</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.funcMotr eq 1}">허용</c:if>
							<c:if test="${data.funcMotr eq 0}">불가</c:if>
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">기관 관리 화면 접근</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.funcDepart eq 1}">허용</c:if>
							<c:if test="${data.funcDepart eq 0}">불가</c:if>
						</div>
						<div class="ctn_tbl_th ">권한 관리 화면 접근</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.funcAuth eq 1}">허용</c:if>
							<c:if test="${data.funcAuth eq 0}">불가</c:if>
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">사용자 관리 화면 접근</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.funcUser eq 1}">허용</c:if>
							<c:if test="${data.funcUser eq 0}">불가</c:if>
						</div>
						<div class="ctn_tbl_th ">단말기 관리 화면 접근</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.funcLter eq 1}">허용</c:if>
							<c:if test="${data.funcLter eq 0}">불가</c:if>
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">사용여부</div>
						<div class="ctn_tbl_td">
							<c:if test="${data.usedYn eq 1}">사용</c:if>
							<c:if test="${data.usedYn eq 0}">미사용</c:if>
						</div>
					</div>
				</div>
				
				<!-- btn_box Start -->
				<div class="btn_box">
					<div class="right">
						<input type="button" class="btn btn_primary" id="btnSave" alt="저장" value="수정" />
						<input type="button" class="btn" id="btnCancel" alt="취소" value="취소" />
					</div>
				</div>
				<!-- btn_box End -->
		</div>
		<!-- contents_box End -->
	</div>

</html>