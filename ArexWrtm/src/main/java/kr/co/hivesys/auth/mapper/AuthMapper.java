package kr.co.hivesys.auth.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.hivesys.auth.vo.AuthVo;

@Mapper("authMapper")
public interface AuthMapper {

	List<AuthVo> selectAuthList(AuthVo thvo);

	void insertAuth(AuthVo thvo);

	AuthVo selectAuth(AuthVo thvo);

	void updateAuth(AuthVo thvo);
	
	void deleteAuth(HashMap<String, Object> map);
}
