package kr.co.hivesys.chart.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.chart.mapper.ChartMapper;
import kr.co.hivesys.chart.service.ChartService;
import kr.co.hivesys.chart.vo.ChartVo;

@Service("companyService")
public class ChartServiceImpl implements ChartService{
	@Resource(name="companyMapper")
	private ChartMapper chartMapper;
	
	@Override
	public List<ChartVo> selectCompanyList(ChartVo cmsVo) {
		return chartMapper.selectCompanyList(cmsVo);
	}
	@Override
	public ChartVo selectCompany(ChartVo cmsVo) {
		return chartMapper.selectCompany(cmsVo);
	}

	@Override
	public String creComId() {
		return chartMapper.creComId();
	}
	
	@Override
	public void insertCompany(ChartVo cmsVo) {
		chartMapper.insertCompany(cmsVo);
		
	}
	
	@Override
	public void updateCompany(ChartVo cmsVo) {
		chartMapper.updateCompany(cmsVo);
		
	}
	//사용자 삭제
	public void deleteCompany(List<String> listArr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chkList",listArr);
		chartMapper.deleteCompany(map);		
	}

}
