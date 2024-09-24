package kr.co.hivesys.depart.service;

import java.util.List;

import kr.co.hivesys.depart.vo.DepartVo;


public interface DepartService {

	List<DepartVo> selectDepartList(DepartVo cmsVo);
	
	List<DepartVo> selectDepartList1(DepartVo cmsVo);
	List<DepartVo> selectDepartList2(DepartVo cmsVo);
	List<DepartVo> selectDepartList3(DepartVo cmsVo);
	
	
	DepartVo selectDepart(DepartVo cmsVo);
	
	String creComId();

	void insertDepart(DepartVo cmsVo);
	
	void updateDepart(DepartVo cmsVo);
	
	public void deleteDepart(List<String> listArr);

}
