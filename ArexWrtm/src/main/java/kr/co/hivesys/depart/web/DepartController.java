package kr.co.hivesys.depart.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hivesys.depart.service.DepartService;
import kr.co.hivesys.depart.vo.DepartVo;




@Controller
public class DepartController {

	public static final Logger logger = LoggerFactory.getLogger(DepartController.class);
	public String url="";
	
	@Resource(name="departService")
	private DepartService departService;
	
	//주소에 맞게 매핑
	@RequestMapping(value= "/depart/*.do")
	public String urlMapping(HttpSession httpSession, HttpServletRequest request,Model model
			) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.단말기 최초 컨트롤러");
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		return url;
	}
	
	//목록 조회
	@RequestMapping(value="/depart/list.ajax")
	public @ResponseBody ModelAndView reqList( 
			HttpServletRequest request
			//@RequestParam(required=false, value="idArr[]")List<String> listArr
			,@ModelAttribute("DepartVo") DepartVo inputVo) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		List<DepartVo> sList= null;
		try {
			sList = departService.selectDepartList(inputVo);
			mav.addObject("data", sList);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//등록 화면
	@RequestMapping(value="/depart/insert.do")
	public @ResponseBody ModelAndView reqInsert (HttpServletRequest request) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView(url);
		try {
			List<DepartVo> departList1 = new ArrayList<>();
			List<DepartVo> departList2 = new ArrayList<>();
			List<DepartVo> departList3 = new ArrayList<>();
			departList1 = departService.selectDepartList1(new DepartVo());
			departList2 = departService.selectDepartList2(new DepartVo());
			departList3 = departService.selectDepartList3(new DepartVo());
			mav.addObject("departList1", departList1);
			mav.addObject("departList2", departList2);
			mav.addObject("departList3", departList3);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//등록 저장
	@RequestMapping(value="/depart/insert.ajax")
	public ModelAndView insertReq(HttpSession httpSession, 
			HttpServletRequest request,Model model
			,@ModelAttribute("DepartVo") DepartVo inputVo
			) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			departService.insertDepart(inputVo);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			e.printStackTrace();
			mav.addObject("msg","저장에 실패하였습니다");
		}
		return mav;
	}
	
	//(상셰)
	@RequestMapping(value="/depart/detail.do")
	public @ResponseBody ModelAndView reqDetail(
	HttpServletRequest request, HttpServletResponse response
	,@ModelAttribute("DepartVo") DepartVo inputVo
	) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		DepartVo data= null;
		try {
			data = departService.selectDepart(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
		} catch (DuplicateKeyException de) {
			de.printStackTrace();
			logger.debug(""+de);
			mav.addObject("msg","기존에 이미 등록된 기관 또는 본부 또는 팀입니다");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 수정 페이지 진입
	@RequestMapping(value="/depart/update.do")
	public @ResponseBody ModelAndView userUpdate( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("DepartVo") DepartVo inputVo
	) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 조회 목록!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		DepartVo data= null;
		try {
			List<DepartVo> departList1 = new ArrayList<>();
			List<DepartVo> departList2 = new ArrayList<>();
			List<DepartVo> departList3 = new ArrayList<>();
			departList1 = departService.selectDepartList1(new DepartVo());
			departList2 = departService.selectDepartList2(new DepartVo());
			departList3 = departService.selectDepartList3(new DepartVo());
			mav.addObject("departList1", departList1);
			mav.addObject("departList2", departList2);
			mav.addObject("departList3", departList3);
			
			data = departService.selectDepart(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return mav;
	}
	
	//수정 저장
	@RequestMapping(value="/depart/update.ajax")
	public @ResponseBody ModelAndView reqUpdate(
			 HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("DepartVo") DepartVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		DepartVo thvo= null;
		try {
			departService.updateDepart(inputVo);
		} catch (DuplicateKeyException de) {
			de.printStackTrace();
			logger.debug(""+de);
			mav.addObject("msg","기존에 이미 등록된 기관 또는 본부 또는 팀입니다");
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 삭제
	@RequestMapping(value="/depart/delete.ajax")
	public @ResponseBody ModelAndView userDelete
	( @RequestParam(value="idArr[]")List<String> dataArr,HttpServletRequest request) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 삭제!!!!!!!!!!!!!!!!");
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			departService.deleteDepart(dataArr);
		} catch (Exception e) {
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
}
