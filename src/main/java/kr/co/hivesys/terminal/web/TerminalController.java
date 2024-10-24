package kr.co.hivesys.terminal.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.hivesys.auth.vo.AuthVo;
import kr.co.hivesys.comm.file.FileUploadSave;
import kr.co.hivesys.comm.file.vo.FileVo;
import kr.co.hivesys.depart.service.DepartService;
import kr.co.hivesys.depart.vo.DepartVo;
import kr.co.hivesys.terminal.vo.TerminalVo;
import kr.co.hivesys.user.vo.UserVO;
import kr.co.hivesys.terminal.service.TerminalService;




@Controller
public class TerminalController {

	public static final Logger logger = LoggerFactory.getLogger(TerminalController.class);
	public String url="";
	
	@Resource(name="terminalService")
	private TerminalService terminalService;
	
	@Resource(name="departService")
	private DepartService departService;
	
	//주소에 맞게 매핑
	@RequestMapping(value= {"/chart/*.do","/terminal/*.do","/stat/*.do"})
	public ModelAndView urlMapping(HttpSession httpSession, HttpServletRequest request,Model model
			) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.단말기 최초 컨트롤러");
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		logger.debug("▶▶▶▶▶▶▶.보내려는 url : "+url);
		ModelAndView mav = new ModelAndView(url);
		return mav;
	}
	
	//목록 조회
	@RequestMapping(value="/terminal/list.ajax")
	public @ResponseBody ModelAndView reqList( 
			HttpServletRequest request
			//@RequestParam(required=false, value="idArr[]")List<String> listArr
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
			,@RequestParam(required=false, value="startNum")String startNum
			,@RequestParam(required=false, value="endNum")String endNum
			,@RequestParam(required=false, value="teamName")String teamCode
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		List<TerminalVo> sList= null;
		try {
			//빌어먹을 mysql이 offset에서 cast가 안되므로(문자 숫자 변환한걸 오프셋으로 못씀)
			//vo는 int로 페이지 만들고 페이징 변수를 만들거나 
			//아니면 별도의 로직처-리 필요
			if(startNum !=null ) {
				inputVo.setStartNum(Integer.parseInt(startNum));
				inputVo.setFlagPage("T");
			}
			if(endNum !=null ) {
				inputVo.setEndNum(Integer.parseInt(endNum));
			}
			
			UserVO reqLoginVo = (UserVO) request.getSession().getAttribute("login");
			
			if(reqLoginVo!=null) {
				if(!(reqLoginVo.getUserAuth().equals("0"))) {
					inputVo.setDepartCode(reqLoginVo.getDepartCode());
				}
			}
			
			
			if(teamCode !=null ) {
				inputVo.setTeamName(teamCode);
			}
			
			sList = terminalService.selectTerminalList(inputVo);
			mav.addObject("data", sList);

			//현재시각 조회
			Calendar cal = Calendar.getInstance();
			String pattern = "yyyy-MM-dd HH:mm:ss"; 
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			mav.addObject("nowDt", formatter.format(cal.getTime()));
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	//셀렉트박스 변경시
	@RequestMapping(value="/terminal/routerTeamCnt.ajax")
	public @ResponseBody ModelAndView reqInsert (
			@ModelAttribute("TerminalVo") TerminalVo inputVo
			,HttpServletRequest request) 
					throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		ModelAndView mav = new ModelAndView("jsonView");
		List<TerminalVo> terminalList = new ArrayList<>();
		try {
			terminalList = terminalService.routerTeamCnt(inputVo);
			mav.addObject("data", terminalList);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
		}
		return mav;
	}
	
	//등록 저장
	@RequestMapping(value="/terminal/insert.ajax")
	public ModelAndView insertReq(HttpSession httpSession, 
			HttpServletRequest request,Model model
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
			) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			terminalService.insertTerminal(inputVo);
		} catch (Exception e) {
			logger.debug("에러메시지 : "+e.toString());
			e.printStackTrace();
			mav.addObject("msg","저장에 실패하였습니다");
		}
		return mav;
	}
	
	//(상세)
	@RequestMapping(value="/terminal/detail.do")
	public @ResponseBody ModelAndView reqDetail( 
	HttpServletRequest request, HttpServletResponse response
	,@ModelAttribute("TerminalVo") TerminalVo inputVo
	) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		TerminalVo data= null;
		try {
			data = terminalService.selectTerminal(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 수정 페이지 진입
	@RequestMapping(value="/terminal/update.do")
	public @ResponseBody ModelAndView userUpdate( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
	) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 조회 목록!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		TerminalVo data= null;
		try {
			
			List<DepartVo> departList = new ArrayList<>();
			departList = departService.selectDepartList(new DepartVo());
			mav.addObject("departList", departList);
			data = terminalService.selectTerminal(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mav;
	}
	
	//수정 저장
	@RequestMapping(value="/terminal/update.ajax")
	public @ResponseBody ModelAndView reqUpdate(
			 HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		TerminalVo thvo= null;
		try {
			terminalService.updateTerminal(inputVo);
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//사용자 삭제
	@RequestMapping(value="/terminal/delete.ajax")
	public @ResponseBody ModelAndView userDelete( @RequestParam(value="idArr[]")List<String> dataArr,HttpServletRequest request) throws Exception{
		logger.debug("▶▶▶▶▶▶▶.회원정보 삭제!!!!!!!!!!!!!!!!");
		
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			terminalService.deleteTerminal(dataArr);
		} catch (Exception e) {
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
	
	//단말기 정보 가져오기
	@RequestMapping(value="/terminal/deviceReload.ajax")
	public @ResponseBody ModelAndView deviceReload(
		HttpServletRequest request, HttpServletResponse response
		,@ModelAttribute("TerminalVo") TerminalVo inputVo
		) throws Exception{
			url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
			
			ModelAndView mav = new ModelAndView("jsonView");
			TerminalVo data= null;
			try {
				data = terminalService.deviceReload(inputVo);
				mav.addObject("rData", data);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.debug(""+e);
				mav.addObject("msg","에러가 발생했습니다.");
			}
			return mav;
		}
	
	//차트 실시간 조회 최초
	
	// 단말기 선택 시, 좌측 메모리 up/down 현황 차트 
	@RequestMapping(value="/realtimeChartFirst.ajax")
	public @ResponseBody ModelAndView realtimeChartFirst
	( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo		
	) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		List<TerminalVo>  tlist= new ArrayList<>();
		try {
			tlist = terminalService.chartQFirst(inputVo);
			mav.addObject("data",tlist);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
	//차트 실시간 조회
	@RequestMapping(value="/realtimeChart.ajax")
	public @ResponseBody ModelAndView realtimeChart
	( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo		
			) throws Exception{
		ModelAndView mav = new ModelAndView("jsonView");
		try {
			TerminalVo tvo = terminalService.chartQ(inputVo);
			mav.addObject("data",tvo);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
	
	// 누적 운영 시간 차트 조회
	@RequestMapping(value="/detailChart.ajax")
	public @ResponseBody ModelAndView detailChart (HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("TerminalVo") TerminalVo inputVo) throws Exception {
		
		ModelAndView mav = new ModelAndView("jsonView");
		List<TerminalVo>  tlist= new ArrayList<>();
		try {
			tlist = terminalService.chartD(inputVo);
			mav.addObject("data",tlist);
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg","에러가 발생하였습니다");
		}
		return mav;
	}
	
	
	
	// 단말기 선택 시 단말기 정보 가져오기
	@RequestMapping(value="/chart/subDetail.do")
	public @ResponseBody ModelAndView subDetail( 
	HttpServletRequest request, HttpServletResponse response
	,@ModelAttribute("TerminalVo") TerminalVo inputVo
	) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		TerminalVo data= null;
		try {
			data = terminalService.selectTerminal(inputVo);
			mav.addObject("data", data);
			mav.setViewName(url);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//메인차트 - 관리자
	@RequestMapping(value="/chart/mainAdminChart.ajax")
	public @ResponseBody ModelAndView mainAdminChart( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		List<TerminalVo>  data1= new ArrayList<>();
		List<TerminalVo>  data2= new ArrayList<>();
		List<TerminalVo>  data3= new ArrayList<>();
		try {
			data1= terminalService.mainChart1(inputVo);
			data2= terminalService.mainChart2(inputVo);
			data3= terminalService.barChart(inputVo);
			mav.addObject("data1", data1);
			mav.addObject("data2", data2);
			mav.addObject("data3", data3);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
	//메인차트 - 일반
	@RequestMapping(value="/chart/mainUserChart.ajax")
	public @ResponseBody ModelAndView mainUserChart( 
			HttpServletRequest request, HttpServletResponse response
			,@ModelAttribute("TerminalVo") TerminalVo inputVo
			) throws Exception{
		url = request.getRequestURI().substring(request.getContextPath().length()).split(".do")[0];
		
		ModelAndView mav = new ModelAndView("jsonView");
		List<TerminalVo>  data1= new ArrayList<>();
		List<TerminalVo>  data2= new ArrayList<>();
		List<TerminalVo>  data3= new ArrayList<>();
		
		UserVO reqLoginVo = (UserVO) request.getSession().getAttribute("login");
		if(reqLoginVo!=null) {
			if(!(reqLoginVo.getUserAuth().equals("0"))) {
				inputVo.setDepartCode(reqLoginVo.getDepartCode());
			}
		}
		try {
			data1= terminalService.userRsrp(inputVo);
			data2= terminalService.userRsrq(inputVo);
			data3= terminalService.barChart(inputVo);
			mav.addObject("data1", data1);
			mav.addObject("data2", data2);
			mav.addObject("data3", data3);
			
		} catch (Exception e) {
			e.printStackTrace();
			logger.debug(""+e);
			mav.addObject("msg","에러가 발생했습니다.");
		}
		return mav;
	}
	
}
