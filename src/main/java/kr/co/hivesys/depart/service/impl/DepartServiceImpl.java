package kr.co.hivesys.depart.service.impl;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.co.hivesys.depart.mapper.DepartMapper;
import kr.co.hivesys.depart.service.DepartService;
import kr.co.hivesys.depart.vo.DepartVo;

@Service("departService")
public class DepartServiceImpl implements DepartService{
	@Resource(name="departMapper")
	private DepartMapper departMapper;
	
	@Override
	public List<DepartVo> selectDepartList(DepartVo cmsVo) {
		return departMapper.selectDepartList(cmsVo);
	}
	
	@Override
	public List<DepartVo> selectDepartList1(DepartVo cmsVo) {
		return departMapper.selectDepartList1(cmsVo);
	}
	
	@Override
	public List<DepartVo> selectDepartList2(DepartVo cmsVo) {
		return departMapper.selectDepartList2(cmsVo);
	}
	
	@Override
	public List<DepartVo> selectDepartList3(DepartVo cmsVo) {
		return departMapper.selectDepartList3(cmsVo);
	}
	
	@Override
	public DepartVo selectDepart(DepartVo cmsVo) {
		return departMapper.selectDepart(cmsVo);
	}

	@Override
	public String creComId() {
		return departMapper.creComId();
	}
	
	@Override
	public void insertDepart(DepartVo cmsVo) {
		departMapper.insertDepart(cmsVo);
		
	}
	
	@Override
	public void updateDepart(DepartVo cmsVo) {
		departMapper.updateDepart(cmsVo);
		
	}
	//사용자 삭제
	public void deleteDepart(List<String> listArr) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("chkList",listArr);
		departMapper.deleteDepart(map);		
	}

}
