package kr.co.hivesys.terminal.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.hivesys.terminal.vo.TerminalVo;

@Mapper("terminalMapper")
public interface TerminalMapper {

	List<TerminalVo> selectTerminalList(TerminalVo cmsVo);
	
	TerminalVo selectTerminal(TerminalVo cmsVo);
	
	String creComId();
	
	void insertTerminal(TerminalVo cmsVo);

	void updateTerminal(TerminalVo cmsVo);

	void deleteTerminal(HashMap<String, Object> map);

	TerminalVo chartQ(TerminalVo inputVo);
	
	List<TerminalVo>  mainChart1(TerminalVo inputVo);
	List<TerminalVo>  mainChart2(TerminalVo inputVo);
	List<TerminalVo>  barChart(TerminalVo inputVo);
	
	List<TerminalVo>  userRsrp(TerminalVo inputVo);
	List<TerminalVo>  userRsrq(TerminalVo inputVo);

	List<TerminalVo> chartQFirst(TerminalVo inputVo);
	
	List<TerminalVo> routerTeamCnt(TerminalVo inputVo);
	
	TerminalVo deviceReload(TerminalVo inputVo);
	
}
