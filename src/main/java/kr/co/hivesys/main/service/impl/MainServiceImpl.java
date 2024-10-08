package kr.co.hivesys.main.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.main.mapper.MainMapper;
import kr.co.hivesys.main.service.MainService;
import kr.co.hivesys.user.vo.UserVO;

@Service("mainService")
public class MainServiceImpl implements MainService{
	@Resource(name="mainMapper")
	private MainMapper mainMapper;
}
