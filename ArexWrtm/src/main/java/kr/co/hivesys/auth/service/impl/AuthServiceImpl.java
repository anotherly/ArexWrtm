package kr.co.hivesys.auth.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.auth.mapper.AuthMapper;
import kr.co.hivesys.auth.service.AuthService;
import kr.co.hivesys.auth.vo.AuthVo;

@Service("authService")
public class AuthServiceImpl implements AuthService{
	@Resource(name="authMapper")
	private AuthMapper authMapper;

	@Override
	public List<AuthVo> selectAuthList(AuthVo thvo) {
		return authMapper.selectAuthList(thvo);
	}

	@Override
	public void insertAuth(AuthVo thvo) {
		authMapper.insertAuth(thvo);
	}

	@Override
	public AuthVo selectAuth(AuthVo thvo) {
		return authMapper.selectAuth(thvo);
	}

	@Override
	public void updateAuth(AuthVo thvo) {
		authMapper.updateAuth(thvo);
	}

	@Override
	public void deleteAuth(List<String> paramArr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chkList",paramArr);
		authMapper.deleteAuth(map);		
	}
}
