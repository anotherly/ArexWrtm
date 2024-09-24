package kr.co.hivesys.chart.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.hivesys.chart.vo.ChartVo;

@Mapper("companyMapper")
public interface ChartMapper {

	List<ChartVo> selectCompanyList(ChartVo cmsVo);
	
	ChartVo selectCompany(ChartVo cmsVo);
	
	String creComId();
	
	void insertCompany(ChartVo cmsVo);

	void updateCompany(ChartVo cmsVo);

	void deleteCompany(HashMap<String, Object> map);
	
}
