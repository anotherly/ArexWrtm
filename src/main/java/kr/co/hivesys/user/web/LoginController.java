package kr.co.hivesys.user.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hivesys.comm.SessionListener;
import kr.co.hivesys.user.web.LoginController;
import kr.co.hivesys.user.service.UserService;
import kr.co.hivesys.user.vo.UserVO;

@Controller
public class LoginController {

	public static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Resource(name = "userService")
	private UserService userService;

	public String url = "";
	public boolean isClose = false;

	// 주소에 맞게 매핑
	@RequestMapping(value = "/login/*.do")
	public String userUrlMapping(HttpSession httpSession, HttpServletRequest request, Model model) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.main 최초 컨트롤러 진입 httpSession : " + httpSession);
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		String springVersion = org.springframework.core.SpringVersion.getVersion();
//		System.out.println("스프링 프레임워크 버전 : " + springVersion);
//		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}

	// 새로고침 / 창 닫기 분기
	@RequestMapping(value = "/user/reloadOrKill.do")
	public String reloadOrKill(@RequestParam(required = false, value = "tagId") boolean tagId, HttpSession httpSession,
			HttpServletRequest request, Model model) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.새로고침 or 창닫기 tag식별 : " + tagId);
		this.isClose = tagId;
		// refresh
		if (isClose) {
			logger.debug("▶▶▶▶▶▶▶.refresh...");
			return "redirect:/";
		}

		Thread.sleep(5000);
		if (!this.isClose) {
			logger.debug("▶▶▶▶▶▶▶.kill session!!!");
			// httpSession.removeAttribute("access");

			httpSession.removeAttribute("login");
			SessionListener.getInstance().removeSession(httpSession);
		}
		return "redirect:/";
	}

	/**
	 * 로그인 -일반사용자 향후 타 프로젝트 재 사용성을 위해 관리자 로그인과는 주소를 달리함
	 * 
	 * @param model
	 * @return
	 * @throws Exception
	 */
	// inputVo : 사용자가 화면에서 입력한 uservo 정보
	// lvo : db에 insert할 실제 loginvo

	@RequestMapping(value = "/login/loginPost.do", method = { RequestMethod.POST })
	public @ResponseBody ModelAndView loginPost(@ModelAttribute UserVO inputVo, HttpSession httpSession,
			HttpServletRequest request, Model model) throws Exception {
		logger.debug("▶▶▶▶▶▶▶.loginPost 진입 세션 : " + httpSession);
		logger.debug("▶▶▶▶▶▶▶.받아온 loginVo 값 : " + inputVo.toString());
		String msg = "";
		UserVO userVo = new UserVO();
		ModelAndView mav = new ModelAndView("jsonView");

		try {
			userVo = userService.selectUser(inputVo);

			// 등록되지 않은 사용자 또는 사용자 입력 오류
			// BCrypt.checkpw(a,b) 메소드(함수)의
			// 두 파라미터 a,b를 각기 비교하여
			// 일치하면 true, 불일치하면 false를 반환
			// inputVo.getUserPw() : 화면에서 받아온(사용자가 입력한) 비밀번호
			// userVo.getUserPw() : db에 저장된 사용자의 비밀번호
			if (userVo == null || !(BCrypt.checkpw(inputVo.getUserPw(), userVo.getUserPw()))) {
				logger.debug("▶▶▶▶▶▶▶.가입되지 않은 사용자이거나 정보를 잘못 입력하셨습니다");
				msg = "가입되지 않은 사용자이거나 정보를 잘못 입력하셨습니다";
				userVo = null;
				return mav;
				// 유효한 계정 및 비밀번호 입력 시
			} else {
				// 기존 세션이 존재(중복로그인)
				if (SessionListener.getInstance().isUsing(inputVo.getUserId())) {
					String relgn = request.getParameter("relgn");
					// 중복로그인
					logger.debug("▶▶▶▶▶▶▶.기존 세션을 삭제하고 재생성");
					SessionListener.getInstance().removeSessionById(inputVo.getUserId());
					// userService.logoutUpdate(inputVo);
					HttpSession loginSession = request.getSession(true);

					// 세션시간 설정 24*60*60 24시간 (시간/분/초 단위 : 초)
					// loginSession.setMaxInactiveInterval(24*60*60);

					logger.debug("로그인vo 세션시간 : " + loginSession.getMaxInactiveInterval());
					// 세션에 값 주입
					httpSession.setAttribute("login", inputVo);
					// 세션 + 시간 해쉬맵에 로그인 세션과 현 시간을 저장
					SessionListener.getInstance().setSession(loginSession, userVo.getUserId());
					// ${}세션
					model.addAttribute("user", userVo);
				} else {// 최초 로그인
					HttpSession loginSession = request.getSession(true);

					// 세션시간 설정 24*60*60 24시간 (시간/분/초 단위 : 초)
					// loginSession.setMaxInactiveInterval(24*60*60);

					logger.debug("로그인vo 세션시간 : " + loginSession.getMaxInactiveInterval());
					// 세션에 값 주입
					httpSession.setAttribute("login", inputVo);
					// 세션 + 시간 해쉬맵에 로그인 세션과 현 시간을 저장
					SessionListener.getInstance().setSession(loginSession, userVo.getUserId());
					// ${}세션
					model.addAttribute("user", userVo);
				}
				url = "/chart/main.do";
			}
			mav.addObject("url", url);
		} catch (Exception e) {
			msg = "가입되지 않은 사용자이거나 정보를 잘못 입력하셨습니다";
			userVo = null;
			logger.debug("▶▶▶▶▶▶▶.캐치 에러 : " + e.getMessage());
			e.printStackTrace();
		} finally {
			mav.addObject("msg", msg);
		}
		return mav;
	}

	// 로그아웃 처리
	@RequestMapping(value = "/login/logout.do")
	public String logout(UserVO loginVo, HttpSession httpSession, Model model, HttpServletRequest request)
			throws Exception {
		logger.debug("▶▶▶▶▶▶▶.logout 메소드 진입");
		return "redirect:/";
	}

}
