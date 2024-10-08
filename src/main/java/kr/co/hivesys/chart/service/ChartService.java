package kr.co.hivesys.chart.service;

import java.util.List;

import kr.co.hivesys.chart.vo.ChartVo;

public interface ChartService {

	List<ChartVo> selectCompanyList(ChartVo cmsVo);
	
	ChartVo selectCompany(ChartVo cmsVo);
	
	String creComId();

	void insertCompany(ChartVo cmsVo);
	
	void updateCompany(ChartVo cmsVo);
	
	public void deleteCompany(List<String> listArr);

}
