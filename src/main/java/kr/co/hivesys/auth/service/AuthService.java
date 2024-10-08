package kr.co.hivesys.auth.service;

import java.util.HashMap;
import java.util.List;

import kr.co.hivesys.auth.vo.AuthVo;

public interface AuthService {
	List<AuthVo> selectAuthList(AuthVo thvo);

	void insertAuth(AuthVo thvo);

	AuthVo selectAuth(AuthVo thvo);

	void updateAuth(AuthVo thvo);
	
	void deleteAuth(List<String> paramArr);
}
