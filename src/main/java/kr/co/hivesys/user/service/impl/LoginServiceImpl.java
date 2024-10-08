package kr.co.hivesys.user.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.user.mapper.LoginMapper;
import kr.co.hivesys.user.mapper.UserMapper;
import kr.co.hivesys.user.service.LoginService;
import kr.co.hivesys.user.service.UserService;
import kr.co.hivesys.user.vo.UserVO;

/**
 * 사용자 서비스 구현 클래스
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

@Service("loginService")
public class LoginServiceImpl implements LoginService{
	
	@Resource(name="loginMapper")
	private LoginMapper loginMapper;
	
	//로그인 정보 주입(어플리케이션 연동)
	public void insertLogin(UserVO lvo) {
		loginMapper.insertLogin(lvo);
	}

	//로그아웃 시 접근 정보 1->0(어플리케이션 연동)
	public void logoutUpdate(UserVO lvo) {
		loginMapper.logoutUpdate(lvo);
	}

	@Override
	public void errloginDelete(UserVO lvo) {
		loginMapper.errloginDelete(lvo);
		
	}

	@Override
	public List<UserVO> loginHistory(UserVO uvo) {
		return loginMapper.loginHistory(uvo);
	}
}
