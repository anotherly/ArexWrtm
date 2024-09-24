<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- JS -->
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.migrate.js"></script>
	<script src="<%=request.getContextPath()%>/js/jquery.js"></script>

	<!-- 로그인 시큐어코딩 관련 -->
	<script  type="text/javascript" charset="utf-8"  src="<%=request.getContextPath()%>/js/loginSC/login.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/common.js"></script>
	<script src="<%=request.getContextPath()%>/js/common/validation.js"></script>
<script>
	$(document).ready(function() {
		console.log("로그인 컨텐츠");
		
		$("#loginForm").submit( function(event){
			console.log("로그인 버튼 클릭");
			event.preventDefault();
			// serialize는 form의 <input> 요소들의 name이 배열형태로 그 값이 인코딩되어 URL query string으로 하는 메서드
			let queryString = $(this).serialize();
			//서버로 로그인 정보를 전송하고 권한에 따라 화면 분기
			inputLogin(queryString,"/login/loginPostAdmin.do");
		});//btnSub
		
		$("#findid").on("click",function(){
			location.href="/login/findid"
		});
		
		$("#findpw").on("click",function(){
			location.href="/login/findpw"
		});
	});
</script>
</head>
<body>
	<div class="login-title-wrapper">
		<div class="login-title"></div>
	</div>
	<form id="loginForm" name="loginForm" method="post" enctype="multipart/form-data">
		<div id="login-form-div">
			<table id ="loginTable">
				<tr>
					<td>ID</td>
					<td><input type="text" id="USER_ID" name="USER_ID" class="form-control"  maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/></td>
				</tr>
				<tr>
					<td>PASSWORD</td>
					<td><input type="password" id="USER_PW" name="USER_PW" class="form-control" maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/></td>
				</tr>
			</table>
			<div class="btnDiv">
				<button id="btnSub" class="buttonDf">로그인</button>
			</div>
		</div>
	</form>
	<button class="buttonDf" id="findid">ID찾기</button>
	<button class="buttonDf" id="findpw" href="/findpw">비밀번호찾기</button>
</body>

</html>