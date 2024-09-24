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
            <div class="">ADT/GDT 단말 정보 등록</div>
            <form id="loginForm" class="login_form" name="loginForm" method="post" enctype="multipart/form-data">
                <div class="login_fm_id">
                	<input type="text" id="USER_ID" name="USER_ID" class="form-control"  maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/>
                </div><!-- 아이디 -->
                <div class="login_fm_pass"><!-- 비밀번호 -->
                	<input type="password" id="USER_PW" name="USER_PW" class="form-control" maxlength="10" onkeyup="spaceChk(this);" onkeydown="spaceChk(this);"  required/>
                </div>
                <!-- <div class="login_fm_util">
                    <div class="fm_checkbox_box">
                        <label class="fm_checkbox">아이디저장<input type="checkbox"><span class="checkmark"></span></label>아이디저장
                    </div>
                    <div class="fm_lang_select">
                        <a class="btn_lang">한국어(Korean)<i class="ico_toggle"></i></a>선택 언어
                        <div class="fm_lang_list">리스트 오픈 fm_lang_list list_open
                            <a class="lang_list active">한국어(Korean)</a>리스트 선택언어
                            <a class="lang_list">영어(English)</a>
                        </div>
                    </div>
                </div> -->
                <div class="login_fm_btn">
                    <button class="btn btn_primary" type="submit" role="button">로그인</button>
                </div>
                <div class="login_fm_link">
                	<a href="/login/findid.do" class="login_link">아이디 찾기</a>
                	<span>|</span><a href="/login/findpw.do" class="login_link">비밀번호 찾기</a>
                	<span>|</span><a href="/login/insertUser.do" class="login_link">회원가입</a>
                </div>
            </form>
        </div>
    </div>
    <!-- container End ------------------>
</body>

</html>