package kr.co.hivesys.user.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.co.hivesys.auth.service.AuthService;
import kr.co.hivesys.auth.vo.AuthVo;
import kr.co.hivesys.depart.service.DepartService;
import kr.co.hivesys.depart.vo.DepartVo;
import kr.co.hivesys.user.service.UserService;
import kr.co.hivesys.user.vo.UserVO;

/**
 * 사용자 컨트롤러 클래스
 * @author 솔루션사업팀 정다빈
 * @since 2021.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2021.07.23  정다빈           최초 생성
 */

@Controller
public class UserController {

	public static final Logger logger = LoggerFactory.getLogger(UserController.class);
	public String url="";
	
	@Resource(name="userService")
	private UserService userService;
	
	@Resource(name="departService")
	private DepartService departService;
	
	@Resource(name="authService")
	private AuthService authService;
	
	//주소에 맞게 매핑
	@RequestMapping(value= "/user/*.do")
	public String urlMapping(HttpSession httpSession, HttpServletRequest request,Model model
			) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.단말기 최초 컨트롤러");
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
	
	//목록 화면 진입시 필요데이터
	@RequestMapping(value="/user/list.do")
	public @ResponseBody ModelAndView listDo(HttpServletRequest request) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView(url);
		try {
			List<DepartVo> departList1 = new ArrayList<>();
			List<DepartVo> departList2 = new ArrayList<>();
			List<DepartVo> departList3 = new ArrayList<>();
			departList1 = departService.selectDepartList1(new DepartVo());
			departList2 = departService.selectDepartList2(new DepartVo());
			departList3 = departService.selectDepartList3(new DepartVo());
			mav.addObject("departList1", departList1);
			mav.addObject("departList2", departList2);
			mav.addObject("departList3", departList3);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	
	//목록 조회
	@RequestMapping(value="/user/list.ajax")
	public @ResponseBody ModelAndView reqList( 
			HttpServletRequest request
			//@RequestParam(required=false, value="idArr[]")List<String> listArr
			,@ModelAttribute("UserVO") UserVO inputVo) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		List<UserVO> sList= null;
		try {
			
			UserVO reqLoginVo = (UserVO) request.getSession().getAttribute("login");
			inputVo.setUserAuth(reqLoginVo.getUserAuth());
			inputVo.setDepartCode(reqLoginVo.getDepartCode());
			
			sList = userService.selectUserList(inputVo);
			mav.addObject("data", sList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//등록 화면
	@RequestMapping(value="/user/insert.do")
	public @ResponseBody ModelAndView reqInsert (HttpServletRequest request) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView(url);
		List<UserVO> UserList = new ArrayList<>();
		try {
			
			List<DepartVo> departList1 = new ArrayList<>();
			List<DepartVo> departList2 = new ArrayList<>();
			List<DepartVo> departList3 = new ArrayList<>();
			List<AuthVo> authList = new ArrayList<>();
			departList1 = departService.selectDepartList1(new DepartVo());
			departList2 = departService.selectDepartList2(new DepartVo());
			departList3 = departService.selectDepartList3(new DepartVo());
			authList = authService.selectAuthList(new AuthVo());
			mav.addObject("departList1", departList1);
			mav.addObject("departList2", departList2);
			mav.addObject("departList3", departList3);
			mav.addObject("authList", authList);
			
			UserList = userService.selectUserList(new UserVO());
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//등록 저장
	@RequestMapping(value="/user/insert.ajax")
	public ModelAndView insertReq(HttpSession httpSession, 
			HttpServletRequest request,Model model
			,@ModelAttribute("UserVO") UserVO inputVo
			) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			//패스워드 암호화 처리
			String hashedPw = BCrypt.hashpw(inputVo.getUserPw(), BCrypt.gensalt());
			//vo에 암호화된 password 삽입
			inputVo.setUserPw(hashedPw);
			//쿼리 조회
			userService.insertUser(inputVo);			
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			e.printStackTrace();
			mav.addObject("msg","저장에 실패하였습니다");
		}
		return mav;
	}
	
	//(상셰)
	@RequestMapping(value="/user/detail.do")
	public @ResponseBody ModelAndView reqDetail( 
	HttpServletRequest request, HttpServletResponse response
	,@ModelAttribute("UserVO") UserVO inputVo
	) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		UserVO data= null;
		try {
			
			List<DepartVo> departList1 = new ArrayList<>();
			List<DepartVo> departList2 = new ArrayList<>();
			List<DepartVo> departList3 = new ArrayList<>();
			List<AuthVo> authList = new ArrayList<>();
			departList1 = departService.selectDepartList1(new DepartVo());
			departList2 = departService.selectDepartList2(new DepartVo());
			departList3 = departService.selectDepartList3(new DepartVo());
			authList = authService.selectAuthList(new AuthVo());
			mav.addObject("departList1", departList1);
			mav.addObject("departList2", departList2);
			mav.addObject("departList3", departList3);
			mav.addObject("authList", authList);
			
			data = userService.selectUser(inputVo);
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
	@RequestMapping(value="/user/update.do")
	public @ResponseBody ModelAndView userUpdate( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("UserVO") UserVO inputVo
	) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 조회 목록!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		UserVO data= null;
		try {
			data = userService.selectUser(inputVo);
			
			List<DepartVo> departList1 = new ArrayList<>();
			List<DepartVo> departList2 = new ArrayList<>();
			List<DepartVo> departList3 = new ArrayList<>();
			List<AuthVo> authList = new ArrayList<>();
			departList1 = departService.selectDepartList1(new DepartVo());
			departList2 = departService.selectDepartList2(new DepartVo());
			departList3 = departService.selectDepartList3(new DepartVo());
			authList = authService.selectAuthList(new AuthVo());
			mav.addObject("departList1", departList1);
			mav.addObject("departList2", departList2);
			mav.addObject("departList3", departList3);
			mav.addObject("authList", authList);
			
			mav.addObject("data", data);
			mav.setViewName(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}
	
	//수정 저장
	@RequestMapping(value="/user/update.ajax")
	public @ResponseBody ModelAndView reqUpdate(
			 HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("UserVO") UserVO inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		UserVO thvo= null;
		try {
			userService.updateUser(inputVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 삭제
	@RequestMapping(value="/user/delete.ajax")
	public @ResponseBody ModelAndView userDelete
	( @RequestParam(value="idArr[]")List<String> dataArr,HttpServletRequest request) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 삭제!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			userService.deleteUser(dataArr);
		} catch (Exception e) {
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
}
