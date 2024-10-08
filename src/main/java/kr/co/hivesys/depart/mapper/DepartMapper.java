package kr.co.hivesys.depart.mapper;

import java.util.HashMap;
import java.util.List;

import egovframework.rte.psl.dataaccess.mapper.Mapper;
import kr.co.hivesys.depart.vo.DepartVo;

@Mapper("departMapper")
public interface DepartMapper {

	List<DepartVo> selectDepartList(DepartVo cmsVo);
	
	List<DepartVo> selectDepartList1(DepartVo cmsVo);
	List<DepartVo> selectDepartList2(DepartVo cmsVo);
	List<DepartVo> selectDepartList3(DepartVo cmsVo);
	
	DepartVo selectDepart(DepartVo cmsVo);
	
	String creComId();
	
	void insertDepart(DepartVo cmsVo);

	void updateDepart(DepartVo cmsVo);

	void deleteDepart(HashMap<String, Object> map);
	
}
