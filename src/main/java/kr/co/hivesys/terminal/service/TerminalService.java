package kr.co.hivesys.terminal.service;

import java.util.List;

import kr.co.hivesys.terminal.vo.TerminalVo;

public interface TerminalService {

	List<TerminalVo> selectTerminalList(TerminalVo cmsVo);
	
	TerminalVo selectTerminal(TerminalVo cmsVo);
	
	String creComId();

	void insertTerminal(TerminalVo cmsVo);
	
	void updateTerminal(TerminalVo cmsVo);
	
	public void deleteTerminal(List<String> listArr);

	TerminalVo chartQ(TerminalVo inputVo);
	
	List<TerminalVo>  mainChart1(TerminalVo inputVo);
	List<TerminalVo>  mainChart2(TerminalVo inputVo);
	List<TerminalVo>  barChart(TerminalVo inputVo);
	
	List<TerminalVo>  userRsrp(TerminalVo inputVo);
	List<TerminalVo>  userRsrq(TerminalVo inputVo);

	List<TerminalVo> chartQFirst(TerminalVo inputVo);

	List<TerminalVo> routerTeamCnt(TerminalVo terminalVo);

	TerminalVo deviceReload(TerminalVo inputVo);
}
