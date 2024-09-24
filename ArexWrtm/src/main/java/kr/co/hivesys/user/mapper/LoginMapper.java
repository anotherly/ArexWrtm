package kr.co.hivesys.user.mapper;
import java.util.List;
import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.hivesys.user.vo.UserVO;

/**
 * 사용자 매퍼 클래스
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

@Mapper("loginMapper")
public interface LoginMapper{
	
	//로그인 정보 주입(어플리케이션 연동)
	public void insertLogin(UserVO lvo);

	//로그아웃 시 접근 정보 1->0(어플리케이션 연동)
	public void logoutUpdate(UserVO lvo);
	
	//에러 로그인 삭제
	public void errloginDelete(UserVO lvo);
	
	public List<UserVO> loginHistory(UserVO uvo);
}
