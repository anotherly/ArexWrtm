package kr.co.hivesys.comm;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ModelAndViewDefiningException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import kr.co.hivesys.auth.service.AuthService;
import kr.co.hivesys.auth.vo.AuthVo;

import kr.co.hivesys.user.vo.UserVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AuthInterceptor extends HandlerInterceptorAdapter {

	public static final Logger logger = LoggerFactory.getLogger(AuthInterceptor.class);

	@Resource(name = "authService")
	private AuthService authService;

	/**
	 * 컨트롤러 수행전에 세션 정보가 있는지 확인하는 처리..
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		logger.debug("☎☎☎☎☎☎☎☎☎☎☎☎☎☎☎☎.AUTH -  프리핸들 request : " + request);
		logger.debug("☎☎☎☎☎☎☎☎☎☎☎☎☎☎☎☎.AUTH -  프리핸들 request.getSession() : " + request.getSession());
		logger.debug(request.getRequestURL().toString());
		String furl = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		boolean rtn = false;//최종 t/f 리턴값
		boolean ynUrl = false; // 권한관리 체크에 포함된 url인지 아닌지
		
		// 모든 컨트롤러의 요청을 다 받음 로그인 빼고
		// 그러면 common/main 같은건 디비에 없으니
		// -> 디스패쳐 서블릿에서 설정함
		
		// 현재 모든 세션 불러와서 접속한 세션이랑 호출한 세션이랑 
		// 권한정보가 다를시 메시지와 함께 튕겨냄
		
		//	체크해서 로그인 사용자가 아닌데 들어온경우
		//	-> 이 부분은 해당 리퀘스트 세션을 가지고 체크해서
		//	해당 세션으로 매핑된 사용자가 없을경우 튕겨버림
		/*if(!SessionListener.getInstance().chkSessionNow(request, request.getSession())) {
			return false;
		}*/
		
		// 일단 동일한 사용자라는게 체크됬으면
		// 디비 전체 권한 주소에 없는 주소 제외하고 있는 주소에서만 체크
		// 그거 비교하는 if문 생성해야함
		/*List<AuthVo> allUrlList = authService.authUrlList();
		for (AuthVo urlVo : allUrlList) {
			if(urlVo.getUrl().equals(furl)) {
				ynUrl=true;
			}
		}	*/
		// 새로고침이나 유효하지 않은 요청 제외하고는 권한체크
		if (request.getSession().getAttribute("login") != null) {
			logger.debug("☎☎☎☎☎☎☎☎☎☎☎☎☎☎☎☎.   request.getSession().getAttribute(login) : " + request.getSession().getAttribute("login"));
			String nowUrl = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
			
			String[] urlList = nowUrl.split("/", 11);
			nowUrl="";
			for (int i = 1; i < urlList.length-1; i++) {
				nowUrl += "/"+urlList[i];
			}
			
			// 현재 세션에 대해 로그인한 사용자 정보를 가져옴
			UserVO reqLoginVo = (UserVO) request.getSession().getAttribute("login");
			AuthVo avo = new AuthVo();
			avo.setAuthLevel(reqLoginVo.getUserAuth());
			//기존 방식과 다르게 url을받아와서
			//db에 있는 컬럼값과 각자 매칭해야 할듯
			//따라서 mapper쪽에서 url로 분기타야함
			//avo.setUrl(nowUrl);
			List<AuthVo> alist = new ArrayList<>();
			alist = authService.selectAuthList(avo);
			if (alist.size() == 0) {// 현재 선택한 메뉴의 권한여부가 n일 경우
				logger.debug("♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨권한이 없음");
				rtn =false;
				return false;
			} else {
				rtn = true;
			}
		} else {
			logger.debug("♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨세션 값이 존재하지 않음");
			rtn =false;
			return false;
		}
		return rtn;
	}

	/**
	 * 세션에 메뉴권한(SessionVO)이 있는지 여부로 메뉴권한 여부를 체크한다. 계정정보(SessionVO)가 없다면, 로그인 페이지로
	 * 이동한다.
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		logger.debug("♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨♨.AUTH - postHandle 메소드 진입 : ");
	}
	/**
	 * 
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		logger.info("★★★★★★★★★★★★★★★★★.Interceptor: afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

} // end class