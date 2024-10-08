<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<!-- css -->
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/css/import.css" media="all">
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
			inputLogin(queryString,"/login/loginPost.do"); 
		});//btnSub
		
	});
</script>
</head>
<body class="login-body">
    <!-- container Start ------------------>
    <div id="login_container" class="login_wrap">
        <div class="login_box">
            <div class="login_logo">공항철도 | Airport Railroad Express | 무선장치 관리시스템 | Wireless device management system</div>
            <form id="loginForm" class="login_form" name="loginForm" method="post" enctype="multipart/form-data">
                <div class="login_fm_id">
                	<input type="text" id="userId" name="userId" class="form-control"  maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/>
                </div><!-- 아이디 -->
                <div class="login_fm_pass"><!-- 비밀번호 -->
                	<input type="password" id="userPw" name="userPw" class="form-control" maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/>
                </div>
                <div class="login_fm_btn">
                    <button class="btn btn_primary" type="submit" role="button">로그인</button>
                </div>
            </form>
        </div>
    </div>
    <!-- container End ------------------>
</body>

</html>