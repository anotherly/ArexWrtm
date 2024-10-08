package kr.co.hivesys.statistic.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.media.jfxmedia.logging.Logger;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.hivesys.comm.KeyMapVo;
import kr.co.hivesys.statistic.mapper.StatisticMapper;
import kr.co.hivesys.statistic.service.StatisticService;
import kr.co.hivesys.statistic.vo.StkAreaVO;
import kr.co.hivesys.terminal.vo.TerminalVo;
import kr.co.hivesys.statistic.vo.ChkDateVO;
import kr.co.hivesys.statistic.vo.LogDataVO;
import kr.co.hivesys.statistic.vo.MainStVo;
import kr.co.hivesys.statistic.vo.MainYTVo;
import kr.co.hivesys.statistic.vo.ScatterVO;
import kr.co.hivesys.user.mapper.UserMapper;
import kr.co.hivesys.user.service.UserService;
import kr.co.hivesys.user.vo.UserVO;

/**
 * code 서비스 구현 클래스
 * @author 미래전략사업팀 정다빈
 * @since 2020.07.23
 * @version 1.0
 * @see
 *
 * << 개정이력(Modification Information) >>
 *
 *   수정일            수정자              수정내용
 *  -------    -------- ---------------------------
 *  2020.07.23  정다빈           최초 생성
 */

@Service("statisticService")
public class StatisticServiceImpl implements StatisticService{
	
	
	@Resource(name="statisticMapper")
	private StatisticMapper statisticMapper;
	
	@Override
	public List<TerminalVo>  selectDayLilst(TerminalVo inputVo) {
		return statisticMapper.selectDayLilst(inputVo);
	}
	
	@Override
	public List<TerminalVo>  mainChart1(TerminalVo inputVo) {
		return statisticMapper.mainChart1(inputVo);
	}
	@Override
	public List<TerminalVo>  mainChart2(TerminalVo inputVo) {
		return statisticMapper.mainChart2(inputVo);
	}
	@Override
	public List<TerminalVo>  barChart(TerminalVo inputVo) {
		return statisticMapper.barChart(inputVo);
	}
	@Override
	public List<TerminalVo>  userRsrp(TerminalVo inputVo) {
		return statisticMapper.userRsrp(inputVo);
	}
	@Override
	public List<TerminalVo>  userRsrq(TerminalVo inputVo) {
		return statisticMapper.userRsrq(inputVo);
	}
	@Override
	public List<TerminalVo> chartQFirst(TerminalVo inputVo) {
		return statisticMapper.chartQFirst(inputVo);
	}
	
}
