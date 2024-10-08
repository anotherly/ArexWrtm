package kr.co.hivesys.auth.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hivesys.auth.service.AuthService;
import kr.co.hivesys.auth.vo.AuthVo;
import kr.co.hivesys.user.service.UserService;


@Controller
public class AuthController {

	public static final Logger logger = LoggerFactory.getLogger(AuthController.class);
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="authService")
	private AuthService authService;
	
	public String url="";
	
	//주소에 맞게 매핑
	@RequestMapping(value= "/auth/*.do")
	public String urlMapping(HttpSession httpSession, HttpServletRequest request,Model model
			) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.단말기 최초 컨트롤러");
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
	
	//목록 조회
	@RequestMapping(value="/auth/list.ajax")
	public @ResponseBody ModelAndView reqList( 
			HttpServletRequest request
			//@RequestParam(required=false, value="idArr[]")List<String> listArr
			,@ModelAttribute("AuthVo") AuthVo inputVo) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		List<AuthVo> sList= null;
		try {
			sList = authService.selectAuthList(inputVo);
			mav.addObject("data", sList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//등록 화면
	@RequestMapping(value="/auth/insert.do")
	public @ResponseBody ModelAndView reqInsert (HttpServletRequest request) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView(url);
		List<AuthVo> AuthList = new ArrayList<>();
		try {
			AuthList = authService.selectAuthList(new AuthVo());
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//등록 저장
	@RequestMapping(value="/auth/insert.ajax")
	public ModelAndView insertReq(HttpSession httpSession, 
			HttpServletRequest request,Model model
			,@ModelAttribute("AuthVo") AuthVo inputVo
			) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			authService.insertAuth(inputVo);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			e.printStackTrace();
			mav.addObject("msg","저장에 실패하였습니다");
		}
		return mav;
	}
	
	//(상셰)
	@RequestMapping(value="/auth/detail.do")
	public @ResponseBody ModelAndView reqDetail( 
	HttpServletRequest request, HttpServletResponse response
	,@ModelAttribute("AuthVo") AuthVo inputVo
	) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		AuthVo data= null;
		try {
			data = authService.selectAuth(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 수정 페이지 진입
	@RequestMapping(value="/auth/update.do")
	public @ResponseBody ModelAndView userUpdate( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("AuthVo") AuthVo inputVo
	) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 조회 목록!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		AuthVo data= null;
		try {
			data = authService.selectAuth(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}
	
	//수정 저장
	@RequestMapping(value="/auth/update.ajax")
	public @ResponseBody ModelAndView reqUpdate(
			 HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("AuthVo") AuthVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		AuthVo thvo= null;
		try {
			authService.updateAuth(inputVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 삭제
	@RequestMapping(value="/auth/delete.ajax")
	public @ResponseBody ModelAndView userDelete
	( @RequestParam(value="idArr[]")List<String> dataArr,HttpServletRequest request) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 삭제!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			authService.deleteAuth(dataArr);
		} catch (Exception e) {
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
}
