package kr.co.hivesys.terminal.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.terminal.mapper.TerminalMapper;
import kr.co.hivesys.terminal.service.TerminalService;
import kr.co.hivesys.terminal.vo.TerminalVo;

@Service("terminalService")
public class TerminalServiceImpl implements TerminalService{
	@Resource(name="terminalMapper")
	private TerminalMapper TerminalMapper;
	
	@Override
	public List<TerminalVo> selectTerminalList(TerminalVo cmsVo) {
		return TerminalMapper.selectTerminalList(cmsVo);
	}
	@Override
	public TerminalVo selectTerminal(TerminalVo cmsVo) {
		return TerminalMapper.selectTerminal(cmsVo);
	}

	@Override
	public String creComId() {
		return TerminalMapper.creComId();
	}
	
	@Override
	public void insertTerminal(TerminalVo cmsVo) {
		TerminalMapper.insertTerminal(cmsVo);
		
	}
	
	@Override
	public void updateTerminal(TerminalVo cmsVo) {
		TerminalMapper.updateTerminal(cmsVo);
		
	}
	//사용자 삭제
	public void deleteTerminal(List<String> listArr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chkList",listArr);
		TerminalMapper.deleteTerminal(map);		
	}
	@Override
	public TerminalVo chartQ(TerminalVo inputVo) {
		return TerminalMapper.chartQ(inputVo);
	}
	@Override
	public List<TerminalVo>  mainChart1(TerminalVo inputVo) {
		return TerminalMapper.mainChart1(inputVo);
	}
	@Override
	public List<TerminalVo>  mainChart2(TerminalVo inputVo) {
		return TerminalMapper.mainChart2(inputVo);
	}
	@Override
	public List<TerminalVo>  barChart(TerminalVo inputVo) {
		return TerminalMapper.barChart(inputVo);
	}
	@Override
	public List<TerminalVo>  userRsrp(TerminalVo inputVo) {
		return TerminalMapper.userRsrp(inputVo);
	}
	@Override
	public List<TerminalVo>  userRsrq(TerminalVo inputVo) {
		return TerminalMapper.userRsrq(inputVo);
	}
	@Override
	public List<TerminalVo> chartQFirst(TerminalVo inputVo) {
		return TerminalMapper.chartQFirst(inputVo);
	}
	@Override
	public List<TerminalVo> routerTeamCnt(TerminalVo inputVo) {
		return TerminalMapper.routerTeamCnt(inputVo);
	}
	@Override
	public TerminalVo deviceReload(TerminalVo inputVo) {
		return TerminalMapper.deviceReload(inputVo);
	}

}
