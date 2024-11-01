<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<script>

	$(document).ready(function(){
		//console.log("상세");
		var tagId='${data.departCode}';
		$("#btnSave").on('click', function(){
			$("#contents").load('/depart/update.do',{"departCode":tagId});
		});
		$("#btnCancel").on('click', function(){
			location.href='/depart/list.do';
		});
	});

</script>
	<!-- work Start -->
	<div id="work" class="work-wrap">
		<!-- contents_box Start -->
		<div id="contents_box" class="contents_box">
			<!-- 컨텐츠 테이블 헤더 Start -->
			<div class="ctn_tbl_header">
				<div class="ttl_ctn">소속기관 상세정보</div>
			</div>
			<!-- 컨텐츠 테이블 헤더 End -->
			<!-- 컨텐츠 테이블 영역 Start -->
				<div class="ctn_tbl_area">
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th"></div>
						<div class="ctn_tbl_th" style="flex: 1;">
							기관정보 (한◦영/16자)
						</div>
						<div class="ctn_tbl_th" style="flex: 1;">
							코드정보 (영문/3자)
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">기관명</div>
						<div class="ctn_tbl_td">
							${data.cpyName}
						</div>
						<div class="ctn_tbl_td">
							${data.cpyCode}
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">본부/처/실</div>
						<div class="ctn_tbl_td">
							${data.hqName}
						</div>
						<div class="ctn_tbl_td">
							${data.hqCode}
						</div>
					</div>
					<div class="ctn_tbl_row">
						<div class="ctn_tbl_th ">기관명</div>
						<div class="ctn_tbl_td">
							${data.teamName}
						</div>
						<div class="ctn_tbl_td">
							${data.teamCode}
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