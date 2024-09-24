package kr.co.hivesys.main.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.hivesys.comm.SessionListener;
import kr.co.hivesys.main.service.MainService;
import kr.co.hivesys.user.service.UserService;
import kr.co.hivesys.user.vo.UserVO;


@Controller
public class MainController {

	public static final Logger logger = LoggerFactory.getLogger(MainController.class);
	
	public String url="";
	@Resource(name="userService")
	private UserService userService;
	
	//권한에 따른 메인화면 분기처리
	@RequestMapping(value= "/main/main.do")
	public String urlMapping(HttpSession httpSession, HttpServletRequest request,Model model
			) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.main 최초 컨트롤러 진입 httpSession : "+httpSession);
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		UserVO userVo = new UserVO();
		/*
		//메인화면에서 로그인 사용자 세션체크
		if(SessionListener.getInstance().getUserID(httpSession)==null) {
			url="/login/login";
		}else {
			String userId = SessionListener.getInstance().getUserID(httpSession);
			userVo.setUSER_ID(userId);
			//이 부분 나중에 권한관리로 대체해야함
			userVo=userService.selectUser(userVo);
			//권한에 따른 최초 로그인 화면 분기
			if(userVo.getAUTH_CODE()==999) {//관리자 권한
				url="/admin/client/register";
			}else {//일반사용자
				url ="/client/svcInfo/intro";
			}
		}*/
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
	
	//jsp include 시 포함하는 페이지 주소를 보내므로
	//* 을 사용했을때 ex: /cmn/top.do 의 주소를 못 가져오고
	//메인 페이지의 주소를 가져오는 것으로 추정
	// 따라서 직접 주소 명시
	@RequestMapping(value = "/cmn/top.do")
	public String top(HttpSession httpSession, HttpServletRequest request,Model model) {
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
	    return "/cmn/top";
	}
	@RequestMapping(value = "/cmn/header.do")
	public String header(HttpSession httpSession, HttpServletRequest request,Model model) {
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return "/cmn/header";
	}
	@RequestMapping(value = "/cmn/menu.do")
	public String menu(HttpSession httpSession, HttpServletRequest request,Model model) {
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
	    return "/cmn/menu";
	}
	//footer부분
	@RequestMapping(value = "/cmn/footer.do")
	public String footer(HttpSession httpSession, HttpServletRequest request,Model model) {
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return "/cmn/footer";
	}
}
