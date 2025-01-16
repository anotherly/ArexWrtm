package kr.co.hivesys.user.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.user.mapper.UserMapper;
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

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Resource(name="userMapper")
	private UserMapper userMapper;
	
	//전체 회원정보 조회
	public List<UserVO> selectUserList(UserVO userVO) throws Exception{
		return userMapper.selectUserList(userVO);
	}

	//사용자 등록
	public void insertUser(UserVO userVO) throws Exception {
		userMapper.insertUser(userVO);
	}
	
	//특정 사용자 조회
	public UserVO selectUser(UserVO userVO) throws Exception {
		return userMapper.selectUser(userVO);
	}

	//사용자 정보 수정
	public void updateUser(UserVO uvo) {
		userMapper.updateUser(uvo);
	}

	//사용자 삭제
	public void deleteUser(List<String> userArr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chkList",userArr);
		userMapper.deleteUser(map);		
	}
	
	// 상세에서 사용자 삭제
	public void deleteUserD(String userId) {
		userMapper.deleteUserD(userId);
	}
	
	// 본부/처/실 선택 시 select box 값 변경
	public List<UserVO> selectTeam(String nowHq) throws Exception {
		return userMapper.selectTeam(nowHq);
	}
}
