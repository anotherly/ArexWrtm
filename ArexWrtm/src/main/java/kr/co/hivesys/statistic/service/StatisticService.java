package kr.co.hivesys.statistic.service;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;
import kr.co.hivesys.statistic.vo.StkAreaVO;
import kr.co.hivesys.terminal.vo.TerminalVo;
import kr.co.hivesys.statistic.vo.ChkDateVO;
import kr.co.hivesys.statistic.vo.LogDataVO;
import kr.co.hivesys.statistic.vo.MainStVo;
import kr.co.hivesys.statistic.vo.MainYTVo;
import kr.co.hivesys.statistic.vo.ScatterVO;
import kr.co.hivesys.user.vo.UserVO;

/**
 * code 서비스 클래스
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

public interface StatisticService {
	
	List<TerminalVo>  selectDayLilst(TerminalVo inputVo);
	
	List<TerminalVo>  mainChart1(TerminalVo inputVo);
	List<TerminalVo>  mainChart2(TerminalVo inputVo);
	List<TerminalVo>  barChart(TerminalVo inputVo);
	
	List<TerminalVo>  userRsrp(TerminalVo inputVo);
	List<TerminalVo>  userRsrq(TerminalVo inputVo);

	List<TerminalVo> chartQFirst(TerminalVo inputVo);
	
}
