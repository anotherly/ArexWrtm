<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
	$(document).ready(function() {
		console.log("메뉴 화면");
	});
</script>
<li class="menu-item" id="ROOT_TEST_SW">
	<a href="/chart/main.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n01"></i>
		<div>운용/사용률 현황</div>
	</a>
</li>
<!-- 기관관리 / 권한 관리 hidden 처리 - 24/10/15 -->
<li class="menu-item" id="ROOT_TEST_SW" style="display:none;">
	<a href="/depart/list.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n09"></i>
		<div>기관 관리</div>
	</a>
</li>	
<li class="menu-item" id="ROOT_TEST_SW"  style="display:none;">
	<a href="/auth/list.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n08"></i>
		<div>권한 관리</div>
	</a>
</li>	
<li class="menu-item" id="ROOT_TEST_SW">
	<a href="/user/list.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n07"></i>
		<div>사용자 관리</div>
	</a>
</li>
<li class="menu-item" id="ROOT_TEST_SW">
	<a href="/terminal/list.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n02"></i>
		<div>단말기 관리</div>
	</a>
</li>
<li class="menu-item" id="ROOT_TEST_SW">
	<a href="/firmware/list.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n03"></i>
		<div>펌웨어 관리</div>
	</a>
</li>
<li class="menu-item" id="ROOT_TEST_SW">
	<a href="/stat/main.do" class="menu-link" style="color: rgb(255, 255, 255);">
		<i class="menu-icon n06"></i>
		<div>통계</div>
	</a>
</li>


